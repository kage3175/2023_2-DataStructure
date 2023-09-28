import java.util.Stack;

public class Test {
  Node tree;

  private class Node{
      String key;
      Node left;
      Node right;
      int frequency;
      int access_count;

      public Node(String data){
          key = data;
          left = null;
          right = null;
          frequency = 0;
          access_count = 0;
      }
  }

  public Test(){
      tree = null;
  }

  public void insert(String key) {
    if(tree == null){ // When tree is empty
        tree = new Node(key);
        tree.frequency++;
    } else { //When tree is not empty
      Node present_node = tree;
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
  }

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
      System.out.println(currNode.key + " " + currNode.frequency + " " + currNode.access_count);
      currNode = currNode.right;
    }
  }

}
