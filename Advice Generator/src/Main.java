import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            App.run();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class App {
    App(){}

    static String APIUrl = "https://api.adviceslip.com/advice";

    static void run() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Advice Generator!!");
        System.out.println("Would you like to: ");
        int choice;

        do{
            Thread.sleep(500);
            System.out.println("1. Get a random advice");
            System.out.println("2. Search for advice");
            System.out.println("99. Quit");
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    getRandomAdvice();
                    break;
                case 2:
                    searchAdvice();
                    break;
                default:
                    if (choice == 99) {
                        System.out.println("Bye!!");
                    } else {
                        System.out.println("Please Enter a valid option");
                    }
                    break;
            }
        }
        while(choice != 99);
    }

    static void getRandomAdvice() throws Exception {
        System.out.println("Fetching advice...");

        JSONObject jsonObject = fetchAdvice(APIUrl);
        JSONObject slip = jsonObject.getJSONObject("slip");
        System.out.println("---------------------");
        System.out.println("\"" + slip.getString("advice") + "\"");
        System.out.println("---------------------");
        System.out.println();
    }

    static void searchAdvice() throws Exception{
        System.out.println("Enter search term: ");
        String query = new Scanner(System.in).nextLine();
        System.out.println("Fetching advices...");
        JSONObject jsonObject = fetchAdvice(APIUrl + "/search/" + query);

        if(!jsonObject.has("slips")){
            System.out.println("No advices found.");
            System.out.println();
            return;
        }

        JSONArray slips = jsonObject.getJSONArray("slips");

        System.out.println("---------------------");
        for(int i = 0;i < slips.length();i++){
            System.out.println((i + 1) + " : " + slips.getJSONObject(i).getString("advice"));
        }
        System.out.println("---------------------");
        System.out.println();
    }

    static JSONObject fetchAdvice(String uri) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new JSONObject(response.body());
    }
}