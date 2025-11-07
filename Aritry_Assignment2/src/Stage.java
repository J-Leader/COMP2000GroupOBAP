import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Stage {
    private final Grid grid;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private volatile boolean running = false;

    
    public Stage(Grid grid) {
        this.grid = grid;
    }

    
    public void start(int tickSeconds, double noiseRange) {
        if (running) return;
        running = true;

        
        scheduler.scheduleAtFixedRate(() -> {
            try {
                
                WeatherData global = WeatherData.randomWeather();

                
                grid.applyGlobalWeatherWithNoise(global, noiseRange);

                
                double avgRain = grid.averageRainfall();
                double avgTemp = grid.averageTemperature();

                if (avgRain > 0.75) {
                    System.out.println("[Stage] Storm conditions detected.");
                } else if (avgTemp > 0.75) {
                    System.out.println("[Stage] Heatwave conditions detected.");
                } else {
                    System.out.println("[Stage] Conditions normal.");
                }

                
                grid.printSnapshot();

            } catch (Exception e) {
                System.out.println("[Stage] Exception in update tick: " + e.getMessage());
                e.printStackTrace();
            }
        }, 0, tickSeconds, TimeUnit.SECONDS);

        System.out.println("Stage started: updates every " + tickSeconds + "s, noiseRange=" + noiseRange);
    }

    public void stop() {
        running = false;
        scheduler.shutdownNow();
        System.out.println("Stage stopped.");
    }
}
