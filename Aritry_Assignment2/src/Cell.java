public class Cell {
    private int x, y;
    private double temperature = 0.5, rainfall = 0.5;
    private boolean flooded = false;  

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isFlooded() { return flooded; }

    
    public void updateWeatherEffect(WeatherData data) {
        this.temperature = data.getTemperature();
        this.rainfall = data.getRainfall();

        
        if (rainfall > 0.8 && temperature < 0.4) {
            if (!flooded) { 
                flooded = true;
                System.out.println("🌧️ Cell (" + x + "," + y + ") flooded!");
            }
        } 
        
        else if (rainfall < 0.3 && temperature > 0.6) {
            if (flooded) {
                flooded = false;
                System.out.println(" Cell (" + x + "," + y + ") dried up!");
            }
        }
    }
}
