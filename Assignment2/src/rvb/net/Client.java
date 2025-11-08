package rvb.net;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String url = "http://13.238.167.130/weather";
        System.out.println("Connecting to " + url);
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                br.lines().limit(20).forEach(System.out::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

