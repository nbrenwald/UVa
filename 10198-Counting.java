import java.util.Scanner;
import java.math.BigInteger;

class Main{
// recurrence 
// T(n) = T(n-1) + T(n-1) + t(n-2) + T(n-3)
// T(0) = 1
// T(1) = 2
// T(2) = 5
// T(3) = 13
// uses BigInteger. I could create my own big integer class. store byte in arrary for each digit,
// time is linear, space is quite large

  public static void main(String[] args){

    BigInteger[] a = new BigInteger[1001];
    a[0] = BigInteger.valueOf(1);
    a[1] = BigInteger.valueOf(2);
    a[2] = BigInteger.valueOf(5);
    a[3] = BigInteger.valueOf(13);
    for(int i = 4; i < a.length; i++ ){
      a[i] = a[i-1].add(a[i-1]).add(a[i-2]).add(a[i-3]);
    }
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextInt()){
      int n = sc.nextInt();
      System.out.println(a[n]);
    }
  }
}