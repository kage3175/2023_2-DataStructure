package test_and_practice;
import java.util.Stack;

public class Infix_to_tree {
  public static Node root = null;

  public static class Node{
    char key;
    Node left;
    Node right;

    public Node(char key){
      this.key = key;
      this.left = null;
      this.right = null;
    }
  }
  public static void main(String[] args){
    Stack<Node> stack_op = new Stack<>();
    Stack<Node> stack_num = new Stack<>();
    char[] equation = {'(', '(','(','(','3','+','4',')','*','5',')','-','6',')','+','(','3','+','4',')'};

    for (char operand : equation){
      if(operand == '('){
        continue;
      }
      else if(operand == ')'){
        Node op = stack_op.pop();
        op.right = stack_num.pop();
        op.left = stack_num.pop();
      }
      else if(operand == '+' || operand == '-' || operand == '*' || operand == '/'){
        stack_op.push(new Node(operand));
      }
      else if((int)(operand - '0') >= 0 && (int)(operand - '0') <= 9){
        stack_num.push(new Node(operand));
      }
    }

    root = stack_num.pop();
    inOrder(root);
  }

  public static void inOrder(Node root){
    if(root == null) return;
    inOrder(root.left);
    System.out.print(root.key);
    inOrder(root.right);
  }
}
