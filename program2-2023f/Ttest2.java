public class Ttest2 {
    public static void main(String args[]){
        Test newtest = new Test();
        newtest.insert("a");
        newtest.insert("a");
        newtest.insert("01");
        newtest.insert("abc");
        newtest.insert("exe");
        System.out.println("find: " + newtest.find("dd"));
        System.out.println("sumFreq: " + newtest.sumFreq());
        System.out.println("Size: " + newtest.size());
        System.out.println("sumProbe: " + newtest.sumProbes());
        System.out.println("sumWP: " + newtest.sumWeightedPath());
        newtest.print();
        newtest.resetCounters();
        System.out.println("sumFreq: " + newtest.sumFreq());
        System.out.println("Size: " + newtest.size());
        System.out.println("sumProbe: " + newtest.sumProbes());
        System.out.println("sumWP: " + newtest.sumWeightedPath());
        newtest.print();
    }
}
