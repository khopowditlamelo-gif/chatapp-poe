package chatapp;

import java.util.Scanner;

/**
 * Main
 *
 * Console entry point for Part 1: lets a user register an account and then
 * log in, using the Login class for all validation and messaging.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Chat App - Registration ===");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter a username (must contain an underscore, max 5 characters): ");
        String username = scanner.nextLine();

        System.out.print("Enter a password (min 8 characters, 1 capital, 1 number, 1 special character): ");
        String password = scanner.nextLine();

        System.out.print("Enter your cell phone number (with +27 country code): ");
        String cellPhoneNumber = scanner.nextLine();

        String registrationResult = login.registerUser(firstName, lastName, username, password, cellPhoneNumber);
        System.out.println("\n" + registrationResult);

        // Only move on to login if registration actually succeeded.
        if (registrationResult.contains("Registration successful")) {
            System.out.println("\n=== Chat App - Login ===");

            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();

            boolean loginSuccessful = login.loginUser(loginUsername, loginPassword);
            System.out.println("\n" + login.returnLoginStatus(loginSuccessful));
        }

        scanner.close();
    }
}