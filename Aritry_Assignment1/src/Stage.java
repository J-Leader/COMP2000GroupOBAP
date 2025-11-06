import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;


public class Stage {
    private Grid grid;
    private List<Actor> actors;

    public Stage(int rows, int cols, int cellSize, int offset) {
        grid = new Grid(rows, cols, cellSize, offset);
        actors = new ArrayList<>();
    }

    public Grid getGrid() { return grid; }

    public void addActor(Actor a) {
        if (a != null) actors.add(a);
    }

    public void paint(Graphics g) {
        grid.paint(g);
        for (Actor a : actors) a.paint(g);
    }

    
    public void reactActorsToCell(TerrainWeatherCell c) {
        for (Actor a : actors) a.reactToCell(c);
        
        if (c != null) c.interact(actors.isEmpty() ? null : actors.get(0));
    }
}
