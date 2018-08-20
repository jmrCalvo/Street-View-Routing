import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


class cross{

  private float lat;
  private float lon;
  private TreeSet child_crosses;

  public cross(float latitude,float longitude){
    lat=latitude;
    lon=longitude;
    child_crosses=new TreeSet();
  }
  public void SetLat(float latitude){
    lat=latitude;
  }
  public void SetLong(float longitude){
    lon=longitude;
  }
  public float GetLat(){
    return lat;
  }
  public float GetLong(float longitude){
    return lon;
  }

}

class city{
  private int n_cross;
  private int n_street;
  private double v_time;
  private int n_cars;
  private cross begining;
  private TreeSet crosses;

  public city(){
    crosses=new TreeSet();
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
    begining=new cross(la,lo);
  }
  public void setCrosses(cross c){
    crosses.add(c);
  }

  public int getNCrosses(){return n_cross;}

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
      int n_line=1;
      int max_line=the_city.getNCrosses();
      while((line = buffer.readLine())!=null) {
          if(n_line <= max_line){System.out.println(line);}
          // cross c=new cross(3.5f,5.6f);
          n_line++;
      }
      buffer.close();
  }

  public static void main(String[] args) throws IOException {
      saveData("paris_54000.txt");
  }

}
