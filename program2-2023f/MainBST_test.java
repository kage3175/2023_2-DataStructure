
// Test Main for (Nearly) Optimal BST and AVL.
//	File IO is done through TextInputStream class.
//
// Bongki Moon (bkmoon@snu.ac.kr), Sep/21/2014.

import java.io.*;
import java.lang.management.*;

public class MainBST_test {
  public static ThreadMXBean TMB;

  public static void main(String args[]) throws IOException
  {
    long cputime;

    if (args.length != 2) {
	System.err.println("Usage: java MainBst train-file query-file");
	System.exit(0);
    }

    TMB = ManagementFactory.getThreadMXBean();
    if (! TMB.isThreadCpuTimeSupported()) {
	System.out.println("ThreadCpuTime is not supported.");
	System.exit(0);
    }

    // (1) Create three plain BSTs and an AVL from the train set.
    BST bst = new BST();
    buildBST(bst, args[0]);

    System.out.println("Number of words in the BST: "+bst.size()
		+" (number of insertions: "+bst.sumFreq()+")");

    // (2) Probe the plain BST and AVL for the words in query set.
    System.out.println("Sum of Weighted Path Lengths (BST): "
		+bst.sumWeightedPath());
    bst.resetCounters();
    probeBST(bst,args[1]);

    Runtime runtime = Runtime.getRuntime();
    System.out.println("Memory consumption: "
		+ (runtime.totalMemory() - runtime.freeMemory()) + " bytes");
  }

  public static void buildBST(BST bst, String input)
  {
    TextInputStream sfs = new TextInputStream(input);

    long cputime = TMB.getCurrentThreadCpuTime();
    while(sfs.ready()) bst.insert(sfs.readWord());
    cputime = TMB.getCurrentThreadCpuTime() - cputime;

    bst.print();
    String bstType = (bst instanceof AVL)? "AVL" : "BST";
    System.out.println("CPU time to build a(n) "+bstType+": "
				+(cputime/1000000)+" millisec");
  }

  public static void probeBST(BST bst, String keys)
  {
    TextInputStream qfs = new TextInputStream(keys);
    int	notfound=0;

    long cputime = TMB.getCurrentThreadCpuTime();
    while(qfs.ready()) {
	String queryWord = qfs.readWord();
	if (bst.find(queryWord)==false) {
	    System.out.println("The word `"+queryWord+"' not found.");
	    notfound++;
	}
    }
    cputime = TMB.getCurrentThreadCpuTime() - cputime;

    bst.print();
    String bstType = "BST";
    if (bst instanceof AVL) bstType = "AVL";
    else if (bst.NOBSTified == true) bstType = "NOBST";
    else if (bst.OBSTified == true) bstType = "OBST";

    System.out.println("Total number of node accesses ("+bstType+"): "
		+bst.sumProbes()+" (failed searches: "+notfound+")");
    System.out.println("CPU time for searching keys ("+bstType+"): "
		+(cputime/1000000)+" millisec");
  }
}
