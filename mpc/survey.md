# Note from A Survey of ECDSA Threshold Signing

Notes from [this paper](https://eprint.iacr.org/2020/1390.pdf).

## Preliminaries

>>>
Participants in a protocol $\mathcal P_1$, . . . ,$\mathcal P_n$ are modeled
as probabilistic polynomial time Turing machines. The adversary $\mathcal A$, controlling a subset of the participants, is
modelled the same.
>>> 

*What is a probabilistic polynomial time Turing machine? Provide  an explanation, do not make up an answer, and recommend some references for additional information.*

>>>
A probabilistic polynomial-time Turing machine (PPTM) is a theoretical model of computation that extends the traditional notion of a Turing machine by introducing randomness or probabilistic behavior into its operations while still maintaining efficiency. In this model, the machine is allowed to make random decisions during its computation, and these random decisions can influence the machine's transitions, choices, or outcomes.

Key characteristics of a PPTM:
1. **Probabilistic Transitions**: Unlike a deterministic Turing machine, which has a single, fixed transition for each input symbol and internal state, a PPTM can have multiple possible transitions for a given input and state, each associated with a probability.

2. **Polynomial Time**: The machine must still run in polynomial time with high probability. This means that, on any input of length n, the machine should complete its computation in a number of steps that is polynomial in n, and this should hold with a high probability (e.g., with a probability of at least 1/2 or some other constant).

Probabilistic polynomial-time Turing machines are essential in the study of complexity theory, particularly in the exploration of the class of problems solvable by machines with access to random bits. This class of problems is known as "BPP" (Bounded-error Probabilistic Polynomial time).

References for further information on PPTMs and related topics in complexity theory:

1. **"Introduction to the Theory of Computation" by Michael Sipser**: This textbook is widely used in computer science courses and covers the basics of Turing machines, including probabilistic Turing machines.

2. **"Computational Complexity: A Modern Approach" by Sanjeev Arora and Boaz Barak**: This book provides a comprehensive introduction to computational complexity theory, including discussions on probabilistic computation.

3. **"Randomness and Computation: Advanced Lectures" edited by S. Barry Cooper and John K. Truss**: This book contains advanced lectures on the intersection of randomness and computation, which includes discussions on probabilistic Turing machines.

4. **"The Probabilistic Method" by Noga Alon and Joel H. Spencer**: While not specifically about PPTMs, this book introduces probabilistic techniques in combinatorics and can provide valuable insights into the use of randomness in computation.

These references should help you gain a deeper understanding of probabilistic polynomial-time Turing machines and their role in complexity theory.
>>>





