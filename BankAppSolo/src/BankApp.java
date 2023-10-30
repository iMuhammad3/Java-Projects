import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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

    static void displayOptions(BankAccount account, File file) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome " + account.username + "!");
        int choice;
        do{
            System.out.println("What would you like to do?");
            System.out.println("1. Check account details");
            System.out.println("2. Change account name");
            System.out.println("3. Check balance");
            System.out.println("4. Withdraw money");
            System.out.println("5. Deposit money");
            System.out.println("99. Quit");

            choice = scanner.nextInt();

            handleCase(choice, account, file);
        } while(choice != 99);
        System.out.println("See you next time!!");
    }

    static void handleCase(int choice, BankAccount account, File file) throws IOException {

        switch (choice){
            case 1:
                System.out.println("Account details: ");
                System.out.println(account.toString());
                break;
            case 2:
                System.out.println("Enter new name: ");
                String name = new Scanner(System.in).nextLine();
                account.setUsername(name);
                new FileWriter(file).write(account.toString());
                System.out.println("Account name has been changed to " + account.username);
                break;
            case 3:
                System.out.println("Balance: " + account.balance);
                break;
            case 4:
                System.out.println("Enter amount: ");
                double wAmount = new Scanner(System.in).nextDouble();
                account.withdraw(wAmount);
                new FileWriter(file).write(account.toString());
                System.out.println(wAmount + " has been debited from your account!");
                break;
            case 5:
                System.out.println("Enter amount: ");
                double dAmount = new Scanner(System.in).nextDouble();
                account.deposit(dAmount);
                new FileWriter(file).write(account.toString());
                System.out.println(dAmount + " has been credited to your account!");
                break;
        }
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

            displayOptions(account, file);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    static void loginAccount(){
        BankAccount account;
        System.out.println("Enter account name: ");
        Scanner scanner = new Scanner(System.in);
        String accountName = scanner.nextLine();

        try{

            File file = findFile(accountName);
            String fileContent = readContent(file);

            HashMap<String,String> details = findDetails(fileContent);

            String username = details.get("Username");
            String userId= details.get("UserId");
            String balance = details.get("Balance");

            account = new BankAccount(username, Integer.valueOf(balance), userId);
            System.out.println(account.toString());
            displayOptions(account, file);

        } catch (IOException e){
            System.out.println("Error: "+ e.getMessage());
        }
        finally {
            scanner.close();
        }
    }

    static HashMap<String,String> findDetails(String line){
        HashMap<String,String> details = new HashMap<>();
        Pattern userNamePattern = Pattern.compile("Username:\\s+(\\w+)");
        Matcher userNameMatcher = userNamePattern.matcher(line);
        Pattern userIdPattern = Pattern.compile("User Id:\\s+(\\w+)");
        Matcher userIdMatcher = userIdPattern.matcher(line);
        Pattern balancePattern = Pattern.compile("Balance:\\s+(\\d+)");
        Matcher balanceMatcher = balancePattern.matcher(line);

        if (userNameMatcher.find() && userIdMatcher.find() && balanceMatcher.find()) {
            String username = userNameMatcher.group(1);
            details.put("Username", username);
            String userId = userIdMatcher.group(1);
            details.put("UserId", userId);
            String balance = balanceMatcher.group(1);
            details.put("Balance", balance);
        }

        return details;
    }

    static File findFile(String fileToFind){
        String directoryPath = "./files/";

        Path directory = Paths.get(directoryPath);

        try(Stream<Path> files = Files.walk(directory)){

            Path foundFile = files.filter(file -> file.getFileName().toString().equals(fileToFind+".txt"))
                    .findFirst()
                    .orElse(null);

            return foundFile.toFile();

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    static String readContent(File file) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        Files.lines(file.toPath()).forEach(line -> fileContent.append(line).append("\n"));
        return fileContent.toString();
    }
}
