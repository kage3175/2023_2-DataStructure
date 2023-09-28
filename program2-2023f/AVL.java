// AVL Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.Stack;

public class AVL extends BST
{
  public AVL() {
    root = null;
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

    return y;
  }

  public Node leftRotate(Node z){
    Node y = z.right;
    Node a = y.left;
    y.left = z;
    z.right = a;

    y.level--;
    z.level++;
    updateLevel(z.left, 1);
    updateLevel(y.right, -1);

    z.height = 1 + Math.max(height(z.left), height(a));
    y.height = 1 + Math.max(height(z), height(y.right));

    return y;
  }

  public int getBalance(Node node){
    return height(node.left) - height(node.right);
  }

  public void insert(String key){
    if(root == null){ // When root is empty
      root = new Node(key);
      size_tree++;
      root.frequency++;
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
            //System.out.println(node.key + "RR");
            node = leftRotate(node);
            //System.out.println(node.key);
          }
          else if(balance < -1 && node.right.key.compareTo(key) > 0){ //RL
            //System.out.println(node.key + "RL");
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
      }
    }
  }
}

