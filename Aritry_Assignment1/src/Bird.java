import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;


public class Bird extends Actor {
    private Color color = Color.GREEN;

    public Bird(TerrainWeatherCell loc) { super(loc); }

    @Override
    public void paint(Graphics g) {
        if (location == null) return;
        int lx = location.x, ly = location.y;
        Color prev = g.getColor();
        g.setColor(color);
        Polygon wing = new Polygon();
        wing.addPoint(lx + 8, ly + 8);
        wing.addPoint(lx + 15, ly + 18);
        wing.addPoint(lx + 8, ly + 18);
        g.fillPolygon(wing);
        g.setColor(prev);
    }

    @Override
    public void reactToCell(TerrainWeatherCell c) {
        super.reactToCell(c);
        
        c.interact(this);
    }
}
