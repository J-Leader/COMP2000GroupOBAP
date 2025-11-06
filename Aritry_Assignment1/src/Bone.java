import java.awt.Graphics;
import java.awt.Color;

public class Bone extends Item<String> {
    public Bone() { super("Bone", "Bone"); }

    @Override
    public void draw(Graphics g) {
        
        Color prev = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(3 + 0, 3 + 0, 6, 3);
        g.fillRect(9 + 0, 3 + 0, 6, 3);
        g.setColor(prev);
    }
}
