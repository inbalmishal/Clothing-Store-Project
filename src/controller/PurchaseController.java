package controller;
import model.*;
import model.entities.Member;
import model.entities.Purchase;

// Connect between the view to the model
public class PurchaseController {
    protected PurchaseModel model;

    public PurchaseController() {
        this.model = new PurchaseModel();
    }

    public Purchase lastPurchase(int memId) {
        return model.lastPurchase(memId);
    }

    public int selling(Purchase pur)
    {
        return model.selling(pur);
    }

    public int newPrice(int price, Member m)
    {
        return model.newPrice(price, m);
    }

    public boolean updateStockMinus(Purchase pur)
    {
        return model.updateStockMinus(pur);
    }
}
