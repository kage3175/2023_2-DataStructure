// Bongki Moon (bkmoon@snu.ac.kr)

public class Flight
{
  String departAirport;
  String arriveAirport;
  String departTime;
  String arriveTime;
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
  }

  public void print() {
    System.out.println(departAirport + arriveAirport + departTime + arriveTime);
  }

}
