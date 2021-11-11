// Queue + hashMap ( so storing visited values)
// We can to generate all possible cases here, using queue
class Solution {
  public int minimumOperations(int[] nums, int start, int goal) {
      Queue<long[]> q = new LinkedList<>();
      HashMap<Long,Integer> map = new HashMap<>();
      
      // map.put(start, 1);
      q.add(new long[]{start, 0L});
      while(!q.isEmpty()){
          long[] v = q.poll();
          long val = v[0];
          
          // System.out.println(val);
          if(map.containsKey(val)) continue;
          
          map.put(val, 1);
          for(int i: nums){
              long add = val + i;
              long sub = val - i;
              long xor = val^i;
              
              
              if(add == goal || sub == goal || xor == goal){
                  return (int)v[1]+1;
              }
              
              if(add >= 0 && add <= 1000){
                  q.add(new long[]{add, v[1]+1});
              }
              
              if(sub >= 0 && sub <= 1000){
                  q.add(new long[]{sub, v[1]+1});
              }
              
              if(xor >= 0 && xor <= 1000){
                  q.add(new long[]{xor, v[1]+1});
              }
          }
      }
      return -1;
  }
}