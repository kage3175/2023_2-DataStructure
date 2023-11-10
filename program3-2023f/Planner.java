// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Stack;

public class Planner {// All time variable is minute-based, from 0 to 1439(which is 23hrs 59min)
  HashMap<Airport, Integer> mapAirport = new HashMap<Airport, Integer>();
  HashMap<String, Airport> mapAirportName = new HashMap<String, Airport>();
  Airport[] arrAirport;
  Time[] earliestArrive;
  Flight[] previous;
  LL[] adListFlight;
  boolean[] visited;
  int numAirport;

  private class LL{
    Flight key;
    LL next;
    public LL(Flight f){
      key = f;
      next = null;
    }
    public void add(Flight f){
      LL tmp = this;
      while(tmp.next != null){
        tmp = tmp.next;
      }
      tmp.next = new LL(f);
    }
    public Flight getKey(){
      return key;
    }
  }

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
      time_onlyhrmin = min + 60*hr;
    }
  }

  // constructor
  public Planner(LinkedList<Airport> portList, LinkedList<Flight> fltList) {
    int cnt = 0;
    int length = portList.size();
    arrAirport = new Airport[length];
    earliestArrive = new Time[length];
    previous = new Flight[length];
    adListFlight = new LL[length];
    visited = new boolean[length];
    Arrays.fill(visited, false);
    numAirport = length;
    for(Airport airport : portList){
      mapAirport.put(airport, cnt);
      mapAirportName.put(airport.getAirportName(), airport);
      arrAirport[cnt] = airport;
      cnt++;
    }
    for(Flight flight : fltList){
      String apt = flight.getDepartAirport();
      int idx = mapAirport.get(mapAirportName.get(apt));
      if(adListFlight[idx] == null){
        adListFlight[idx] = new LL(flight);
      }else{
        adListFlight[idx].add(flight);
      }
    }
  }

  public Time possibleArrive(Flight flight, Time depart, int day){
    Time result;
    //Airport start = mapAirportName.get(flight.getDepartAirport());

    if(depart.getTimeHrMin() > flight.getDepartTime_min()){ //have to get next day ticket
      result = new Time(flight.getArriveTime(),day+1+flight.getDay());
    }else{
      result = new Time(flight.getArriveTime(),day+flight.getDay());
    }
    return result;
  }

  public int findMin(){
    Time arriveTime = null;
    int minIdx = -1;
    for(int i = 0; i < numAirport;i++){
      if(visited[i] || earliestArrive[i] == null){continue;}
      //if(earliestArrive[i] == null){continue;}
      if(arriveTime==null){
        arriveTime = earliestArrive[i];
        minIdx = i;
      }else if(arriveTime.getTime() > earliestArrive[i].getTime()){
        arriveTime = earliestArrive[i];
        minIdx=i;
      }
    }
    return minIdx;
  }

  public void updateArriveTime(Time currTime, Airport currAirport){
    int idx = mapAirport.get(currAirport);
    LL tmp = adListFlight[idx];
    while(tmp!=null){
      Flight f = tmp.getKey();
      Time arrTime = possibleArrive(f, currTime, currTime.getDay());
      idx = mapAirport.get(mapAirportName.get(f.getArriveAirport()));
      if(earliestArrive[idx]==null){ // If earliestArrive of the arriving airport is not updated at all yet
        earliestArrive[idx] = arrTime;
        previous[idx] = f;
        //System.out.println("New: " + f.getArriveAirport() + " " + arrTime.getTime());
      }else if(earliestArrive[idx].getTime() > arrTime.getTime()){ // If update is needed
        earliestArrive[idx] = arrTime;
        previous[idx] = f;
        //System.out.println("Update: " + f.getArriveAirport() + " " + arrTime.getTime());
      }
      tmp = tmp.next;
    }
  }

  public Itinerary Schedule(String start, String end, String departure) {
    Itinerary iti = null;
    Airport startAirport = mapAirportName.get(start);
    Airport endAirport = mapAirportName.get(end);
    int startIdx = 0;
    int endIdx = 0;
    try{
      startIdx = mapAirport.get(startAirport);
    }catch(NullPointerException e){
      return new Itinerary(null);
    }
    try{
      endIdx = mapAirport.get(endAirport);
    }catch(NullPointerException e){
      return new Itinerary(null);
    }
    Time departTime = new Time(departure, 0);
    Arrays.fill(earliestArrive, null);
    Arrays.fill(visited, false);
    earliestArrive[startIdx] = departTime;
    visited[startIdx] = true;
    //LL tmp = adListFlight[startIdx];
    /*while(tmp!=null){
      Flight f = tmp.getKey();
      Time arrTime = possibleArrive(f, departTime, 0);
      int idx =mapAirport.get(mapAirportName.get(f.getArriveAirport()));
      if(earliestArrive[idx]==null){ // If earliestArrive of the arriving airport is not updated at all yet
        earliestArrive[idx] = arrTime;
      }else if(earliestArrive[idx].getTime() > arrTime.getTime()){ // If update is needed
        earliestArrive[idx] = arrTime;
      }
      tmp = tmp.next;
    } //Initialize End*/
    updateArriveTime(departTime, startAirport);
    for(int i = 0; i < numAirport-1;i++){ //dijkstra
      //idx = findMin by (arriveTime of arriving airport)
      // if idx == endIdx, then we found the fastest arrival time.
      // for all edges that start from arrAirport[idx], update earliestArrive values
      // previous also should be updated
      int idx = findMin();
      if(idx==-1){
        //When no such plan is available
        iti = null;
        break;
      }else if(idx == endIdx){
        //When the plan has been made
        
        Flight tmp = previous[idx];
        LinkedList<Flight> ticket = new LinkedList<Flight>();
        ticket.add(tmp);
        while(true){
          if(tmp.getDepartAirport().equals(startAirport.getAirportName())){break;}
          Airport departAirport = mapAirportName.get(tmp.getDepartAirport());
          tmp = previous[mapAirport.get(departAirport)];
          ticket.add(tmp);
        }
        iti = new Itinerary(ticket);
        break;
      }else{
        visited[idx] = true;
        Airport currAirport = arrAirport[idx];
        Time currTime = earliestArrive[idx];
        //System.out.println("Airport: " + currAirport.getAirportName() + " Before: " + currTime.getTime());
        currTime.add(currAirport.getConnect()); //Add connect time
        //System.out.println(" After: " + currTime.getTime());
        updateArriveTime(currTime, currAirport);
      }
    }

    return iti;
  }

}

