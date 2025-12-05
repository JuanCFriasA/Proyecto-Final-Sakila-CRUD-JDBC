package controllers;

import data.*;
import models.*;
import java.sql.*;
import java.util.*;

public class CityManager extends DataContext<City>{

    public boolean post(City o){
        try{
            var ps=prepare("INSERT INTO city(city, country_id) VALUES(?, ?)");
            ps.setString(1, o.getCity());
            ps.setInt(2, o.getCountryId());
            ps.executeUpdate(); 
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public City get(int id){
        try{
            var ps=prepare("SELECT * FROM city WHERE city_id=?");
            ps.setInt(1,id);
            var rs=ps.executeQuery();
            if(rs.next())
                return new City(
                    rs.getInt("city_id"),
                    rs.getString("city"),
                    rs.getInt("country_id")
                );
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<City> get(){
        List<City> list=new ArrayList<>();
        try{
            var ps=prepare("SELECT * FROM city");
            var rs=ps.executeQuery();
            while(rs.next()){
                list.add(
                    new City(
                        rs.getInt("city_id"),
                        rs.getString("city"),
                        rs.getInt("country_id")
                    )
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean put(City o){
        try{
            var ps=prepare("UPDATE city SET city=?, country_id=? WHERE city_id=?");
            ps.setString(1, o.getCity());
            ps.setInt(2, o.getCountryId());
            ps.setInt(3, o.getId());
            ps.executeUpdate(); 
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id){
        try{
            var ps=prepare("DELETE FROM city WHERE city_id=?");
            ps.setInt(1,id);
            ps.executeUpdate();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
@Override
public List<City> get(String filter){
    List<City> list = new ArrayList<>();
    try{
        var ps = prepare("SELECT * FROM city WHERE city LIKE ?");
        ps.setString(1, "%" + filter + "%");
        var rs = ps.executeQuery();

        while(rs.next()){
            list.add(new City(
                rs.getInt("city_id"),
                rs.getString("city"),
                rs.getInt("country_id")
            ));
        }
    }catch(Exception e){ e.printStackTrace(); }
    return list;
}

}
