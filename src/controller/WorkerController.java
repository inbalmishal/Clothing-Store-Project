package controller;
import model.*;
import model.entities.Worker;

// Connect between the view to the model
public class WorkerController {
  WorkerModel model;

    public WorkerController() {
        this.model = new WorkerModel();
    }

    public boolean addWorker(Worker w) {
        return model.addWorker(w);
    }

    public int watchMonthlySalary(int workerId)
    {
        return model.watchMonthlySalary(workerId);
    }

    public boolean isExistsWorker(int workerId)
    {
        return model.isExistsWorker(workerId);
    }
}
