import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.Math;


  // System.out.println(c.GetLat()+"  "+c.GetLong());
class cross{

  private  int ID;
  private float lat;
  private float lon;
  private Set<cross> child_crosses;
  private Set<street> child_streets;

  public cross(float latitude,float longitude,int Id){
    lat=latitude;
    lon=longitude;
    child_crosses=new HashSet<>();
    child_streets=new HashSet<>();
    ID=Id;
  }
  public void SetLat(float latitude){
    lat=latitude;
  }
  public void SetLong(float longitude){
    lon=longitude;
  }
  public void addChildCrosses(cross c){
      child_crosses.add(c);
  }
  public void addChildStreets(street c){
      child_streets.add(c);
  }
  public float GetLat(){
    return lat;
  }
  public float GetLong(){
    return lon;
  }
  public int GetID(){
    return ID;
  }
  public Set<cross> GetChildCrosses(){
    return child_crosses;
  }
  public Set<street> GetChildStreetss(){
    return child_streets;
  }
  public int isclosed(){
    if (child_crosses == null || child_crosses.isEmpty()){return 3;}
    else{
      if(child_crosses.size()==1){
        for(cross c: child_crosses){
          if(c == this){return 3;}
        }
      }
    }
    return 1;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o)return true;
    if(o == null || getClass() != o.getClass())return false;
    cross c=(cross)o;
    return (lat==c.GetLat() && lon == c.GetLong());
  }
  @Override
  public int hashCode(){
    return ID;
  }

  @Override
  public String toString() {
    String lat=Float.toString(this.GetLat());
    String lon=Float.toString(this.GetLong());
    String i=String.valueOf(this.GetID());
    String id=i+" "+lat+","+lon+"   ";
    for (street c: child_streets){id=id+c.toString();}
    return id;
  }
}


class street{
  private cross end;
  private int cost_time;
  private int length_street;

  private int passed_times;

  public street(cross end_point,int cost,int length){
    end=end_point;
    cost_time=cost;
    length_street=length;
    passed_times=1;
  }
  public void passed(){
    passed_times++;
  }
  public double average(){
    double solution=(length_street)/((Math.pow(10,passed_times))*cost_time*end.isclosed());
    return solution;
  }

  public int getCostTime(){
    return cost_time;
  }
  public cross getCrossEnd(){
    return end;
  }

  @Override
  public String toString(){
    String ID=String.valueOf(end.GetID())+" ";
    return (ID);
  }
}



class city{
  private int n_cross;
  private int n_street;
  private int v_time;
  private int n_cars;
  private String begining;
  private Set<cross> crosses;
  private Set<street> streets;

  public city(){
    crosses=new HashSet<>();
  }

  public void setNCross(int n){
    n_cross=n;
  }
  public void setNStreet(int n){
    n_street=n;
  }
  public void setVTime(int n){
    v_time=n;
  }
  public void setNCar(int n){
    n_cars=n;
  }
  public void setBegining(String p_start){
    begining=p_start;
  }
  public void setCrosses(cross c){
    crosses.add(c);
  }

  public int getNCrosses(){
    return n_cross;
  }
  public int getNCars(){
    return n_cars;
  }
  public int getVTime(){
    return v_time;
  }
  public String getBeginning(){
    return begining;
  }

  public void imprimir(){
      for(cross c: crosses){
        System.out.println(c.toString());
      }
  }

  public cross getcross(int id){
    for(cross c: crosses){
      if(c.GetID()==id){return c;}
    }
    return null;
  }

}
class car{

  private ArrayList<ArrayList <String> > movements = new ArrayList<ArrayList <String> >();
  private int time_spent;
  private city city_chosen;
  private int n_cars;

  public car(int n_car,city the_city){
    n_cars=n_car;
    for (int i=0;i<n_car;i++){
      ArrayList <String> n_movement=new ArrayList <String>();
      n_movement.add(the_city.getBeginning());
      movements.add(n_movement);
      city_chosen=the_city;
      time_spent=the_city.getVTime();
    }
  }


  public void addTime(int n,street s_chosen){
    time_spent=time_spent-n;
    s_chosen.passed();
  }

  public boolean isway(){
    for (int i=0;i<n_cars;i++){
        if(!movements.get(i).isEmpty()){
            int last=movements.get(i).size()-1;
            String id=movements.get(i).get(last);
            cross last_c=city_chosen.getcross(Integer.parseInt(id));
            Set<street> s=last_c.GetChildStreetss();
            for(street t : s){
              int stimation= t.getCostTime();
              if(stimation<=time_spent){return true;}
            }
        }
      }
      return false;
  }


  public void travell(){
    int selected_car;
    int cost_time;
    double max;
    cross c_chosen=null;
    street s_chosen=null;

    while(isway()){
      selected_car=9;
      cost_time=0;
      max=0.0;
      for (int car=0;car<n_cars;car++){
        String id=movements.get(car).get(movements.get(car).size()-1);
        cross last_c=city_chosen.getcross(Integer.parseInt(id));
        Set<street> s=last_c.GetChildStreetss();
        for(street t : s){
          double result=t.average();
          if(result >= max && time_spent >= t.getCostTime()){
            c_chosen=t.getCrossEnd();
            max=result;
            selected_car=car;
            cost_time=t.getCostTime();
            s_chosen=t;
          }
        }
      }
      movements.get(selected_car).add(String.valueOf(c_chosen.GetID()));
      addTime(cost_time,s_chosen);
    }
      for(ArrayList<String> m: movements){
        System.out.println(m.size());
        for(String s: m){
          System.out.println(s);
        }
      }
  }

}





public class car_schedule {

  public static void Metadata(String first_line, city the_city){
    String []separate=first_line.split("\\s");
    the_city.setNCross(Integer.parseInt(separate[0]));
    the_city.setNStreet(Integer.parseInt(separate[1]));
    the_city.setVTime(Integer.parseInt(separate[2]));
    the_city.setNCar(Integer.parseInt(separate[3]));
    the_city.setBegining(separate[4]);
  }
  public static void onedirection(cross cstart,cross cend,street s){
    cstart.addChildCrosses(cend);
    cstart.addChildStreets(s);
  }
  public static void bidirection(cross cstart,cross cend,street s1,street s2){
    cstart.addChildCrosses(cend);
    cstart.addChildStreets(s1);
    cend.addChildCrosses(cstart);
    cend.addChildStreets(s2);
  }

  public static void saveData(String file,city the_city) throws FileNotFoundException, IOException {
      String line;
      FileReader f = new FileReader(file);
      BufferedReader buffer = new BufferedReader(f);
      Metadata(buffer.readLine(),the_city);
      int n_line=1;float lat=0.0f,lon=0.0f;
      int max_line=the_city.getNCrosses();
      while((line = buffer.readLine())!=null) {
          if(n_line <= max_line){
            String []separate=line.split("\\s");
            lat=Float.parseFloat(separate[0]);
            lon=Float.parseFloat(separate[1]);
            cross c=new cross(lat,lon,n_line-1);
            the_city.setCrosses(c);
          }
          else{
            String []separate=line.split("\\s");
            int start=Integer.parseInt(separate[0]);
            int end=Integer.parseInt(separate[1]);
            int direction=Integer.parseInt(separate[2]);
            int cost=Integer.parseInt(separate[3]);
            int length=Integer.parseInt(separate[4]);
            cross cstart=the_city.getcross(start);
            cross cend=the_city.getcross(end);
            street s1=new street(cend,cost,length);
            if(direction==1){
              onedirection(cstart,cend,s1);
            }
            else{
              street s2=new street(cstart,cost,length);
              bidirection(cstart,cend,s1,s2);
            }
          }
          n_line++;
      }
      buffer.close();
  }

  public static void main(String[] args) throws IOException {
      int n_cars;
      city the_city = new city();
      saveData("paris_54000.txt",the_city);
      System.out.println(the_city.getNCars());
      car car_management=new car(the_city.getNCars(),the_city);
      car_management.travell();
  }

}
