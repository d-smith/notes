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


#### Elliptic Curves

>>>
An elliptic curve $\mathrm E$ over a finite field $\mathrm K$ is denoted
$\mathrm E(K)$, and we write $\Bbb G \subseteq  E(K)$ a subgroup of E(K)
of prime order q. A generator for $\Bbb G$ is denoted $\mathrm G$ and
we assumed that $\mathrm G$ is known to all parties as part of the
group’s description
>>>

*Concisely explain what a finite field is. Cite some references for further exploration.*

<blockquote>
A finite field, often denoted as GF(q) or Fq, is a mathematical structure in which arithmetic operations (addition, subtraction, multiplication, and division) are performed modulo a prime number "q," resulting in a set of elements that form a finite, closed, and algebraic system. Finite fields are fundamental in various areas, including cryptography, error-correcting codes, and algebraic geometry.

Key points about finite fields:

1. **Finite Size**: A finite field contains a finite number of elements. The number of elements in a finite field is given by q, which is a prime power (q = p^n, where p is a prime and n is a positive integer).

2. **Closure under Operations**: Finite fields are closed under addition, subtraction, multiplication, and division (excluding division by zero). The result of each operation remains within the finite field.

3. **Characteristics**: Each finite field has a characteristic, denoted as char(Fq), which is the smallest positive integer "k" such that adding "1" to itself "k" times results in zero. For prime fields (Fp, where p is a prime), the characteristic is simply the prime number "p."

4. **Algebraic Structure**: Finite fields exhibit many algebraic properties similar to real numbers, but they operate within a finite set of elements, making them particularly useful in applications where finite resources are involved.

References for further exploration:

1. **"A Course in Number Theory and Cryptography" by Neal Koblitz**: This book provides an introduction to finite fields, their properties, and their applications in cryptography.

2. **"Finite Fields and Applications" by Gary L. Mullen and Daniel Panario**: A comprehensive book that covers finite fields, their constructions, and applications in various fields.

3. **"Introduction to Cryptography: Principles and Applications" by Hans Delfs and Helmut Knebl**: This book includes sections on finite fields and their role in modern cryptography.

4. **Online Resources**: Websites like Khan Academy (https://www.khanacademy.org/computing/computer-science/cryptography/modarithmetic/a/what-are-modular-arithmetic-and-remainder) provide introductory explanations and examples of finite fields and modular arithmetic.

Exploring these resources will help you gain a deeper understanding of finite fields, their properties, and their significance in various mathematical and practical applications.
</blockquote>

In programming bitcoin, this definition is used:

<blockquote>
Mathematically, a finite field is defined as a finite set of numbers and two operations + (addition) and ⋅ (multiplication) that satisfy the following: 

1. If a and b are in the set, a + b and a ⋅ b are in the set. We call this property closed.  

2. 0 exists and has the property a + 0 = a. We call this the additive identity.  
3. 1 exists and has the property a ⋅ 1 = a. We call this the multiplicative identity.  
4. If a is in the set, –a is in the set, which is defined as the value that makes a + (–a) = 0. This is what we call the additive inverse.  
5. If a is in the set and is not 0, $a^{–1}$ is in the set, which is defined as the value that makes $a ⋅ a^{–1} = 1$. This is what we call the multiplicative inverse.

Song, Jimmy. Programming Bitcoin: Learn How to Program Bitcoin from Scratch (p. 2). O'Reilly Media. Kindle Edition. 
</blockquote>

Elliptic curve canonical form: $y^2 = x^3 + ax + b$.

Elliptic curves over finite fields are used in cryptography because of the difficulty of solving the discrete logarithm problem for the group of points on an elliptic curve defined over a finite field.

Scalar multiplication - denotes the number of times from add a point to itself.

*Explain how point addition works on elliptic curves over finite fields, and what the discrete log problem is in this context. Include an explanation of why the addition involves reflecting the intersection over the x axis.*

<blockquote>
Point addition on elliptic curves over finite fields is a fundamental operation used in elliptic curve cryptography (ECC). It's a method for combining two points on an elliptic curve to produce a third point that also lies on the curve. This operation is essential for various cryptographic algorithms, including key exchange and digital signatures.

Here's a high-level explanation of how point addition works on elliptic curves over finite fields:

1. **Elliptic Curve Equation**: An elliptic curve is defined by an equation of the form: y^2 = x^3 + ax + b, where "a" and "b" are constants, and the curve is defined over a finite field. The set of points (x, y) that satisfy this equation form the elliptic curve.

2. **Point Addition**: To add two points, P and Q, on the elliptic curve, you draw a line that passes through both P and Q. This line intersects the curve at a third point, R. The reflection of R over the x-axis gives you the result of the point addition operation, denoted as P + Q.

3. **Special Cases**: Special care must be taken for the cases when P and Q are equal (P = Q) or when the line connecting them is vertical. These special cases have their rules for point addition.

The discrete log problem in the context of elliptic curves over finite fields is related to finding a positive integer "k" given a base point G and another point P such that P = k * G. In other words, it involves determining the exponent "k" when given the result of a point multiplication operation. The difficulty of the discrete log problem in this context is the foundation of the security of certain cryptographic schemes based on elliptic curves, like the Diffie-Hellman key exchange and the Digital Signature Algorithm (DSA).

The reason that point addition involves reflecting the intersection point over the x-axis is due to the properties of elliptic curves. When a line intersects an elliptic curve, it typically intersects the curve at a third point, and the slope of the line used in the calculation is used to find the coordinates of this third point. The reflection over the x-axis ensures that the resulting point is on the curve. This property, combined with the group structure of points on the elliptic curve, allows for efficient and secure cryptographic operations.

Understanding the mathematical properties of elliptic curves, point addition, and the discrete log problem is crucial for implementing and analyzing elliptic curve cryptography. Further exploration of books and resources on elliptic curve cryptography, such as "Guide to Elliptic Curve Cryptography" by Darrel Hankerson, Alfred J. Menezes, and Scott Vanstone, can provide in-depth explanations and examples.
</blockquote>

Finite groups

<blockquote>
Another property of scalar multiplication is that at a certain multiple, we get to the point at infinity (remember, the point at infinity is the additive identity or 0). If we imagine a point G and scalar-multiply until we get the point at infinity, we end up with a set: 

{ G, 2G, 3G, 4G, ... nG } where nG = 0 

It turns out that this set is called a group, and because n is finite, we have a finite group (or more specifically, a finite cyclic group). Groups are interesting mathematically because they behave well with respect to addition: G + 4G = 5G or aG + bG = (a + b)G When we combine the fact that scalar multiplication is easy to do in one direction but hard in the other and the mathematical properties of a group, we have exactly what we need for elliptic curve cryptography.

Song, Jimmy. Programming Bitcoin: Learn How to Program Bitcoin from Scratch (pp. 48-49). O'Reilly Media. Kindle Edition. 
<blockquote>
