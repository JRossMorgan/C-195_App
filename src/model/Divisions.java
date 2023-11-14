package model;

/**
 * Divisions class Divisions.java
 */
/**
 *
 * @author Jedediah R Morgan
 */

public class Divisions {
    public int divisionId;
    public String name;
    public int countryId;

    public Divisions(int divisionId, String name, int countryId){
        this.divisionId = divisionId;
        this.name = name;
        this.countryId = countryId;
    }

    /**
     @return returns the division Id */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     @return returns the division name */
    public String getName() {
        return name;
    }

    /**
     @return returns the division's country Id */
    public int getCountryId() {
        return countryId;
    }

    @Override
    /**
     @return overrides the toString method and returns the division name */
    public String toString(){
        return name;
    }
}
