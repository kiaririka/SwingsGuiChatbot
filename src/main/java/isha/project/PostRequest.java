package isha.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PostRequest {
    public static String post(String message) throws Exception {
        URL url = new URL("https://gpa-rho.vercel.app/getBard_Response");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        String requestBody = "{\"Query\": \"" + message + "\" ,\"Key\":\"sweetfriend\"}";
        connection.setDoOutput(true);
        connection.getOutputStream().write(requestBody.getBytes());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.append(line);
        }

        // Use Gson to parse the JSON response
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        String reply = jsonObject.get("Content").getAsString();

        return reply;
    }
}
