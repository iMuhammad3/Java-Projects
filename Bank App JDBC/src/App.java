import java.util.Scanner;

public class App {
    public static void run(){
        int choice;
        do{
            System.out.println("Do you have an account?");
            System.out.println("1. Yes, Sign me in!");
            System.out.println("2. Nope.. I want to create an account");
            System.out.println("99. Quit");
            choice = new Scanner(System.in).nextInt();

            switch(choice){
                case 1 -> signIn();
                case 2 -> createAccount();
                case 99 -> System.out.println("Bye!");
                default -> System.out.println("Please select a valid option :)");
            }
        }while(choice != 99);
    }

    private static void createAccount(){
        System.out.println("Glad you decided to create an account!");
        String fullName = validateInput("Please enter your full name: ");
        String username = validateInput("Create a username: ");
        String password = validateInput("Create a password");
        String confirmedPassword = validateInput("Please confirm the password: ");
    }

    // checks the users input to see if it's an empty string
    private static String validateInput(String query){
        Scanner scanner = new Scanner(System.in);
        String field;
        do{
            System.out.println(query);
            field = scanner.nextLine();
            if(field.isEmpty()){
                System.out.println("You did not enter a value");
            }
        }while(field.isEmpty());
        return field;
    }

    private static void signIn(){
        System.out.println("sign in");
    }

}
