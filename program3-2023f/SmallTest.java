public class SmallTest {

  public static class Time{
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
  public static void main(String args[]){
    Time t = new Time("1515", 0);
    System.out.println(t.getTime());
    t.add("0050");
    System.out.println(t.getTime());
  }
}
