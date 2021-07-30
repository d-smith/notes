# notes

From the stream adventure on node school... so old i am afraid to npm install it.

```
  "BEEP BOOP",
  "MEET PIPE",
  "INPUT OUTPUT",
  "READ IT",
  "WRITE TO ME",
  "TRANSFORM",
  "LINES",
  "CONCAT",
  "HTTP SERVER",
  "HTTP CLIENT",
  "WEBSOCKETS",
  "HTML STREAM",
  "DUPLEXER",
  "DUPLEXER REDUX",
  "COMBINER",
  "CRYPT",
  "SECRETZ"
  ```

Summary

1. Use pipe to connect the output of a readable stream to the input of a writeable stream

Experimenting with readable (from this [stackoverflow thread](https://stackoverflow.com/questions/12755997/how-to-create-streams-from-string-in-node-js))

```
const Readable = require('stream').Readable;
const s = new Readable();
s._read = () => {};
s.pipe(process.stdout);
s.push('your text here');
s.push(null);
```

