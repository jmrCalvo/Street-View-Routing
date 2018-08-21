import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


  // System.out.println(c.GetLat()+"  "+c.GetLong());
class cross{

  private  int ID;
  private float lat;
  private float lon;
  private Set<cross> child_crosses;

  public cross(float latitude,float longitude,int Id){
    lat=latitude;
    lon=longitude;
    child_crosses=new HashSet<>();
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
    String id=i+" "+lat+" "+lon;
    return id;
  }
}
class street{
  private cross start;
  private cross end;
  private int direction;
  private int cost_time;
  private int length_street;

  private int passed_times=1;

  public street(cross start_point,cross end_point,int dir,int cost,int length){

  }
  public int getClosed(){
    return 0;
  }
}



class city{
  private int n_cross;
  private int n_street;
  private double v_time;
  private int n_cars;
  private cross begining;
  private Set<cross> crosses;

  public city(){
    crosses=new HashSet<>();
  }

  public void setNCross(int n){
    n_cross=n;
  }
  public void setNStreet(int n){
    n_street=n;
  }
  public void setVTime(double n){
    v_time=n;
  }
  public void setNCar(int n){
    n_cars=n;
  }
  public void setBegining(float la,float lo){
    begining=new cross(la,lo,1);
  }
  public void setCrosses(cross c){
    crosses.add(c);
  }

  public int getNCrosses(){return n_cross;}


  public void imprimir(){
      for(cross c: crosses){
        System.out.println(c);
      }
  }

  public cross getcross(int id){
    for(cross c: crosses){
      if(c.GetID()==id){return c;}
    }
    return null;
  }

}



public class car_schedule {

  public static void Metadata(String first_line, city the_city){
    String []separate=first_line.split("\\s");
    the_city.setNCross(Integer.parseInt(separate[0]));
    the_city.setNStreet(Integer.parseInt(separate[1]));
    the_city.setVTime(Double.parseDouble(separate[2]));
    the_city.setNCar(Integer.parseInt(separate[3]));
    float f_lat=Float.parseFloat(separate[4]);
    float f_long=Float.parseFloat(separate[0]);
    the_city.setBegining(f_lat,f_long);
  }

  public static void saveData(String file) throws FileNotFoundException, IOException {
      String line;
      FileReader f = new FileReader(file);
      BufferedReader buffer = new BufferedReader(f);
      city the_city = new city();
      Metadata(buffer.readLine(),the_city);
      int n_line=1;float lat=0.0f,lon=0.0f;
      int max_line=the_city.getNCrosses();
      while((line = buffer.readLine())!=null) {
          if(n_line <= max_line){
            String []separate=line.split("\\s");
            lat=Float.parseFloat(separate[0]);
            lon=Float.parseFloat(separate[1]);
            cross c=new cross(lat,lon,n_line);
            the_city.setCrosses(c);
          }
          else{
            System.out.println(line);
          }
          n_line++;
      }
      buffer.close();

      the_city.imprimir();
  }

  public static void main(String[] args) throws IOException {
      saveData("paris_54000.txt");
  }

}
