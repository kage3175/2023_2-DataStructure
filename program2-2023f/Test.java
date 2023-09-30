import java.util.Stack;

public class Test {
  Node tree;
  int size_tree = 0;

  public class Node{
      String key;
      Node left;
      Node right;
      int frequency;
      int access_count;
      int level;
      int height;

      public Node(String data){
          key = new String(data);
          left = null;
          right = null;
          frequency = 1;
          access_count = 0;
          level = 0;
          height = 1;
      }
  }

  public Test(){
      tree = null;
  }

  public boolean find(String key) {
    if(tree == null) {return false;}
    Node currNode = tree;
    while(currNode != null){
      currNode.access_count++;
      if(currNode.key.compareTo(key) == 0){
        return true;
      }
      else if(currNode.key.compareTo(key) > 0){ // finding key is smaller than currNode's key
        if(currNode.left == null) {return false;}
        else{
          currNode = currNode.left;
        }
      }
      else{
        if(currNode.right == null) {return false;}
        else{
          currNode = currNode.right;
        }
      }
    }
    return false;
  } //End of find

  public int height(Node tree){
    if(tree == null) {return 0;}
    return tree.height;
  } //End of height

  public void insert(String key){
    if(tree == null){ // When tree is empty
      tree = new Node(key);
      size_tree++;
      tree.level = 1;
    } 
    else{ //When tree is not empty
      Node present_node = tree;
      Node parent = null;
      int level = 1;
      boolean flag = false;
      while(present_node != null){
        level++;
        parent = present_node;
        if(present_node.key.compareTo(key) > 0){
          present_node = present_node.left;
        }
        else if(present_node.key.compareTo(key) < 0){
          present_node = present_node.right;
        }
        else{
          present_node.frequency++;
          flag = true;
          break;
        }
      }
      if(!flag){ // Not ended with frequency increment
        Node tmp = new Node(key);
        size_tree++;
        tmp.level = level;
        tmp.frequency = 1;
        if(parent.key.compareTo(key) > 0){
          parent.left = tmp;
        }
        else{
          parent.right = tmp;
        }
        
      }
    }
  } //End of insert

  public void print() { //print in Inorder
    if(tree == null){
      return;
    }
    Stack<Node> stack = new Stack<>();
    Node currNode = tree;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      System.out.println("[" + currNode.key + ":" + currNode.frequency + ":" + currNode.access_count + ":" + currNode.level + ":" + currNode.height + "]");
      currNode = currNode.right;
    }
  } //End of print

  public void resetCounters() {
    if(tree == null){
      return;
    }
    Stack<Node> stack = new Stack<>();
    Node currNode = tree;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      currNode.frequency = 0;
      currNode.access_count = 0;
      currNode = currNode.right;
    }
    return;
  } //End of resetCounters

  public int size(){
    return size_tree;
  } //End of size

  public int sumFreq() {
    if(tree == null){
      return 0;
    }
    int freq = 0;
    Stack<Node> stack = new Stack<>();
    Node currNode = tree;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      freq+=currNode.frequency;
      currNode = currNode.right;
    }
    return freq;
  } //End of sumFreq

  public int sumProbes() {
    if(tree == null){
      return 0;
    }
    int access_cnt = 0;
    Stack<Node> stack = new Stack<>();
    Node currNode = tree;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      access_cnt+=currNode.access_count;
      currNode = currNode.right;
    }
    return access_cnt;
  } //End of sumProbes

  public int sumWeightedPath() {
    if(tree == null){
      return 0;
    }
    Stack<Node> stack = new Stack<>();
    Node currNode = tree;
    int wp = 0;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      wp += currNode.level * currNode.frequency;
      currNode = currNode.right;
    }
    return wp;
  } //End of sumWeightedPath

}


  /*public void insert(String key) {
    if(tree == null){ // When tree is empty
        tree = new Node(key);
        size_tree++;
        tree.frequency++;
        tree.level = 1;
    } else { //When tree is not empty
      Node present_node = tree;
      int level = 1;
      while(true){
        if(present_node.key.compareTo(key) > 0){// When new key is smaller than present node's key
          level++;
          if(present_node.left == null){
            Node tmp = new Node(key);
            size_tree++;
            tmp.frequency++;
            tmp.level = level;
            present_node.left = tmp;
            break;
          }
          else{
            present_node = present_node.left;
          }
        }
        else if(present_node.key.compareTo(key) < 0){
          level++;
          if(present_node.right == null){
            Node tmp = new Node(key);
            size_tree++;
            tmp.frequency++;
            tmp.level = level;
            present_node.right = tmp;
            break;
          }
          else{
            present_node = present_node.right;
          }
        }
        else{ // If the key is same as present_node's key, then just +1 to frequency
          present_node.frequency++;
          break;
        }
      } // End of While
    }
  }*/

  /*public void insert(String key) {
    if(tree == null){ // When tree is empty
        tree = new Node(key);
        size_tree++;
        tree.frequency++;
        tree.level = 1;
    } else { //When tree is not empty
      Node present_node = tree;
      int level = 1;
      while(true){
        if(present_node.key.compareTo(key) > 0){// When new key is smaller than present node's key
          level++;
          if(present_node.left == null){
            Node tmp = new Node(key);
            size_tree++;
            tmp.frequency++;
            tmp.level = level;
            present_node.left = tmp;
            break;
          }
          else{
            present_node = present_node.left;
          }
        }
        else if(present_node.key.compareTo(key) < 0){
          level++;
          if(present_node.right == null){
            Node tmp = new Node(key);
            size_tree++;
            tmp.frequency++;
            tmp.level = level;
            present_node.right = tmp;
            break;
          }
          else{
            present_node = present_node.right;
          }
        }
        else{ // If the key is same as present_node's key, then just +1 to frequency
          present_node.frequency++;
          break;
        }
      } // End of While
    }
  }*/