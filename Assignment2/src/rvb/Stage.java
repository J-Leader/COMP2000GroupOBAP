package rvb;

import rvb.actors.Actor;
import rvb.grid.Cell;
import rvb.grid.Grid;
import rvb.weather.Attribute;
import rvb.weather.WeatherListener;
import rvb.weather.WeatherRecord;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class Stage implements WeatherListener {

    private final Grid grid = new Grid();
    private final List<Actor> actors = new ArrayList<>();
    private StageState state = new HumanTurnState();

    private final double[][] rain = new double[Grid.COLS][Grid.ROWS];
    private final double[][] temp = new double[Grid.COLS][Grid.ROWS];

    private double windX = 0.0;
    private double windY = 0.0;
    private long lastWindNudgeMs = 0L;

    private static final double RAIN_DECAY = 0.975;
    private static final double TEMP_DECAY = 0.995;

    private long tempEvents = 0;
    private long rainEvents = 0;
    private long windEvents = 0;

    public Grid getGrid() { return grid; }
    public List<Actor> getActors() { return actors; }
    public void addActor(Actor a) { actors.add(a); }

    public void setState(StageState s) { this.state = s; s.onEnter(this); }
    public void nextState() { state.next(this); }

    public void tryNudgeActor(Actor a, int dc, int dr) {
        Cell cur = a.getCell();
        if (cur == null) return;
        grid.cellAtColRow(cur.col + dc, cur.row + dr).ifPresent(a::setCell);
    }

    @Override
    public void onWeather(WeatherRecord rec) {
        int col = rec.x + Grid.COLS / 2;
        int row = -rec.y + Grid.ROWS / 2;
        switch (rec.attribute) {
            case RAIN -> {
                if (inGrid(col, row)) {
                    rain[col][row] = 0.70 * rain[col][row] + 0.30 * clamp01(rec.value);
                    rainEvents++;
                }
            }
            case TEMP -> {
                if (inGrid(col, row)) {
                    temp[col][row] = 0.85 * temp[col][row] + 0.15 * clamp01(rec.value);
                    tempEvents++;
                }
            }
            case WINDX -> {
                windX = 0.9 * windX + 0.1 * (rec.value * 2 - 1);
                windEvents++;
            }
            case WINDY -> {
                windY = 0.9 * windY + 0.1 * -(rec.value * 2 - 1);
                windEvents++;
            }
        }
    }

    private boolean inGrid(int c, int r) {
        return c >= 0 && c < Grid.COLS && r >= 0 && r < Grid.ROWS;
    }

    @Override public void onWeatherError(Exception e) { System.err.println("[Stage] weather error: " + e.getMessage()); }
    @Override public void onWeatherClosed() { System.out.println("[Stage] weather stream closed"); }

    public void paint(Graphics2D g, Point mouse, Dimension size) {
        g.setColor(new Color(20, 20, 24));
        g.fillRect(0, 0, size.width, size.height);

        fadeWeatherData();

        grid.paint(g, mouse);

        paintRainOverlay(g);

        paintTempOverlay(g);

        for (Actor a : actors) a.paint(g);

        paintWindArrowsAndMaybeNudge(g);

        paintHud(g, mouse, size);

        state.update(this);
    }

    private void paintRainOverlay(Graphics2D g) {
        Composite prev = g.getComposite();
        for (int c = 0; c < Grid.COLS; c++) {
            for (int r = 0; r < Grid.ROWS; r++) {
                double rv = clamp01(rain[c][r]);
                if (rv < 0.05) continue;
                Cell cell = grid.rawCells()[c][r];
                float alpha = (float)Math.min(0.35, 0.12 + rv * 0.35); 
                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                g.setColor(new Color(0, 90, 255)); 
                g.fillRect(cell.x + 1, cell.y + 1, cell.width - 2, cell.height - 2);
            }
        }
        g.setComposite(prev);
    }

private void paintTempOverlay(Graphics2D g) {
    
    double min = Double.POSITIVE_INFINITY;
    double max = Double.NEGATIVE_INFINITY;
    for (int c = 0; c < Grid.COLS; c++) {
        for (int r = 0; r < Grid.ROWS; r++) {
            double v = temp[c][r];
            if (v < min) min = v;
            if (v > max) max = v;
        }
    }


      
    double range = max - min;
    if (range < 1e-4) {
        
        double centre = (max + min) * 0.5;
        min = centre - 0.05;
        max = centre + 0.05;
        range = max - min;
    }

    
    final double amplify = 1.8; 

    Composite prev = g.getComposite();
    
    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.88f));

    
    Color[] gradient = {
            new Color(10, 10, 170),   
            new Color(0, 180, 255),   
            new Color(60, 255, 120),  
            new Color(255, 230, 60),  
            new Color(255, 40, 20)    
    };

    for (int c = 0; c < Grid.COLS; c++) {
        for (int r = 0; r < Grid.ROWS; r++) {
            Cell cell = grid.rawCells()[c][r];
            
            double raw = temp[c][r];
            double norm = (raw - min) / range;      
            
            norm = clamp01(Math.pow(norm * amplify, 0.85)); 

            
            Color tint;
            if (norm < 0.25) {
                tint = lerpColor(gradient[0], gradient[1], norm / 0.25);
            } else if (norm < 0.5) {
                tint = lerpColor(gradient[1], gradient[2], (norm - 0.25) / 0.25);
            } else if (norm < 0.75) {
                tint = lerpColor(gradient[2], gradient[3], (norm - 0.5) / 0.25);
            } else {
                tint = lerpColor(gradient[3], gradient[4], (norm - 0.75) / 0.25);
            }

            g.setColor(tint);
            g.fillRect(cell.x, cell.y, cell.width, cell.height);
        }
    }

    g.setComposite(prev);
}

    private void paintWindArrowsAndMaybeNudge(Graphics2D g) {
        double mag = Math.hypot(windX, windY);
        if (mag < 0.05) return;

        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2));
        int step = 5;
        for (int c = 0; c < Grid.COLS; c += step) {
            for (int r = 0; r < Grid.ROWS; r += step) {
                Cell cell = grid.rawCells()[c][r];
                int cx = cell.x + cell.width / 2;
                int cy = cell.y + cell.height / 2;
                int len = 10 + (int)Math.round(20 * Math.min(1.0, mag));
                int dx = (int)Math.round(len * windX);
                int dy = (int)Math.round(len * windY);

                g.drawLine(cx, cy, cx + dx, cy + dy);

                Graphics2D g2 = (Graphics2D) g.create();
                AffineTransform at = new AffineTransform();
                at.translate(cx + dx, cy + dy);
                at.rotate(Math.atan2(dy, dx));
                g2.transform(at);
                Polygon head = new Polygon(new int[]{0, -4, -4}, new int[]{0, 2, -2}, 3);
                g2.fillPolygon(head);
                g2.dispose();
            }
        }

        long now = System.currentTimeMillis();
        if (mag > 0.2 && now - lastWindNudgeMs > 800) {
            int dc = (int)Math.signum(windX);
            int dr = (int)Math.signum(windY);
            for (Actor a : actors) tryNudgeActor(a, dc, dr);
            lastWindNudgeMs = now;
        }
    }

private void paintHud(Graphics2D g, Point mouse, Dimension size) {
    int hudX = Grid.OFFSET_X + Grid.COLS * Grid.SIZE + 16;
    int hudY = Grid.OFFSET_Y;
    int hudW = Math.max(280, size.width - hudX - 16);
    g.setColor(new Color(25, 25, 28, 230));
    g.fillRect(hudX - 8, hudY - 8, hudW, 210);

    g.setColor(Color.WHITE);
    g.setFont(new Font("Consolas", Font.PLAIN, 14));
    g.drawString("State: " + state.name(), hudX, hudY + 16);
    g.drawString(String.format("Wind: (%.2f, %.2f)  |v|=%.2f", windX, windY, Math.hypot(windX, windY)), hudX, hudY + 36);

    double liveMin = Double.POSITIVE_INFINITY, liveMax = Double.NEGATIVE_INFINITY;
    for (int c = 0; c < Grid.COLS; c++) {
        for (int r = 0; r < Grid.ROWS; r++) {
            double v = temp[c][r];
            if (v < liveMin) liveMin = v;
            if (v > liveMax) liveMax = v;
        }
    }
    if (liveMin == Double.POSITIVE_INFINITY) { liveMin = 0.0; liveMax = 0.0; }

    g.drawString(String.format("Events  temp=%d  rain=%d  wind=%d", tempEvents, rainEvents, windEvents), hudX, hudY + 56);
    g.drawString(String.format("TempMin: %.3f  TempMax: %.3f", liveMin, liveMax), hudX, hudY + 76);

    String cellLabel = "<none>", rainLabel = "-", tempLabel = "-";
    if (mouse != null) {
        outer:
        for (int c = 0; c < Grid.COLS; c++) {
            for (int r = 0; r < Grid.ROWS; r++) {
                Cell cell = grid.rawCells()[c][r];
                if (cell.contains(mouse)) {
                    cellLabel = String.format("[%c%d]", (char)('A' + c), r + 1);
                    rainLabel = String.format("%.2f", rain[c][r]);
                    tempLabel = String.format("%.2f", temp[c][r]);
                    break outer;
                }
            }
        }
    }

    g.drawString("Hover: " + cellLabel, hudX, hudY + 98);
    g.drawString("Rain:  " + rainLabel, hudX, hudY + 118);
    g.drawString("Temp:  " + tempLabel, hudX, hudY + 138);
    g.setColor(Color.GRAY);
    g.drawString("Tip: TEMP overlay autoscaled & amplified for visibility.", hudX, hudY + 162);
}

    private void fadeWeatherData() {
        for (int c = 0; c < Grid.COLS; c++) {
            for (int r = 0; r < Grid.ROWS; r++) {
                rain[c][r] *= RAIN_DECAY;
                temp[c][r] *= TEMP_DECAY;
            }
        }
    }

    private static double clamp01(double v) { return v < 0 ? 0 : (v > 1 ? 1 : v); }

    private static Color lerpColor(Color a, Color b, double t) {
        t = clamp01(t);
        int r = (int)(a.getRed() + (b.getRed() - a.getRed()) * t);
        int g = (int)(a.getGreen() + (b.getGreen() - a.getGreen()) * t);
        int bl= (int)(a.getBlue() + (b.getBlue() - a.getBlue()) * t);
        return new Color(r, g, bl);
    }
}