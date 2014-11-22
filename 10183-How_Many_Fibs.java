import java.util.Scanner;
import java.math.BigInteger;

class Main{

  private static int fibBetween(BigInteger a, BigInteger b){
    BigInteger fibMinus2 = BigInteger.valueOf(1);
    BigInteger fibMinus1 = BigInteger.valueOf(2);   
    

    int count = 0;
    if(a.compareTo(BigInteger.ZERO) <= 0 && b.compareTo(BigInteger.ZERO) <= 0) return count;
    if(a.compareTo(BigInteger.valueOf(1)) <= 0) {
      if (b.equals(BigInteger.valueOf(1))){
        return 1;
      }
      else {
        count = 2;
      }
    }
    if(a.equals(BigInteger.valueOf(2))) {
      count++;
    }

        
    BigInteger fib = fibMinus2.add(fibMinus1);
    while(fib.compareTo(b) <= 0) {
    if (fib.compareTo(a) >= 0) {
      count++;
    }
    fibMinus2 = fibMinus1;
    fibMinus1 = fib;
    fib = fibMinus2.add(fibMinus1);
    }
    return count;
    
  }

  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextBigInteger()){
      BigInteger a = sc.nextBigInteger();
      BigInteger b = sc.nextBigInteger();
      if(a.equals(BigInteger.valueOf(0)) && b.equals(BigInteger.valueOf(0)))return;
      System.out.println(fibBetween(a, b));
    }
  }
}