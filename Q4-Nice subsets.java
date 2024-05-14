/*
Nice subsets
Mikahs is fond of mathematics and his favourite topic is Sets. He boasts of his knowledge in this topic and Ramu the mathematician is jealous of Mikahs.
To take him down, Ramu prepares a tough challenge for Mikahs. He first describes a new quantity for numbers discovered by him.
For a number X, the product of prime divisors of X is defined as the Niceness value of X by Ramu.

For Eg. X = 36, 36 = 22 * 32 therefore Niceness value of 36 is 2*3 = 6.

Now comes his question, an array of size N is given, and 3 integers K, A and B are given.
Mikahs is asked to find the number of subsets of size atmost K whose Niceness value is in the range [A, B],
where Niceness value of a subset is equal to the sum of Niceness values of its elements.


NOTE: Two subsets are different when they contain at least one pair of elements A[i] (in first subset),
A[j] (in second subset) where i != j. For eg. For array {2, 3, 2, 4}, subsets {A[0], A[1]} and {A[1], A[2]} are different.

Input
First line of the input contains N, K, A, B which are length of array,
maximum size of subset required, and the lower and upper bound of the range respectively.

Second line contains N space separated integers, the array elements.

Output
Print the number of subsets of size at most K whose Niceness value is in the range [A, B].

Constraints
1 ≤ N ≤ 32
1 ≤ K ≤ N
2 ≤ A < B ≤ 108
Array elements are in the range [2, 106]


Example
Input:
4 2 3 6
5 2 3 4
Output:
5
Explanation
The 5 subsets are :

{5} - Niceness value 5

{3} - Niceness value 3

{2, 3} - Niceness value 2 + 3 = 5

{2, 4} - Niceness value 2 + 2 = 4

{3, 4} - Niceness value 3 + 2 = 5

Thus all subsets are of size atmost 2 and their niceness values are in the range [3, 6].
*/

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static void main(String[] args) throws java.lang.Exception
    {
        // your code goes here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        int n = Integer.parseInt(st[0]);
        int k = Integer.parseInt(st[1]);
        int a = Integer.parseInt(st[2]);
        int b = Integer.parseInt(st[3]);

        String[] pts = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(pts[i]);
            arr[i] = primeFactorCal(val);
        }

        int f = n / 2;
        int s = n - n / 2;


        List < List < Integer >> left = new ArrayList < > ();
        List < List < Integer >> right = new ArrayList < > ();

        for (int i = 0; i <= f; i++) {
            left.add(new ArrayList < > ());
        }
        for(int i=0;i<=s;i++){
            right.add(new ArrayList < > ());
        }

        // Left fill:
        for (int i = 0; i <  (1 << f); i++) {
            int setbit = 0;
            int sum = 0;
            for (int j = 0; j < f; j++) {
                if ((i & (1 << j)) != 0) {
                    setbit += 1;
                    sum += arr[j];
                }
            }
            left.get(setbit).add(sum);
        }

        //right fill::
        for (int i = 0; i < (1 << s); i++) {
            int setbit = 0;
            int sum = 0;
            for (int j = 0; j < s; j++) {
                if ((i & (1 << j)) != 0) {
                    setbit += 1;
                    sum += arr[f + j];
                }
            }
            right.get(setbit).add(sum);
        }

        for (int i = 1; i < left.size(); i++) {
            Collections.sort(left.get(i));
        }
        for (int i = 1; i < right.size(); i++) {
            Collections.sort(right.get(i));
        }

        long ans = 0L;
        for (int i = 1; i < left.size(); i++) {
            List < Integer > l = left.get(i);

            for (int ele: l) {
                if (ele > b) {
                    continue;
                }
                // we got range :-
                int mini = Math.max(0, a - ele);
                int maxi = b - ele;

                for (int j = 1; j < right.size(); j++) {
                    if (i + j > k) continue;
                    // binary search :: lower bound and upperbound :-
                    int fs = fbsearch(right.get(j), mini);
                    int ss = sbsearch(right.get(j), maxi);

                    if (fs == -1 || ss == -1) continue;
                    int size = (ss - fs + 1);
                    if (size > 0) {
                        ans += size;
                    }
                }
            }
        }
        // lets check indivisual element:

        // left candidates::-
        for (int i = 0; i < left.size(); i++) {
            List < Integer > temp = left.get(i);

            for (int ele: temp) {
                if (ele >= a && ele <= b) {
                    ans++;
                }
            }
        }

        //right candidates ::-
        for (int i = 0; i < right.size(); i++) {
            List < Integer > temp = right.get(i);

            for (int ele: temp) {
                if (ele >= a && ele <= b) {
                    ans++;
                }
            }
        }
        //return ans;
        System.out.println(ans);

    }
    // lower bound ::
    public static int fbsearch(List < Integer > lis, int target) {
        int ans = -1;
        int l = 0;
        int h = lis.size() - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            int curr = lis.get(mid);
            if (curr >= target) {
                ans = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
    //Uper bound
    public static int sbsearch(List < Integer > lis, int target) {
        int ans = -1;
        int l = 0;
        int h = lis.size() - 1;

        while (l <= h) {
            int mid = l + (h - l) / 2;
            int curr = lis.get(mid);
            if (curr <= target) {
                ans = mid;
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }
        return ans;
    }

    public static int primeFactorCal(int val) {
        int ans = 1;
        if (val % 2 == 0) {
            ans *= 2;
        }
        while (val % 2 == 0) {
            val /= 2;
        }

        for (int i = 3; i * i <= val; i++) {
            if (val % i == 0) {
                ans *= i;
            }
            while (val % i == 0) {
                val /= i;
            }
        }
        if (val > 2) {
            ans *= val;
        }
        return ans;
    }
}
