package models;

public class Rental {

    private int id;
    private int inventoryId;
    private int customerId;
    private int staffId;

    public Rental(int id, int inventoryId, int customerId, int staffId){
        this.id = id;
        this.inventoryId = inventoryId;
        this.customerId = customerId;
        this.staffId = staffId;
    }

    public int getId(){
        return id;
    }

    public int getInventoryId(){
        return inventoryId;
    }

    public int getCustomerId(){
        return customerId;
    }

    public int getStaffId(){
        return staffId;
    }
}
