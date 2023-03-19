package ch.aelgict.easychatapp.model;

import ch.aelgict.easychatapp.entity.Nachricht;
import ch.aelgict.easychatapp.entity.User;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.*;
import java.text.ParseException;
import java.util.ArrayList;

public class APIHandler {
    private final static String BASE_URL = "http://localhost/dashboard/www/chatAppAPI/chatAppApi/";
    private URL url;
    private URLConnection urlConnection;

    public APIHandler() {
        try {
            url = new URL(BASE_URL);
            urlConnection = url.openConnection();
            urlConnection.connect();
            System.out.println("Connection Stable");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Connection not Stable");
        }
    }

    public boolean createNewUser(User user) throws IOException, InterruptedException, URISyntaxException {
        String urlAddOn = "api/user/create.php";
        URI uri = new URI(BASE_URL + urlAddOn);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(user.createUserJSON())).header("Content-type", "application/json").build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            System.out.println("Successfully created user");
            return true;
        } else {
            System.out.println("Didnt create user");
            System.out.println("Status Code: " + response.statusCode());
            return false;
        }
    }
    public User getUserByNameAndPassword(String username, String password) throws URISyntaxException, IOException, InterruptedException {
        JSONObject object = new JSONObject();
        object.put("username", username);
        object.put("password", password);
        String urlAddOn = "api/user/read_single_by_name_and_password.php";
        URI uri = new URI(BASE_URL + urlAddOn);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(String.valueOf(object))).header("Content-type", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            System.out.println("Successfully created message");
            System.out.println(response.body());
            return User.fromJSON(response.body());
        } else {
            System.out.println("Didnt create message");
            System.out.println("Status Code: " + response.statusCode());
            return null;
        }
    }

    public User getUserById(String useruid) throws URISyntaxException, IOException, InterruptedException {
        JSONObject object = new JSONObject();
        object.put("useruid", useruid);
        String urlAddOn = "api/user/read_single.php";
        URI uri = new URI(BASE_URL + urlAddOn);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(String.valueOf(object))).header("Content-type", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            System.out.println("Successfully created message");
            System.out.println(response.body());
            return User.fromJSON(response.body());
        } else {
            System.out.println("Didnt create message");
            System.out.println("Status Code: " + response.statusCode());
            return null;
        }
    }

    public ArrayList<User> getAllUsers() throws IOException, InterruptedException, URISyntaxException {
        String urlAddOn = "api/user/read.php";
        URI uri = new URI(BASE_URL + urlAddOn);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).GET().header("Content-type", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            System.out.println("Successfully created message");
            System.out.println(response.body());
            return User.fromJSONList(response.body());
        } else {
            System.out.println("Didnt create message");
            System.out.println("Status Code: " + response.statusCode());
            return null;
        }
    }

    public boolean createNewMessage(Nachricht nachricht) throws URISyntaxException, IOException, InterruptedException {
        String urlAddOn = "api/message/create.php";
        URI uri = new URI(BASE_URL + urlAddOn);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(nachricht.createMessageJSON())).header("Content-type", "application/json").build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            System.out.println("Successfully created message");
            return true;
        } else {
            System.out.println("Didnt create message");
            System.out.println("Status Code: " + response.statusCode());
            return false;
        }
    }

    public ArrayList<Nachricht> getMessagesBetweenTwoUsers(String idUserIncomming, String idUserOutgoing) throws URISyntaxException, IOException, InterruptedException, ParseException, org.json.simple.parser.ParseException {
        JSONObject object = new JSONObject();
        object.put("idUserOutgoing", idUserOutgoing);
        object.put("idUserIncomming", idUserIncomming);
        String urlAddOn = "api/message/read_single_chat.php";
        URI uri = new URI(BASE_URL + urlAddOn);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(String.valueOf(object))).header("Content-type", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            System.out.println("Successfully got messages");
            System.out.println(response.body());
            return Nachricht.fromJSONArray(response.body());
        } else {
            System.out.println("Didnt get messages");
            System.out.println("Status Code: " + response.statusCode());
            return null;
        }
    }

    public ArrayList<Nachricht> getAllMessagesOfUser(String userid) throws URISyntaxException, IOException, InterruptedException, ParseException, org.json.simple.parser.ParseException {
        JSONObject object = new JSONObject();
        object.put("idUser", userid);
        String urlAddOn = "api/message/read.php";
        URI uri = new URI(BASE_URL + urlAddOn);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(uri).POST(HttpRequest.BodyPublishers.ofString(String.valueOf(object))).header("Content-type", "application/json").build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 201 || response.statusCode() == 200) {
            System.out.println("Successfully got messages");
            System.out.println(response.body());
            return Nachricht.fromJSONArray(response.body());
        } else {
            System.out.println("Didnt get messages");
            System.out.println("Status Code: " + response.statusCode());
            return null;
        }
    }
}
