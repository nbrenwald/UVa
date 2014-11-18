import java.util.Scanner;

class Main {

  static final int colours = 101;
  static final String[] desc = {"front","back","left","right","top","bottom"};
  
  public static void towerOfCubes(int[][] cubes){

    int[][] towers  = new int[cubes.length][colours];
    String[][] stack = new String[cubes.length][colours];

    for (int i = 0; i<6; i++) {
      towers[cubes.length-1][cubes[cubes.length-1][i]]=1;
      stack[cubes.length-1][cubes[cubes.length-1][i]] = cubes.length+ " "+desc[i];
    }

    for(int i = cubes.length-2; i >= 0; i--){
      // copies over the previous row
      for(int j = 0; j < colours; j++){
        towers[i][j] = towers[i+1][j];
        stack[i][j] = stack[i+1][j];
      }
      for(int j = 0; j < 6; j++){
        int faceColour = cubes[i][j];
        int oppositeColour = getOppositeColour(cubes[i], j);
        if(towers[i+1][oppositeColour]+1 > towers[i][faceColour]){
          towers[i][faceColour] = towers[i+1][oppositeColour]+1;
          stack[i][faceColour] = (i+1) +" "+desc[j]+ ((stack[i+1][oppositeColour] != null) ? "\n" + stack[i+1][oppositeColour] : "" );
        }
         
      }
    }
   
    int max = 0;
    int maxTower = 0;
    for(int i = 1; i<towers[0].length; i++){
      if(max< towers[0][i]){
       max = towers[0][i];
       maxTower=i;
      }
    }
    System.out.println(max);
    System.out.println(stack[0][maxTower]);
  }

  private static int getOppositeColour(int[] cube, int face){
    if(face == 0) return cube[1];
    else if(face ==1) return cube[0];
    else if(face==2)return cube[3];
    else if(face==3)return cube[2];
    else if(face==4)return cube[5];
    else return cube[4];
  }

  

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int c = 1;
    while(sc.hasNextInt()) {
      int n = sc.nextInt();
      if(n==0)break;
      System.out.println("Case #"+c++);
      int[][] cubes = new int[n][6];
      for (int i = 0; i< n; i++) {
        for(int j = 0; j<6; j++){
          cubes[i][j] = sc.nextInt();
        }
      }
      towerOfCubes(cubes);
      System.out.println();
    }
    sc.close();
  }

}

