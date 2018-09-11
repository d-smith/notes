let foo = new Promise((resolve, reject) => {
    reject(new Error('foo'));
});

foo.then((x) => {
    console.log('promise fullfilled now do some stuffs');
}).catch((x) => {
    console.log(`caught ${x} ${x.stack}`);
});