package com.project;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class City implements Comparator<City> {
    public ArrayList<City> cities;
    private String state;
    private String name;
    private double Latitude;
    private double Longitude;
    private String summary;
    private double temperature;
    private double humidity;
    private int UVIndex;

    public City(String state,String name, double latitude, double longitude, double temperature, double humidity, int UVIndex){
        this.state = state;
        this.name = name;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.temperature = temperature;
        this.humidity = humidity;
        this.UVIndex = UVIndex;
    }
    public City(String state, String name, double latitude, double longitude) {
        this.state = state;
        this.name = name;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.temperature = temperature;
        this.humidity = humidity;
        this.UVIndex = UVIndex;
    }

    public City(){
        this.state= "";
        this.name = "";
        this.Latitude = 0;
        this. Longitude = 0;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getUVIndex() {
        return UVIndex;
    }


    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {

        this.humidity = humidity;
    }

    public double getTemperature() {

        return temperature;
    }

    public double getHumidity() {

        return humidity;
    }
    public void setState(String state) {

        this.state = state;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setLatitude(double latitude) {

        Latitude = latitude;
    }

    public void setLongitude(double longitude) {

        Longitude = longitude;
    }

    public String getState() {

        return state;
    }

    public String getName() {

        return name;
    }

    public double getLatitude() {

        return Latitude;
    }

    public double getLongitude() {

        return Longitude;
    }

    public void setUVIndex(int UVIndex) {
        this.UVIndex = UVIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Double.compare(city.getLatitude(), getLatitude()) == 0 &&
                Double.compare(city.getLongitude(), getLongitude()) == 0 &&
                getState().equals(city.getState()) &&
                getName().equals(city.getName());
    }

    @Override
    public int compare(City o1, City o2) {
        return (int)((int)o1.getTemperature()-o2.getTemperature());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getName(), getLatitude(), getLongitude());
    }
}
