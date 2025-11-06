import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;


public class Cat extends Actor {
    private Color color = Color.BLUE;

    public Cat(TerrainWeatherCell loc) { super(loc); }

    @Override
    public void paint(Graphics g) {
        if (location == null) return;
        int lx = location.x, ly = location.y;
        Color prev = g.getColor();
        g.setColor(color);
        g.fillRect(lx + 6, ly + 6, 24, 24);
        
        Polygon ear = new Polygon();
        ear.addPoint(lx + 11, ly + 4);
        ear.addPoint(lx + 15, ly + 12);
        ear.addPoint(lx + 7, ly + 12);
        g.fillPolygon(ear);
        g.setColor(prev);
    }

    @Override
    public void reactToCell(TerrainWeatherCell c) {
        CellInfo info = c.getData();
        if (info.weather == WeatherType.RAINY) System.out.println("Cat: hides from rain at " + c.getRow()+","+c.getCol());
        else super.reactToCell(c);
    }
}
