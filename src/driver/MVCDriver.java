package driver;
import view.UserInterface;

public class MVCDriver {
    // The running of the application starts here
    public static void main(String[] args) {
        UserInterface view = new UserInterface();
        view.start();
    }
}

