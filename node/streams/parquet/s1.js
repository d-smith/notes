var parquet = require('parquetjs');
var through2 = require('through2');

let { ParquetSchema, ParquetTransformer } = require("parquetjs");
let { createReadStream, createWriteStream } =require("fs");


const schema = new ParquetSchema({
    //value: {
      //fields: {
        Country: { type: "UTF8" },
        Indicator: { type: "UTF8" },
        Value: { type: "FLOAT" },
        Year: { type: "INT64" }
      //}
    //}
  });


var objectStream = through2.obj(function(chunk, encoding, callback) {
    console.log(`push ${chunk}`)
    this.push(chunk)
    callback()
})

var jsonStream = through2.obj(function(chunk, encoding, callback) {
    this.push(JSON.stringify(chunk, null, 4) + '\n')
    callback()
})

//objectStream.pipe(jsonStream)
//    .pipe(process.stdout)
const destination = createWriteStream("countries.parquet");
objectStream
    .pipe(new ParquetTransformer(schema) )
    .pipe(destination)


objectStream.write({Country: 'USA', Indicator: 'Yes', Value: 1234.2, Year: 1999});
objectStream.write({Country: 'USA', Indicator: 'Yes', Value: 1234.2, Year: 1999});
objectStream.write({Country: 'USA', Indicator: 'Yes', Value: 1234.2, Year: 1999});

objectStream.push(null)