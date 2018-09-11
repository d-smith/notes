let foo = new Promise((resolve, reject) => {
    resolve('foo');
});

 

foo.then((x) => {
    console.log(x);
})

 

foo.then((x) => {
    console.log(x);
})

 

console.log('do some stuff');

 

foo.then((x) => {
    console.log(`doing some more stuff with ${x}`);
});
