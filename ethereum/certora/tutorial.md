# Cetora Tutorials

[Gambit](https://github.com/Certora/gambit) mutation generator for Solidity
[Gambit docs](https://docs.certora.com/en/latest/docs/gambit/index.html)
[Cetora Tutorials](https://github.com/Certora/Tutorials)

## 00

Auditing

* Review the docs and code
* Check against common mistakes
* Identify issues
* Evaluate security of the process

Formal verification - like a fancy diff between the code and a formal specification.

Software Lifecycle

* Design
* Spec, Code, Test
* Testing
* Spec Checking
* Fuzzing
* Auditing
* Formal Verification => Proof the rules you wrote hold

Iterate

Formal verification myths

* FV can only prove absense of bugs
* Hardest problem is computational
* FV is one time deal

Reality

* Biggest value of FV is finding bugs
* Hardest problem is specification
* FV guarantees code upgrade safety

Review materials - chapter 1 [here](http://web.mit.edu/gleitz/www/Introduction%20to%20Logic%20-%20P.%20Suppes%20(1957)%20WW.pdf) and chapter 3.1 [here](https://discrete.openmathbooks.org/dmoi2/sec_propositional.html)

# 01

Install instructions [here](https://docs.certora.com/en/latest/docs/user-guide/getting-started/install.html#)
Solidity downloads [here](https://github.com/ethereum/solidity/releases)

Tutorial also recommends Ethereum Security Bundle by tintinweb

Preconditions - use to eliminate infeasible states

```
rule monotonousIncreasingNumOfParticipants(method f, uint256 meetingId) {
	env e;
	calldataarg args;
	require getStateById(e, meetingId) == 0 => getnumOfParticipants(e, meetingId) == 0;
	uint256 numOfParticipantsBefore = getnumOfParticipants(e, meetingId);
	f(e, args);
    uint256 numOfParticipantsAfter = getnumOfParticipants(e, meetingId);

	assert numOfParticipantsBefore <= numOfParticipantsAfter, "the number of participants decreased as a result of a function call";
}
```

Bug 2 in meeting scheduler flagged by this rule

```
rule checkStartedToStateTransition(method f, uint256 meetingId) {
	env e;
	calldataarg args;
	uint8 stateBefore = getStateById(e, meetingId);
	f(e, args);
	assert (stateBefore == 2 => (getStateById(e, meetingId) == 2 || getStateById(e, meetingId) == 3)), "the status of the meeting changed from STARTED to an invalid state";
	assert ((stateBefore == 2 && getStateById(e, meetingId) == 3) => f.selector == endMeeting(uint256).selector), "the status of the meeting changed from STARTED to ENDED through a function other then endMeeting()";
}
```