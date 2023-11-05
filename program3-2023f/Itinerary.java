// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.LinkedList;

public class Itinerary
{

  String[][] planned;
  // constructor

  public class Time{
    int hr;
    int min;
    int time;
    int day;
    public Time(String t, int d){
      hr = Integer.parseInt(t) / 100;
      min = Integer.parseInt(t) % 100;
      time = min + hr * 60;
      day = d;
    }
  }

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
