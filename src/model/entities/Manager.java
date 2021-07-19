package model.entities;

public class Manager extends Worker{
    public Manager(String name, String dateOfBirth, int id, int hourlySalary, int numHourMonth, String jobType, String password) {
        super(name, dateOfBirth, id, hourlySalary, numHourMonth, jobType, password);
    }

    public Manager() {
        super();
    }
}
