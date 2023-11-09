// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.LinkedList;

public class Planner {// All time variable is minute-based, from 0 to 1439(which is 23hrs 59min)
  LinkedList<Airport> airportlist;
  LinkedList<Flight> flightlist;

  public class Time{
    int hr;
    int min;
    int time;
    int day;
    public Time(String t, int d){
      hr = Integer.parseInt(t) / 100;
      min = Integer.parseInt(t) % 100;
      day = d;
      time = min + hr * 60 + d * 1440;
    }
  }

  // constructor
  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
    airportlist = portList;
    flightlist = fltList;
  }

  public Itinerary Schedule(String start, String end, String departure) {}

}

