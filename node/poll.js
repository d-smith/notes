var myCount = 0;

const aFunction = () => {
  return myCount++ == 10;
}


function poll(fn, timeout, interval) {
    var endTime = Number(new Date()) + (timeout || 2000);
    interval = interval || 100;

    var checkCondition = function(resolve, reject) {
        console.log('checkCondition');
        // If the condition is met, we're done!
        var result = fn();
        if(result) {
            resolve(result);
        }
        // If the condition isn't met but the timeout hasn't elapsed, go again
        else if (Number(new Date()) < endTime) {
            setTimeout(checkCondition, interval, resolve, reject);
        }
        // Didn't match and too much time, reject!
        else {
            reject(new Error('timed out for ' + fn + ': ' + arguments));
        }
    };

    return new Promise(checkCondition);
}


const fillInX = async () =>  {
  try {
    let x = await poll(aFunction,2000, 1000);
    return x;
  } catch(theError) {
    console.log(theError.message);
    return 'dang it';
  }
}


const doFoo = async() => {

  let x = {};

  x = await fillInX();

  return x;

}

const go = async () => {
  myX = await doFoo();
  console.log(`myX is ${myX}`);
}

go();
