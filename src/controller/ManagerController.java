package controller;
import model.*;

// Connect between the view to the model
public class ManagerController extends WorkerController {

    protected ManagerModel model;

    public ManagerController() {
        this.model = new ManagerModel();
    }

    public boolean changeHourlySalary(int workerId, int newSalary) {
        return model.changeHourlySalary(workerId, newSalary);
    }
}
