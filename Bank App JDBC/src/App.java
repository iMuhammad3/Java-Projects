import java.time.LocalDate;
import java.util.Scanner;

public class App {

    User currentUser;

    public App(){}

    public void run(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        Auth auth = new Auth();
        do{
            System.out.println("\nDo you have an account?");
            System.out.println("1. Yes, Sign me in!");
            System.out.println("2. Nope.. I want to create an account");
            System.out.println("99. Quit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1 -> {
                    currentUser = auth.signIn();
                    if(currentUser == null){
                        System.out.println("Account was not found\nPlease check your details and try again");
                    } else {
                        welcomeUser();
                    }
                }
                case 2 -> auth.createAccount();
                case 99 -> System.out.println("Bye!");
                default -> System.out.println("Please select a valid option :)");
            }
        }while(choice != 99);
    }

    private void welcomeUser(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        int lastChoice = 5;
        System.out.println("\nWelcome " + currentUser.getFullName() + "!");
        System.out.println("What would you like to do?");
        do{
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check balance");
            System.out.println("4. Transfer money to others");
            System.out.println(lastChoice + ". Sign Out");
            choice = scanner.nextInt();

            switch (choice){
                case 1 -> withdraw();
                case 2 -> deposit();
                case 3 -> System.out.println("Your balance is " + currentUser.getBalance());
                case 4 -> transfer();
            }
        }while(choice != lastChoice);
    }

    private void withdraw(){
        double userBalance = currentUser.getBalance();
        System.out.println("\nHow much would you like to withdraw?");
        double amount = new Scanner(System.in).nextDouble();
        if(userBalance >= amount){
            double debitAmount = userBalance - amount;
            System.out.println("Processing...");
            int rowAffected = Database.updateBalance(currentUser.getId(), debitAmount);
            if(rowAffected > 0){
                currentUser.setBalance(debitAmount);
                System.out.println("\nYour account has been debited by " + amount);
                System.out.println("You have " + currentUser.getBalance() + " remaining.");
            } else {
                System.out.println("\nSomething went wrong");
            }
        } else {
            System.out.println("\nYou don't have enough money :(");
        }
    }

    private void deposit(){
        System.out.println("\nHow much would you like to deposit?");
        double amount = new Scanner(System.in).nextDouble();
        double totalAmount = currentUser.getBalance() + amount;
        System.out.println("Processing...");
        int rowAffected = Database.updateBalance(currentUser.getId(), totalAmount);
        if(rowAffected > 0){
            currentUser.setBalance(totalAmount);
            System.out.println("\nYou have been credited with " + amount);
            System.out.println("You now have " + currentUser.getBalance() + " in your account");
        } else {
            System.out.println("\nSomething went wrong");
        }
    }
    
    private void transfer(){
        Scanner scanner = new Scanner(System.in);
        User user;
        System.out.println("\nEnter the accounts username: ");
        String usernameInput = scanner.nextLine();
        System.out.println("Searching for user...");
        user = Database.searchUser(usernameInput);
        if(user == null){
            System.out.println("User was not found :(");
            return;
        }
        System.out.println("\nFound user: " + user.getFullName());
        System.out.println("How much would you like to send to " + user.getFullName());
        double amount = scanner.nextDouble();
        if(amount > currentUser.getBalance()){
            System.out.println("You don't have enough money!");
            return;
        }
        System.out.println("Sending " + amount + " to " + user.getFullName() + "...");

        double creditAmount = user.getBalance() + amount;
        double debitAmount = currentUser.getBalance() - amount;

        int userRowAffected = Database.updateBalance(user.getId(), creditAmount);
        int currentUserRowAffected = Database.updateBalance(currentUser.getId(), debitAmount);

        // check if transaction fails (if one query fails)
        if(
                (userRowAffected > 0 && currentUserRowAffected == 0)
                     ||
                (userRowAffected == 0 && currentUserRowAffected > 0)
        ) {
            System.out.println("Transaction failed");
            System.out.println("Reversing money");
            // return former money back to both accounts
            Database.updateBalance(user.getId(), user.getBalance());
            Database.updateBalance(currentUser.getId(), currentUser.getBalance());
        } else if(userRowAffected > 0 && currentUserRowAffected > 0){
            System.out.println("Transaction successful");

            // update user objects
            user.setBalance(creditAmount);
            currentUser.setBalance(debitAmount);
            System.out.println("\nTransaction details: ");
            System.out.println("\tSender's Name: " + currentUser.getFullName());
            System.out.println("\tReceiver's Name: " + user.getFullName());
            System.out.println("\tDebit: " + amount);
            System.out.println("\tDate: " + LocalDate.now());
            System.out.println("\tBalance: " + currentUser.getBalance());
        }
    }

}
