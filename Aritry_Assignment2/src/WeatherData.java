public class WeatherData {
    private double rainfall;
    private double temperature;
    private double windX;
    private double windY;

    public WeatherData(double rainfall, double temperature, double windX, double windY) {
        this.rainfall = rainfall;
        this.temperature = temperature;
        this.windX = windX;
        this.windY = windY;
    }

    public double getRainfall() { return rainfall; }
    public double getTemperature() { return temperature; }
    public double getWindX() { return windX; }
    public double getWindY() { return windY; }

    @Override
    public String toString() {
        return String.format("Rain=%.2f, Temp=%.2f, WindX=%.2f, WindY=%.2f", rainfall, temperature, windX, windY);
    }
}
