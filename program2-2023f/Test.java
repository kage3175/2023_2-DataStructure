public class Test {
    Node tree;

    private class Node{
        int key;
        Node left;
        Node right;

        public Node(int data){
            key = data;
            left = null;
            right = null;
        }
    }

    public Test(){
        tree = null;
    }

    public void insert(int data){
        if(tree == null){
            tree = new Node(data);
        }
    }
}
