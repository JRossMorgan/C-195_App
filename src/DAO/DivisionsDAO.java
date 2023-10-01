package DAO;

import DBConnection.JDBC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionsDAO {
    public static ObservableList<Divisions> getDivisions(){
        ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();
        String sql = "SELECT Division_ID, Division, Country_ID FROM first_level_divisions";

        try{
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int divisionId = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                Divisions createDivision = new Divisions(divisionId, name, countryId);
                allDivisions.add(createDivision);
            }
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return allDivisions;
    }
}
