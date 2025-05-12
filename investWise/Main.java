package investWise;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        User.loadUsers();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("***WELCOM TO INVESTWISE***");
        System.out.println("for sing-up type '1' ");
        System.out.println("for login type '2' ");
        String choice= scanner.nextLine();
        if(choice.equals("1")){
            SignUp.signUp();
            Login.login();
        }
        else if(choice.equals("2")){
            Login.login();
        }
        else{
            System.out.println("EXITING...");
        }
        scanner.close();
    }

}
