/*
805. Split Array With Same Average
Hard

You are given an integer array nums.
You should move each element of nums into one of the two arrays A and B such that A and B are non-empty, and average(A) == average(B).
Return true if it is possible to achieve that and false otherwise.
Note that for an array arr, average(arr) is the sum of all the elements of arr over the length of arr.

Example 1:
Input: nums = [1,2,3,4,5,6,7,8]
Output: true
Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have an average of 4.5.

Example 2:
Input: nums = [3,1]
Output: false
 

Constraints:

1 <= nums.length <= 30
0 <= nums[i] <= 104

*/





class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        if(n==1) return false;
        if(n==2) return nums[0]==nums[1];
        
        int total = Arrays.stream(nums).sum();
        
        int N = n/2;
        
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
        
        for(int i=0;i<left.size();i++){
            for(int j=0;j<right.size();j++){
                List<Integer> l = left.get(i);
                List<Integer> r = right.get(j);
                
                Collections.sort(l);
                Collections.sort(r);
                
                if((total*(i+j))%n>0 || i+j==0 || i+j==n){
                    continue;
                }
                
                int t = total*(i+j)/n ;
                int a=0,b=r.size()-1;
                
                while(a<l.size() && b>=0){
                    int val = l.get(a)+r.get(b);
                    if(val == t){
                        return true;
                    }else if(val>t){
                        b--;
                    }else{
                        a++;
                    }
                }
            }
        }
        return false;
    }
}

