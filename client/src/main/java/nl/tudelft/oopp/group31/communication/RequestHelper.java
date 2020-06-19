package nl.tudelft.oopp.group31.communication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

/**
 * A class that wraps different types of HTTP requests for ease of use in the rest of the application.
 */
public class RequestHelper {

    private static String username;
    private static String password;
    private static Integer type;
    private static final String domain = "http://185.224.89.22:8080";

    /**
     * Helper function for an http GET request with Basic authentication.
     *
     * @param path The path to the endpoint, relative to the domain
     * @return An {@link HttpRequest} object representing the GET request
     */
    public static HttpRequest getRequest(String path) {

        String reqPath = domain + path;
        String userAndPass = username + ":" + password;
        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(userAndPass.getBytes());

        return HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(reqPath))
                .header("Authorization", basicAuthPayload)
                .build();
    }

    /**
     * Helper function for an http POST request with Basic authentication.
     *
     * @param path The path to the endpoint, relative to the domain
     * @param data The contents (body) of the POST request
     * @return an {@link HttpRequest} object representing the POST request
     */
    public static HttpRequest postRequest(String path, String data) {

        String reqPath = domain + path;
        String userAndPass = username + ":" + password;
        String basicAuthPayload = "Basic " + Base64.getEncoder().encodeToString(userAndPass.getBytes());

        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .uri(URI.create(reqPath))
                .header("Content-Type", "application/json")
                .header("Authorization", basicAuthPayload)
                .build();
    }

    /**
     * Sends a request and returns the response body as a string.
     *
     * @param request A {@link HttpRequest} object created by {@link #getRequest(String)}
     *                or {@link #postRequest(String, String)}
     * @param client A {@link HttpClient} object that will be the sender of the request.
     * @return the response body of the request or the status code if it's empty
     */
    public static String sendRequest(HttpRequest request, HttpClient client) {
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.body().isEmpty()) {
            return Integer.toString(response.statusCode());
        }
        return response.body();
    }

    // Getters and Setters
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        RequestHelper.username = username;
    }


    public static void setPassword(String password) {
        RequestHelper.password = password;
    }

    public static int getType() {
        return type;
    }

    public static void setType(int type) {
        RequestHelper.type = type;
    }


}
