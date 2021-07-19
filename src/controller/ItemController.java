package controller;
import model.*;
import model.entities.*;

// Connect between the view to the model
public class ItemController {
    protected ItemModel model;

    public ItemController() {
        this.model = new ItemModel();
    }

    public Item searchItem(int id, int size)
    {
        return model.searchItem(id,size);
    }

    public Item bestSellingProduct() { return model.bestSellingProduct(); }

    public boolean isItemExists(int id, int size){return model.isItemExists(id,size);}

    public boolean addPants(Pants pants) {
        return model.addPants(pants);
    }

    public boolean addShoe(Shoe shoe) {
        return model.addShoe(shoe);
    }

    public boolean addShirt(Shirt shirt) {
        return model.addShirt(shirt);
    }

    public boolean deleteItem(int id, int size)
    {
        return model.deleteItem(id,size);
    }
}
