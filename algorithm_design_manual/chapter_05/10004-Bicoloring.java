import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class Main {

  public static boolean isBiColorable(Map<Integer, List<Integer>> a){
  // Space, O(v) required for the Deque, O(n) required for the color map
  // Runtime, O(V+E) i visit every vertex twice, and each time check every edge
  // for that vertex, runtime O(2*(V+E))
    if(a.size() > 1){

      Deque<Integer> s = new LinkedList<>();
      Map<Integer, Boolean> c = new HashMap<>();

      int init = 0;
      boolean color = true;
      c.put(init, color); // true = red, false = black
      s.push(init);

      while(!s.isEmpty()){
        int top = s.peek();
        color = c.get(top);
        for (int i : a.get(top)) {
          if(!c.containsKey(i)) {
            s.push(i);
            c.put(i, !color);
          }
          else {
            if(c.get(i) == color){
              return false;
            }
          }
        } 
        if (top == s.peek()){
          s.pop();
        } 
      }   
    }
    return true;
  }

  public static boolean isBiColorableRecursive(Map<Integer, List<Integer>> a){
      Map<Integer, Boolean> c = new HashMap<>();
      int init = 0;
      c.put(init, true); // true = red, false = black
      return dfs(0, a, c);

  }

  public static boolean dfs(int v, Map<Integer, List<Integer>> a, Map<Integer,Boolean> c){
    boolean color = c.get(v);
    boolean result = true;
        for (int i : a.get(v)) {
          if(!c.containsKey(i)) {
            c.put(i, !color);
            result = true && dfs(i, a, c);
          }
          else {
            if(c.get(i) == color){
              return false;
            }
          }
        } 
      return result;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int c = 1;
    while(sc.hasNextInt()) {
      int n = sc.nextInt();
      if(n==0)break;
      int e = sc.nextInt();
      Map<Integer, List<Integer>> a = new HashMap<>();
      for (int i = 0; i < n; i++){
        List<Integer> li = new ArrayList<>();
        a.put(i, li);
      }
      for (int i = 0; i< e; i++) {
        int u = sc.nextInt();
        int v = sc.nextInt();
        if(!a.get(u).contains(v)){
          a.get(u).add(v);
        }
        if(!a.get(v).contains(u)){
          a.get(v).add(u);
        }
      }
      System.out.println((isBiColorableRecursive(a))? "BICOLORABLE." : "NOT BICOLORABLE.");
    }
    sc.close();
  }

}

