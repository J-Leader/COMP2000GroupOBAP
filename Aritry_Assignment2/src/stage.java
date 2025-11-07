import java.util.*;
import java.util.stream.*;

public class Stage implements WeatherObserver {
    private int width, height;
    private Cell[][] grid;
    private GameState state;
    private List<Actor> actors = new ArrayList<>();

    public Stage(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Cell(x, y);
            }
        }
        System.out.println("Stage initialized with " + width + "x" + height + " grid.");
    }

    public void setState(GameState newState) {
        this.state = newState;
        System.out.println("State changed to: " + newState.getClass().getSimpleName());
    }

    public void startGame() {
        actors.add(new Actor("Hero", new DefensiveStrategy()));
        actors.add(new Actor("Bot", new AggressiveStrategy()));
        state.handle();
    }

    @Override
    public void onWeatherUpdate(WeatherData data) {
        System.out.println("Weather updated: " + data);

        
        Arrays.stream(grid)
            .flatMap(Arrays::stream)
            .filter(c -> Math.abs(c.getX()) < width && Math.abs(c.getY()) < height)
            .forEach(c -> c.updateWeatherEffect(data));

        
        if (data.getRainfall() > 0.7) {
            setState(new BotMovingState(this));
        } else {
            setState(new ChoosingActorState(this));
        }
        state.handle();
    }
}

