package controller;
import model.*;

// Connect between the view to the model
public class GeneralOpController {
    protected GeneralOpModel model;

    public GeneralOpController() {
        this.model = new model.GeneralOpModel();
    }

    public double averageSellingRate()
    {
        return model.averageSellingRate();
    }

    public String Login(int id, String password)
    {
        return model.Login(id, password);
    }

    public boolean isManager(int id, String password) {

        String session = model.isManager(id, password);
        if (session != null) {
            return true;
        }
        return false;
    }

    public boolean isWorker(int id, String password){
        String session = model.isWorker(id, password);
        if (session != null) {
            return true;
        }
        return false;
    }
}
