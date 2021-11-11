class Solution {
  // ref = https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings/discuss/1550342/Java-Clean-(DFS-//     //   %2B-memo)
  
  /**
  *  s1 = "abc123ad"
            i
  *  s2 = "bc12de"
               j
     1. Why 2000 options ?
     Since max 3 digits can be place, 999
     so difference b/w i and j can be -999 to 999 == 2000
     since i can be -999 and j can be 999 so diff == 2000
     
     What is diff ?
     This tell diff in index b/w i and j
     Suppose
     s1 = 'aaaaaaaabbbbccccc'
              i=4
     s2 = 'bbbbbbbaaaaaaaaaaaaaadddddddddd'
                           j=16
          diff =  16 - 4 = 12
          diff > 0, j > i 
          diff < 0, i > j
          Since digit are introduced in b/w, we have to take all possible digit/numbers that can be form
          means
          s1 = 'aaa123bb'
                   i
                   val= 1, 12, 123
                     val = 2, 23
                       val=3
                       all possible no. that can be formed
                  suppose val = 12.
                  so new i = i + val
                     new i = 3 + 12 = 15
                 aaa(12 character)(3character)bb
                               
                 
          s2 = 'ab12c'
                 j=1
                 diff = j - i, 1 - 15 = -14
                 -ve means i > j
     2. Basic is to check index value at i and j
    case 1. if both at i and j, are character and diff != 0, it means both i and j are at different positions
    case 2. if both are digit
    case 3. if both are character diff == 0, both are at same position, move to i+1, j+1
    case 4. if one character, one digit is there
  */
  Boolean[][][] dp;
  boolean dfs(int i, int j, int diff, String s1, String s2){
      if (i >= s1.length() && j >= s2.length() && diff == 0) return true;
      // diff > 0, j > i
      // diff < 0, i > j
      // System.out.println(" == "  + diff + " "  + (diff+1000) + " " + dp[i][j][diff + 1000]);
      
      if(dp[i][j][diff + 1000] != null){
          return dp[i][j][diff+1000];
      }
      
      boolean res = false;
      if(i < s1.length()){
          if(Character.isDigit(s1.charAt(i))){
              int value = 0, count = 0;
              while(i+count < s1.length() && count < 3 && Character.isDigit(s1.charAt(i+count))){
                  value = value*10 + (s1.charAt(i+count) - '0');
                  count++;
                  if(dfs(i+count, j, diff - value, s1, s2)) res = true;
              }
          }else{
              if(diff > 0){
                  if(dfs(i+1, j, diff-1, s1, s2)) res = true;
              }else if(diff == 0 && j < s2.length() && s1.charAt(i) == s2.charAt(j)){
                  if(dfs(i+1, j+1, diff, s1, s2)) res = true;
              }
          }
      }
      
      
      if(j < s2.length()){
          if(Character.isDigit(s2.charAt(j))){
              int value = 0, count = 0;
              while(j + count < s2.length() && count < 3 && Character.isDigit(s2.charAt(j+count))){
                  value = value*10 + (s2.charAt(j+count) - '0');
                  count++;
                  if(dfs(i, j+count, diff + value, s1, s2)) res = true;
              }
          }else{
              if(diff < 0){
                  if(dfs(i, j+1, diff+1, s1, s2)) res = true;
              }
          }
      }
      
      return dp[i][j][diff + 1000] = res;
  }
  public boolean possiblyEquals(String s1, String s2) {
    int m = s1.length();
    int n = s2.length();
    dp = new Boolean[m + 1][n + 1][2001];   
    return dfs(0, 0, 0, s1, s2);
  }
}