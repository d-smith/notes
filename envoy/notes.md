# Envoy Notes

Install on mac - https://www.envoyproxy.io/docs/envoy/latest/start/install#install-envoy-on-mac-osx

```
brew update
brew install envoy
```

Or via docker

```
docker pull envoyproxy/envoy-dev:f28a199a1b3a5b750f7597122949e0bde386c36c
docker run --rm envoyproxy/envoy-dev:f28a199a1b3a5b750f7597122949e0bde386c36c --version
```


Run with no config

```
docker run --rm -it \
      -p 9901:9901 \
      -p 10000:10000 \
      envoyproxy/envoy-dev:f28a199a1b3a5b750f7597122949e0bde386c36c
```



