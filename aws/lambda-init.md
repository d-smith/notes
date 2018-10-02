For node sometime you need to make a call to a service to get something you need in your lambda function, for instance an oauth token.

But... how do you make sure you lambda code waits for the result?

```console
var thing = callFunctionThatReturnsAPromise();

const handler = (event, context, callback) => {
    await thing.then(async (fromPromise)=> {
        //Do stuff...
    });
}
```

Note you can also provide a onRejected handler function as well - see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise/then

This can also be extended using Promise.all if there are multiple items that need to be initialize, e.g.


```console
var thing1 = callFunctionThatReturnsAPromise();
var thing2 = callFunctionThatReturnsAPromise();

const handler = (event, context, callback) => {
    await Promise.all([thing1,thing2]).then(async (things)=> {
        let resolvedThing1 = things[0];
        let resolvedThing2 = things[1];
        //Do stuff...
    });
}
```