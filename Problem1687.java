class Solution {
  /**
  1. [a ,  b, c,  d,  e,  f]
         left=1       right=4
      0    1   2   3   4   5
  2. So, min. trips upto right = min trips upto left + trips need from left to right 
  3. dp[4] = dp[1] + trips(from 1 to 4)
  4. initial left, and right will be 0th index
  5. Right will move to capture upto max boxes and max weight.
  6. When maxWeight or maxBoxes are achieved then we will shift left toward right, so get boxes within the 
     constraint, weight < maxWeight, and right - left <= maxBoxes
  7. While increase left (means shifting toward right), we will decrease trips
      left and left+1 both are NOT same
      because we they are same, then no trips will be done/added.
  8. Suppose dp[left] === dp[left+1], then left can be shifted to right, because it is not affecting the trips,          and also additional weight will be decrease as we move toward right.
  9. We have initialised trip = 2, as every break point, 2 trips will be added for going to storage and coming to        port again.
  10. dp[n] will contain min trips needed.
  */
  public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
      
      int t = 2;
      int weight = 0;
      
      int n = boxes.length;
      int[] dp = new int[n+1];
      dp[0] = 0;
      int left = 0;
      for(int right = 0; right < boxes.length; right++){
          weight += boxes[right][1];
          if(right > 0 && boxes[right][0] != boxes[right-1][0]) t++;
          
          // checking if weight, boxes are less than or equal to max contraint
          while(weight > maxWeight || right - left >= maxBoxes || (left < right && dp[left] == dp[left+1])){
              weight -= boxes[left][1];
              if(boxes[left][0] != boxes[left+1][0]) t--;
              left++;
          }
          // System.out.println(Arrays.toString(dp));
          dp[right+1] = dp[left] + t;
      }
      
      return dp[n];
  }
}