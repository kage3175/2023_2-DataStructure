// (Nearly) Optimal Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)
import java.util.Stack;
import java.io.*;

public class BST { // Binary Search Tree implementation

  protected boolean NOBSTified = false;
  protected boolean OBSTified = false;
  protected Node root;
  public int size_tree = 0;

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
        frequency = 1;
        level = 0;
        height = 1;
    }
  }

  public BST() {
    root = null;
  }

  public boolean find(String key) {
    if(root == null) {return false;}
    Node currNode = root;
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

  public void insert(String key) {
    if(root == null){ // When tree is empty
      root = new Node(key);
      size_tree++;
      root.level = 1;
    } 
    else{ //When tree is not empty
      Node present_node = root;
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
  } //End of print

  public Node buildOBST(int[][] r ,Node[] nodelst, int left, int right, int level){
    if(right < 1 || left > size_tree || left > right){ //End case
      return null;
    }
    int index;
    index = r[left][right];
    Node node = nodelst[index];
    node.level = level;
    node.left = buildOBST(r, nodelst, left, index - 1, level+1);
    node.right = buildOBST(r, nodelst, index + 1, right, level+1);
    return node;
    //node.left = buildOBST(r, nodelst, left, right);
    //return node;
  }

  public Node buildNobst(long[] accumule, Node[] nodelst, int left, int right, int level){
    if(left > right) return null;
    if(left == right){
      Node node = nodelst[left];
      node.level = level;
      node.left = null;
      node.right = null;
      return node;
    }
    long min = accumule[right];
    int k = left;
    long temp = accumule[k-1] + accumule[k] - accumule[right];
    while(temp < min && temp > -min){
      k++;
      if(k > size_tree) break;
      min = (temp > 0) ? temp:-temp;
      temp = accumule[k-1] + accumule[k] - accumule[right];

    }
    k--;
    Node node = nodelst[k];
    node.level = level;
    node.left = buildNobst(accumule, nodelst, left, k-1, level+1);
    node.right = buildNobst(accumule, nodelst, k+1, right, level+1);
    return node;
  }

  public void nobst() {
    NOBSTified = true;
    Node[] nodelst = new Node[size_tree+1]; // Reading inorder, so it will be sorted by its key
    long[] accumule = new long[size_tree+1];
    accumule[0] = 0;
    Stack<Node> stack = new Stack<>();
    Node currNode = root;
    int cnt=1;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      nodelst[cnt++] = currNode;
      currNode = currNode.right;
    }
    for(int i = 1; i<= size_tree;i++){
      accumule[i] = accumule[i-1] + nodelst[i].frequency;
    }
    root = buildNobst(accumule, nodelst, 1, size_tree, 1);

  }	// Set NOBSTified to true.
  public void obst() {
    OBSTified = true;
    Node[] nodelst = new Node[size_tree+1]; // Reading inorder, so it will be sorted by its key
    Stack<Node> stack = new Stack<>();
    Node currNode = root;
    int cnt=1;
    while(currNode != null || !stack.empty()){
      while(currNode != null){
        stack.push(currNode);
        currNode = currNode.left;
      }
      currNode = stack.pop();
      nodelst[cnt++] = currNode;
      currNode = currNode.right;
    }
    long[][] c = new long[size_tree+2][size_tree+1];
    int[][] r = new int[size_tree+2][size_tree+1]; //r contains the index of corresponding node
    for(int i = 0; i < size_tree+2; i++){ //initialize
      for(int j = 0; j < size_tree+1; j++){
        if(i == j && i != 0) {
          c[i][j] = nodelst[i].frequency;
          r[i][j] = i;
        }
        else {
          c[i][j] = 0;
          r[i][j] = 0;
        }
      }
    }
    long min_value = 0;
    long value = 0;
    long sigmap = 0;
    for(int gap = 1; gap < size_tree; gap++){ //대각선으로 채우기
      for (int left = 1; left <= size_tree - gap;left++){ //C(left, left+gap)
        min_value = c[left][left-1] + c[left+1][left+gap]; //k = left case
        r[left][left+gap] = left;
        for(int k = left + 1;k <= left + gap;k++){
          value = c[left][k - 1] + c[k+1][left + gap];
          if(value < min_value){ //min case
            min_value = value;
            r[left][left+gap] = k;
          }
        }
        sigmap = 0;
        for(int k=left;k<=left+gap;k++){
          sigmap += nodelst[k].frequency;
        }
        c[left][left+gap] = min_value + sigmap;
        //C[left][left+gap] = min(C[i][k-1] + C[k+1][j]) + sigma p
      }
    }

    /*for(int i = 0; i < size_tree+2;i++){
      for(int j = 0; j < size_tree+1;j++){
        System.out.print(c[i][j] + "\t");
      }
      System.out.println("");
    }
    System.out.println("");
    for(int i = 0; i < size_tree+2;i++){
      for(int j = 0; j < size_tree+1;j++){
        System.out.print(r[i][j] + "\t");
      }
      System.out.println("");
    }*/
    //System.out.println(nodelst[r[1][4]].key);
    root = buildOBST(r, nodelst, 1, size_tree, 1);
    //System.out.println(root.left.key+ " " + root.key);

    //C[i][j] = min(C[i][k-1] + C[k+1][j]) + sigma p
  }	// Set OBSTified to true.

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
  } //End of resetCounters

  public int size() {
    return size_tree;
  } //End of size

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
  } //End of sumFreq

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
  } //End of sumProbes

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
      /*if(NOBSTified){
        System.out.println(currNode.key);
      }*/
      wp += currNode.level * currNode.frequency;
      currNode = currNode.right;
    }
    return wp;
  } //End of sumWeightedPath

}
  

