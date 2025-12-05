package models;

public class City {
    private int id;
    private String city;
    private int countryId;

    public City(int id, String city, int countryId){
        this.id = id;
        this.city = city;
        this.countryId = countryId;
    }

    public int getId(){ return id; }
    public String getCity(){ return city; }
    public int getCountryId(){ return countryId; }
}
