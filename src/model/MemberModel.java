package model;

import model.entities.Member;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class MemberModel {
    public MemberModel() {
    }

    // Add a club member to the db
    public boolean addClubMember(Member m) {
        if(m.getName().trim().equals("") || (m.getId() <= 0) || m.getPointsGained() < 0 || m.getDateOfBirth().trim().equals(""))
        {
            throw new IllegalArgumentException("name or id or pointsgaind or dateofbirth must not be null or wrong");
        }

        if (isExistsClubMember(m.getId()) == true) {
            throw new IllegalArgumentException("the member is already exist");
        }

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");
            String INSERT_USERS_SQL = "INSERT INTO clubmembers" + "  (name, id, dateofbirth, pointgained) VALUES " +
                    " (?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            connection.setAutoCommit(false);
            preparedStatement.setString(1, m.getName());
            preparedStatement.setInt(2, m.getId());
            preparedStatement.setString(3, m.getDateOfBirth());
            preparedStatement.setInt(4, m.getPointsGained());
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

    // Delete club member from the db
    public boolean deleteClubMember(int id) {
        Connection connection = null;
        String idNumber = String.valueOf(id);
        String DELETE_USERS_SQL = "delete from clubmembers where id = " + idNumber + ";";
        if (isExistsClubMember(id) == true) {
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

    // Check if a specific club member is exits
    public boolean isExistsClubMember(int id) {
        int i;
        ArrayList<Member> members = new ArrayList<>();
        try {
            members = StoreModel.getInstance().getClubMembers();
            for (i = 0; i < members.size(); i++) {
                if (Integer.valueOf(id) == (members.get(i).getId())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    // Returns Member object of specific member
    public Member searchMember(int id) {
        int i;
        ArrayList<Member> members = new ArrayList<>();
        try {
            members = StoreModel.getInstance().getClubMembers();
            for (i = 0; i < members.size(); i++) {
                if (Integer.valueOf(id) == (members.get(i).getId())) {
                    return members.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update the points of members who have birthday today. Returns a list of them.
    public ArrayList<Member> birthdayPointAuto() {
        int i;
        ArrayList<Member> clubMembers;
        ArrayList<Member> birthdayMembers;
        try {
            birthdayMembers = new ArrayList<Member>();
            clubMembers = new ArrayList<Member>();
            clubMembers = StoreModel.getInstance().getClubMembers();
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int month = localDate.getMonthValue();
            int day   = localDate.getDayOfMonth();
            for (i = 0; i < clubMembers.size(); i++) {
                String[] dateClubMember;
                dateClubMember = clubMembers.get(i).getDateOfBirth().split("-");
                int dayMember = Integer.parseInt(dateClubMember[2]);
                int monthMember = Integer.parseInt(dateClubMember[1]);
                if (Integer.valueOf(month) == Integer.valueOf(monthMember) && Integer.valueOf(day) == Integer.valueOf(dayMember)) {
                    updateMembersPoints(250, clubMembers.get(i));
                    birthdayMembers.add( clubMembers.get(i));
                }
            }
            return birthdayMembers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update the points of a member according to the price
    public void updateMembersPoints(int price, Member m) {
        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");

            Statement stmt = connection.createStatement();
            String strUpdate = "update clubmembers set pointgained = pointgained +" + price + " where id =" + m.getId();
            int countUpdated = stmt.executeUpdate(strUpdate);
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
