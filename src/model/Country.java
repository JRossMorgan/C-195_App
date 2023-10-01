package model;

public class Country {
    public int countryId;
    public String name;

    public Country (int countryId, String name){
        this.countryId = countryId;
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return name;
    }
}
