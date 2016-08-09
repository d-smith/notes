## Cloud Pub/Sub

A global service for real-time and reliable messaging and 
streaming data.

## Set Up

Go to the cloud console, create or select a project, enable the 
pub/sub API.

https://console.cloud.google.com/start

Created project first-pub-sub

Enable the pub/sub API

* Go to API manager for the project, then Library. Select the cloud 
pub/sub API under Google Cloud APIs, click enable at the top of the 
next page.

[Install the Cloud SDK](https://cloud.google.com/sdk/docs/)

Run install.sh, then gcloud init.

Pub/Sub - create a topic, add a subscription (pull)

Install alpha:

<pre>
gcloud components install alpha
</pre>

gcloud alpha pubsub subscriptions pull xtsub

Add --auto-ack to remove the messages once they are pulled

Gradle dependency:

<pre>
repositories {
    mavenCentral()
}

dependencies {
    compile 'com.google.apis:google-api-services-pubsub:v1-rev11-1.22.0'
}
</pre>

Programmatic Access - set up includes:

* Making sure the HTTP transport puts the credentials in scope
* Generating a service accound and downloading credentials
* Pointing to the credentials via the GOOGLE_APPLICATION_CREDENTIALS environment variable
* Adding the producer to the Pub/Sub publisher role and granting publish access to the topic

