import java.util.ArrayList;
import java.util.List;

public class Customer {
    private double money;
    List<Product> products;

    public Customer(){
        this.money = 5000;
        this.products = new ArrayList<>();
    }

    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        double productPrice = product.getPrice();
        if(productPrice > this.money){
            System.out.println("Oops! You don't have enough money to buy this product");
        } else {
            this.products.add(product);
            this.money -= productPrice;
            System.out.println(
                    "You have successfully purchased the " +
                    product.getTitle() + ". You have $" +
                    this.money + " left"
                    );
        }
    }
}
