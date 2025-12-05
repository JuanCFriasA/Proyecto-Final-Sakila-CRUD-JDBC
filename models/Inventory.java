package models;

public class Inventory {

    private int id;
    private int filmId;
    private int storeId;

    public Inventory(int id, int filmId, int storeId){
        this.id = id;
        this.filmId = filmId;
        this.storeId = storeId;
    }

    public int getId(){
        return id;
    }

    public int getFilmId(){
        return filmId;
    }

    public int getStoreId(){
        return storeId;
    }
}
