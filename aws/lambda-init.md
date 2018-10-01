For node sometime you need to make a call to a service to get something you need in your lambda function, for instance an oauth token.

But... how do you make sure you lambda code waits for the result?

var thing = callFunctionThatReturnsAPromose();

const handler = (event, context, callback) => {
    await thing.then(async (fromPromise)=> {
        //Do stuff
    })
}

Note you can also provide a onRejected handler function as well - see https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Promise/then