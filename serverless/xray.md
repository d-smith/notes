# xray

To add xray to your serverless app...

1. npm install the serverless-plugin-tracing as a dev dependency, and aws-xray-sdk as a runtime dependency.
2. Reference the plugin in the serverless.yml plugins section, and add tracing: true in the provider section
3. Add IAM statements to serverless.yml to allow xray:PutTraceSegments and xray:PutTelemetryRecords, e.g.

```console
    - Effect: "Allow"
      Action:
        - xray:PutTraceSegments
        - xray:PutTelemetryRecords
      Resource: '*'
```

4. Import the xray sdk and wrap the AWS sdk to get auto capture of AWS timings:

```console
const AWSXray = require('aws-xray-sdk');
const AWS =  AWSXray.captureAWS(require('aws-sdk'));
```

5. Use the XRAY API to break out other timing components - read the [docs](https://docs.aws.amazon.com/xray-sdk-for-nodejs/latest/reference/index.html).