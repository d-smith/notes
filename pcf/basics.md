
Every application is scoped to a space

<pre>
cf spaces
cf space <space name> #for details
</pre>

Organizations segregate tenants

<pre>
cf orgs
cf org <org name>
</pre>

Quotas define resource limits for orgs and spaces

Orgs contain spaces, spaces contain apps and service instances.

Use `cf target` to confirm current org and space.

CF push

* Uploads artifacts to CF
* Stages an executable artifact created from the uploaded artifact
* App starts on the web host, receives traffic if bound to a port

Buildpacks create a runnable artifact called a droplet.

Apps are started on specialized VMs called cells.

* If it's a web process, it binds to a TCP port
* Instances are distributed across multiple cells
* Router distributes traffic across instances

Build packs

* A build pack is a CF component that resolves your app's runtime dependencies

Build packs execute a set of scripts:

* bin/detect - can the build pack handle this?
* bin/compile - if it can handle it, make the droplet
* bin/release - build the metadata (env variables, start command, etc)

Resilience and Scale

* Run many instances of the same app: `cf scale <app name> -i <no. instances>`

Toubleshooting

* cf logs
* cf events
* cf ssh

Services

* cf marketplace

