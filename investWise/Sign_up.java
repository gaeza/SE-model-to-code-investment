package investWise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User{
    private String username;
    private String email;
    private String password;

    public User(String userName,String Email,String Password){
        this.username=userName;
        this.email=Email;
        this.password=Password;
    }

    //for validation
    public String getEmail(){
        return email;
    }

    public String getUserName(){
        return username;
    }
}

public class Sign_up {

    public static List<User>users=new ArrayList<>();

    public static boolean isUsedEmail(String Email){
        for(User user : users){
            if(user.getEmail().equalsIgnoreCase(Email)){
                return true;
            }
        }
        return false;
    }

    public static boolean isUsedUserName(String Name){
        for(User user : users){
            if(user.getUserName().equals(Name)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isValidEmail(String Email){
        
        return Email.contains("@") && Email.contains(".");

    }

    public static void signUp(){

        Scanner scanner = new Scanner(System.in);

        String username;
        while (true) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();

            if (isUsedUserName(username)) {
                System.out.println("This username is already taken. Please try another one.");
            } else {
                break; // valid and not taken
            }
        }

        String email;
        while (true) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine();

            if (!isValidEmail(email)) {
                System.out.println("Invalid email format. Please enter a valid email.");
            } else if (isUsedEmail(email)) {
                System.out.println("This email is already registered. Try another one.");
            } else {
                break; // valid and not used
            }
        }

        System.out.print("Enter Password");
        String password= scanner.nextLine();

        users.add(new User(username,email, password));
        System.out.println("Sign up successful! Redirecting to login page...");

        scanner.close();
    }

    public static void main(String[] args) {
        signUp();  // you can call this in a loop or with a menu in real use
    }
    

}

