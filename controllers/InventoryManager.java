package controllers;

import data.*;
import models.*;
import java.sql.*;
import java.util.*;

public class InventoryManager extends DataContext<Inventory> {

    // POST
    @Override
    public boolean post(Inventory o){
        try{
            var ps = prepare("INSERT INTO inventory(film_id, store_id) VALUES(?, ?)");
            ps.setInt(1, o.getFilmId());
            ps.setInt(2, o.getStoreId());
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // GET BY ID
    @Override
    public Inventory get(int id){
        try{
            var ps = prepare("SELECT * FROM inventory WHERE inventory_id=?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();

            if (rs.next()){
                return new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getInt("film_id"),
                    rs.getInt("store_id")
                );
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // GET ALL
    @Override
    public List<Inventory> get(){
        List<Inventory> list = new ArrayList<>();
        try{
            var ps = prepare("SELECT * FROM inventory");
            var rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getInt("film_id"),
                    rs.getInt("store_id")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // GET WITH FILTER — OBLIGATORIO POR iDataPost
    // Vamos a filtrar por film_id o store_id
    @Override
    public List<Inventory> get(String filter){
        List<Inventory> list = new ArrayList<>();
        try{
            var ps = prepare("SELECT * FROM inventory WHERE film_id LIKE ? OR store_id LIKE ?");
            ps.setString(1, "%" + filter + "%");
            ps.setString(2, "%" + filter + "%");

            var rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getInt("film_id"),
                    rs.getInt("store_id")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean put(Inventory o){
        try{
            var ps = prepare("UPDATE inventory SET film_id=?, store_id=? WHERE inventory_id=?");
            ps.setInt(1, o.getFilmId());
            ps.setInt(2, o.getStoreId());
            ps.setInt(3, o.getId());
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
            var ps = prepare("DELETE FROM inventory WHERE inventory_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
