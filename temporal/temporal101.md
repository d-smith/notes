# Temporal 101

Notes from [this course](https://temporal.talentlms.com/catalog/info/id:140)

For local use: clone [this repo](git clone https://github.com/temporalio/edu-101-java-code
)

Temporal training forum - [here](https://community.temporal.io/c/training/54)

Temporal is a platform that guarantees durable execution of application code.

Workflow - abstraction for building temporal applications.

Conceptually, a workflow defines a sequence of steps. With Temporal, those steps are defined by writing code, known as a Workflow Definition, and are carried out by running that code, which results in a Workflow Execution.

Temporal server requires a database -  Apache Cassandra, PostgreSQL, or MySQL

Execution environment options

* Self host
* Temporal Cloud

Temporal Java SDK

```xml
       <dependency>
            <groupId>io.temporal</groupId>
            <artifactId>temporal-sdk</artifactId>
            <version>1.19.1</version>
        </dependency>
```

There are two steps for turning a Java interface and implementation into a Workflow Definition:

1. Import the io.temporal.workflow.WorkflowInterface and io.temporal.workflow.WorkflowMethod annotation types provided by the SDK
2. Annotate the interface with @WorkflowInterface
3. Annotate the method signature with @WorkflowMethod

Input values and return types

* Must be serializable
* Can use custom data converters to encrypt data going into temporal and decrypt data coming out of temporal.
* Look to minimize the data that is passed in and out of temporal as data will be included in the event history. So instead of passing in a large object, pass in an id and have the workflow method retrieve the object from a database.


Workers

Workers are the processes that host the code that carries out the steps of a workflow. They are responsible for executing the code that defines the steps of a workflow.


Worker initialization

```java
package io.temporal.learn;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class GreetingWorker {

    public static void main(String[] args) {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        // Specify the name of the Task Queue that this Worker should poll
        Worker worker = factory.newWorker("greeting-tasks");

        // Specify which Workflow implementations this Worker will support
        worker.registerWorkflowImplementationTypes(GreetingImpl.class);

        // Begin running the Worker
        factory.start();
    }
}

```

Start workflow from the command line

```bash
temporal workflow start \
    --type HelloWorkflowWorkflow \
    --task-queue greeting-tasks \
    --workflow-id my-first-workflow \
    --input '"Mason"'

```

Get the workflow result via the command line:

```bash
temporal workflow show --workflow-id my-first-workflow
```

Start a worker using code:

```java
package helloworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class Starter {
    public static void main(String[] args) throws Exception {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(service);        

        WorkflowOptions options = WorkflowOptions.newBuilder()
                    .setWorkflowId("my-first-workflow")
                    .setTaskQueue("greeting-tasks")
                    .build();
       
        HelloWorkflowWorkflow workflow = client.newWorkflowStub(HelloWorkflowWorkflow.class, options);

        String greeting = workflow.greetSomeone(args[0]);

        String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();

        System.out.println(workflowId + " " + greeting);

    }
}

```

In the above note the blocking call - `String greeting = workflow.greetSomeone(args[0]);` - there are non-blocking options as well.


More details event history:

```bash
temporal workflow show --workflow-id my-first-workflow --fields long
```

Web console - local environment is locaohost:8080

Namespaces - use to isolate different workflows from each other. 

* Some configuration is provided on a namespace level

Changes to workflows

* Backwards compatibility is an important consideration in Temporal. You might execute a given Workflow Definition hundreds, thousands, or millions of times. If the execution fails, then Temporal will reconstruct the Workflow's state before the failure, and then continue on with the execution. 
* In general, you should avoid changing the number or types of input parameters and return values for your Workflow. Temporal recommends that your Workflow Definition takes a single input parameter, a custom object, rather than multiple input parameters. Changing which fields are part of the object doesn't change the type of the object itself, so this provides a backwards-compatible way to evolve your code.
* Also, your Workflow must be deterministic. Temporal has a specific definition for this, but understanding it requires more detailed knowledge of Workflow Execution, so we can generalize for now. You can view determinism as a requirement that each execution of a given Workflow must produce the same output, given the same input. (Note this is for workflows, not activities.)
* Since Workflow Executions might run for months or years, it's possible that you'll need to make major changes to a Workflow Definition while there are already executions running based on the current definition of that Workflow. If these changes do not affect the deterministic nature of the Workflow, you can simply deploy them. However, you can use the SDK's "Versioning" feature to identify when a non-deterministic change is introduced, which allows older executions to use the original code and new executions to use the modified version.

Activities

You learned earlier that Workflow code must be deterministic, and must produce the same output each time, given the same input. This also implies that it can't interact with the outside world; for example, accessing files or network resources, because those might not be available at a given point in time. However, your business logic may require you to do such things. How do you reconcile this?

In Temporal, you can use Activities to encapsulate business logic that is prone to failure. Unlike the Workflow Definition, there is no requirement for an Activity Definition to be deterministic.

In general, any operation that introduces the possibility of failure should be done as part of an Activity, rather than as part of the Workflow directly. While Activities are executed as part of Workflow Execution, they have an important characteristic: they're retried if they fail.

Activity definition:

```java
package farewellworkflow;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@ActivityInterface
public interface GreetingActivities {

    public String greetInSpanish(String name);
}
```

```java
public class GreetingActivitiesImpl implements GreetingActivities {
    
    @Override
    public String greetInSpanish(String name) {
        StringBuilder builder = new StringBuilder();

        String baseUrl = "http://localhost:9999/get-spanish-greeting?name=";
        URL url = new URL(baseUrl + URLEncoder.encode(name, "UTF-8"));

        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                builder.append(line).append("\n");
            }
        }

        return builder.toString();
    }
}
```
Note the workers need to register activities as well:

```java
package farewellworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class GreetingWorker {
    public static void main(String[] args) {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker("greeting-tasks");

        worker.registerWorkflowImplementationTypes(GreetingWorkflowImpl.class);

        worker.registerActivitiesImplementations(new GreetingActivitiesImpl());

        factory.start();
    }
}

```

Executing activities - specifying options

```java
ActivityOptions options = ActivityOptions.newBuilder()
        .setStartToCloseTimeout(Duration.ofSeconds(5))
        .setRetryOptions(retryOptions)
        .build();

private final GreetingActivities activities = 
    Workflow.newActivityStub(GreetingActivities.class, options);
```

While specifying a Start-to-Close Timeout that is too short for the execution of your Activity is a problem, you should also avoid one that is too long. The Start-to-Close Timeout is one way Temporal detects a Worker crash, so an excessively long value wastes time by delaying the detection and recovery, ultimately reducing throughput. The Start-to-Close Timeout should be set to slightly longer than the slowest successful execution you expect for the Activity.


Synchronous execution:

```java
    String spanishGreeting = activities.greetInSpanish(name);
    String spanishFarewell = activities.farewellInSpanish(name);

```

Asynch Execution

```java
    Promise<String> spanishGreeting = Async.function(activities::greetInSpanish, name);
    Promise<String> spanishFarewell = Async.function(activities::farewellInSpanish, name);
```

Retrieving the result

```java
String greeting;

try {
    greeting = hello.get();
} catch(RuntimeExeption e) {
    // handle the failure as dictated by your business logic
}
```

Handling exceptions in activities

* Activity method signatures should not include checked exceptions. If your Activity implementation calls code that throws a checked exception, we recommend using the Activity.wrap method to re-throw it. This converts it to a Temporal-specific unchecked exception and the original exception can be retrieved (if needed) by calling its getCause() method.

Example:

```java
try {
    data = readData(dataFilePath);
} catch (IOException e) {
    throw Activity.wrap(e);
}

```

Temporal's default behavior is to automatically retry an Activity, with a short delay between each attempt, until it either succeeds or is canceled. That means that intermittent failures require no action on your part. When a subsequent request succeeds, your code will resume as if the failure never occurred. However, that behavior may not always be desirable, so Temporal allows you to customize it through a custom Retry Policy.







