/*
2035. Partition Array Into Two Arrays to Minimize Sum Difference
Hard

2909

You are given an integer array nums of 2 * n integers. 
You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. 
To partition nums, put each element of nums into one of the two arrays.

Return the minimum possible absolute difference.

 

Example 1:

example-1
Input: nums = [3,9,7,3]
Output: 2
Explanation: One optimal partition is: [3,9] and [7,3].
The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
Example 2:

Input: nums = [-36,36]
Output: 72
Explanation: One optimal partition is: [-36] and [36].
The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
Example 3:

example-3
Input: nums = [2,-1,0,4,-2,-9]
Output: 0
Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
 

Constraints:
1 <= n <= 15
nums.length == 2 * n
-107 <= nums[i] <= 107

*//


class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length;
        int total = Arrays.stream(nums).sum();
        int N = n/2;
        
        // Meet in Middle Concept :: - we can use map also :
        List<List<Integer>> left = new ArrayList<>();
        List<List<Integer>> right = new ArrayList<>();
        
        
        for(int i=0;i<=N;i++){
            left.add(new ArrayList<>());
            right.add(new ArrayList<>());
        }
        
        // let generate all subset 0-N -left & N+1 -n -right
        // total no. of subset -- 2^n ---: left - 2^N
        for(int i=0;i<(1<<N);i++){
            int setbit=0;
            int sum=0;
            // check every bits 
            for(int j=0;j<N;j++){
                // if it set means - we gona pick that ele:
                if((i & (1<<j))!=0){
                    setbit++;
                    sum+=nums[j];
                }
            }
            left.get(setbit).add(sum);
        }
        
        
        //right half :
        for(int i=0;i<(1<<N);i++){
            int setbit=0;
            int sum=0;
            // check every bits 
            for(int j=0;j<N;j++){
                // if it set means - we gona pick that ele:
                if((i & (1<<j))!=0){
                    setbit++;
                    sum+=nums[j+N];
                }
            }
            right.get(setbit).add(sum);
        }
        
        int ans = Integer.MAX_VALUE;
        
        for(int i=0;i<=N;i++){
            List<Integer> l = left.get(i);
            List<Integer> r = right.get(N-i);
            
            Collections.sort(l);
            Collections.sort(r);
            int a =0,b=r.size()-1;
            while(a<l.size() && b>=0){
               int currsum = l.get(a) + r.get(b);
               int diff = Math.abs(total - 2*currsum);
                ans = Math.min(ans,diff);
                if(2*currsum > total )b--;
                else a++;
            }
            
        }
        
        return ans;
    }
}
