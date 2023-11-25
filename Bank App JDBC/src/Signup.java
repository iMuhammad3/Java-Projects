import java.util.Scanner;

public class Signup {

    public Signup(){}

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

        System.out.println("Creating a account...");
        Database.insertUser(username, password, fullName);

    }

    // checks the users input to see if it's an empty string
    public String validateInput(String query){
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
}
