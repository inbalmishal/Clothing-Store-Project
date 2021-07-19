package view;

import model.entities.*;
import java.util.*;
import controller.*;

public class ViewFunc {
    // The view connect to the controller and update according to it.
    // Here we create object for each type of controller.
    private AutoFuncController autoFuncController;
    private ItemController itemController;
    private ManagerController managerController;
    private MemberController memberController;
    private PurchaseController purchaseController;
    private StoreController storeController;
    private WorkerController workerController;
    private GeneralOpController generalOpController;

    // Regular constructor.
    public ViewFunc() {
        autoFuncController = new AutoFuncController();
        itemController = new ItemController();
        managerController = new ManagerController();
        memberController = new MemberController();
        purchaseController = new PurchaseController();
        storeController = new StoreController();
        workerController = new WorkerController();
        generalOpController = new GeneralOpController();
    }

    // Checks if member has a birthday
    public void birthDayAuto() {
        ArrayList<Member> temp = memberController.birthdayPointAuto();
        for(int i=0;i<temp.size();i++)
        {
            System.out.println("System message: Member - " + temp.get(i).getName() + " Id - " + temp.get(i).getId() + " has a birthday and he gets 250 points!");
        }
    }

    // Login function
    public String login(){
        System.out.println("Login");
        Scanner s = new Scanner(System.in);
        System.out.println("enter your id: ");
        int id = s.nextInt();
        System.out.println("enter your password: ");
        String pass = s.next();
        if(generalOpController.isManager(id,pass))
            return "manager";
        if(generalOpController.isWorker(id,pass))
            return "worker";
        return "none";
    }

    // Search item by its id and size
    public void searchItem() {
        int id, size;
        Scanner s = new Scanner(System.in);
        System.out.println("enter item id: ");
        id = s.nextInt();
        System.out.println("enter the size: ");
        size = s.nextInt();
        Item item = itemController.searchItem(id, size);
        if(!itemController.isItemExists(id,size))
            System.out.println("the product does not exist");
        else if (item.getItemId()<0)
            System.out.println("the product doesn't in stock!");
        else {
                System.out.println("the id:"+item.getItemId());
                System.out.println("the color:"+item.getColor());
                System.out.println("the brand:"+item.getBrand());
                System.out.println("the gender:"+item.getGender());
                System.out.println("the type:"+item.getType());
                System.out.println("the price:"+item.getPrice());
                System.out.println("the size:"+item.getSize());
                System.out.println("current Stock:"+item.getCurrentStock());
                System.out.println("base Stock:"+item.getBaseStock());
                switch (item.getType()) {
                    case "shirt":
                        Shirt shirt = (Shirt)item;
                        System.out.println("shirt Type:"+ shirt.getShirtType());
                        break;
                    case "pants":
                        Pants pants = (Pants) item;
                        System.out.println("pants Type:"+pants.getPantsType());
                        break;
                    case "shoe":
                        Shoe shoe = (Shoe)item;
                        System.out.println("drawstring Color:" + shoe.getDrawstringColor());
                        break;
            }
        }
    }

    // Add new club member
    public void addClubMember(){
        Scanner s = new Scanner(System.in);
        boolean a;
        System.out.println("enter member's name: ");
        String name = s.next();
        System.out.println("enter birthday: ");
        String birthday = s.next();
        System.out.println("enter id: ");
        int id = s.nextInt();
        int gainedPoints = 0;
        Member m = new Member(name,birthday,id,gainedPoints);
        a = memberController.addClubMember(m);
        if(a == true)
        {
            System.out.println("action succeed");
        }
    }

    // Delete existing club member
    public void deleteClubMember(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter member id: ");
        int id = s.nextInt();
        if(memberController.deleteClubMember(id)){
            System.out.println("Deletion was successfully");
        } else{
            System.out.println("Unable to delete, no such user in the system");
        }
    }

    // Checks by id if the member exists or not
    public void isMemberExists() {
        Scanner s = new Scanner(System.in);
        System.out.println("enter member id: ");
        int id = s.nextInt();
        if(memberController.isExistsClubMember(id))
            System.out.println("the member exists");
        else System.out.println("the member doesn't exist");
    }

    // Do a selling process
    public void selling(){
        ArrayList<Item> items = new ArrayList<Item>();
        int price = 0, temp;
        Scanner s = new Scanner(System.in);
        System.out.println("how many items?");
        int num = s.nextInt();
        for(int i=0;i<num;i++){
            temp = i+1;
            System.out.println("enter the id of item number "+ temp);
            int id = s.nextInt();
            System.out.println("enter it's size");
            int size = s.nextInt();
            if(!itemController.isItemExists(id,size))
            {
                System.out.println("item:" +id+" is not exist!");
                return;
            }
            Item item = itemController.searchItem(id, size);
            items.add(item);
            items.get(i).setItemId(id);
            price += item.getPrice();
        }
        System.out.println("enter member id:");
        int memId = s.nextInt();
        Member m = memberController.searchMember(memId);
        if(m == null)
        {
            System.out.println("the member doesn't exist!");
            return;
        }
        System.out.println("enter shopping rating:");
        int shopRate = s.nextInt();

        Purchase p = new Purchase(m,items, price,shopRate);
        int pr = purchaseController.selling(p);
        if(pr<0)
        {
            System.out.println("stock is low for your Purchase - Purchase faild!");
        }
        else
        {
            System.out.println("The price is: " + pr);
            System.out.println("Purchase succeeded!");
        }
    }

    // Show the last purchase of specific member
    public void watchLastPurchase() {
        int temp;
        Scanner s = new Scanner(System.in);
        System.out.println("enter member id: ");
        int id = s.nextInt();
        Purchase p = purchaseController.lastPurchase(id);
        if(p==null)
        {
            System.out.println("the member is not exist or he didn't bought something in the store!");
            return;
        }
        for (int i = 0; i < p.getItem().size(); i++) {
            temp = i + 1;
            System.out.println("item number: " + temp);
            System.out.println("the color:" + p.getItem().get(i).getColor());
            System.out.println("the brand:" + p.getItem().get(i).getBrand());
            System.out.println("the gender:" + p.getItem().get(i).getGender());
            System.out.println("the type:" + p.getItem().get(i).getType());
            System.out.println("the price:" + p.getItem().get(i).getPrice());
            System.out.println("the size:" + p.getItem().get(i).getSize());
            switch (p.getItem().get(i).getType()) {
                case "shirt":
                    Shirt shirt = (Shirt) p.getItem().get(i);
                    System.out.println("shirt Type:" + shirt.getShirtType());
                    break;
                case "pants":
                    Pants pants = (Pants) p.getItem().get(i);
                    System.out.println("pants Type:" + pants.getPantsType());
                    break;
                case "shoe":
                    Shoe shoe = (Shoe) p.getItem().get(i);
                    System.out.println("drawstring Color:" + shoe.getDrawstringColor());
                    break;
            }
            System.out.print("\n");
        }
    }

    // Watch the monthly salary of worker
    public void watchMonthlySalary(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter your id: ");
        int id = s.nextInt();
        int temp = workerController.watchMonthlySalary(id);
        if(temp<0)
        {
            System.out.println("You are not registered!");
        }else {
            System.out.println("Your monthly salary is: " + temp +"\n");
        }
    }

    // Calculate the average selling rate of the store
    public void averageSellingRate() {
        System.out.println("the average selling rate is: " + generalOpController.averageSellingRate());
    }

    // Checks what is the best product in the store
    public void bestSellingProduct() {
        System.out.println("the best selling product is:");
        Item item = itemController.bestSellingProduct();
        System.out.println("the id:"+item.getItemId());
        System.out.println("the color:"+item.getColor());
        System.out.println("the brand:"+item.getBrand());
        System.out.println("the gender:"+item.getGender());
        System.out.println("the type:"+item.getType());
        System.out.println("the price:"+item.getPrice());
        System.out.println("the size:"+item.getSize());
        System.out.println("current Stock:"+item.getCurrentStock());
        System.out.println("base Stock:"+item.getBaseStock());
        switch (item.getType()) {
            case "shirt":
                Shirt shirt = (Shirt) item;
                System.out.println("shirt Type:" + shirt.getShirtType());
                break;
            case "pants":
                Pants pants = (Pants) item;
                System.out.println("pants Type:" + pants.getPantsType());
                break;
            case "shoe":
                Shoe shoe = (Shoe) item;
                System.out.println("drawstring Color:" + shoe.getDrawstringColor());
                break;
        }

    }

    // Add new item to the data base
    public void addItem(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the type:(shirt/shoe/pants)");
        String type = s.next();
        System.out.println("enter the id:");
        int id = s.nextInt();
        System.out.println("enter the color:");
        String color = s.next();
        System.out.println("enter the brand:");
        String brand = s.next();
        System.out.println("enter the gender:");
        String gender = s.next();
        System.out.println("enter the price:");
        int price = s.nextInt();
        System.out.println("enter the size:");
        int size = s.nextInt();
        System.out.println("enter current Stock:");
        int currStock = s.nextInt();
        System.out.println("enter base Stock:");
        int baseStock = s.nextInt();

        boolean i=false;
        switch (type) {
            case "shirt":
                System.out.println("enter shirt Type:");
                String shirtType = s.next();
                Shirt shirt = new Shirt(color,brand, gender, type, price, size, currStock, baseStock, id,shirtType );
                i= itemController.addShirt(shirt);
                break;
            case "pants":
                System.out.println("enter pants Type:");
                String pantsType = s.next();
                Pants pants = new Pants(color,brand,gender,type,price,size,currStock,baseStock,id,pantsType);
                i= itemController.addPants(pants);
                break;
            case "shoe":
                System.out.println("enter drawstring Color:");
                String drawstringColor = s.next();
                Shoe shoe = new Shoe(color,brand,gender,type,price,size,currStock,baseStock,id,drawstringColor);
                i= itemController.addShoe(shoe);
                break;
        }
        if(i)
            System.out.println("action succeeded!");
        else
            System.out.println("the item is already exists!");

    }

    // Change the hourly salary of a worker
    public void changeHourlySalary() {
        Scanner s = new Scanner(System.in);
        System.out.println("enter the id of the worker.");
        int id = s.nextInt();
        System.out.println("enter the new hourly salary of the worker.");
        int salary = s.nextInt();
        if(managerController.changeHourlySalary(id,salary))
            System.out.println("update successfully passed!");
        else System.out.println("worker are not registered! - update failed!\n");
    }

    // Add new worker ro the system
    public void addWorker(){
        Scanner s = new Scanner(System.in);
        System.out.println("enter the id of the worker: ");
        int id = s.nextInt();
        System.out.println("enter worker's name: ");
        String name = s.next();
        System.out.println("enter birthday: ");
        String birthday = s.next();
        System.out.println("enter hourly salary: ");
        int hourlySalary = s.nextInt();
        System.out.println("enter num Hour Month salary: ");
        int numHourMonth = s.nextInt();
        System.out.println("enter job type: ");
        String jobType = s.next();
        System.out.println("enter password: ");
        String pass = s.next();

        Worker w = new Worker(name, birthday, id, hourlySalary,numHourMonth,jobType,pass );
        if(managerController.addWorker(w))
        {
            System.out.println("action succeeded!");
        }
        else
        {
            System.out.println("the worker is already exists!");
        }
    }

    // Check the current stock.
    public void runCheckCurrentStock() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ArrayList<String> itemsLow = new ArrayList<String>();
               itemsLow = autoFuncController.checkCurrentStock();
              System.out.println(itemsLow.toString());

            }
        };

        Timer timer = new Timer();
        timer.schedule(task, new Date(), 300000);
    }
}
