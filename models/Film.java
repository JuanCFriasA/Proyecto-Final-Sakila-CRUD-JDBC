package models;

public class Film {

    private int id;
    private String title;
    private String description;
    private int languageId;

    public Film(int id, String title, String description, int languageId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.languageId = languageId;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public int getLanguageId(){
        return languageId;
    }
}
