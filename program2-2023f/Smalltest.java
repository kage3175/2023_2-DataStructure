import java.util.Stack;
import java.io.*;

public class Smalltest {
  public int[][] array;
  public static void main(String args[]){
    int n = 5;
    System.out.println("Hello, World!");
    String[][] array = new String[n][n];
    for(int i = 0;i < 5; i++){
      for(int j = 0; j < 5; j++){
        array[i][j] = "hee";
        System.out.println(array[i][j]);
      }
    }
  }
}
