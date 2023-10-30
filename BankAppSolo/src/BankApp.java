import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class BankApp {
    static void runApp(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Bank App");
        System.out.println("Enter an option: ");
        System.out.println("1. Create an account");
        System.out.println("2. Login to existing account");
        int choice = scanner.nextInt();
        if(choice == 1){
            createAccount();
        } else {
            loginAccount();
        }
        scanner.close();
    }
    static void createAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account name: ");
        String accountName = scanner.nextLine();
        BankAccount account = new BankAccount(accountName);
        File file = new File("./files/" + account.username + ".txt");

        try(FileWriter writer = new FileWriter(file);) {

            writer.write(account.toString());
            System.out.println("Account created successfully!");
            System.out.println("Account Details: ");
            System.out.println(account.toString());

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    static void loginAccount(){
        System.out.println("Enter account name: ");
        Scanner scanner = new Scanner(System.in);
        String accountName = scanner.nextLine();
        String directoryPath = "./files/";

        Path directory = Paths.get(directoryPath);

        try{
            Files.walk(directory)
                    .filter(file -> file.getFileName().toString().equals(accountName+".txt"))
                    .forEach(file -> {
                        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
