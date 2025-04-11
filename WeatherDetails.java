import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class WeatherDetails {
    // api = d012fe60b0a968b0ee30ac*********
    // https://api.openweathermap.org/data/2.5/weather?q={Dhaka}&appid=d012fe60b0a968b0ee30ac********* // api HIDE

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner sc= new Scanner(System.in);


        System.out.println("Enter your city ===>  ");
        String city = sc.nextLine();
        var url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=d012fe60b0a968b0ee30ac271515f031";

        var request = HttpRequest.newBuilder().GET()
                .uri(URI.create(url))
                .build();

        var client = HttpClient.newBuilder().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsn = new JSONObject(response.body());

        /*niche Temp, humidity eshb JSON er 'main'
         Object er vetor ache tai age main obj ta fetch korechi 31 line e
        *
        * */

        var main = jsn.getJSONObject("main");


        var temp = main.optDouble("temp");
        var main_temp = temp - 273; // kelvin to C
        var humdity = main.optString("humidity","Not found");
        var pressure = main.optString("pressure","not found");

        //
        //
        // System.out.println(jsn.toString(4));


         var weather = jsn.getJSONObject("weather"); //eikhaneo weather obj ta fetch korchi..

        String description = weather.optString("description", "Not found");
        String clouds = weather.optString("main","Not found");



        var name = jsn.optString("name","not found");


        System.out.println("Country Name: "+name);

        System.out.println("Temp: "+String.format("%.2f", main_temp)+" °C");
        System.out.println("humdity: "+humdity);
        System.out.println(description);
        System.out.println(clouds);
        System.out.println("pressure: "+ pressure);


    }
}
