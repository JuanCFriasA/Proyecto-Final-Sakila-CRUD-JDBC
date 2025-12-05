package controllers;

import data.*;
import models.*;
import java.sql.*;
import java.util.*;

public class RentalManager extends DataContext<Rental> {

    // POST
    @Override
    public boolean post(Rental o){
        try{
            var ps = prepare("INSERT INTO rental(inventory_id, customer_id, staff_id) VALUES(?, ?, ?)");
            ps.setInt(1, o.getInventoryId());
            ps.setInt(2, o.getCustomerId());
            ps.setInt(3, o.getStaffId());
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // GET BY ID
    @Override
    public Rental get(int id){
        try{
            var ps = prepare("SELECT * FROM rental WHERE rental_id=?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();

            if(rs.next()){
                return new Rental(
                    rs.getInt("rental_id"),
                    rs.getInt("inventory_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("staff_id")
                );
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // GET ALL
    @Override
    public List<Rental> get(){
        List<Rental> list = new ArrayList<>();
        try{
            var ps = prepare("SELECT * FROM rental");
            var rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Rental(
                    rs.getInt("rental_id"),
                    rs.getInt("inventory_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("staff_id")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // GET WITH FILTER 
    @Override
    public List<Rental> get(String filter){
        List<Rental> list = new ArrayList<>();
        try{
            var ps = prepare("SELECT * FROM rental WHERE customer_id LIKE ? OR inventory_id LIKE ? OR staff_id LIKE ?");
            ps.setString(1, "%" + filter + "%");
            ps.setString(2, "%" + filter + "%");
            ps.setString(3, "%" + filter + "%");

            var rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Rental(
                    rs.getInt("rental_id"),
                    rs.getInt("inventory_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("staff_id")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean put(Rental o){
        try{
            var ps = prepare("UPDATE rental SET inventory_id=?, customer_id=?, staff_id=? WHERE rental_id=?");
            ps.setInt(1, o.getInventoryId());
            ps.setInt(2, o.getCustomerId());
            ps.setInt(3, o.getStaffId());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    @Override
    public boolean delete(int id){
        try{
            var ps = prepare("DELETE FROM rental WHERE rental_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
