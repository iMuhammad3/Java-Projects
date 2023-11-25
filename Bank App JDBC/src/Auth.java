import java.util.Scanner;

public class Auth {

    public Auth(){}

    public void createAccount(){
        System.out.println("Glad you decided to create an account!");
        String fullName = validateInput("Please enter your full name: ");
        String username = validateInput("Create a username: ");
        String password = validateInput("Create a password: ");
        String confirmedPassword = validateInput("Confirm password: ");

        while(!password.equals(confirmedPassword)){
            System.out.println("Passwords don't match\nPlease try again");
            password = validateInput("Create a password: ");
            confirmedPassword = validateInput("Create a password: ");
        }

        System.out.println("Creating account...");
        Database.insertUser(username, password, fullName);

    }

    // checks the users input to see if it's an empty string
    private String validateInput(String query){
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

    public User signIn(){
        Scanner scanner = new Scanner(System.in);
        User user;

        System.out.println("Enter your username: ");
        String usernameInput = scanner.nextLine();

        System.out.println("Enter your password: ");
        String passwordInput = scanner.nextLine();

        System.out.println("Searching for account...");

        user = Database.checkUser(usernameInput, passwordInput);

        return user;
    }
}
