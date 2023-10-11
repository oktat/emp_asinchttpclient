import controllers.AuthController;
import controllers.EmployeeController;

public class App {
    public static void main(String[] args) throws Exception {
        // new EmployeeController().index();
        // new EmployeeController().create();
        // new EmployeeController().update();
        new EmployeeController().delete();
        
        // new AuthController().register();
        // new AuthController().login();
    }

}
