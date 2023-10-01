package DAO;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {
    public static ObservableList<Country> getCountries(){
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        String sql = "SELECT Country_ID, Country FROM countries";

        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int countryId = rs.getInt("Country_ID");
                String name = rs.getString("Country");
                Country createCountry = new Country(countryId, name);
                allCountries.add(createCountry);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return allCountries;
    }
}
