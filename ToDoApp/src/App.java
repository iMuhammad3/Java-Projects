import java.util.ArrayList;
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

    private void createTodo(){
        System.out.println("Enter Todo: ");
        // read description from user and assign to variable
        String description = new Scanner(System.in).nextLine();
        System.out.println("Creating todo...");
        // insert a new row (To Do) into the database
        Database.insertData(description);
        System.out.println("You have successfully created the to do!");
    }

    private void viewTodos(){
        System.out.println("Do you want to");
        System.out.println("1. View all your todos");
        System.out.println("2. View completed todos");
        System.out.println("3. View uncompleted todos");
        System.out.println("4. View last modified todo");

        int choice = new Scanner(System.in).nextInt();
        loader();
        switch (choice){
            case 1 -> viewAllTodos();
            case 2 -> displayTodos(Database.fetchTodos("completed","true"));
            case 3 -> displayTodos(Database.fetchTodos("completed","false"));
            case 4 -> displayTodos(Database.fetchTodos("last_modified desc limit 1"));
            default -> System.out.println("Choose a valid option");
        }
    }

    private void viewAllTodos(){
        System.out.println("Would you like to sort the todos?");
        System.out.println("1. Yes\n2. No");
        int choice = new Scanner(System.in).nextInt();
        if(choice == 1){
            loader();
            displayTodos(sortTodos());
        } else if(choice == 2) { // display unsorted todos
            loader();
            displayTodos(Database.fetchTodos());
        } else {
            System.out.println("Please choose a valid option");
        }
    }

    private List<ToDo> sortTodos(){
        List<ToDo> todos = new ArrayList<>();
        System.out.println("How would you like to sort by?");
        System.out.println("1. By Id");
        System.out.println("2. By Date Created");
        System.out.println("3. By Last Modified");
        int choice = new Scanner(System.in).nextInt();
        switch(choice){
            case 1 -> todos = Database.fetchTodos("id");
            case 2 -> todos = Database.fetchTodos("date_created");
            case 3 -> todos = Database.fetchTodos("last_modified");
            default -> System.out.println("You didn't choose a valid option");
        }
        return todos;
    }

    private void displayTodos(List<ToDo> todos){
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

    private void modifyTodos(){

    }

    private void loader(){
        System.out.println("Fetching todos...");
    }
}
