docker pull gridgain/gridgain-com

 docker run -it --net=host -v /vagrant:/ignite  -e "CONFIG_URI=file:///ignite/example-cache.xml" -e "OPTION_LIBS=ignite-rest-http" -p 8080:8080 -e "IGNITE_QUIET=false"  gridgain/gridgain-com


