import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public App(){}

    String basicSQL = "select * from to_do ";

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
        loader("Creating todo...");
        // insert a new row (To Do) into the database
        int rowAffected = Database.insertTodo(description);
        if(rowAffected == 1){
            System.out.println("Todo created successfully!");
        } else if(rowAffected == 0) {
            System.out.println("Unable to create todo. Please try again");
        }
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
            case 2 -> displayTodos(Database.fetchTodos(basicSQL + " where completed = true"));
            case 3 -> displayTodos(Database.fetchTodos(basicSQL + " where uncompleted = true"));
            case 4 -> displayTodos(Database.fetchTodos(basicSQL+ " order by last_modified desc limit 1"));
            default -> System.out.println("Choose a valid option");
        }
    }

    private void viewAllTodos(){
        System.out.println("Would you like to sort the todos?");
        System.out.println("1. Yes\n2. No");
        int choice = new Scanner(System.in).nextInt();
        if(choice == 1){
            displayTodos(sortTodos());
        } else if(choice == 2) { // display unsorted todos
            loader();
            displayTodos(Database.fetchTodos(basicSQL));
        } else {
            System.out.println("Please choose a valid option");
        }
    }

    private List<ToDo> sortTodos(){
        List<ToDo> todos = new ArrayList<>();
        System.out.println("What sort method do you prefer?");
        System.out.println("1. By Id");
        System.out.println("2. By Date Created");
        System.out.println("3. By Last Modified");
        int choice = new Scanner(System.in).nextInt();
        loader();
        switch(choice){
            case 1 -> todos = Database.fetchTodos(basicSQL + " order by id");
            case 2 -> todos = Database.fetchTodos(basicSQL + " order by date_created");
            case 3 -> todos = Database.fetchTodos(basicSQL + " order by last_modified");
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
        loader();
        List<ToDo> todos = Database.fetchTodos(basicSQL);
        ToDo selectedTodo = null;
        System.out.println("Which of your todos would you like to modify? (provide the todo's id)");
        displayTodos(todos);
        int id = new Scanner(System.in).nextInt();
        if(todos != null){
            for(ToDo todo : todos){
                if(todo.getId() == id){
                    selectedTodo = todo;
                }
            }
        } else {
            System.out.println("No todos found");
        }
        if(selectedTodo == null){
            System.out.println("To Do not found, please check again");
        } else {
            System.out.println("How would you like to modify this todo?");
            System.out.println("1. Change completed state");
            System.out.println("2. Change todo description");
            System.out.println("3. Delete todo");
            int choice = new Scanner(System.in).nextInt();
            switch (choice){
                case 1 -> changeComplete(id);
                case 2 -> changeTodoDescription(id);
                case 3 -> deleteTodo(id);
            }
        }
    }

    private void changeComplete(int id){
        loader("Updating...");
        boolean completed = Database.updateCompleted(id);
        System.out.println("Todo's completed has been set to " + completed);
    }

    private void changeTodoDescription(int id){
        System.out.println("Enter new description: ");
        String description = new Scanner(System.in).nextLine();
        loader("Updating...");
        Database.changeDescription(id, description);
        System.out.println("To do description has been modified successfully!");
    }

    private void deleteTodo(int id){
        loader("Deleting todo...");
        int rowAffected = Database.deleteTodo(id);
        if(rowAffected == 1){
            System.out.println("Todo has successfully been deleted");
        } else {
            System.out.println("Something went wrong, please check and try again");
        }
    }

    private void loader(){
        System.out.println("Fetching todos...");
    }
    private void loader(String message){
        System.out.println(message);
    }
}
