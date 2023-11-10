import java.util.Scanner;
import java.util.LinkedList;

public class Test {
  LinkedList<String> test;
  public static void main(String args[]){
    /*Scanner sc = new Scanner(System.in);
    String nameAirport; String connectTime;
    String startAirport; String destAirport; String startTime; String destTime;
    
    nameAirport = sc.next();
    connectTime = sc.next();
    Airport testAirport = new Airport(nameAirport, connectTime);

    System.out.println(testAirport.getAirportName() + " " + testAirport.getConnect());
    System.out.println(testAirport.getConnectTimeMin());
    startAirport = sc.next();
    destAirport = sc.next();
    startTime = sc.next();
    destTime = sc.next();
    Flight flight = new Flight(startAirport, destAirport, startTime, destTime);


    System.out.println(flight.getDepartAirport() + " " + flight.getArriveAirport() + " " + flight.getDepartTime() + " " + flight.getArriveTime() + " " + flight.getDay());

    sc.close();*/



  }

  public void getTest(LinkedList<String> t){
    test = t;
  }

  public Test(){

  }

  public void print(){
    LinkedList<String> tmp = test;
    while(!test.isEmpty()){
      System.out.print(tmp.removeFirst() + " ");
    }
  }
}
