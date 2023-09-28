import java.util.Stack;

public class Test_AVL extends Test{
  public Test_AVL(){
    tree = null;
  }

  public void updateHeight(Node tree){
    tree.height = 1 + Math.max(height(tree.left), height(tree.right));
  }

  public void insert(String key){
    if(tree == null){ // When tree is empty
      tree = new Node(key);
      size_tree++;
      tree.frequency++;
      tree.level = 1;
  } else { //When tree is not empty
    Stack<Node> stack = new Stack<>();
    Node present_node = tree;
    Node parent = null;
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
          stack.push(present_node);
          break;
        }
        else{
          parent = present_node;
          stack.push(present_node)
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
          parent = present_node;
          stack.push(present_node)
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
}
