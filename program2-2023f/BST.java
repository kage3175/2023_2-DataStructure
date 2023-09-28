// (Nearly) Optimal Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)
import java.util.Stack;

public class BST { // Binary Search Tree implementation

  protected boolean NOBSTified = false;
  protected boolean OBSTified = false;
  protected Node root;
  int size_tree = 0;

  protected class Node {
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
        access_count = 0;
        frequency = 0;
        level = 0;
        height = 1;
    }
  }

  public BST() {
    root = null;
  }

  public int size() {
    return size_tree;
  }
  public int sumFreq() {
    if(root == null){
      return 0;
    }
    int freq = 0;
    Stack<Node> stack = new Stack<>();
    Node currNode = root;
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
  }
  public int sumProbes() {
    if(root == null){
      return 0;
    }
    int access_cnt = 0;
    Stack<Node> stack = new Stack<>();
    Node currNode = root;
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
  }
  public int sumWeightedPath() {
    if(root == null){
      return 0;
    }
    int wp = 0;
    Stack<Node> stack = new Stack<>();
    Node currNode = root;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      wp+=currNode.frequency * currNode.level;
      currNode = currNode.right;
    }
    return wp;
  }
  public void resetCounters() {
    if(root == null){
      return;
    }
    Stack<Node> stack = new Stack<>();
    Node currNode = root;
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
  }

  public void nobst() { }	// Set NOBSTified to true.
  public void obst() { }	// Set OBSTified to true.

  public void insert(String key) {
    if(root == null){ // When tree is empty
        root = new Node(key);
        root.frequency++;
        size_tree++;
        root.level = 1;
    } else { //When tree is not empty
      Node present_node = root;
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
  } //End of insert

  public boolean find(String key) {
    if(root == null) {return false;}
    Node currNode = root;
    while(currNode != null){
      currNode.access_count++;
      if(currNode.key.compareTo(key) == 0){
        return true;
      }
      else if(currNode.key.compareTo(key) > 0){ // finding key is smaller than currNode's key
        if(currNode.left == null) return false;
        else{
          currNode = currNode.left;
        }
      }
      else{
        if(currNode.right == null) return false;
        else{
          currNode = currNode.right;
        }
      }
    }
    return false;
  } // End of find

  

  public void print() { //print in Inorder
    if(root == null){
      return;
    }
    Stack<Node> stack = new Stack<>();
    Node currNode = root;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      System.out.println("[" + currNode.key + ":" + currNode.frequency + ":" + currNode.access_count + "]");
      currNode = currNode.right;
    }
  } // End of print

}

