import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Results {
    public Results(){}

    static String URL = "https://dummyjson.com/products";
    static Gson gson = new Gson();

    public  static  List<Product> fetchProducts(String URL) throws Exception{
        System.out.println("Loading products...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        ProductList products = gson.fromJson(response.body(), ProductList.class);
        return products.getProducts();
    }

    public static List<Product> fetchAllProducts() throws Exception {
        System.out.println("Loading products...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        ProductList products = gson.fromJson(response.body(), ProductList.class);
        return products.getProducts();
    }

    public static List<Product> fetchProductsByName(String productInput) throws Exception {
        if(!productInput.endsWith("s")){
            System.out.println("Searching for " + productInput + "'s...");
        } else {
            System.out.println("Searching for " + productInput + "...");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL + "/search?q=" + productInput))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        ProductList products = gson.fromJson(response.body(), ProductList.class);
        return products.getProducts();
    }

    public static List<String> fetchCategories() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL + "/categories"))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), List.class);
    }

}
