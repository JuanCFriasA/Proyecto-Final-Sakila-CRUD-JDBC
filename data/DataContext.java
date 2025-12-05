package data;

import java.sql.*;

public abstract class DataContext<T> implements iDataPost<T>{
    protected Connection conn;

    public final Connection getConnection(){
        try{
            if(conn == null || conn.isClosed()){
                conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sakila"
,
                    "root",
                    "Kizigers15_"
                );
            }
        }catch(Exception e){e.printStackTrace();}
        return conn;
    }

    protected final PreparedStatement prepare(String sql) throws Exception{
        return getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }
}
