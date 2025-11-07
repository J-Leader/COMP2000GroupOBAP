import java.util.Random;
import java.util.stream.DoubleStream;

public class WeatherData {
    private double temperature; 
    private double rainfall;    
    private double windX;
    private double windY;

    private static final Random random = new Random();

    
    public WeatherData(double temperature, double rainfall, double windX, double windY) {
        this.temperature = clamp(temperature);
        this.rainfall = clamp(rainfall);
        this.windX = clamp(windX);
        this.windY = clamp(windY);
    }

    // Create randomized weather (for testing without server)
    public static WeatherData randomWeather() {
        double temperature = DoubleStream.generate(() -> random.nextDouble())
                                         .limit(1).findFirst().orElse(0.5);
        double rainfall = DoubleStream.generate(() -> random.nextDouble())
                                      .limit(1).findFirst().orElse(0.5);
        double windX = random.nextDouble();
        double windY = random.nextDouble();

        return new WeatherData(temperature, rainfall, windX, windY);
    }

    
    private static double clamp(double value) {
        return Math.max(0.0, Math.min(1.0, value));
    }

    
    public double getTemperature() { return temperature; }
    public double getRainfall() { return rainfall; }
    public double getWindX() { return windX; }
    public double getWindY() { return windY; }

    @Override
    public String toString() {
        return String.format("Weather [Temp: %.2f, Rain: %.2f, WindX: %.2f, WindY: %.2f]",
                temperature, rainfall, windX, windY);
    }
}
