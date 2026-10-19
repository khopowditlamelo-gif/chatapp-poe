package chatapp;

import java.util.Scanner;

/**
 * Main
 *
 * Console entry point: lets a user register an account, log in,
 * and then use QuickChat to send messages.
 */
public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
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
                
                // Messaging is only available after a successful login.
                if (loginSuccessful) {
                    startQuickChat(scanner);
                }
            }
        }
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void startQuickChat(Scanner sc) {
        System.out.println("\nWelcome to QuickChat.");
        int maxMessages = readInt(sc, "How many messages do you want to enter? ");
        int entered = 0;
        boolean running = true;

        while (running) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            int option = readInt(sc, "Choose an option: ");

            switch (option) {
                case 1 -> {
                    if (entered >= maxMessages) {
                        System.out.println("You have already entered all " + maxMessages + " messages.");
                        break;
                    }

                    String recipient;
                    while (true) {
                        System.out.print("Recipient cell number (with international code): ");
                        recipient = sc.nextLine().trim();
                        if (Message.isValidRecipient(recipient)) {
                            System.out.println("Cell phone number successfully captured.");
                            break;
                        }
                        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                    }

                    String text;
                    while (true) {
                        System.out.print("Message: ");
                        text = sc.nextLine();
                        if (text.length() <= 250) {
                            System.out.println("Message sent");
                            break;
                        }
                        System.out.println("Please enter a message of less than 250 characters.");
                    }

                    Message m = new Message(entered, recipient, text);
                    System.out.println("1) Send Message  2) Disregard Message  3) Store Message to send later");
                    int choice = readInt(sc, "Choose: ");
                    System.out.println(m.sentMessage(choice));
                    System.out.println(m.details());
                    entered++;
                }

                case 2 -> System.out.println("Coming Soon.");

                case 3 -> running = false;

                default -> System.out.println("Invalid option.");
            }
        }

        System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
    }
}