import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;

public class Fish extends Item<String> {
    public Fish() { super("Fish", "Fish"); }

    @Override
    public void draw(Graphics g) {
        Color prev = g.getColor();
        g.setColor(Color.CYAN);
        Polygon p = new Polygon();
        p.addPoint(6, 10);
        p.addPoint(14, 7);
        p.addPoint(14, 13);
        g.fillPolygon(p);
        g.setColor(prev);
    }
}
