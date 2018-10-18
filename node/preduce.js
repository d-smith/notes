const dayjs = require('dayjs');

const fnThatReturnsAPromise = (nextID) => {
	return new Promise((resolve, reject) => {
		setTimeout(() => {
			console.log(`Resolve: ${dayjs().format('hh:mm:ss')}`);
			resolve();
		}, 1000);
	});
}

[1,2,3].reduce((accumulatorPromise,nextID) => {
	console.log(`Loop: ${dayjs().format('hh:mm:ss')}`);
	return accumulatorPromise.then(() => {
		return fnThatReturnsAPromise(nextID);
	});
}, Promise.resolve());
