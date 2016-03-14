Docker now support --build-args which means instead of specifying
proxy information inside the Dockerfile using ENV, you can pass it
on the command line, e.g.

<pre>
docker docker build --build-arg HTTP_PROXY=http://<proxy-host>:<proxy-port> --build-arg HTTPS_PROXY=http://<proxy-host>:<proxy-port> -t something/something .
</pre>
