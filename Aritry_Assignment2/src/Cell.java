

public class Cell {
    private int x, y;
    private double temperature = 0.5;
    private double rainfall = 0.5;
    private boolean flooded = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public double getTemperature() { return temperature; }
    public double getRainfall() { return rainfall; }

    public void updateWeatherEffect(WeatherData data) {
        this.temperature = data.getTemperature();
        this.rainfall = data.getRainfall();

        
        boolean wasFlooded = flooded;
        flooded = rainfall > 0.6;

        if (!wasFlooded && flooded) {
            System.out.println("Cell (" + x + "," + y + ") flooded!");
        } else if (wasFlooded && !flooded) {
            System.out.println("Cell (" + x + "," + y + ") dried out.");
        }
    }

    
    public char getSymbol() {
        if (flooded) return '~';        
        else if (temperature > 0.7) return '^'; 
        else if (rainfall < 0.3) return '.';   
        else return ' ';                
    }
}
