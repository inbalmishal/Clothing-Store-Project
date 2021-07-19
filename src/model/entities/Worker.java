package model.entities;

public class Worker extends Person{
    private int hourlySalary, numHourMonth;
    private String jobType, password;

    public int getHourlySalary() {
        return hourlySalary;
    }
    public void setHourlySalary(int hourlySalary) {
        this.hourlySalary = hourlySalary;
    }
    public int getNumHourMonth() {
        return numHourMonth;
    }
    public void setNumHourMonth(int numHourMonth) {
        this.numHourMonth = numHourMonth;
    }
    public String getJobType() {
        return jobType;
    }
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Worker(String name, String dateOfBirth, int id, int hourlySalary, int numHourMonth, String jobType, String password) {
        super(name, dateOfBirth, id);
        this.hourlySalary = hourlySalary;
        this.numHourMonth = numHourMonth;
        this.jobType = jobType;
        this.password = password;
    }

    public Worker() {
        super();
    }
}
