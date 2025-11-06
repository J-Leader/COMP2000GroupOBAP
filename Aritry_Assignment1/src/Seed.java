import java.awt.Graphics;
import java.awt.Color;

public class Seed extends Item<String> {
    public Seed() { super("Seed", "Seed"); }

    @Override
    public void draw(Graphics g) {
        Color prev = g.getColor();
        g.setColor(Color.ORANGE);
        g.fillOval(8, 8, 5, 5);
        g.setColor(prev);
    }
}
