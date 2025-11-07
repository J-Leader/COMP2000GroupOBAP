
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final int COLS = 20;
        final int ROWS = 20;
        final int TICK_SECONDS = 5;
        final double NOISE_RANGE = 0.08; 
        System.out.println("Starting WeatherGrid simulation...");
        Grid grid = new Grid(COLS, ROWS);
        Stage stage = new Stage(grid);

       
        stage.start(TICK_SECONDS, NOISE_RANGE);

        System.out.println("Press ENTER to stop...");
        System.in.read();

        stage.stop();
        System.out.println("Goodbye!");
    }
}
