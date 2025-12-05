package models;

public class Payment {

    private int id;
    private int customerId;
    private int rentalId;
    private double amount;

    public Payment(int id, int customerId, int rentalId, double amount){
        this.id = id;
        this.customerId = customerId;
        this.rentalId = rentalId;
        this.amount = amount;
    }

    public int getId(){
        return id;
    }

    public int getCustomerId(){
        return customerId;
    }

    public int getRentalId(){
        return rentalId;
    }

    public double getAmount(){
        return amount;
    }
}
