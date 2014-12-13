import java.util.Scanner;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;

class Main {

  public static int[][][] getAutomata(int n) {
    int[][][] a = new int[2][2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
          a[i][j][k] = n % 2;
          n /= 2;
        }
      }
    }
    return a;
  }

  public static int[] getTargetArray(String s) {
    int[] t = new int[s.length()];

    for (int i = 0; i < s.length(); i++) {
      t[i] = s.charAt(i) - '0';
    }

    return t;
}
  

  public static boolean isReachable(int[][][] a, int[] t) {
    
    int[] s = new int[t.length];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
          if (a[i][j][k] == t[0] && isCandidate(a, i, t[t.length-1]) ) {
            s[s.length-1] = i; 
            s[0] = j;
            s[1] = k;
            if (backTrack(a, t, s, 1, i)) return true;
          }
        }
      }
    }
    return false;
  }

  public static boolean isCandidate(int[][][] a, int i, int t) {
  // is i a candidate to calculate t, is there a rule where i is the cell and it equals t
    for (int j = 0; j < 2; j++) {
      for (int k = 0; k < 2; k++) {
        if (a[j][i][k] == t) return true;
      }
    }
    return false;
  }



  public static boolean backTrack(int[][][] a, int[] t, int[] s, int i, int last) {
    if (i == s.length-1) {
      if (a[s[i-1]][s[i]][s[0]] == t[i] && s[i]== last) return true;
      else return false;
    }

        for (int k = 0; k < 2; k++) {
          if (a[s[i-1]][s[i]][k] == t[i] ) {
            s[i+1] = k;
            if (backTrack(a, t, s, i+1, last)) return true;
          }
        }
 
    return false;
  }





  public static void main(String[] args) {
    PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
    String line = br.readLine();
    while (line != null) {
      //System.out.println(line);
      String[] arguments = line.split("\\s");
      //System.out.println(Arrays.toString(arguments));
      int automataId = Integer.valueOf(arguments[0]);
      int cells = Integer.valueOf(arguments[1]);
      String targetState = arguments[2];
  
      int[][][] automata = getAutomata(automataId);
      int[] t = getTargetArray(targetState); 
 
      out.println((isReachable(automata, t)) ? "REACHABLE" : "GARDEN OF EDEN");  
      
      line = br.readLine();
    }
    out.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}