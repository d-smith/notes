# Localstack Notes

https://localstack.cloud/
https://github.com/localstack
https://docs.localstack.cloud/getting-started/

https://reshefsharvit.medium.com/go-faster-with-golang-and-localstack-ace2138dc0e7

https://github.com/KOBA-Systems/aws_local_dev_setup

```
$ uname -m
x86_64
```

```
curl -Lo localstack-cli-2.3.0-linux-amd64-onefile.tar.gz \
    https://github.com/localstack/localstack-cli/releases/download/v2.3.0/localstack-cli-2.3.0-linux-amd64-onefile.tar.gz

sudo tar xvzf localstack-cli-2.3.0-linux-*-onefile.tar.gz -C /usr/local/bin

localstack --version
2.3.0
```


```
export AWS_ACCESS_KEY_ID="test"
export AWS_SECRET_ACCESS_KEY="test"
export AWS_DEFAULT_REGION="us-east-1"

aws --endpoint-url=http://localhost:4566 kinesis list-streams

alias aws='aws --endpoint-url=http://localhost:4566'
aws kinesis list-streams
```

```
```