# Enclaves Notes

[Workshop - my first enclaves](https://nitro-enclaves.workshop.aws/en/my-first-enclave/nitro-enclaves-cli.html)

* Grab template here - curl https://raw.githubusercontent.com/aws-samples/aws-nitro-enclaves-workshop/main/resources/templates/cloud9.yaml -o cloud9.yaml
* CLoud 9 set up - https://nitro-enclaves.workshop.aws/en/getting-started/prerequisites.html
* Cloning the workshop repo - git clone --depth 1 https://github.com/aws-samples/aws-nitro-enclaves-workshop.git
* Enclave resources - cat /etc/nitro_enclaves/allocator.yaml
* Enclaves CLI repo - https://github.com/aws/aws-nitro-enclaves-cli
* Default vsock-proxy allow list -  /etc/nitro_enclaves/vsock-proxy.yaml

To run the secure channel server sample...

nitro-cli run-enclave --cpu-count 2 --memory 2068 --eif-path secure-channel-example.eif --debug-mode