import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://andruxnet-random-famous-quotes.p.rapidapi.com/?cat=famous&count=10"))
                .header("X-RapidAPI-Key", "8c234ae3dcmsh657fae357f8f7fcp1d08eajsn6a9d2fd40a50")
                .header("X-RapidAPI-Host", "andruxnet-random-famous-quotes.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}