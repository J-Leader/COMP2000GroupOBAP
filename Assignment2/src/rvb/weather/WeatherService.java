package rvb.weather;

import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class WeatherService implements AutoCloseable {
    private final String endpoint;
    private final CopyOnWriteArrayList<WeatherListener> listeners = new CopyOnWriteArrayList<>();
    private volatile boolean running;
    private Thread thread;

    public WeatherService(String endpoint) { this.endpoint = Objects.requireNonNull(endpoint); }

    public void addListener(WeatherListener l) { listeners.addIfAbsent(l); }
    public void removeListener(WeatherListener l) { listeners.remove(l); }

    public void start() {
        if (running) return;
        running = true;
        thread = new Thread(this::run, "WeatherService");
        thread.setDaemon(true);
        thread.start();
    }

    private void run() {
	System.out.println("[WeatherService] connecting to " + endpoint);
	HttpURLConnection conn = null;
        try {
	    conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setConnectTimeout(7000);
	    conn.setReadTimeout(0);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
		System.out.println("[WeatherService] connected, streaming....");
                Stream<String> lines = br.lines();
                lines.takeWhile(l -> running)
                     .map(String::trim)
                     .filter(s -> !s.isEmpty())
                     .map(s -> {
                         try { return WeatherRecord.parse(s); }
                         catch (Exception e) { 
				listeners.forEach(li -> li.onWeatherError(e)); 
				System.err.println("[WeatherService] parse error: " + e.getMessage());
				return null;
				}
                     })
                     .filter(Objects::nonNull)
		     .peek(rec -> System.out.println("[Weather] " + rec))
                     .forEach(rec -> listeners.forEach(l -> l.onWeather(rec)));
            }
        } catch (IOException e) {
            listeners.forEach(l -> l.onWeatherError(e));
	    System.err.println("[WeatherService] 10 error: " + e.getMessage());
        } finally {
            listeners.forEach(WeatherListener::onWeatherClosed);
	    System.out.println("[WeatherService] stream closed");
            running = false;
        }
    }

    @Override 
    public void close() { 
	running = false; 
	if (thread != null) 
		thread.interrupt(); 
	}
}
