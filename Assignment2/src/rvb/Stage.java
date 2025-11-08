package rvb;

import rvb.actors.Actor;
import rvb.grid.*;
import rvb.weather.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Stage implements WeatherListener {
    private final Grid grid = new Grid();
    private final List<Actor> actors = new ArrayList<>();
    private StageState state = new HumanTurnState();


    private final ConcurrentHashMap<Point, WeatherRecord> latestWeather = new ConcurrentHashMap<>();

    public Grid getGrid() { return grid; }
    public List<Actor> getActors() { return actors; }

    public void addActor(Actor a) { actors.add(a); }

    public void setState(StageState s) {
        this.state = s;
        s.onEnter(this);
    }

    public void nextState() { state.next(this); }

    public void tryNudgeActor(Actor a, int dc, int dr) {
        Cell oldCell = a.getCell();
        Optional<Cell> target = grid.cellAtColRow(oldCell.col + dc, oldCell.row + dr);
        target.ifPresent(a::setCell);
    }

    public void paint(Graphics2D g, Point mouse, Dimension size) {

        double avgTemp = latestWeather.values().stream()
                .filter(w -> w.attribute == Attribute.TEMP)
                .mapToDouble(w -> w.value)
                .average().orElse(0.5);
        int col = (int)(50 + avgTemp * 205);
        g.setColor(new Color(col, col, 255));
        g.fillRect(0, 0, size.width, size.height);

        grid.paint(g, mouse);


        g.setColor(new Color(0, 0, 255, 80));
        latestWeather.values().stream()
                .filter(w -> w.attribute == Attribute.RAIN && w.value > 0.5)
                .forEach(w -> {
                    grid.cellAtColRow(w.x + Grid.COLS / 2, w.y + Grid.ROWS / 2)
                            .ifPresent(c -> g.fillRect(c.x + 5, c.y + 5, c.width - 10, c.height - 10));
                });


        actors.forEach(a -> a.paint(g));


        g.setColor(Color.WHITE);
        g.drawString("State: " + state.name(), 20, size.height - 20);
    }


    @Override
    public void onWeather(WeatherRecord rec) {
        Point p = new Point(rec.x, rec.y);
        latestWeather.put(p, rec);
    }

    @Override
    public void onWeatherError(Exception e) {
        System.err.println("Weather error: " + e.getMessage());
    }

    @Override
    public void onWeatherClosed() {
        System.out.println("Weather stream closed");
    }
}
