package model;

import model.entities.Purchase;
import model.entities.Worker;

import java.util.ArrayList;
import java.util.UUID;

public class GeneralOpModel {
    // Calculate the average selling rate of the store
    public double averageSellingRate() {
        int i=1;
        double sum=0;
        ArrayList<Purchase> pur = new ArrayList<>();
        try {
            pur = StoreModel.getInstance().getAllPurchase();
            for (i = 0; i < pur.size(); i++) {
                sum += pur.get(i).getShoppingRating();
            }
            sum/=i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }

    // Login function
    public String Login(int id, String password) {
        int i;
        ArrayList<Worker> Workers = null;
        try {
            Workers = new ArrayList<>();
            Workers = StoreModel.getInstance().getWorkers();
            for (i = 0; i < Workers.size(); i++) {
                if((Integer.valueOf(id) == Workers.get(i).getId() && Workers.get(i).getPassword().equals(password)))
                {
                    return Workers.get(i).getJobType();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "null";
    }

    // Returns true if the id and the password are true and belong to a manager.
    public String isManager(int id, String password) {
        String i;
        if(id <= 0 || password == null || password.trim().equals(""))
        {
            throw new IllegalArgumentException("id or password must not be null or wrong");
        }

        try {
            i = Login(id, password);
            if(i.equals("manager"))
            {
                return UUID.randomUUID().toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns true if the id and the password are true and belong to a worker.
    public String isWorker(int id, String password) {

        if( id <= 0 || password == null || password.trim().equals(""))
        {
            throw new IllegalArgumentException("id or password must not be null or wrong");
        }

        try {
            String i = Login(id, password);
            if(i.equals("worker"))
            {
                return UUID.randomUUID().toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
