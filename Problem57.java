class Solution {
  boolean isOverlap(int idx, int[] newInterval, int[][] intervals){
      if(newInterval[1] < intervals[idx][0]) return false;
      else return true;
  }
  public int[][] insert(int[][] intervals, int[] newInterval) {
      
      if(intervals.length == 0) return new int[][]{newInterval};
      List<int[]> ll = new ArrayList<>();
      int idx = 0;
      while(idx < intervals.length && intervals[idx][1] < newInterval[0]){
          ll.add(intervals[idx]);
          idx++;
      }
      
      if(idx >= intervals.length || !isOverlap(idx, newInterval, intervals)){
          ll.add(newInterval);
      }else{
          int[] p = new int[2];
          p[0] = Math.min(intervals[idx][0], newInterval[0]);
          p[1] = Math.max(intervals[idx][1], newInterval[1]);
          idx++;
          // System.out.println(Arrays.toString(p));
         while(idx < intervals.length && intervals[idx][0] <= p[1]){
           p[1] = Math.max(intervals[idx][1],p[1]);
           idx++;
       }
          // System.out.println(Arrays.toString(p));
          ll.add(p);
      }
      
      while(idx < intervals.length){
          ll.add(intervals[idx]);
          idx++;
      }

//         for(int[] ii: ll){
//            System.out.println(Arrays.toString(ii));
//         }
      
      int[][] ans = new int[ll.size()][2];
      int index = 0;
      for(int[] i: ll){
          ans[index] = i;
          index++;
      }
      return ans;
  }
}