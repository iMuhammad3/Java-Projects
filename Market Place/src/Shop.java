import java.util.List;
import java.util.Scanner;

public class Shop {
    public Shop(){}

    public void startShopping() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Welcome to the Market place");
        System.out.println("What would you like to do?");
        System.out.println("1. Buy products");
        System.out.println("2. Sell products");
        int choice = scanner.nextInt();

        switch (choice){
            case 1 -> buyProducts();
            default -> System.out.println("Please choose a valid option");
        }
    }

    public void buyProducts() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select an option: ");
        System.out.println("1. View 30 available products");
        System.out.println("2. Search for products by name");
        System.out.println("3. Search for products by category");
        int choice = scanner.nextInt();
        switch (choice){
            case 1 -> displayAllProducts();
        }
    }

    private void displayAllProducts() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        List<Product> products = Results.fetchAllProducts();
        for(int i = 0;i < products.size();i++){
            System.out.println((i + 1) + ". " + products.get(i).getTitle());
        }
        System.out.println("Would you like to buy anyone?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        choice = scanner.nextInt();
        if(choice == 1){

        } else if(choice == 2){
            return;
        } else {
            System.out.println("Quitting anyway");
        }
    }

    void buyFrom30Products(List<Product> products){
        Scanner scanner = new Scanner(System.in);
        int productNo;
        System.out.println("What product would you like to buy (the options from 1 - 30)");
        productNo = scanner.nextInt();
        if(productNo < 1 || productNo > 30) return;
        // start looping from 1 to be match with user choice
        for(int i = 1;i <= products.size();i++){
            if(productNo == i){

            }
        }
    }
}
