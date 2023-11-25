import java.util.Scanner;

public class App {
    public  void run(){
        int choice;
        Auth auth = new Auth();
        do{
            System.out.println("Do you have an account?");
            System.out.println("1. Yes, Sign me in!");
            System.out.println("2. Nope.. I want to create an account");
            System.out.println("99. Quit");
            choice = new Scanner(System.in).nextInt();

            switch(choice){
                case 1 -> auth.signIn();
                case 2 -> auth.createAccount();
                case 99 -> System.out.println("Bye!");
                default -> System.out.println("Please select a valid option :)");
            }
        }while(choice != 99);
    }


    private  void loader(String message){
        System.out.println(message);
    }

}
