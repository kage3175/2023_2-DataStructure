// (Nearly) Optimal Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)
import java.util.Stack;

public class BST { // Binary Search Tree implementation

  protected boolean NOBSTified = false;
  protected boolean OBSTified = false;
  Node root;

  private class Node {
    String key;
    Node left;
    Node right;
    int frequency;
    int access_count;
    boolean lthreaded;
    boolean rthreaded;

    public Node(String data){
        key = new String(data);
        left = null;
        right = null;
        access_count = 0;
        frequency = 0;
        lthreaded = false;
        rthreaded = true;
    }
  }

  public BST() {
    root = null;
  }

  public int size() { }
  public void insert(String key) {
    if(root == null){ // When tree is empty
        root = new Node(key);
        root.frequency++;
    } else { //When tree is not empty
      Node present_node = root;
      while(true){
        if(present_node.key.compareTo(key) > 0){// When new key is smaller than present node's key
          if(present_node.left == null){
            Node tmp = new Node(key);
            tmp.frequency++;
            present_node.left = tmp;
            break;
          }
          else{
            present_node = present_node.left;
          }
        }
        else if(present_node.key.compareTo(key) < 0){
          if(present_node.right == null){
            Node tmp = new Node(key);
            tmp.frequency++;
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
  }
  public boolean find(String key) { }

  public int sumFreq() { }
  public int sumProbes() { }
  public int sumWeightedPath() { }
  public void resetCounters() { }

  public void nobst() { }	// Set NOBSTified to true.
  public void obst() { }	// Set OBSTified to true.
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
      System.out.println(currNode.key + currNode.frequency);
      currNode = currNode.right;
    }
  } 

}

