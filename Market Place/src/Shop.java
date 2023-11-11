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
            System.out.println("3. Check my balance");
            System.out.println("4. View my products");
            System.out.println("99. Leave market");
            choice = scanner.nextInt();

            switch (choice){
                case 1 -> buyProducts();
                case 2 -> sellProducts();
                case 3 -> System.out.println("Your balance is " + customer.getMoney());
                case 4 -> viewProducts();
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
            case 2 -> searchProductByName();
            case 3 -> searchProductByCategory();
            default -> System.out.println("Enter a valid option");
        }
    }

    public void sellProducts() {
        List<Product> products = customer.getProducts();
        Product productToSell = null;
        if(products.isEmpty()){
            System.out.println("You don't have any products to sell");
            return;
        }
        System.out.println("Which of your products would you like to sell?");
        for(int i = 0;i < products.size();i++){
            System.out.println((i+1) + ". " + products.get(i).getTitle());
        }
        int choice = new Scanner(System.in).nextInt();
        for(int i = 0;i < products.size();i++){
            if(choice == (i+1)){
                productToSell = products.get(i);
                break;
            }
        }
        products.remove(productToSell);
        customer.setProducts(products);
        customer.setMoney(
                customer.getMoney() + productToSell.getPrice()
        );
        System.out.println(
                productToSell.getTitle() +
                " has been sold and you've gained $" +
                productToSell.getPrice()
        );
    }

    public void viewProducts(){
        List<Product> customerProducts = customer.getProducts();
        if(customerProducts.isEmpty()){
            System.out.println("You don't have any products");
            return;
        }
        System.out.println("These are your products:");
        for(Product product : customerProducts){
            System.out.println("\t" + product.getTitle());
        }
    }

    private void displayAllProducts() throws Exception {
        List<Product> products = Results.fetchProducts("https://dummyjson.com/products");
        displayProducts(products);
    }

    private void searchProductByName() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What product would you like to search for?");
        String productInput = scanner.nextLine();
        List<Product> products = Results.fetchProducts("https://dummyjson.com/products/search?q=" + productInput);
        displayProducts(products);
    }

    private void searchProductByCategory() throws Exception {
        System.out.println("Fetching available categories...");
        List<String> categories = Results.fetchCategories();
        System.out.println("These are the available categories:");
        for(int i = 0;i < categories.size();i++){
            System.out.println((i + 1) + ". " + categories.get(i));
        }
        System.out.println("Which category of products would you like to view?(options 1 - " + categories.size() + ")");
        int choice = new Scanner(System.in).nextInt();
        for(int i = 0;i < categories.size();i++){
            if(choice == (i+1)){
                String category = categories.get(i);
                List<Product> products = Results.fetchProducts("https://dummyjson.com/products/category/" + category);
                displayProducts(products);
            }
        }
    }

    private void buyFromProducts(List<Product> products){
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

    private void displayProducts(List<Product> products){
        for(int i = 0;i <products.size();i++){
            System.out.println(
                    (i + 1) + ". Name: " + products.get(i).getTitle() +
                            ".\n\t Price: " + products.get(i).getPrice()
            );
            System.out.println("-------------------------");
        }
        System.out.println("Would you like to buy anyone?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = new Scanner(System.in).nextInt();
        if(choice == 1){
            buyFromProducts(products);
        } else {
            System.out.println("Quitting");
        }
    }

}

