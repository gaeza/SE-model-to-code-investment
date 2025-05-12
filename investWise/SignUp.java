package investWise;

import java.util.Scanner;


public class SignUp {
    
    public static boolean isUsedEmail(String Email){
        for(User user : User.getUsers()){
            if(user.getEmail().equalsIgnoreCase(Email)){
                return true;
            }
        }
        return false;
    }

    public static boolean isUsedUserName(String Name){
        for(User user : User.getUsers()){
            if(user.getUsername().equals(Name)){
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

        // Create and add user
        User newUser = new User(username, email, password);
        User.addUser(newUser); // add user to the list and save

        System.out.println("Sign up successful! Redirecting to login page...");

        }
    
 }

