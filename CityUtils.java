package com.project;

import org.apache.commons.io.IOUtils;
import java.io.*;
import java.net.URL;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;

public class CityUtils extends City {
    public ArrayList<City> cities= new ArrayList<City>();
    public static final String CITIES_URL = "https://gist.githubusercontent.com/tacksoo/07ec0e01122d02f30ef02b3a8418391f/raw/a78acee82835ac9af0b8595651102f16362d0c62/states.csv";
    private static final String DARK_SKY_URL = "weather.json";

    public CityUtils() {
        String str = loadCitiesInfo();
        createCities(str);
        createWeather();
    }

    public void createCities(String str) {
        String[] lines = str.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] words = lines[i].split(",");
            double lat = Double.parseDouble(words[2]);
            double longitude = Double.parseDouble(words[3]);
            City city = new City(words[0], words[1], lat, longitude);
            cities.add(city);
        }
    }

    public void createWeather() throws ClassCastException{
        for(int i=0; i<cities.size();i++){
            try {
                double latitude = cities.get(i).getLatitude();
                double longitude = cities.get(i).getLongitude();
                JSONReadertoFile("https://api.darksky.net/forecast/4501c47130723b8b94883bcdc68ce5db/"+ latitude + "," + longitude );
                File json = new File(DARK_SKY_URL);
                ObjectMapper mapper = new ObjectMapper();
                MapType type = mapper.getTypeFactory().constructMapType(Map.class, String.class, Object.class);
                Map<String, Object> map = mapper.readValue(json, type);
                Map<String, Object> currently = (Map<String, Object>) map.get("currently");
                double humidity = (double) currently.get("humidity");
                String summary = (String) currently.get("icon");
                double temperature = (double) currently.get("temperature");
                cities.get(i).setHumidity(humidity);
                cities.get(i).setTemperature(temperature);
                cities.get(i).setSummary(summary);
                cities.get(i).setUVIndex((Integer) currently.get("uvIndex"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String loadCitiesInfo() {
        String str = "";
        try {
            URL url = new URL(CITIES_URL); //this converts states.csv into a usable string so that CreateCities can put it into an array
            InputStream is = url.openStream();
            str = IOUtils.toString(is, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void JSONReadertoFile(String blackSky) { //this method goes to the blackSky url and reads then prints it to weather.json
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(blackSky).openStream());
             FileOutputStream fileOS = new FileOutputStream("weather.json")) {
            byte data[] = new byte[1024];
            int byteContent;
            while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
                fileOS.write(data, 0, byteContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printRainingCities() { //this method prints cities from arraylist that are raining
        int count = 0;
        for( int i = 0; i<cities.size(); i++) {
            if(cities.get(i).getSummary().equalsIgnoreCase("rain")) {
                System.out.println(cities.get(i).getName() + ": "+ cities.get(i).getSummary());
                count++;
            }
        }
        if (count==0){
            System.out.println("There are currently no raining cities.");
        }
    }
}
class TempSort implements Comparator<City>{ //class method to sort by temp
    public int compare(City o1, City o2){
        return (int)((int)o1.getTemperature()-o2.getTemperature());
    }
}

class UVSort implements Comparator<City>{ //comparator to sort by uv index
    public int compare(City o1, City o2) {
        return (int)((int)o1.getUVIndex()-o2.getUVIndex());
    }
}