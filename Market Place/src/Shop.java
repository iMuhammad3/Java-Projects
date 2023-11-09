import java.util.List;
import java.util.Scanner;

public class Shop {

    Customer customer;
    public Shop(){
        customer = new Customer();
    }

    public void startShopping() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Hello! Welcome to the Market place");
        do {
            System.out.println("What would you like to do?");
            System.out.println("1. Buy products");
            System.out.println("2. Sell products");
            System.out.println("99. Leave market");
            choice = scanner.nextInt();

            switch (choice){
                case 1 -> buyProducts();
                default -> {
                    if (choice == 99) {
                        System.out.println("Bye!!!");
                    } else {
                        System.out.println("Please choose a valid option");
                    }
                }
            }
        } while (choice != 99);
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
            System.out.println(
                    (i + 1) + ". Name: " + products.get(i).getTitle() +
                    ".\n\t Price: " + products.get(i).getPrice()
            );
            System.out.println("-------------------------");
        }
        System.out.println("Would you like to buy anyone?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        choice = scanner.nextInt();
        if(choice == 1){
            buyFrom30Products(products);
        } else {
            System.out.println("Quitting");
        }
    }

    void buyFrom30Products(List<Product> products){
        Scanner scanner = new Scanner(System.in);
        int productNo;
        int productsSize = products.size();
        System.out.println("What product would you like to buy (the options from 1 - " + productsSize + ")");
        productNo = scanner.nextInt();
        if(productNo < 1 || productNo > productsSize) return;
        // start looping from 1 to be match with user choice
        for(int i = 1;i <= productsSize;i++){
            if(productNo == i){
                customer.addProduct(products.get(i - 1));
            }
        }
    }
}
