
import org.json.JSONObject;

import java.awt.datatransfer.Clipboard;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class First {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter user's userName: ");
        var user = sc.nextLine();

        String  url ="https://api.github.com/users/"+user;

        //request nite hobe

        var req= HttpRequest.newBuilder().GET()
                .uri(URI.create(url))
                .build();
        // client er maddhome request pathaite hbe

        var client = HttpClient.newBuilder().build();

        // respone ta collect korte hbe..

        var response = client.send(req, HttpResponse.BodyHandlers.ofString());


        // Json obj diye design 

        JSONObject jsn = new JSONObject(response.body());

        var login = jsn.optString("login","Not found");

        var email = jsn.optString("email","Not found");

        var name = jsn.optString("name", "not found");
        var location = jsn.optString("location", "not found");


        //System.out.println(jsn.toString(4));

        System.out.println("login ID name: " + login);
        System.out.println("name: " + name);
        System.out.println("mail: " + email);
        System.out.println("location: " + location);

    }
}
