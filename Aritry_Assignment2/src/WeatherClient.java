import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class WeatherClient {
    private WeatherObserver observer;
    private Random rand = new Random();

    public WeatherClient(WeatherObserver observer) {
        this.observer = observer;
    }

    public void startFetchingWeather() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {
            WeatherData data = new WeatherData(
                rand.nextDouble(), 
                rand.nextDouble(), 
                rand.nextDouble(), 
                rand.nextDouble()  
            );
            observer.onWeatherUpdate(data);
        }, 0, 3, TimeUnit.SECONDS);
    }
}

