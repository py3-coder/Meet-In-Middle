/*
1755. Closest Subsequence Sum
Hard

You are given an integer array nums and an integer goal.
You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal.
That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
Return the minimum possible value of abs(sum - goal).

Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.

Example 1:
Input: nums = [5,-7,3,5], goal = 6
Output: 0
Explanation: Choose the whole array as a subsequence, with a sum of 6.
This is equal to the goal, so the absolute difference is 0.

Example 2:
Input: nums = [7,-9,15,-2], goal = -5
Output: 1
Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.

Example 3:
Input: nums = [1,2,3], goal = -7
Output: 7
 

Constraints:

1 <= nums.length <= 40
-107 <= nums[i] <= 107
-109 <= goal <= 109
*/

class Solution {
    public int minAbsDifference(int[] nums, int goal) {
        
        
        // TC : 2^n/2 +  2*(2^n/2 log(2^n/2)) + O(n) 
        // SC : O(n/2)+O(n/2)
                
        int n = nums.length;
        int total = Arrays.stream(nums).sum();
        int f=0,s=0;
        if(n%2==0){
            f =n/2;
            s =n/2;
        }else{
            f=n/2+1;
            s=n-f;
        }
        
        // Meet in Middle Concept ::  :
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        
        // let generate all subset 0-N -left & N+1 -n -right
        // total no. of subset -- 2^n ---: left - 2^N
        for(int i=0;i<(1<<f);i++){
            int sum=0;
            // check every bits 
            for(int j=0;j<f;j++){
                // if it set means - we gona pick that ele:
                if((i & (1<<j))!=0){
                    sum+=nums[j];
                }
            }
            left.add(sum);
        }
        
        
        //right half :
        for(int i=0;i<(1<<s);i++){
            int sum=0;
            // check every bits 
            for(int j=0;j<s;j++){
                // if it set means - we gona pick that ele:
                if((i & (1<<j))!=0){
                    sum+=nums[j+f];
                }
            }
            right.add(sum);
        }
        
        // we can also apply binary search over there by just sorting single list-
        Collections.sort(left);
        Collections.sort(right);
        
        
        int ans = Integer.MAX_VALUE;
        int a=0,b=right.size()-1;
        
        while(a<left.size() && b>=0){
            int currSum = left.get(a)+right.get(b);
            ans = Math.min(Math.abs(currSum-goal),ans);
            if(currSum==goal){
                ans=0;
                break;
            }else if(currSum>goal){
                b--;
            }else{
                a++;
            }
        }
        return ans;
    }
}
