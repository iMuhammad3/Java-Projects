import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

public class App {
    public App(){}

    public void run(){
        // initialize choice variable
        int choice;
        System.out.println("Welcome to the To Do App");
        System.out.println("What would you like to do?");
        // keep running the app until user quits
        do{
            System.out.println("1. Create a todo");
            System.out.println("2. View to do's");
            System.out.println("3. Modify to do's");
            System.out.println("99. Quit");
            // get users choice with Scanner and assign it to choice variable
            choice = new Scanner(System.in).nextInt();
            switch (choice){
                case 1 -> createTodo();
                case 2 -> viewTodos();
                case 3 -> modifyTodos();
                case 99 -> System.out.println("Bye!");
                default -> System.out.println("Please choose a valid option");
            }
        } while(choice != 99); // break loop if user chooses 99
    }

    public void createTodo(){
        System.out.println("Enter Todo: ");
        // read description from user and assign to variable
        String description = new Scanner(System.in).nextLine();
        System.out.println("Creating todo...");
        // insert a new row (To Do) into the database
        Database.insertData(description);
        System.out.println("You have successfully created the to do!");
    }

    public void viewTodos(){
        System.out.println("Do you want to");
        System.out.println("1. View all your todos");
        System.out.println("2. View completed todos");
        System.out.println("3. View uncompleted todos");
        System.out.println("4. View last modified todo");

        int choice = new Scanner(System.in).nextInt();
        switch (choice){
            case 1 -> displayAllTodos();
        }
    }

    public void askToSort(){
        System.out.println("Would you like to sort the todos?");
        System.out.println("1. Yes\n2. No");
        int choice = new Scanner(System.in).nextInt();
        if(choice == 1){

        } else if(choice == 2) {

        } else {
            System.out.println("Please choose a valid option");
        }
    }

    public void displayAllTodos(){
        System.out.println("Fetching todos...");
        // receive List of fetched todos from method
        List<ToDo> todos = Database.fetchTodos();
        // check if there are any todos
        if(todos == null || todos.isEmpty()){
            System.out.println("You have no todos");
        } else {
            // format and print out To Do details
            for(ToDo todo : todos){
                System.out.println("---------------------------------------------------");
                System.out.println(
                        "\t" + "ID: " + todo.getId() + "\n" +
                        "\t" + "Description: " + todo.getDescription() + "\n" +
                        "\t" + "Completed: " + todo.getCompleted() + "\n" +
                        "\t" + "Date Created: " + todo.getDateCreated() + "\n" +
                        "\t" + "Last Modified: " + todo.getLastModified()
                );
            }
        }
        System.out.println();
    }

    public void modifyTodos(){

    }
}
