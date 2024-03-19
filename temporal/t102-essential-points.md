From the summary page for the course (verbatim)

## Essential Points
The following is a summary of the most essential things that you have learned during the course.

## Overview
Temporal is an open source durable execution system, which can significantly increase your productivity as a developer by providing tools and APIs that make it possible to develop applications that scale easily and run reliably even under adverse conditions.

## Temporal Applications
Temporal applications include both code you develop and features provided by a Temporal SDK. As a developer, you are responsible for developing Workflow Definitions and Activity Definitions—code that represents your business logic—as well as configuring the Worker and Client components provided by the SDK.

Should a Worker crash during a Workflow Execution, another Worker will automatically reconstruct the previous state and continue the execution from that point forward, as if the crash had never occurred at all. It achieves this by using a technique called History Replay, which in turn requires that execution of the Workflow Definition is deterministic. This affects how you should approach Temporal application development and the following are best practices to follow:

### Best practices for Temporal Application Development
* Use a single class, rather than individual parameters, as input parameters and return values in Workflow and Activity definitions. This helps to support backwards-compatible evolution of the data supplied and returned in their execution.
* Since Temporal imposes limits on the size of Events in the history associated with a Workflow Execution, avoid using large amounts of data for the inputs and outputs of Workflows and Activities.
* Avoid common sources of non-determinism, such as random numbers and system time, in your Workflow code. For example, instead of using Java's Thread.sleep method, you can use Workflow.sleep to introduce a delay during Workflow Execution.
* Although Workflow Definitions must execute deterministically, there is no such constraint on Activity Definitions. If an Activity fails due to a bug in the code, you can deploy a fix while the Workflow is still running. Activity failure is normal and expected, since they are retried automatically, but failing a Workflow is much less common.
* Use Temporal's logging API, which won't result in duplicate messages during History Replay, but consider integrating a third-party logging implementation. This will give you better control over which messages are logged, how they are displayed, and where they are stored.
Testing Temporal Applications
Automated testing is an investment in the quality of your code. Temporal's Java SDK provides support for testing Temporal Workflow and Activites via the io.temporal.testing package.

You'll run these the same as you would other tests in Java. You may use third-party libraries, such as JUnit and Mockito, to extend the capabilities of these tests by adding support for assertions and mock objects. Using Mocks in a Workflow test allows you to verify its business logic in isolation from the Activity implementation.

The time-skipping feature of the Workflow testing environment fires Timers immediately, allowing you to quickly run tests for long-running Workflows. Another novel feature of Temporal is the ability to test the compatibility of a modification by replaying previous executions, which you can download using the Web UI or command-line tool, with the updated Workflow Definition.

## Workflow Execution
Workflow Execution begins with a request from a Client, which specifies the Workflow Definition and input data to use. A single Workflow Definition can be executed any number of times, potentially using different input each time. These executions can run concurrently, although each must have a Workflow ID that is unique among all other Workflow Executions in the same Namespace, and the uniqueness requirement of the Workflow ID may be further constrained by the Workflow ID Reuse Policy associated with an execution.

Although not required in the Java SDK, we recommend that you specify a Workflow ID when you start a Workflow Execution. You should choose a value that is meaningful to your business logic and which will be unique among all Workflow Executions (not just those for the same Workflow Type) running in the same Namespace at any given point in time.

Once started, a Workflow Execution immediately enters the open state, which simply means that it's running. As the Worker executes code in the Workflow Definition, it may encounter calls to certain Temporal APIs, such as an Activity Method or Workflow.sleep, that require some interaction with the Temporal Cluster. When this occurs, the Worker issues a Command to the Temporal Cluster specifying the requested action and the details needed to achieve it. For example, a call to Workflow.sleep causes the Worker to issue a StartTimer Command, which specifies the duration of the Timer. In response, the Temporal Cluster starts the Timer and logs the TimerStarted event. When the Timer fires, the cluster logs a TimerFired event and adds a new Workflow Task to its queue. The Worker subsequently polls the Task Queue, accepts this Task, and resumes execution.

Although any available Worker can accept tasks, a performance optimization known as "sticky execution" favors using the same Worker for multiple Workflow Tasks during a given execution. Since the Temporal Cluster does not assign tasks to Workers, the cluster does this by adding subsequent Workflow Tasks to a private queue shared with the Worker. This makes History Replay faster and more efficient, since Workers are often able to restore execution state using Event History data they have cached.

Eventually, the Workflow Execution will transition to the closed state, meaning that it has come to an end. If the method associated with the Workflow Definition returns a result, it closes with a status of Completed,
indicating that execution was successful. If it instead raises an ApplicationFailure exception, it closes with a status of Failed. The other four reasons it can close are because it was canceled, terminated, timed out, or continued-as-new. Cancellation and termination are similar in that they can both end the Workflow Execution, but cancellation is a more graceful way to achieve this. Cancellation allows for cleanup, while termination does not. An analogy to UNIX would be that cancellation is like the kill command, while termination is like kill -9.

## Event History
The Event History documents a Workflow Execution, from the perspective of the Temporal Cluster. It is an ordered append-only list of Events, each of which has a timestamp and Event Type, plus an Event ID that represents its position within the history. Events may have additional attributes, which vary based on the Event Type. For example, the WorkflowExecutionCompleted Event contains the result of that execution, while a WorkflowExecutionFailed Event contains the error that caused the execution to fail.

Temporal maintains limits on both the size and count of Items in the Event History. The cluster begins to log warnings when Workflow Executions exceed 10K events. Workflow Executions with Event Histories that exceed 50K events or 50 MB may be terminated. We recommend not exceeding a few thousand Events in a single Workflow Execution, since this should provide sufficient time to address the issue. Using Continue-As-New is one approach for this, since it continues running the code in a new Workflow Execution, and therefore, a new Event History.

The retention period, which is set on a per-Namespace basis, defines how long the Event History and other data associated with Workflow Executions are retained after they close. It's important to note that the countdown to the retention period only begins when the Workflow Execution ends, so the retention period has no effect on the ones that are still running, regardless of how long they take to finish.

## Building and Running Temporal Applications
Temporal does not mandate specific tools or processes for building, deploying, or running applications. For example, you may choose to build them using a mvn clean package command, perhaps as part of a script or Makefile. You can deploy them by copying the resulting executable to the production system, perhaps using a CI/CD tool to manage the build and deployment process. You can run the application directly on physical hardware ("bare metal"), in virtual machines, or in containers (either with or without Kubernetes).

In production, you will typically deploy and run multiple copies of your application concurrently, since each instance can increase the overall scalability and availability.

## Temporal Cluster, Temporal Cloud, and Temporal Server
The term Temporal Cluster refers to the Temporal Server software, plus the database it requires for persistence and any other optional components, such as Elasticsearch or Grafana. Temporal Cloud is a managed service that provides an alternative to self-hosting a Temporal cluster. Although Temporal Cloud is obviously quite different from an operational perspective, since it relieves you from having to set up and manage a self-hosted cluster, they are equivalent from the perspective of an application developer.

The Temporal Server consists of a Frontend Service and three backend services:

1. The History Service maintains the Event History and progress of Workflow Executions
2. The Matching Service manages the Task Queue, matching Workers with available Workflow and Activity Tasks.
3. The Worker Service runs internal system Workflows, which are not visible to users. Despite the name, it's unrelated to the Worker that executes your application code.

The Frontend Service is responsible for accepting requests from clients and routing them to the appropriate backend service if necessary. These requests, as well as other internode communication, uses gRPC and can be secured with TLS.

## Deploying to Production
By default, Temporal Clients connect to a Frontend Service via TCP port 7233 on localhost. You can customize this in the WorkflowServiceStubsOptions and WorkflowClientOptions used to create a Client. As you move your application between environments—for example, from development to production—you may need to change them for the Worker, which contains a Client. This typically involves specifying a different address used to access the Frontend Service, and depending on your requirements, may involve specifying a Namespace, custom Client ID, and/or TLS options. This is usually just a few lines of code and the only modification required when deploying to production.

Once deployed, you must take care when deploying changes. It's safe to change Activity Definitions, but changing a Workflow Definition can potentially lead to non-deterministic errors if there are open Workflow Executions for that Workflow Type and the change affects the Commands generated when the code is run. You can test compatibility by replaying the history for one or more past Workflow Executions using the updated version of the code.