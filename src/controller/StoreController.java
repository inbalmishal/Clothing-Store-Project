package controller;
import model.*;
import model.entities.*;

import java.util.ArrayList;

// Connect between the view to the model
public class StoreController {
    protected StoreModel model;

    public StoreController() { }

    public static ArrayList<Purchase> getAllPurchase()
    {
        return StoreModel.getAllPurchase();
    }

    public static ArrayList<Member> getClubMembers()
    {
        return StoreModel.getClubMembers();
    }

    public static ArrayList<Item> getItems()
    {
        return StoreModel.getItems();
    }

    public static ArrayList<Worker> getWorkers()
    {
        return StoreModel.getWorkers();
    }
}
