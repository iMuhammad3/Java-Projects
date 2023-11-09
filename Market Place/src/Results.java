import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class Results {
    public Results(){}

    static String URL = "https://dummyjson.com/products";

    public static List<Product> fetchAllProducts() throws Exception {
        System.out.println("Loading products...");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(URL))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        ProductList products = gson.fromJson(response.body(), ProductList.class);
        System.out.println(Arrays.toString(products.getProducts().toArray()));
        return products.getProducts();
    }
}
