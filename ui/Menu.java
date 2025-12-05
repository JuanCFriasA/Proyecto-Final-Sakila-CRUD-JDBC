package ui;

import java.util.*;
import controllers.*;
import models.*;

public class Menu {

    private final Scanner input = new Scanner(System.in);

    private final CountryManager countryManager = new CountryManager();
    private final CityManager cityManager = new CityManager();
    private final FilmManager filmManager = new FilmManager();
    private final CustomerManager customerManager = new CustomerManager();
    private final InventoryManager inventoryManager = new InventoryManager();
    private final RentalManager rentalManager = new RentalManager();
    private final PaymentManager paymentManager = new PaymentManager();

    public void show(){

        while(true){
            System.out.println("\n---------------- DB DATA MANAGER ----------------");
            System.out.println("1. Country");
            System.out.println("2. City");
            System.out.println("3. Film");
            System.out.println("4. Customer");
            System.out.println("5. Inventory");
            System.out.println("6. Rental");
            System.out.println("7. Payment");
            System.out.println("8. Reportes");
            System.out.println("0. Salir del programa");
            System.out.print("Seleccionar: ");

            int op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1 -> countryMenu();
                case 2 -> cityMenu();
                case 3 -> filmMenu();
                case 4 -> customerMenu();
                case 5 -> inventoryMenu();
                case 6 -> rentalMenu();
                case 7 -> paymentMenu();
                case 8 -> reportMenu();
                case 0 -> {
                    System.out.println("Saliendo del programa...");
                    return;
                }
                default -> System.out.println("Opcion Invalida");
            }
        }
    }

    // COUNTRY CRUD
    private void countryMenu(){
        while(true){
            System.out.println("\n=== COUNTRY MENU ===");
            System.out.println("1. Ingresar Pais");
            System.out.println("2. Mostrar Pais por ID");
            System.out.println("3. Listar todos los paises");
            System.out.println("4. Actualizar Pais");
            System.out.println("5. Eliminar Pais");
            System.out.println("0. Atras");

            int op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1 -> {
                    System.out.print("Nombre del pais: ");
                    String c = input.nextLine();
                    countryManager.post(new Country(0, c));
                }
                case 2 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    var obj = countryManager.get(id);
                    System.out.println(obj != null ? obj.getCountry() : "No encontrado");
                }
                case 3 -> countryManager.get().forEach(x -> 
                    System.out.println(x.getId() + " - " + x.getCountry())
                );
                case 4 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String c = input.nextLine();
                    countryManager.put(new Country(id, c));
                }
                case 5 -> {
                    System.out.print("ID para borrar: ");
                    int id = input.nextInt();
                    input.nextLine();
                    countryManager.delete(id);
                }
                case 0 -> { return; }
            }
        }
    }


    // CITY CRUD
    private void cityMenu(){
        while(true){
            System.out.println("\n=== CITY MENU ===");
            System.out.println("1. Ingresar Ciudad");
            System.out.println("2. Mostrar Ciudad por ID");
            System.out.println("3. Mostrar todas las Ciudades");
            System.out.println("4. Actualizar Ciudad");
            System.out.println("5. Borrar Ciudad");
            System.out.println("0. Atras");

            int op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1 -> {
                    System.out.print("Nombre de ciudad: ");
                    String name = input.nextLine();
                    System.out.print("ID de Pais: ");
                    int cid = input.nextInt();
                    input.nextLine();
                    cityManager.post(new City(0, name, cid));
                }
                case 2 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    var o = cityManager.get(id);
                    if(o != null){
                        System.out.println(o.getId() + " - " + o.getCity());
                    } else System.out.println("no encontrada");
                }
                case 3 -> cityManager.get().forEach(x ->
                    System.out.println(x.getId()+" - "+x.getCity()+" (pais "+x.getCountryId()+")")
                );
                case 4 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.print("nuevo nombre de ciudad: ");
                    String n = input.nextLine();
                    System.out.print("pais ID: ");
                    int cid = input.nextInt();
                    cityManager.put(new City(id, n, cid));
                }
                case 5 -> {
                    System.out.print("ID: ");
                    cityManager.delete(input.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }


    // FILM CRUD
    private void filmMenu(){
        while(true){
            System.out.println("\n=== FILM MENU ===");
            System.out.println("1. Insert");
            System.out.println("2. Get by ID");
            System.out.println("3. List All");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Back");

            int op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1 -> {
                    System.out.print("Title: ");
                    String t = input.nextLine();
                    System.out.print("Description: ");
                    String d = input.nextLine();
                    System.out.print("Language ID: ");
                    int l = input.nextInt();
                    filmManager.post(new Film(0, t, d, l));
                }
                case 2 -> {
                    System.out.print("ID: ");
                    var f = filmManager.get(input.nextInt());
                    System.out.println(f != null ? f.getTitle() : "Not found.");
                }
                case 3 -> filmManager.get().forEach(x ->
                    System.out.println(x.getId()+" - "+x.getTitle())
                );
                case 4 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.print("New title: ");
                    String t = input.nextLine();
                    System.out.print("New description: ");
                    String d = input.nextLine();
                    System.out.print("Language ID: ");
                    int l = input.nextInt();
                    filmManager.put(new Film(id, t, d, l));
                }
                case 5 -> {
                    System.out.print("ID: ");
                    filmManager.delete(input.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }

    // CUSTOMER CRUD
    private void customerMenu(){
        while(true){
            System.out.println("\n=== CUSTOMER MENU ===");
            System.out.println("1. Insert");
            System.out.println("2. Get");
            System.out.println("3. List");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Back");

            int op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1 -> {
                    System.out.print("First name: ");
                    String f = input.nextLine();
                    System.out.print("Last name: ");
                    String l = input.nextLine();
                    System.out.print("Email: ");
                    String e = input.nextLine();
                    customerManager.post(new Customer(0,f,l,e));
                }
                case 2 -> {
                    System.out.print("ID: ");
                    var c = customerManager.get(input.nextInt());
                    System.out.println(c != null ? c.getFirstName()+" "+c.getLastName() : "Not found.");
                }
                case 3 -> customerManager.get().forEach(x ->
                    System.out.println(x.getId()+" - "+x.getFirstName()+" "+x.getLastName())
                );
                case 4 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.print("First name: ");
                    String f = input.nextLine();
                    System.out.print("Last name: ");
                    String l = input.nextLine();
                    System.out.print("Email: ");
                    String e = input.nextLine();
                    customerManager.put(new Customer(id,f,l,e));
                }
                case 5 -> {
                    System.out.print("ID: ");
                    customerManager.delete(input.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }

    // INVENTORY CRUD
    private void inventoryMenu(){
        while(true){
            System.out.println("\n=== INVENTORY MENU ===");
            System.out.println("1. Insert");
            System.out.println("2. Get");
            System.out.println("3. List");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Back");

            int op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1 -> {
                    System.out.print("Film ID: ");
                    int film = input.nextInt();
                    System.out.print("Store ID: ");
                    int store = input.nextInt();
                    inventoryManager.post(new Inventory(0,film,store));
                }
                case 2 -> {
                    System.out.print("ID: ");
                    var inv = inventoryManager.get(input.nextInt());
                    System.out.println(inv != null ? inv.getFilmId()+" / "+inv.getStoreId() : "Not found.");
                }
                case 3 -> inventoryManager.get().forEach(x ->
                    System.out.println(x.getId()+" - film:"+x.getFilmId()+", store:"+x.getStoreId())
                );
                case 4 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    System.out.print("Film ID: ");
                    int film = input.nextInt();
                    System.out.print("Store ID: ");
                    int store = input.nextInt();
                    inventoryManager.put(new Inventory(id,film,store));
                }
                case 5 -> {
                    System.out.print("ID: ");
                    inventoryManager.delete(input.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }

    // RENTAL CRUD
    private void rentalMenu(){
        while(true){
            System.out.println("\n=== RENTAL MENU ===");
            System.out.println("1. Insert");
            System.out.println("2. Get");
            System.out.println("3. List");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Back");

            int op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1 -> {
                    System.out.print("Inventory ID: ");
                    int inv = input.nextInt();
                    System.out.print("Customer ID: ");
                    int cus = input.nextInt();
                    System.out.print("Staff ID: ");
                    int staff = input.nextInt();
                    rentalManager.post(new Rental(0,inv,cus,staff));
                }
                case 2 -> {
                    System.out.print("ID: ");
                    var r = rentalManager.get(input.nextInt());
                    System.out.println(r != null ? r.getInventoryId()+" / "+r.getCustomerId() : "Not found.");
                }
                case 3 -> rentalManager.get().forEach(x ->
                    System.out.println(x.getId()+" - inv:"+x.getInventoryId()+" cust:"+x.getCustomerId()+" staff:"+x.getStaffId())
                );
                case 4 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    System.out.print("Inventory ID: ");
                    int inv = input.nextInt();
                    System.out.print("Customer ID: ");
                    int cus = input.nextInt();
                    System.out.print("Staff ID: ");
                    int staff = input.nextInt();
                    rentalManager.put(new Rental(id,inv,cus,staff));
                }
                case 5 -> {
                    System.out.print("ID: ");
                    rentalManager.delete(input.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }

    // PAYMENT CRUD
    private void paymentMenu(){
        while(true){
            System.out.println("\n=== PAYMENT MENU ===");
            System.out.println("1. Insert");
            System.out.println("2. Get");
            System.out.println("3. List");
            System.out.println("4. Update");
            System.out.println("5. Delete");
            System.out.println("0. Back");

            int op = input.nextInt();
            input.nextLine();

            switch(op){
                case 1 -> {
                    System.out.print("Customer ID: ");
                    int cus = input.nextInt();
                    System.out.print("Rental ID: ");
                    int ren = input.nextInt();
                    System.out.print("Amount: ");
                    double am = input.nextDouble();
                    paymentManager.post(new Payment(0,cus,ren,am));
                }
                case 2 -> {
                    System.out.print("ID: ");
                    var p = paymentManager.get(input.nextInt());
                    System.out.println(p != null ? p.getAmount() : "Not found.");
                }
                case 3 -> paymentManager.get().forEach(x ->
                    System.out.println(x.getId()+" - amount: "+x.getAmount())
                );
                case 4 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    System.out.print("Customer ID: ");
                    int cus = input.nextInt();
                    System.out.print("Rental ID: ");
                    int ren = input.nextInt();
                    System.out.print("Amount: ");
                    double am = input.nextDouble();
                    paymentManager.put(new Payment(id,cus,ren,am));
                }
                case 5 -> {
                    System.out.print("ID: ");
                    paymentManager.delete(input.nextInt());
                }
                case 0 -> { return; }
            }
        }
    }

    // REPORT MENU
    private void reportMenu(){
        System.out.println("\n=== REPORTS ===");
        System.out.println("1. Total films");
        System.out.println("2. Total customers");
        System.out.println("3. Total rentals");
        System.out.println("0. Back");

        System.out.print("Select: ");
        int op = input.nextInt();

        switch(op){
            case 1 -> System.out.println("Total films: " + filmManager.get().size());
            case 2 -> System.out.println("Total customers: " + customerManager.get().size());
            case 3 -> System.out.println("Total rentals: " + rentalManager.get().size());
            case 0 -> {}
        }
    }

}
