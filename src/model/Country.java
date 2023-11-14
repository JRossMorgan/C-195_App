package model;

/**
 * Country class Country.java
 */
/**
 *
 * @author Jedediah R Morgan
 */

public class Country {
    public int countryId;
    public String name;

    public Country (int countryId, String name){
        this.countryId = countryId;
        this.name = name;
    }

    /**
     @return returns the country Id */
    public int getCountryId() {
        return countryId;
    }

    /**
     @return returns the country name */
    public String getName() {
        return name;
    }
    @Override
    /**
     @return overrides the toString method and returns the country name */
    public String toString(){
        return name;
    }
}
