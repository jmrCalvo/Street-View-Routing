import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


class cross{

  private float lat;
  private float lon;
  private TreeSet crosses;

  public cross(float latitude,float longitude){
    lat=latitude;
    lon=longitude;
    crosses=new TreeSet();
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
  private float n_cross;
  private float n_street;
  private double v_time;
  private int n_cars;
  private cross begining;

  public city(){}

  public void setNCross(float n){
    n_cross=n;
  }
  public void setNStreet(float n){
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

}


public class car_schedule {





  public static void Metadata(String first_line, city the_city){
    String []separate=first_line.split("\\s");
    the_city.setNCross(Float.parseFloat(separate[0]));
    the_city.setNStreet(Float.parseFloat(separate[1]));
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
      city the_city=new city();
      Metadata(buffer.readLine(),the_city);
      while((line = buffer.readLine())!=null) {
          System.out.println(line);
          cross c=new cross(3.5f,5.6f);
      }
      buffer.close();
  }

  public static void main(String[] args) throws IOException {
      saveData("paris_54000.txt");
  }

}
