package model;

import java.sql.*;

public class ManagerModel extends WorkerModel {

    // Change the hourly salary of a worker and return true if the act succeeded
    public boolean changeHourlySalary(int workerId, int newSalary) {
        if (!isExistsWorker(workerId)) {
            return false;
        }

        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");

            Statement stmt = connection.createStatement();
            String strUpdate = "update workers set hourlysalary = " + newSalary + " where id =" + workerId;
            int countUpdated = stmt.executeUpdate(strUpdate);
            return true;
        } catch (IllegalAccessException | InstantiationException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
