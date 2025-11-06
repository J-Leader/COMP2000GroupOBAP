import java.awt.Color;
import java.awt.Graphics;


public class WeatherCell extends Cell<WeatherType> {
    public WeatherCell(int x, int y, int size, int row, int col, WeatherType w) {
        super(x, y, size, size, row, col, w);
    }

    @Override
    public void paint(Graphics g) {
        WeatherType w = getData();
        Color prev = g.getColor();
        if (isHighlighted()) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y, width, height);
        } else {
            if (w == WeatherType.SUNNY) g.setColor(new Color(255, 230, 120)); 
            else if (w == WeatherType.RAINY) g.setColor(new Color(180, 200, 255)); 
            else g.setColor(new Color(240, 240, 255));            	    g.fillRect(x, y, width, height);
        }
        // draw border and seed if present
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        if (hasSeed()) {
            g.drawString("s", x + 3, y + 12); 
        }
        g.setColor(prev);
    }
}
