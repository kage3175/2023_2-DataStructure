// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.LinkedList;

public class Itinerary
{

  LinkedList<Flight> planned;

  // constructor

  public class Time{
    int hr;
    int min;
    int time;
    int day;
    int time_onlyhrmin;
    public Time(String t, int d){
      hr = Integer.parseInt(t) / 100;
      min = Integer.parseInt(t) % 100;
      day = d;
      time = min + hr * 60 + d * 1440;
      time_onlyhrmin = min + hr*60;
    }
    public int getDay(){
      return day;
    }
    public int getTime(){
      return time;
    }
    public int getTimeHrMin(){
      return time_onlyhrmin;
    }
    public void add(String t){
      int hrInc = Integer.parseInt(t) / 100;
      int minInc = Integer.parseInt(t) % 100;
      min += minInc;
      if(min >= 60){
        min-=60;
        hrInc++;
      }
      hr += hrInc;
      if(hr >= 24){
        hr-=24;
        day+=1;
      }
      time = min + hr * 60 + day * 1440;
    }
  }

  Itinerary(LinkedList<Flight> ticket) {
    planned = ticket;
  }

  public boolean isFound() { //True if plan is  empty(null)
    return planned==null;
  }

  public void print() {
    int length = planned.size();
    for(int i = 0;i < length;i++){
      Flight f = planned.removeLast();
      System.out.print("[" + f.getDepartAirport()+"->"+f.getArriveAirport()+":"+f.getDepartTime()+"->"+f.getArriveTime() + "]");
    }
  }

}
