public class Ttest_AVL {
  public static void main(String args[]){
    Test_AVL newtest = new Test_AVL();
    newtest.insert("a");
    newtest.insert("a");
    newtest.insert("01");
    newtest.insert("abc");
    newtest.insert("exe");
    newtest.print();
    System.out.println("find: " + newtest.find("dd"));
    System.out.println("sumFreq: " + newtest.sumFreq());
    System.out.println("Size: " + newtest.size());
    System.out.println("sumProbe: " + newtest.sumProbes());
    System.out.println("sumWP: " + newtest.sumWeightedPath());
    //newtest.resetCounters();
    newtest.insert("ff");
    newtest.print();
    System.out.println("sumFreq: " + newtest.sumFreq());
    System.out.println("Size: " + newtest.size());
    System.out.println("sumProbe: " + newtest.sumProbes());
    System.out.println("sumWP: " + newtest.sumWeightedPath());
    
  }
}
