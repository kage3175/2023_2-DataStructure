// Bongki Moon (bkmoon@snu.ac.kr)

import java.io.*;
import java.util.*;

public class Airport {
  int connectTime_min = 0;
  String connect;
  String code;

  public Airport(String port, String connectTime) {
    connect = new String(connectTime);
    connectTime_min = Integer.parseInt(connectTime.substring(0, 2)) * 60 + Integer.parseInt(connectTime.substring(2, 4));
    code = new String(port);
  }	// constructor

  public String getAirportName(){
    return code;
  }
  public String getConnect(){
    return connect;
  }
  public int getConnectTimeMin(){
    return connectTime_min;
  }
  public void print() {
    System.out.println(code + " " + connect);
  }
}
