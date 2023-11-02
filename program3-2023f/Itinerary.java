// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.LinkedList;

public class Itinerary
{

  String[][] planned;
  // constructor
  Itinerary(String[][] plan) {
    planned = plan.clone();
  }

  public boolean isFound() { //True if plan is  empty(null)
    return planned==null;
  }

  public void print() {
    int length = planned.length;
    for(int i = 0;i < length;i++){
      System.out.print(planned[i][0]+"->"+planned[i][1]+":"+planned[i][2]+"->"+planned[i][3]);
    }
  }

}
