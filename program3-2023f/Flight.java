// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight
{
  String departAirport;
  String arriveAirport;
  String departTime;
  String arriveTime;
  int dayArrival = 0;
  int departTime_min;
  int arriveTime_min;

  // constructor
  public Flight(String src, String dest, String stime, String dtime) {
    departAirport = new String(src);
    arriveAirport = new String(dest);
    departTime = new String(stime);
    arriveTime = new String(dtime);
    departTime_min = Integer.parseInt(stime.substring(0, 2)) * 60 + Integer.parseInt(stime.substring(2, 4));
    arriveTime_min = Integer.parseInt(dtime.substring(0, 2)) * 60 + Integer.parseInt(dtime.substring(2, 4));
    if(arriveTime_min < departTime_min){dayArrival = 1;}
  }

  public String getDepartAirport(){
    return departAirport;
  }
  public String getArriveAirport(){
    return arriveAirport;
  }
  public int getDepartTime_min(){
    return departTime_min;
  }
  public int getArriveTime_min(){
    return arriveTime_min;
  }
  public int getDay(){
    return dayArrival;
  }
  public String getDepartTime(){
    return departTime;
  }
  public String getArriveTime(){
    return arriveTime;
  }

  public void print() {
    System.out.println(departAirport + arriveAirport + departTime + arriveTime);
  }

}
