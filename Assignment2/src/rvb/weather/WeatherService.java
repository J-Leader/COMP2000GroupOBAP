package rvb.weather;

import java.io.*;
import java.net.*;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class WeatherService implements AutoCloseable {
    private final String endpoint;
    private final CopyOnWriteArrayList<WeatherListener> listeners = new CopyOnWriteArrayList<>();
    private Thread thread;
    private volatile boolean running;

    public WeatherService(String endpoint) { this.endpoint = Objects.requireNonNull(endpoint); }

    public void addListener(WeatherListener l) { listeners.addIfAbsent(l); }

    public void start() {
        if (running) return;
        running = true;
        thread = new Thread(this::run, "WeatherService");
        thread.setDaemon(true);
        thread.start();
    }

    private void run() {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                Stream<String> lines = br.lines();
                lines.takeWhile(l -> running)
                     .map(String::trim)
                     .filter(s -> !s.isEmpty())
                     .map(s -> {
                         try { return WeatherRecord.parse(s); }
                         catch (Exception e) { listeners.forEach(li -> li.onWeatherError(e)); return null; }
                     })
                     .filter(Objects::nonNull)
                     .forEach(rec -> listeners.forEach(l -> l.onWeather(rec)));
            }
        } catch (IOException e) {
            listeners.forEach(l -> l.onWeatherError(e));
        } finally {
            listeners.forEach(WeatherListener::onWeatherClosed);
            running = false;
        }
    }

    @Override public void close() { running = false; if (thread != null) thread.interrupt(); }
}
