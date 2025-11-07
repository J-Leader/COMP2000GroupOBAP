public class Cell {
    private int x, y;
    private double temperature = 0.5, rainfall = 0.5;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void updateWeatherEffect(WeatherData data) {
        this.temperature = data.getTemperature();
        this.rainfall = data.getRainfall();
        if (rainfall > 0.6) {
            System.out.println("Cell (" + x + "," + y + ") flooded!");
        }
    }
}

