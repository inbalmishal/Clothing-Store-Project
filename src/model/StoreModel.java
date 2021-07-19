package model;

import model.entities.*;

import java.sql.*;
import java.util.ArrayList;

public class StoreModel {
    private static StoreModel db;
    private static Object LockObject = new Object();
    private StoreModel() {}

    // Singleton design pattern
    public static StoreModel getInstance() {
        if (db == null)
        {
            synchronized (LockObject) {
                if (db == null) {
                    db = new StoreModel();
                }
            }
        }
        return db;
    }

    // Returns list of all the purchases in the db
    public static ArrayList<Purchase> getAllPurchase() {
        ArrayList<Purchase> allpurchase = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        int totalPrice;
        int rate;
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");

            // Step 2:Create a statement using connection object
            Statement stmt = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet rs2 = stmt.executeQuery("SELECT distinct * FROM (items join allpurchase using(itemid))join clubmembers using(id) order by id;");

            rs2.next();
            String name = rs2.getString("name");
            int id = rs2.getInt("id");
            String dateofbirth = rs2.getString("dateofbirth");
            String pointgained = rs2.getString("pointgained");
            Integer pointgained1 = Integer.valueOf(pointgained);
            Member m1 = new Member(name,dateofbirth, id, pointgained1);
            String date =rs2.getString("date");
            totalPrice = rs2.getInt("Price");
            rate = rs2.getInt("shoppingrating");
            int itemid = rs2.getInt("itemid");
            String color = rs2.getString("color");
            int price = rs2.getInt("price");
            String type = rs2.getString("type");
            int size = rs2.getInt("size");
            String brand = rs2.getString("brand");
            String gender = rs2.getString("gender");
            int baseStock = rs2.getInt("baseStock");
            int currentStock = rs2.getInt("currentStock");
            String drawstringColor = rs2.getString("drawstringcolor");
            String pantsType = rs2.getString("pantstype");
            String shirtsType = rs2.getString("shirtstype");
            switch(type){
                case "shirt":
                    Shirt shirts = new Shirt(color, brand, gender,type, price, size, currentStock, baseStock, id, shirtsType);
                    items.add(shirts);
                    break;
                case "pants":
                    Pants pants = new Pants(color, brand, gender,type, price, size, currentStock, baseStock, id, pantsType);
                    items.add(pants);
                    break;
                case "shoe":
                    Shoe shoes = new Shoe(color, brand, gender,type, price, size, currentStock, baseStock, id, drawstringColor);
                    items.add(shoes);
                    break;
            }

            // Step 4: Process the ResultSet object.
            while (rs2.next()) {
                if(m1.getId()!=rs2.getInt("id") || !date.equals(rs2.getString("date")))
                {
                    Purchase p = new Purchase(m1, items, totalPrice, rate);
                    allpurchase.add(p);
                    items = new ArrayList<>();
                    name = rs2.getString("name");
                    id = rs2.getInt("id");
                    dateofbirth = rs2.getString("dateofbirth");
                    pointgained = rs2.getString("pointgained");
                    pointgained1 = Integer.valueOf(pointgained);
                    m1 = new Member(name,dateofbirth, id, pointgained1);
                    totalPrice = 0;
                    rate = rate = rs2.getInt("shoppingrating");
                    date =rs2.getString("date");
                }

                itemid = rs2.getInt("itemid");
                color = rs2.getString("color");
                price = rs2.getInt("price");
                totalPrice+=price;
                type = rs2.getString("type");
                size = rs2.getInt("size");
                brand = rs2.getString("brand");
                gender = rs2.getString("gender");
                baseStock = rs2.getInt("baseStock");
                currentStock = rs2.getInt("currentStock");
                drawstringColor = rs2.getString("drawstringcolor");
                pantsType = rs2.getString("pantstype");
                shirtsType = rs2.getString("shirtstype");
                switch(type){
                    case "shirt":
                        Shirt shirts = new Shirt(color, brand, gender,type, price, size, currentStock, baseStock, id, shirtsType);
                        items.add(shirts);
                        break;
                    case "pants":
                        Pants pants = new Pants(color, brand, gender,type, price, size, currentStock, baseStock, id, pantsType);
                        items.add(pants);
                        break;
                    case "shoe":
                        Shoe shoes = new Shoe(color, brand, gender,type, price, size, currentStock, baseStock, id, drawstringColor);
                        items.add(shoes);
                        break;
                }
            }

            Purchase p = new Purchase(m1, items, totalPrice, rate);
            allpurchase.add(p);
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return allpurchase;
    }

    // Returns list of all the club members in the db
    public static ArrayList<Member> getClubMembers() {
        Connection connection = null;
        ArrayList<Member> ClubMembers = null;
        try {
            ClubMembers = new ArrayList<Member>();

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");

            // Step 2:Create a statement using connection object
            Statement stmt = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet rs = stmt.executeQuery("select name, id, dateofbirth, pointgained from clubmembers");
            {

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    String name = rs.getString("name");
                    int id = rs.getInt("id");
                    String dateofbirth = rs.getString("dateofbirth");
                    String pointgained = rs.getString("pointgained");
                    Integer pointgained1 = Integer.valueOf(pointgained);
                    Member m = new Member(name,dateofbirth, id, pointgained1);
                    ClubMembers.add(m);
                }
            }
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return ClubMembers;
    }

    // Returns list of all the items in the db
    public static ArrayList<Item> getItems() {
        Connection connection = null;

        ArrayList<Item> items = new ArrayList<>();
        try {


            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");

            // Step 2:Create a statement using connection object
            Statement stmt = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet rs = stmt.executeQuery("select * from items");

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("itemid");
                String color = rs.getString("color");
                int price = rs.getInt("price");
                String type = rs.getString("type");
                int size = rs.getInt("size");
                String brand = rs.getString("brand");
                String gender = rs.getString("gender");
                int baseStock = rs.getInt("baseStock");
                int currentStock = rs.getInt("currentStock");
                String drawstringColor = rs.getString("drawstringcolor");
                String pantsType = rs.getString("pantstype");
                String shirtsType = rs.getString("shirtstype");
                switch(type){
                    case "shirt":
                        Shirt shirts = new Shirt(color, brand, gender, type, price, size, currentStock, baseStock, id, shirtsType);
                        items.add(shirts);
                        break;
                    case "pants":
                        Pants pants = new Pants(color, brand, gender,type, price, size, currentStock, baseStock, id, pantsType);
                        items.add(pants);
                        break;
                    case "shoe":
                        Shoe shoes = new Shoe(color, brand, gender,type, price, size, currentStock, baseStock, id, drawstringColor);
                        items.add(shoes);
                        break;
                }

            }

        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return items;
    }

    // Returns list of all the workers in the db
    public static ArrayList<Worker> getWorkers() {
        Connection connection = null;
        ArrayList<Worker> Workers = null;
        try {
            Workers = new ArrayList<>();

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false" , "root", "ProjectClothingStore");

            // Step 2:Create a statement using connection object
            Statement stmt = connection.createStatement();

            // Step 3: Execute the query or update query
            ResultSet rs = stmt.executeQuery("select * from workers");
            {

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    String name = rs.getString("name");
                    int id = rs.getInt("id");
                    String dateofbirth = rs.getString("dateofbirth");
                    int hourlysalary = rs.getInt("hourlysalary");
                    int numHourMonth = rs.getInt("numHourMonth");
                    String jobType = rs.getString("jobType");
                    String password = rs.getString("password");
                    Worker w = new Worker(name, dateofbirth, id ,hourlysalary, numHourMonth, jobType, password);
                    Workers.add(w);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return Workers;
    }
}


