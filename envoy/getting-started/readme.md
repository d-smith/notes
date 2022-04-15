DEAD END - runs on the website with an old version of envoy but forget the latest - see https://github.com/envoyproxy/envoy/issues/9662

https://www.envoyproxy.io/try/getting-started

https://github.com/envoyproxy/envoy/blob/6a578630a8f6189f86bc1e6b4b4d7ebffabadadd/configs/google_com_proxy.v2.yaml

start  envoy

docker run --name=proxy -d \
  -p 80:10000 \
  -v $(pwd)/envoy/envoy.yaml:/etc/envoy/envoy.yaml \
  envoyproxy/envoy:v1.20-latest


  WHat a shitshow!


Ok - try this:

  https://www.tetrate.io/blog/get-started-with-envoy-in-5-minutes/

  