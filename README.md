# primeNumbers

Summary/Proof:
PrimeNumbers.java uses Sieve Of Eratosthenes' implementation of finding a prime Number (https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) 
from 1 to 10^8 by first creating a boolean array of size 10^8 and setting all the booleans in that array to true initially.
I then used Sieve's algorithm in order to set the boolean array to values of either true or false corresponding to whether the value at that
index was a primeNumber or not. Eight threads were then created and ran with a set range to evaluate. The threads would go to every index in 
their range and check the to see whether the index is true or false and update the count and sum using an AtomicInteger and AtomicLong accordingly.
This ensures that no threads overlap in the work that they do as they have a different set of ranges to work on.

Efficiency:
The efficiency of this algorithm is O(sqrt(n) * n). The algorithm initially took a total time of about 3 seconds to complete without using 
concurrency. After multithreading was implemented, the algorithm took approximately 0.65 seconds. This is due to the fact that eight threads are 
working simultaneously to check how many primes are set to true in the boolean array.

Experimental Evaluation:
I tested the program on inputs of less than 10^8, 10^8, and more than 10^8 in order to ensure that the output was always correct and it indeed was.


```
How to Compile
```
First Command: Javac primeNumbers.java

Second Command: Java primeNumbers 

