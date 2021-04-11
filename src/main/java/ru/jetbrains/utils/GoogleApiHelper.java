package ru.jetbrains.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class GoogleApiHelper {

    public static String clientId = "463026591923-cluehjj7a3role8ith7chfibm29idvc1.apps.googleusercontent.com";

    public static String clientSecret = "W4qUb9SxFbR7J8giWQmyMeJ8";

    public static String redirectUri = "http://localhost:8080/home";

    public static String oauthUri = "https://accounts.google.com/o/oauth2/auth?";

    public static String tokenUri = "https://accounts.google.com/o/oauth2/token";

    public static String scopes = "https://www.googleapis.com/auth/userinfo.profile";

    public static String accessToken = "";

    public static String getOauthUri() {
        return oauthUri +
                "client_id=" + clientId +
                "&redirect_uri=" + redirectUri +
                "&response_type=" + "code" +
                "&scope=" + scopes +
                "&access_type=" + "online";
    }

    /**
     * Initialize access token with given code.
     */
    public static void initAccessToken(String code) {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost(tokenUri);
            request.addHeader("content-type", "application/json");
            var postEntity = getRequestEntity(code);
            request.setEntity(postEntity);

            HttpResponse response = httpClient.execute(request);
            var entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);
            JSONObject resultJson = new JSONObject(responseString);

            accessToken = resultJson.getString("access_token");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Make request for username using access token.
     * <p>Call after {@code initAccessToken} method.</p>
     */
    public static String getUserName() {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            var uri = new URIBuilder(
                    "https://www.googleapis.com/oauth2/v1/userinfo")
                    .addParameter("alt", "json")
                    .addParameter("access_token", accessToken)
                    .build();

            HttpGet request = new HttpGet(uri.toString());

            var response = httpClient.execute(request);
            var entity = response.getEntity();
            String responseString = EntityUtils.toString(entity);
            JSONObject resultJson = new JSONObject(responseString);

            return resultJson.getString("name");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return "";
    }

    /**
     * Sign out from account.
     * <p>Call after {@code initAccessToken} method.</p>
     */
    public static void signOut() {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpPost request = new HttpPost(
                    "https://accounts.google.com/o/oauth2/revoke?token=" +
                            accessToken);
            httpClient.execute(request);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Create body for POST request to initialize access token.
     */
    private static StringEntity getRequestEntity(String code) throws JSONException,
            UnsupportedEncodingException {
        JSONObject body = new JSONObject();
        body.put("code", code);
        body.put("client_id", clientId);
        body.put("client_secret", clientSecret);
        body.put("redirect_uri", redirectUri);
        body.put("grant_type", "authorization_code");
        return new StringEntity(body.toString());
    }
}
