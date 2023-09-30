public class Ttest_AVL {
  public static void main(String args[]){
    Test_AVL newtest = new Test_AVL();

    newtest.insert("a");
    newtest.insert("b");
    //newtest.print();
    newtest.insert("c");
    newtest.insert("d");
    //System.out.println("-------------------");
    //newtest.print();
    //System.out.println("-------------------");
    newtest.insert("b");
    newtest.insert("c");
    newtest.insert("d");
    newtest.insert("c");
    newtest.insert("d");
    newtest.insert("c");
    newtest.print();
    System.out.println("find: " + newtest.find("dd"));
    System.out.println("sumFreq: " + newtest.sumFreq());
    System.out.println("Size: " + newtest.size());
    System.out.println("sumProbe: " + newtest.sumProbes());
    System.out.println("sumWP: " + newtest.sumWeightedPath());
    //newtest.resetCounters();
    newtest.insert("ff");
    System.out.println("");
    newtest.print();
    System.out.println("sumFreq: " + newtest.sumFreq());
    System.out.println("Size: " + newtest.size());
    System.out.println("sumProbe: " + newtest.sumProbes());
    System.out.println("sumWP: " + newtest.sumWeightedPath());
    
  }
}
