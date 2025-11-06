import java.awt.Graphics;
import java.awt.Color;


public class Dog extends Actor {
    private Color color = Color.YELLOW;

    public Dog(TerrainWeatherCell loc) { super(loc); }

    @Override
    public void paint(Graphics g) {
        if (location == null) return;
        int lx = location.x, ly = location.y;
        Color prev = g.getColor();
        g.setColor(color);
        g.fillRect(lx + 6, ly + 8, 24, 20);
        g.setColor(prev);
    }

    @Override
    public void reactToCell(TerrainWeatherCell c) {
        CellInfo info = c.getData();
        if (info.terrain == TerrainType.WATER) System.out.println("Dog: splashes happily in water at " + c.getRow()+","+c.getCol());
        else super.reactToCell(c);
    }
}
