package model;

import model.entities.*;

import java.sql.*;
import java.util.ArrayList;

public class ItemModel {
     // Search an item in the data base and returns object of it
    public Item searchItem(int id, int size) {
        if (id < 0 || size < 0)
        {
            throw new IllegalArgumentException("id or size must not be negative");
        }

        int i;
        ArrayList<Item> items = new ArrayList<>();
        try {
            items = StoreModel.getInstance().getItems();
            for (i = 0; i < items.size(); i++) {
                if (id == items.get(i).getItemId() && items.get(i).getSize() == size && (items.get(i).getBaseStock() - items.get(i).getCurrentStock()) > 0) {
                    return items.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns true if the item exists. other, returns false
    public boolean isItemExists(int id, int size) {
        int i;
        ArrayList<Item> items = new ArrayList<>();
        try {
            items = StoreModel.getInstance().getItems();
            for (i = 0; i < items.size(); i++) {
                if (id == items.get(i).getItemId() && items.get(i).getSize() == size) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Returns the best item in the store
    public Item bestSellingProduct() {
        int i, max = 0;
        Item temp = null;
        ArrayList<Item> items = new ArrayList<>();
        try {
            items = StoreModel.getInstance().getItems();
            for (i = 0; i < items.size(); i++) {
                if (items.get(i).getBaseStock() - items.get(i).getCurrentStock() > max) {
                    max = items.get(i).getBaseStock() - items.get(i).getCurrentStock();
                    temp = items.get(i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    // Add new pants to the DB
    public boolean addPants(Pants pants) {
        if (pants.getItemId() < 0 || pants.getSize() < 0)
        {
            throw new IllegalArgumentException("id or size must not be negative");
        }
        if (isItemExists(pants.getItemId(), pants.getSize()))
        {
            throw new IllegalArgumentException("id and size exist");
        }
        if (!isItemExists(pants.getItemId(), pants.getSize())) {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");
                String INSERT_USERS_SQL = "INSERT INTO items" + "  (itemid, color, price, type, size, brand, gender, drawstringcolor, pantstype, shirtstype, basestock, currentStock) VALUES " +
                        " (?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?);";

                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, pants.getItemId());
                preparedStatement.setString(2, pants.getColor());
                preparedStatement.setInt(3, pants.getPrice());
                preparedStatement.setString(4, pants.getType());
                preparedStatement.setInt(5, pants.getSize());
                preparedStatement.setString(6, pants.getBrand());
                preparedStatement.setString(7, pants.getGender());
                preparedStatement.setString(8, "null");
                preparedStatement.setString(9, pants.getPantsType());
                preparedStatement.setString(10, "null");
                preparedStatement.setInt(11, pants.getBaseStock());
                preparedStatement.setInt(12, pants.getCurrentStock());
                preparedStatement.addBatch();
                int[] updateCounts = preparedStatement.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);


            } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            return true;
        }
        return false;
    }

    // Add new shoe to the DB
    public boolean addShoe(Shoe shoe) {
        if (shoe.getItemId() < 0 || shoe.getSize() < 0)
        {
            throw new IllegalArgumentException("id or size must not be negative");
        }
        if (isItemExists(shoe.getItemId(), shoe.getSize()))
        {
            throw new IllegalArgumentException("id and size exist");
        }

        if (!isItemExists(shoe.getItemId(), shoe.getSize())) {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");
                String INSERT_USERS_SQL = "INSERT INTO items" + "  (itemid, color, price, type, size, brand, gender, drawstringcolor, pantstype, shirtstype, basestock, currentStock) VALUES " +
                        " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, shoe.getItemId());
                preparedStatement.setString(2, shoe.getColor());
                preparedStatement.setInt(3, shoe.getPrice());
                preparedStatement.setString(4, shoe.getType());
                preparedStatement.setInt(5, shoe.getSize());
                preparedStatement.setString(6, shoe.getBrand());
                preparedStatement.setString(7, shoe.getGender());
                preparedStatement.setString(8, shoe.getDrawstringColor());
                preparedStatement.setString(9, "null");
                preparedStatement.setString(10, "null");
                preparedStatement.setInt(11, shoe.getBaseStock());
                preparedStatement.setInt(12, shoe.getCurrentStock());
                preparedStatement.addBatch();
                int[] updateCounts = preparedStatement.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);


            } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    // Add new shirt to the DB
    public boolean addShirt(Shirt shirt) {
        if (shirt.getItemId() < 0 || shirt.getSize() < 0)
        {
            throw new IllegalArgumentException("id or size must not be negative");
        }
        if (isItemExists(shirt.getItemId(), shirt.getSize()))
        {
            throw new IllegalArgumentException("id and size exist");
        }
        if (!isItemExists(shirt.getItemId(), shirt.getSize())) {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");
                String INSERT_USERS_SQL = "INSERT INTO items" + "  (itemid, color, price, type, size, brand, gender, drawstringcolor, pantstype, shirtstype, basestock, currentStock) VALUES " +
                        " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, shirt.getItemId());
                preparedStatement.setString(2, shirt.getColor());
                preparedStatement.setInt(3, shirt.getPrice());
                preparedStatement.setString(4, shirt.getType());
                preparedStatement.setInt(5, shirt.getSize());
                preparedStatement.setString(6, shirt.getBrand());
                preparedStatement.setString(7, shirt.getGender());
                preparedStatement.setString(8, "null");
                preparedStatement.setString(9, "null");
                preparedStatement.setString(10, shirt.getShirtType());
                preparedStatement.setInt(11, shirt.getBaseStock());
                preparedStatement.setInt(12, shirt.getCurrentStock());

                preparedStatement.addBatch();
                int[] updateCounts = preparedStatement.executeBatch();
                connection.commit();
                connection.setAutoCommit(true);


            } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    // Delete item from the DB
    public boolean deleteItem(int id, int size) {
        Connection connection = null;
        String idNumber = String.valueOf(id);
        String sizeNumber = String.valueOf(size);
        String DELETE_USERS_SQL = "delete from items where itemid = " + idNumber + " and size = "+ sizeNumber+ ";";
        if (isItemExists(id,size) == true) {
            try {

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");
                Statement statement = connection.createStatement();
                statement.executeUpdate(DELETE_USERS_SQL);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

