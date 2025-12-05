package controllers;

import data.*;
import models.*;
import java.sql.*;
import java.util.*;

public class CountryManager extends DataContext<Country> {

    // POST
    @Override
    public boolean post(Country o) {
        try {
            var ps = prepare("INSERT INTO country(country) VALUES(?)");
            ps.setString(1, o.getCountry());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET BY ID
    @Override
    public Country get(int id) {
        try {
            var ps = prepare("SELECT * FROM country WHERE country_id=?");
            ps.setInt(1, id);
            var rs = ps.executeQuery();

            if (rs.next()) {
                return new Country(
                    rs.getInt("country_id"),
                    rs.getString("country")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // GET ALL
    @Override
    public List<Country> get() {
        List<Country> list = new ArrayList<>();
        try {
            var ps = prepare("SELECT * FROM country");
            var rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Country(
                    rs.getInt("country_id"),
                    rs.getString("country")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // GET WITH FILTER
    @Override
    public List<Country> get(String filter) {
        List<Country> list = new ArrayList<>();
        try {
            var ps = prepare("SELECT * FROM country WHERE country LIKE ?");
            ps.setString(1, "%" + filter + "%");
            var rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Country(
                    rs.getInt("country_id"),
                    rs.getString("country")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public boolean put(Country o) {
        try {
            var ps = prepare("UPDATE country SET country=? WHERE country_id=?");
            ps.setString(1, o.getCountry());
            ps.setInt(2, o.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    @Override
    public boolean delete(int id) {
        try {
            var ps = prepare("DELETE FROM country WHERE country_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
