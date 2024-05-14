
## Meet in the middle

This is a beautiful algorithm which helps us solve hard problems where an array is given consisting of n integers where n <= 40 .

This algorithm is generally used when we have to find certain subset of the array fulfilling certain contraints like equal sum subset , equal average subset , etc .
A Brute Force approach to solve this type of problem would be to find all possible subset and check if it is under the given constraints . The time complexity using this approach would be O(2^n) and n is at most 40. * 2^40 will be quite large and hence we need to find more optimal approach.*

Meet in the middle is a search technique which is used when the input size is small but not as small that brute force can be used.
The essense of the algorithm is we try combinations optimally where upon we take i elements from the first Set and j elements from the second Set such that (i+j) elements say N1 is statisfying some condition and we have N - (i+j) say N2 elements left such that N1 + N2 = N , which is satisfiying the second part of condition.

### Algorithm at high level :

1.  Divide the given array into two equal halves , lets call it leftSet and rightSet .
2.  Now for each half calculate the subSet sum i.e generate the power set of each half and calculate the desired values , for instance , sum of each subset . Hence, size of each half X (Set of all subset sum of leftSet) and Y (Set of all subset sum of rightSet) will be at most 2^n/2.
3.  Now merge these 2 subSets logically . Find combinations from array X and Y such that their combined value satisfies our constraint.
4. * One way to do that is simply iterate over all elements of array Y for each element of array X to check the existence of such a combination which satisfies the constaints . This will take O( (2^n/2)^2) which is equivalent to O(2n).
   * To Optimise it , first sort array Y and then iterate over each element of X and for each element x in X use binary search to find maximum element y in Y.
   * Binary search here helps in reducing complexity from 2^n to (2^n/2)log(2^n/2)which is equivalent to (2^n/2)n.
5.  Thus overall running time is reduced to O((2^n/2)n).

####  Some Problem Based on this Concept:-
1. https://leetcode.com/problems/split-array-with-same-average/
2. https://leetcode.com/problems/closest-subsequence-sum/
3. https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/
4. https://www.codechef.com/problems/INSQ15_C
5. https://lightoj.com/problem/funny-knapsack
6. https://lightoj.com/problem/coin-change-iv

## CP Blog - https://codeforces.com/blog/entry/95571
