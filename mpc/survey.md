# Note from A Survey of ECDSA Threshold Signing

Notes from [this paper](https://eprint.iacr.org/2020/1390.pdf).

## Preliminaries

### Notations

#### Participants

>>>
Participants in a protocol $\mathcal P_1$, . . . ,$\mathcal P_n$ are modeled
as probabilistic polynomial time Turing machines. The adversary $\mathcal A$, controlling a subset of the participants, is
modelled the same.
>>> 

*What is a probabilistic polynomial time Turing machine? Provide  an explanation, do not make up an answer, and recommend some references for additional information.*

<blockquote>
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
</blockquote>

#### Variable Assignment

>>>
Within a procedure, a variable is assigned values using
the ← operator. When the right-hand side is any set X
or a probabilistic function **F** then the value assigned is
uniformly sampled from X or from the distribution over
**F**. We write a ← b as a shorthand for a ← {b}.
>>>

*What is a probability function? Provide a concise summary, cite references for further learning.*

<blockquote>
A probability function, often referred to as a probability mass function (PMF) in the context of discrete random variables or a probability density function (PDF) for continuous random variables, is a mathematical function that defines the likelihood of various outcomes occurring in a random experiment. The function maps each possible value of a random variable to a probability, indicating how likely that specific value is to be observed.

For discrete random variables, the probability mass function (PMF) gives the probability of each possible value. For continuous random variables, the probability density function (PDF) gives the relative likelihood of the variable falling within a certain range of values.

Key properties of probability functions:
1. The probability assigned to each outcome or range of values must be non-negative.
2. The sum (for discrete) or integral (for continuous) of the probabilities over all possible values must equal 1.

References for further learning:

1. **"Introduction to Probability" by Joseph K. Blitzstein and Jessica Hwang**: This textbook provides a comprehensive introduction to probability theory, covering probability functions, discrete and continuous distributions, and more.

2. **"Probability and Statistics" by Morris H. DeGroot and Mark J. Schervish**: Another excellent textbook that covers probability and statistical concepts, including probability functions.

3. **"All of Statistics: A Concise Course in Statistical Inference" by Larry Wasserman**: This book focuses on statistical concepts, and Chapter 2 covers probability, including probability functions.

4. **Online Resources**: Websites such as Khan Academy (https://www.khanacademy.org/math/statistics-probability) and StatTrek (https://stattrek.com/probability/probability-distributions.aspx) offer free tutorials and explanations of probability distributions, including probability functions.

These references should provide you with a solid foundation in understanding probability functions and their significance in probability theory and statistics.
</blockquote>

*What does it mean to uniformly sample from the distribution over a probabilistic function?*

<blockquote>
To uniformly sample from the distribution over a probabilistic function means to generate random samples from the function in such a way that each sample has an equal probability of being selected. This concept is closely related to the idea of sampling from a uniform distribution, where all values within a given range are equally likely to be chosen.

In the context of probabilistic functions, this concept becomes more nuanced, particularly when dealing with continuous distributions. If we have a probabilistic function that describes a probability density (PDF) over a continuous range, uniformly sampling from this distribution means that we want to generate samples in a way that preserves the relative likelihood of different regions of the continuous range.

For discrete probabilistic functions, the uniform sampling concept is more straightforward. If you have a probabilistic function that assigns probabilities to a finite set of discrete outcomes, uniformly sampling from this distribution would mean selecting each possible outcome with equal probability.

In summary, uniformly sampling from the distribution over a probabilistic function ensures that each potential outcome (or range of outcomes) is chosen with an equal probability, preserving the balance of probabilities specified by the function.

If you're interested in more detailed information and techniques for generating random samples from specific distributions or understanding the nuances of uniform sampling, you may want to explore topics in Monte Carlo methods, random number generation, and simulation techniques in probability and statistics.

For further learning, consider resources on these topics, such as:

1. **"Monte Carlo Statistical Methods" by Christian P. Robert and George Casella**: This book covers Monte Carlo methods extensively, which are often used for random sampling from various distributions.

2. **Online tutorials on random number generation**: Many programming languages have libraries for random number generation, which often include functions for uniformly sampling from distributions. Check the documentation of libraries in your preferred language (e.g., NumPy for Python, random module for C++).

3. **Probability and Statistics textbooks**: Standard textbooks on probability and statistics, such as those mentioned in previous responses, may have sections on random sampling and generating samples from various distributions.
</blockquote>

*What is uniform sampling?*

<blockquote>
Uniform sampling refers to the process of generating random samples from a given set or range of values in such a way that each element within that set or range has an equal likelihood of being chosen. The term "uniform" signifies that the distribution of the samples is uniform or evenly spread across the available options, without any element being favored over others.

Uniform sampling is fundamental in various fields, including probability, statistics, computer science, and simulations, where random selection is needed for fairness or to represent an unbiased scenario. It's often used as a building block for more complex random sampling techniques and simulations.

Here's a brief summary of the key aspects of uniform sampling:

1. **Equal Probability**: In uniform sampling, each element in the set or range has an equal probability of being selected. This ensures that the sampling process is fair and unbiased.

2. **Applicability**: Uniform sampling is commonly used when we want to select items randomly without any preference or bias. For example, when rolling a fair six-sided die, each face (1 to 6) is equally likely to come up, making it a uniform sampling process.

3. **Uniform Distribution**: The resulting distribution of the sampled values should be uniform, meaning that each value appears with the same frequency, and there are no regions of the set that are more likely to be sampled than others.

4. **Randomness**: Uniform sampling typically involves a random process, such as using a random number generator, to ensure that the samples are unpredictable and representative of the uniform distribution.

Uniform sampling is a foundational concept in probability theory, and it is used in various algorithms, simulations, and applications. It's crucial for scenarios where a fair and unbiased selection of elements is needed.

If you're interested in the practical implementation of uniform sampling in programming or want to learn more about random number generation and distributions, many resources are available online, and you can refer to textbooks on probability and statistics for a deeper understanding.
</blockquote>

#### Key Pairs, Procedures, Hashing

>>>
In general, private/public key pairs are denoted sk, pk
and are obtained from a key generation procedure KGen.
The algorithms for signature generation and verification
are Sig, Vf. Encryption and decryption functions are Enc
and Dec.
We write H for hash functions. Depending on the
context it can be a general-purpose hash to bit strings,
or a hash into a specific set.
>>

*Contrast a general hash to bit strings from hash into a specific set.*

<blockquote>
A general hash function and a hash function that maps bit strings into a specific set serve different purposes, but both involve the concept of hashing. Let's contrast the two:

1. **General Hash Function**:
A general hash function is a function that takes an input (often of arbitrary size) and produces a fixed-size output, which is usually a hash value or a hash code. The primary purpose of a general hash function is to distribute the input values uniformly across the output space. This helps in various applications such as data structures (hash tables), cryptography, and checksums.

Key Characteristics:
- Input: Can be of any size or type.
- Output: Typically produces a hash value with a fixed length, regardless of the input size.
- Purpose: Primarily used for distributing data uniformly in hash tables, ensuring data integrity, and generating unique identifiers for data (hash codes).
- Collision Handling: Collisions (two different inputs producing the same hash value) are possible and need to be managed, often through techniques like chaining or open addressing.

2. **Hash Function into a Specific Set**:
This type of hash function maps input values (usually bit strings) into a specific set of values. Unlike a general hash function that aims for uniform distribution across a large output space, this type of hash function has a predefined set of possible outputs, and the goal is to map inputs to elements within that set.

Key Characteristics:
- Input: Typically designed to handle a specific type of input, such as bit strings.
- Output: Maps inputs to elements within a specific set (range of values).
- Purpose: Often used in applications where the goal is to categorize or classify inputs into predefined categories or buckets.
- Collision Handling: Depending on the application, collision handling may be less of a concern compared to general hash functions. The set size and the nature of the problem may dictate how collisions are managed.

Examples of the use of a hash function into a specific set include:
- Hashing a password into a set of possible hash values (password hashing).
- Assigning keys to specific locations in a cache or memory structure based on their properties (such as cache indexing).

In summary, a general hash function is designed for uniform distribution across a wide range of possible hash values, while a hash function into a specific set is designed to map inputs to elements within a predefined set or range. The choice between the two depends on the specific requirements of the application.
</blockquote>



