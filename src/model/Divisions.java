package model;

public class Divisions {
    public int divisionId;
    public String name;
    public int countryId;

    public Divisions(int divisionId, String name, int countryId){
        this.divisionId = divisionId;
        this.name = name;
        this.countryId = countryId;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getName() {
        return name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    @Override
    public String toString(){
        return name;
    }
}
