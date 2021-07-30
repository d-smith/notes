through2 = require('through2');

process.stdin.pipe(through2(function (chunk, enc, callback) {
    for (let i = 0; i < chunk.length; i++)
      if (chunk[i] == 97)
        chunk[i] = 122 // swap 'a' for 'z'
 
    this.push(chunk)
 
    callback()
   }))
   .pipe(through2(function (chunk, enc, callback) {
       this.push('here we go: ');
       this.push(chunk)
       callback();
   }))
  .pipe(process.stdout)
  .on('finish', () => console.log('done'));