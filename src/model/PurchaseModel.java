package model;

import model.entities.Member;
import model.entities.Purchase;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PurchaseModel {

    // Returns the last purchase of specific member
    public Purchase lastPurchase(int memId) {
        if(memId < 0)
        {
            throw new IllegalArgumentException("id must not be negative");
        }
        if(memId == 0)
        {
            throw new IllegalArgumentException("id must not be zero");
        }
        int i;
        ArrayList<Purchase> pur = new ArrayList<>();
        try {
            pur = StoreModel.getInstance().getAllPurchase();
            for (i = 0; i < pur.size() - 1; i++)
                if (pur.get(i).getClubMember().getId() == memId && pur.get(i + 1).getClubMember().getId() != memId)
                    return pur.get(i);
            if (pur.get(i).getClubMember().getId() == memId)
                return pur.get(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Do the selling process and returns the new price after using the member points and update the stock
    public int selling(Purchase pur) {
        if(pur.getClubMember().getName().equals("valueless") || pur.getClubMember().getDateOfBirth().equals("-1") || pur.getClubMember().getId() < 0 || pur.getClubMember().getPointsGained() < 0)
        {
            throw new IllegalArgumentException("name or id or pointsgaind or dateofbirth must not be null or wrong");
        }
        if(pur.getItem().isEmpty())
        {
            throw new IllegalArgumentException("The Array list Items must not be null or empty");
        }
        if(pur.getPrice() < 0 || pur.getShoppingRating() < 0)
        {
            throw new IllegalArgumentException("the price or shoppingRating must not be negative");
        }

        MemberModel memMod = new MemberModel();
        ItemModel itemModel = new ItemModel();

        if(!updateStockMinus(pur))
            return -1;

        int price = newPrice(pur.getPrice(),  pur.getClubMember());
        memMod.updateMembersPoints((int) (0.1*price), pur.getClubMember());
        int i;
        String strDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");

            Statement stmt = connection.createStatement();
            for(i=0;i<pur.getItem().size();i++) {
                String strInsert = "insert into allpurchase values (" + pur.getClubMember().getId() + "," + pur.getItem().get(i).getItemId()+",\""+strDate+"\","+pur.getShoppingRating()+")";
                int countUpdated = stmt.executeUpdate(strInsert);
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
        return price;
    }

    // Returns the new price after using the points of the club member
    public int newPrice(int price, Member m) {
        int points = m.getPointsGained();
        MemberModel memMod = new MemberModel();
        if (price>=points) {
            m.setPointsGained(0);
            memMod.updateMembersPoints(0, m);
            return price - points;
        }
        else {
            m.setPointsGained(points - (int)price);
            memMod.updateMembersPoints(-1* (int)price, m);
            return 0;
        }

    }

    // Reduce the stock of each item from the purchase and returns true if the action succeeded, else false.
    public boolean updateStockMinus(Purchase pur) {
        int i, j;
        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");

            Statement stmt = connection.createStatement();
            for(i=0;i<pur.getItem().size();i++) {
                String strUpdate = "update items set currentStock = currentStock -1 where itemid =" + pur.getItem().get(i).getItemId();
                int countUpdated = stmt.executeUpdate(strUpdate);
                ResultSet rs = stmt.executeQuery("select currentStock from items where itemid ="+pur.getItem().get(i).getItemId());
                rs.next();
                int currStock = rs.getInt("currentStock");
                if(currStock<0)
                {
                    for(j=i;j>=0;j--)
                    {
                        String strUpdate2 = "update items set currentStock = currentStock +1 where itemid =" + pur.getItem().get(j).getItemId();
                        int countUpdated2 = stmt.executeUpdate(strUpdate2);
                    }
                    return false;
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
        return true;
    }
}
