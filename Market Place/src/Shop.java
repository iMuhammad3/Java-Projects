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
        List<Product> products = Results.fetchAllProducts();
        for(Product product : products){
            System.out.println(product.getTitle());
        }
    }
}
