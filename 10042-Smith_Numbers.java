import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main{

  private static List<Integer> primes;

  public static int getNextSmith(int n){
    n++;

    while(true){
      if(isSmith(n)) return n;
      n++;
    }
  }

  public static boolean isSmith(int n){
    if(isPrime(n)) return false;
    int sumFactors = factors(n);
    if(sumFactors == sumDigits(n)) return true;
    else return false;
  }

  public static boolean isPrime(int n){
    if(n < 2) return false;
    if(n == 2) return true;
    if(n % 2 == 0) return false;
    int max = (int) Math.sqrt(n) + 1;
    // can n be divided by any known primes?
    int i = 0;
    while(i < primes.size()){
      if(primes.get(i) > max) return true;
      if(n % primes.get(i) == 0){ 
        return false;
      }
      i++;
    }
    for(int j = primes.get(primes.size()-1); j < max; j++){
      if(n % j == 0) return false;
    }  
    return true;
  }

  public static int sumDigits(int n){
    int sum = 0;
    while(n>0){
      sum += n % 10;
      n /= 10;
    }
    return sum;
  }

  public static int factors(int n){
    int p = 2;
    int result = 0;
    int i = 0;
    p = primes.get(i);
    while(n > 1){
      if (i < primes.size() || isPrime(p))
      while(n % p == 0){
        n /= p;
        int tmp = p;
        while(tmp > 0){
          result+= tmp%10;
          tmp /= 10;
        }
      }
      if(isPrime(n)) return result+sumDigits(n);
      i++;
      p = (i<primes.size()) ? primes.get(i) : p+1;
    }
    return result;
  }

  public static void main(String[] args){

    boolean[] marked = new boolean[10000000];
    marked[0] = true;
    marked[1] = true;
    
    int i = 2;
    while(i < marked.length){
      int m = i + i;
      for(; m<marked.length; m+=i){
        marked[m] = true;
      }
      i++;
      while(i < marked.length && marked[i]){
         i++;
      }
    }
    primes = new ArrayList<>();
    for(int j =0; j < marked.length; j++){
      if(!marked[j]){
        primes.add(j);
      }
    }
    Scanner sc = new Scanner(System.in);
    int test = sc.nextInt();
    while(sc.hasNextInt()){
      int n = sc.nextInt();
      System.out.println(getNextSmith(n));
    }
  }
    
}