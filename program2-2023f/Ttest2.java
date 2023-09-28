public class Ttest2 {
    public static void main(String args[]){
        Test newtest = new Test();
        newtest.insert("a");
        newtest.insert("a");
        newtest.insert("01");
        newtest.insert("abc");
        System.out.println(newtest.find("dd"));
        newtest.print();
    }
}
