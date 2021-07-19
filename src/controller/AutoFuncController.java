package controller;

import model.*;
import java.util.ArrayList;

// Connect between the view to the model
public class AutoFuncController {
    protected AutoFuncModel model;
    public AutoFuncController() {
        this.model = new AutoFuncModel();
    }
    public ArrayList<String> checkCurrentStock()
    {
        return model.checkCurrentStock();
    }
}
