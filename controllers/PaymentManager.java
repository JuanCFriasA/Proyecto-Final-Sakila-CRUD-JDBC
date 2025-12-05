package controllers;

import data.*;
import models.*;
import java.sql.*;
import java.util.*;

public class PaymentManager extends DataContext<Payment> {

    // POST
    @Override
    public boolean post(Payment o){
        try{
            var ps = prepare("INSERT INTO payment(customer_id, rental_id, amount) VALUES(?, ?, ?)");
            ps.setInt(1, o.getCustomerId());
            ps.setInt(2, o.getRentalId());
            ps.setDouble(3, o.getAmount());
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // GET BY ID
    @Override
    public Payment get(int id){
        try{
            var ps = prepare("SELECT * FROM payment WHERE payment_id=?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();

            if (rs.next()){
                return new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("rental_id"),
                    rs.getDouble("amount")
                );
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // GET ALL
    @Override
    public List<Payment> get(){
        List<Payment> list = new ArrayList<>();
        try{
            var ps = prepare("SELECT * FROM payment");
            var rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("rental_id"),
                    rs.getDouble("amount")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // GET WITH FILTER 
    @Override
    public List<Payment> get(String filter){
        List<Payment> list = new ArrayList<>();
        try{
            var ps = prepare("SELECT * FROM payment WHERE customer_id LIKE ? OR rental_id LIKE ?");
            ps.setString(1, "%" + filter + "%");
            ps.setString(2, "%" + filter + "%");

            var rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("rental_id"),
                    rs.getDouble("amount")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean put(Payment o){
        try{
            var ps = prepare("UPDATE payment SET customer_id=?, rental_id=?, amount=? WHERE payment_id=?");
            ps.setInt(1, o.getCustomerId());
            ps.setInt(2, o.getRentalId());
            ps.setDouble(3, o.getAmount());
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
            var ps = prepare("DELETE FROM payment WHERE payment_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
