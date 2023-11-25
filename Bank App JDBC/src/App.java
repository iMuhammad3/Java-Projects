import java.util.Scanner;

public class App {

    User user;

    public App(){
        user = null;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        Auth auth = new Auth();
        do{
            System.out.println("Do you have an account?");
            System.out.println("1. Yes, Sign me in!");
            System.out.println("2. Nope.. I want to create an account");
            System.out.println("99. Quit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1 -> {
                    user = auth.signIn();
                    if(user == null){
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
        System.out.println("Welcome " + user.getFullName() + "!");
        System.out.println("What would you like to do?");
        do{
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check balance");
            System.out.println("4. Transfer money to others");
            System.out.println(lastChoice + ". Back to main menu");
            choice = scanner.nextInt();

            switch (choice){
                case 1 -> withdraw();
                case 2 -> deposit();
                case 3 -> System.out.println("Your balance is " + user.getBalance());
            }
        }while(choice != lastChoice);
    }

    private void withdraw(){
        double userBalance = user.getBalance();
        System.out.println("How much would you like to withdraw?");
        double amount = new Scanner(System.in).nextDouble();
        if(userBalance >= amount){
            double debitAmount = userBalance - amount;
            System.out.println("Processing...");
            int rowAffected = Database.updateBalance(user.getId(), debitAmount);
            if(rowAffected > 0){
                user.setBalance(debitAmount);
                System.out.println("Your account has been debited by " + debitAmount);
                System.out.println("You have " + user.getBalance() + " remaining.");
            } else {
                System.out.println("Something went wrong");
            }
        } else {
            System.out.println("You don't have enough money :(");
        }
    }

    private void deposit(){
        System.out.println("How much would you like to deposit?");
        double amount = new Scanner(System.in).nextDouble();
        double totalAmount = user.getBalance() + amount;
        System.out.println("Processing...");
        int rowAffected = Database.updateBalance(user.getId(), totalAmount);
        if(rowAffected > 0){
            user.setBalance(totalAmount);
            System.out.println("You have been credited with " + amount);
            System.out.println("You now have " + user.getBalance() + " in your account");
        } else {
            System.out.println("Something went wrong");
        }
    }

}
