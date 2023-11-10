import java.util.LinkedList;
import java.util.Scanner;

public class Test_sub {
  public Test_sub(){

  }
  public Test doTest(){
    Scanner sc = new Scanner(System.in);
    String input = sc.next();
    LinkedList<String> test = new LinkedList<String>();
    test.add(input);
    input = sc.next();
    test.add(input);
    Test t = new Test();
    t.getTest(test);
    t.print();
    sc.close();
    return t;
  }
}
