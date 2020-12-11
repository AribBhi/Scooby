package com.vyy.scooby.util;

import com.sun.net.httpserver.HttpExchange;
import com.vyy.scooby.VoteListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class WebUtils {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject parseJson(String url) {
        InputStream is = null;
        BufferedReader rd = null;

        try {
            is = new URL(url).openStream();
            rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (final IOException | JSONException ignored) { }
        finally {
            try {
                if (is != null && rd != null) {
                    is.close();
                    rd.close();
                }
            } catch (final IOException ignored){ }
        }

        return null;
    }

    public static void handleRequest(HttpExchange exchange) {
        JSONObject json = null;
        InputStreamReader is = null;
        BufferedReader rd = null;

        if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) return;
        if (!exchange.getRequestHeaders().keySet().contains("Authorization")) return;

        String authorization = exchange.getRequestHeaders().get("Authorization").toString();

        try {
            is = new InputStreamReader(exchange.getRequestBody(), "utf-8");
            rd = new BufferedReader(is);

            String postText = readAll(rd);
            json = new JSONObject(postText);

            VoteListener.getInstance().registerVote(json, authorization);
        } catch (final IOException | JSONException ignored) { }
        finally {
            try {
                if (is != null && rd != null) {
                    is.close();
                    rd.close();
                }
            } catch (final IOException ignored){ }
        }
    }
}
