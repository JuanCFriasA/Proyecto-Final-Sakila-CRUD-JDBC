package controllers;

import data.*;
import models.*;
import java.sql.*;
import java.util.*;

public class FilmManager extends DataContext<Film> {

    // POST
    @Override
    public boolean post(Film o){
        try{
            var ps = prepare("INSERT INTO film(title, description, language_id) VALUES(?, ?, ?)");
            ps.setString(1, o.getTitle());
            ps.setString(2, o.getDescription());
            ps.setInt(3, o.getLanguageId());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    // GET BY ID
    @Override
    public Film get(int id){
        try{
            var ps = prepare("SELECT * FROM film WHERE film_id=?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();

            if(rs.next()){
                return new Film(
                    rs.getInt("film_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getInt("language_id")
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // GET ALL
    @Override
    public List<Film> get(){
        List<Film> list = new ArrayList<>();
        try{
            var ps = prepare("SELECT * FROM film");
            var rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Film(
                    rs.getInt("film_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getInt("language_id")
                ));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // GET WITH STRING FILTER 
    @Override
    public List<Film> get(String filter){
        List<Film> list = new ArrayList<>();
        try{
            var ps = prepare("SELECT * FROM film WHERE title LIKE ? OR description LIKE ?");
            ps.setString(1, "%" + filter + "%");
            ps.setString(2, "%" + filter + "%");
            var rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Film(
                    rs.getInt("film_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getInt("language_id")
                ));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean put(Film o){
        try{
            var ps = prepare("UPDATE film SET title=?, description=?, language_id=? WHERE film_id=?");
            ps.setString(1, o.getTitle());
            ps.setString(2, o.getDescription());
            ps.setInt(3, o.getLanguageId());
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
        try{
            var ps = prepare("DELETE FROM film WHERE film_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
