package model;

import model.entities.Worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkerModel {

    // Add a worker to the db
    public boolean addWorker(Worker w) {
        if (isExistsWorker(w.getId()) == false) {
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false", "root", "ProjectClothingStore");
                String INSERT_USERS_SQL = "INSERT INTO workers" + "  (name, id, dateofbirth, hourlysalary, numHourMonth, jobType, password) VALUES " +
                        " (?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
                connection.setAutoCommit(false);
                preparedStatement.setString(1, w.getName());
                preparedStatement.setInt(2, w.getId());
                preparedStatement.setString(3, w.getDateOfBirth());
                preparedStatement.setInt(4, w.getHourlySalary());
                preparedStatement.setInt(5, w.getNumHourMonth());
                preparedStatement.setString(6, w.getJobType());
                preparedStatement.setString(7, w.getPassword());
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

    // Watch the monthly salary of a worker
    public int watchMonthlySalary(int workerId) {

        if (!isExistsWorker(workerId)) {
            return -1;
        }

        int i;
        ArrayList<Worker> workers = new ArrayList<>();
        try {
            workers = StoreModel.getInstance().getWorkers();
            for (i = 0; i < workers.size(); i++) {
                if (workerId == (workers.get(i).getId())) {
                    return workers.get(i).getHourlySalary() * workers.get(i).getNumHourMonth();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Check if specific worker is exists
    public boolean isExistsWorker(int workerId) {
        int i;
        ArrayList<Worker> workers = new ArrayList<>();
        try {
            workers = StoreModel.getInstance().getWorkers();
            for (i = 0; i < workers.size(); i++) {
                if (workerId == (workers.get(i).getId())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
