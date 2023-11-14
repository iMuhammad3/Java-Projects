import java.util.Scanner;

public class App {
    public App(){}

    public void run(){
        int choice = 0;
        System.out.println("Welcome to the To Do App");
        System.out.println("What would you like to do?");
        do{
            System.out.println("1. Create a todo");
            System.out.println("2. View to do's");
            System.out.println("3. Modify to do's");
            System.out.println("99. Quit");
            // assign users choice to variable
            choice = new Scanner(System.in).nextInt();
            switch (choice){
                case 1 -> createTodo();
                case 2 -> viewTodos();
                case 3 -> modifyTodos();
                default -> {
                    if(choice == 99){
                        System.out.println("Bye!");
                    } else {
                        System.out.println("Please choose a valid option");
                    }
                }
            }
        } while(choice != 99); // break loop if user chooses 99
    }

    public void createTodo(){

    }

    public void viewTodos(){

    }

    public void modifyTodos(){

    }
}
