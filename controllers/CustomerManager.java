package controllers;

import data.*;
import models.*;
import java.sql.*;
import java.util.*;

public class CustomerManager extends DataContext<Customer> {

    // POST
    @Override
    public boolean post(Customer o){
        try {
            var ps = prepare("INSERT INTO customer(first_name, last_name, email) VALUES(?, ?, ?)");
            ps.setString(1, o.getFirstName());
            ps.setString(2, o.getLastName());
            ps.setString(3, o.getEmail());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // GET BY ID
    @Override
    public Customer get(int id){
        try {
            var ps = prepare("SELECT * FROM customer WHERE customer_id=?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();

            if (rs.next()){
                return new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // GET ALL
    @Override
    public List<Customer> get(){
        List<Customer> list = new ArrayList<>();
        try {
            var ps = prepare("SELECT * FROM customer");
            var rs = ps.executeQuery();

            while (rs.next()){
                list.add(new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                ));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // GET WITH FILTER 
    @Override
    public List<Customer> get(String filter){
        List<Customer> list = new ArrayList<>();
        try {
            var ps = prepare("SELECT * FROM customer WHERE first_name LIKE ? OR last_name LIKE ?");
            ps.setString(1, "%" + filter + "%");
            ps.setString(2, "%" + filter + "%");

            var rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Customer(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email")
                ));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean put(Customer o){
        try {
            var ps = prepare("UPDATE customer SET first_name=?, last_name=?, email=? WHERE customer_id=?");
            ps.setString(1, o.getFirstName());
            ps.setString(2, o.getLastName());
            ps.setString(3, o.getEmail());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    @Override
    public boolean delete(int id){
        try {
            var ps = prepare("DELETE FROM customer WHERE customer_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
