// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.LinkedList;

public class Itinerary
{

  LinkedList<Flight> planned = null;

  // constructor

  public Itinerary(LinkedList<Flight> ticket) {
    planned = ticket;
  }

  public Itinerary() {
    planned = null;
  }

  public boolean isFound() { //False if plan is  empty(null)
    return planned!=null;
  }

  public void print() {
    if(planned == null){
      System.out.println("No Flight Schedule Found.");
      return;
    }
    int length = planned.size();
    for(int i = 0;i < length;i++){
      Flight f = planned.removeLast();
      System.out.print("[" + f.getDepartAirport()+"->"+f.getArriveAirport()+":"+f.getDepartTime()+"->"+f.getArriveTime() + "]");
    }
    System.out.println("");
  }

}
