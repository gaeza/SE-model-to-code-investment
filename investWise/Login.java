package investWise;

import java.util.Scanner;

public class Login {

    public static void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        // Check if username exists
        User foundUser = null;
        for (User user : User.getUsers()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                foundUser = user;
                break;
            }
        }

        if (foundUser == null) {
            System.out.println("Username not exist ! PLS sign up first");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (foundUser.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + foundUser.getUsername());
        } else {
            System.out.println("Wrong password ! PLS Try again");
        }
    }
}
