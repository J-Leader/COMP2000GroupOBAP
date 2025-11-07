

import java.util.Random;
import java.util.stream.IntStream;

public class Grid {
    private final int cols;
    private final int rows;
    private final Cell[][] cells;
    private final Random rnd = new Random();

    public Grid(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        cells = new Cell[cols][rows];
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
    }

    public void applyGlobalWeatherWithNoise(WeatherData global, double noiseRange) {
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                double temp = clamp(global.getTemperature() + randomNoise(noiseRange));
                double rain = clamp(global.getRainfall() + randomNoise(noiseRange));
                double windX = clamp(global.getWindX() + randomNoise(noiseRange));
                double windY = clamp(global.getWindY() + randomNoise(noiseRange));
                WeatherData local = new WeatherData(temp, rain, windX, windY);
                cells[x][y].updateWeatherEffect(local);
            }
        }
    }

    private double randomNoise(double range) {
        return (rnd.nextDouble() * 2.0 - 1.0) * range; 
    }

    private static double clamp(double v) {
        if (v < 0.0) return 0.0;
        if (v > 1.0) return 1.0;
        return v;
    }

    
    public double averageRainfall() {
        double sum = 0.0;
        int count = cols * rows;
        for (int x = 0; x < cols; x++) for (int y = 0; y < rows; y++) sum += cells[x][y].getRainfall();
        return sum / count;
    }

    
    public double averageTemperature() {
        double sum = 0.0;
        int count = cols * rows;
        for (int x = 0; x < cols; x++) for (int y = 0; y < rows; y++) sum += cells[x][y].getTemperature();
        return sum / count;
    }

    
    public void printSnapshot() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                sb.append(cells[x][y].getSymbol()).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
        System.out.printf("Avg rain: %.3f  Avg temp: %.3f%n", averageRainfall(), averageTemperature());
    }
}
