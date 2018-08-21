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
  private cross begining;
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
  public void setBegining(float la,float lo){
    begining=new cross(la,lo,0);
  }
  public void setCrosses(cross c){
    crosses.add(c);
  }

  public int getNCrosses(){return n_cross;}


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

  private ArrayList<ArrayList <string>> movements = new ArrayList<ArrayList <string>>();
  private double time_spent;
  private city city_chosen;
  private int n_cars;

  public car(n_car,the_city){
    time_spent=0;
    n_cars=n_car;
    for (int i=0;i<n_car;i++){
      ArrayList <String> n_movement=new ArrayList <String>();
      n_movement.add("0");
      movements.add(n_movement);
      city_chosen=the_city;
    }
  }


  public void addTime(int n){
    time_spent=time_spent-n;
  }

  public Bool isway(){
    for (int i=0;i<n_cars;i++){
        if(!movements[i].isEmpty()){
            int last=movements[i].size()-1;
            String id=movements[i][last]
            cross last_c=city_chosen.getcross(id);
            Set<street> s=last_c.GetChildStreetss();
            for(street t : s){
              int stimation= t.getCostTime();
              if(stimation>=time_spent){return true;}
            }
        }
      }
      return false;
  }

  public void choose(int car, double max,cross cend,cross cstart,int selected_car){
    int last=movements[car].size()-1;
    String id=movements[i][last]
    cross last_c=city_chosen.getcross(id);
    Set<street> s=last_c.GetChildStreetss();
    for(street t : s){
      double result=t.average()

      if(result >= max && time_spent >= t.getCostTime()){
        cstart=last_c;
        cend=t.getCrossEnd();
        max=result;
        selected_car=car;
      }

    }
  }

  public void travel(){
    cross c_last, c_start;
    int selected_car;
    double max=0.0;
    if(isway()){
      for (int i=0;i<n_cars;i++){
          choose(i,max,c_last,c_start,selected_car);
      }
    }
    else{

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
    float f_lat=Float.parseFloat(separate[4]);
    float f_long=Float.parseFloat(separate[0]);
    the_city.setBegining(f_lat,f_long);
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
            //System.out.println(line);
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

        the_city.imprimir();
  }
  public static void main(String[] args) throws IOException {
      city the_city = new city();
      saveData("paris_54000.txt",the_city);

  }

}
