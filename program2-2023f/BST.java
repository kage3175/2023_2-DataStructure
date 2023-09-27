// (Nearly) Optimal Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)

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

    public Node(String data){
        key = new String(data);
        left = null;
        right = null;
        access_count = 0;
        frequency = 0;
    }
  }

  public BST() {
    root = null;
  }

  public int size() { }
  public void insert(String key) {
    if(root == null){
        root = new Node(key);
    }
  }
  public boolean find(String key) { }

  public int sumFreq() { }
  public int sumProbes() { }
  public int sumWeightedPath() { }
  public void resetCounters() { }

  public void nobst() { }	// Set NOBSTified to true.
  public void obst() { }	// Set OBSTified to true.
  public void print() { }

}

