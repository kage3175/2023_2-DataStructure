import java.util.Stack;

public class AVL extends BST{
  public AVL(){
    root = null;
  }

  public void updateHeight(Node root){
    root.height = 1 + Math.max(height(root.left), height(root.right));
  }

  /*public void updateLevel(Node node, int factor){
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
  }*/

  public void updateLevel(Node node, int level){
    if(node == null) return;
    node.level = level;
    updateLevel(node.left, level+1);
    updateLevel(node.right, level+1);
  }

  public Node rightRotate(Node z){
    boolean flag = false;
    if(z == root) {flag = true;}
    Node y = z.left;
    Node b = y.right;
    y.right = z;
    z.left = b;

    y.level--;
    z.level++;

    z.height = 1 + Math.max(height(z.right), height(b));
    y.height = 1 + Math.max(height(z), height(y.left));

    if(flag) {root = y;}

    return y;
  }

  public Node leftRotate(Node z){
    boolean flag = false;
    if(z == root) {flag = true;}
    Node y = z.right;
    Node tmp = y.left;
    y.left = z;
    z.right = tmp;

    y.level--;
    z.level++;

    z.height = 1 + Math.max(height(z.left), height(z.right));
    y.height = 1 + Math.max(height(y.left), height(y.right));

    if(flag) {root = y;}

    return y;
  }

  public int getBalance(Node node){
    return height(node.left) - height(node.right);
  }

  public void insert(String key){
    if(root == null){ // When root is empty
      root = new Node(key);
      size_tree++;
      root.level = 1;
    }
    else{ //When root is not empty

      Stack<Node> stack = new Stack<>();
      Node present_node = root;
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
            node = rightRotate(node);
          }
          else if(balance > 1 && node.left.key.compareTo(key) < 0){ //LR
            node.left = leftRotate(node.left);
            node = rightRotate(node);
          }
          else if(balance < -1 && node.right.key.compareTo(key) < 0){ //RR
            node = leftRotate(node);
          }
          else if(balance < -1 && node.right.key.compareTo(key) > 0){ //RL
            node.right = rightRotate(node.right);
            node = leftRotate(node);
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
        updateLevel(root, 1);
      }
    }
  }
}
