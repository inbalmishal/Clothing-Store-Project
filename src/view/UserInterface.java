package view;

import java.util.*;

public class UserInterface implements Runnable {
    // This object allows us to call to the controller functions
    private ViewFunc viewFunc;

    // Regular constructor.
    public UserInterface() {
        this.viewFunc = new ViewFunc();
    }

    // Show to the user the first screen using a thread
    public void start() {
        new Thread(this).run();
    }

    @Override
    public void run() {
        int i = 1;
        while (true){
            String str = viewFunc.login();
            if (str.equals( "manager")) {
                managerScreen();
            }
            if (str.equals("worker")) {
                workerScreen();
            }
            if (str.equals("none")) {
                if (i==3) {
                    System.out.println("mistake number "+i+"/3");
                    System.out.println("the program closes");
                    return;
                }
                System.out.println("username or password are not correct - try again!");
                System.out.println("mistake number "+i+"/3");
                i++;
            }
        }

    }

    // Show to the user the worker screen
    public void workerScreen() {
        viewFunc.runCheckCurrentStock();
        viewFunc.birthDayAuto();
        Scanner s = new Scanner(System.in);
        while(true) {
            System.out.println("Hello Worker!!");
            System.out.println("what do you want to do?(choose number)");
            System.out.println("1. search product by Id and size");
            System.out.println("2. add club member");
            System.out.println("3. delete club member");
            System.out.println("4. check if the club member exists");
            System.out.println("5. selling");
            System.out.println("6. watch last purchase");
            System.out.println("7. watch my monthly salary");
            System.out.println("Any other number to logout");
            int temp=s.nextInt();
            switch (temp) {
                case 1:
                    viewFunc.searchItem();
                    break;
                case 2:
                    viewFunc.addClubMember();
                    break;
                case 3:
                    viewFunc.deleteClubMember();
                    break;
                case 4:
                    viewFunc.isMemberExists();
                    break;
                case 5:
                    viewFunc.selling();
                    break;
                case 6:
                    viewFunc.watchLastPurchase();
                    break;
                case 7:
                    viewFunc.watchMonthlySalary();
                    break;
                default:
                    System.out.println("** logout **");
                    System.out.println("");
                    return;
            }
        }

    }

    // Show to the user the manager screen
    public void managerScreen() {
        viewFunc.runCheckCurrentStock();
        viewFunc.birthDayAuto();
        Scanner s = new Scanner(System.in);
        while(true) {
            System.out.println("Hello Manager!!");
            System.out.println("what do you want to do?(choose number)");
            System.out.println("1. search product by Id and size");
            System.out.println("2. add club member");
            System.out.println("3. delete club member");
            System.out.println("4. check if the club member exists");
            System.out.println("5. selling");
            System.out.println("6. watch last purchase");
            System.out.println("7. watch my monthly salary");
            System.out.println("8. watch average Selling Rate");
            System.out.println("9. find the best Selling Product");
            System.out.println("10. add new shoe/pants/shirt");
            System.out.println("11. Change Hourly Salary");
            System.out.println("12. add Worker");
            System.out.println("Any other number to logout");
            int temp=s.nextInt();
            switch (temp) {
                case 1:
                    viewFunc.searchItem();
                    break;
                case 2:
                    viewFunc.addClubMember();
                    break;
                case 3:
                    viewFunc.deleteClubMember();
                    break;
                case 4:
                    viewFunc.isMemberExists();
                    break;
                case 5:
                    viewFunc.selling();
                    break;
                case 6:
                    viewFunc.watchLastPurchase();
                    break;
                case 7:
                    viewFunc.watchMonthlySalary();
                    break;
                case 8:
                    viewFunc.averageSellingRate();
                    break;
                case 9:
                    viewFunc.bestSellingProduct();
                    break;
                case 10:
                    viewFunc.addItem();
                    break;
                case 11:
                    viewFunc.changeHourlySalary();
                    break;
                case 12:
                    viewFunc.addWorker();
                    break;
                default:
                    System.out.println("** logout **");
                    System.out.println("");
                    return;
            }
        }

    }
}
