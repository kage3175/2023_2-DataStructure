import java.util.Stack;

public class Test_AVL extends Test{
  public Test_AVL(){
    tree = null;
  }

  public void updateHeight(Node tree){
    tree.height = 1 + Math.max(height(tree.left), height(tree.right));
  }

  public void updateLevel(Node node, int factor){
    Stack<Node> stack = new Stack<>();
    Node currNode = node;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      currNode.level += factor;
      currNode = currNode.right;
    }
  }

  public Node rightRotate(Node z){
    boolean flag = false;
    if(z == tree) {flag = true;}
    Node y = z.left;
    Node b = y.right;
    y.right = z;
    z.left = b;

    y.level--;
    z.level++;
    updateLevel(z.right, 1);
    updateLevel(y.left, -1);

    z.height = 1 + Math.max(height(z.right), height(b));
    y.height = 1 + Math.max(height(z), height(y.left));

    if(flag) {tree = y;}

    return y;
  }

  public Node leftRotate(Node z){
    boolean flag = false;
    if(z == tree) {flag = true;}
    Node y = z.right;
    Node tmp = y.left;
    y.left = z;
    z.right = tmp;

    y.level--;
    z.level++;
    updateLevel(z.left, 1);
    updateLevel(y.right, -1);

    z.height = 1 + Math.max(height(z.left), height(z.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));

    if(flag) {tree = y;}

    return y;
  }

  public int getBalance(Node node){
    return height(node.left) - height(node.right);
  }

  public void insert(String key){
    if(tree == null){ // When tree is empty
      tree = new Node(key);
      size_tree++;
      tree.level = 1;
    }
    else{ //When tree is not empty

      Stack<Node> stack = new Stack<>();
      Node present_node = tree;
      Node parent = null;
      int level = 1;
      boolean flag = false;

      while(present_node != null){
        level++;
        parent = present_node;
        stack.push(present_node);
        if(present_node.key.compareTo(key) > 0){
          present_node = present_node.left;
        }
        else if(present_node.key.compareTo(key) < 0){
          present_node = present_node.right;
        }
        else{
          //System.out.println(present_node.key);
          present_node.frequency++;
          flag = true;
          break;
        }
      }
      if(!flag){ // Not ended with frequency increment
        Node tmp = new Node(key);
        tmp.frequency = 1;
        size_tree++;
        tmp.level = level;
        if(parent.key.compareTo(key) > 0){
          parent.left = tmp;
        }
        else{
          parent.right = tmp;
        }
        while(!stack.isEmpty()){

          Node node = stack.pop();
          updateHeight(node);
          int balance = getBalance(node);
          //System.out.println("track: " + node.key + " " + balance);

          if(balance > 1 && node.left.key.compareTo(key) > 0){ //LL
            //System.out.println("---- Before LL ----");
            //print();
            node = rightRotate(node);
            //System.out.println("---- After LL ----");
            //print();
          }
          else if(balance > 1 && node.left.key.compareTo(key) < 0){ //LR
            //System.out.println("---- Before LR ----");
            //print();
            node.left = leftRotate(node.left);
            node = rightRotate(node);
            //System.out.println("---- After LR ----");
            //print();
          }
          else if(balance < -1 && node.right.key.compareTo(key) < 0){ //RR
            //System.out.println("---- Before RR ----");
            //print();
            node = leftRotate(node);
            //System.out.println("---- After RR ----");
            //print();
          }
          else if(balance < -1 && node.right.key.compareTo(key) > 0){ //RL
            //System.out.println("---- Before RL ----");
            //print();
            node.right = rightRotate(node.right);
            node = leftRotate(node);
            //System.out.println("---- After RL ----");
            //print();
          }

          if(!stack.isEmpty()){ //update parent node, especially for the case of rotation. If rotation didn't occr, then it will change nothing.
            Node parentNode = stack.peek();
            if(parentNode.key.compareTo(key) > 0){
              parentNode.left = node;
              //System.out.println(parentNode.key + "  " + node.key);
            }
            else{
              //System.out.println(parentNode.key + "  " + node.key);
              parentNode.right = node;
            }
          }
        }
      }
    }
  }
}
