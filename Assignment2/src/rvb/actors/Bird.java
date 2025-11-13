package rvb.actors;

import rvb.grid.Cell;
import java.awt.*;

public class Bird extends Actor {

    public Bird(Cell cell) {
        super(cell);
    }

    @Override
    public void paint(Graphics2D g) {
        Cell c = getCell();
        if (c == null) return;

        int x = c.x;
        int y = c.y;

        Polygon wing1 = new Polygon(new int[]{x + 5, x + 15, x + 5}, new int[]{y + 5, y + 17, y + 17}, 3);
        Polygon wing2 = new Polygon(new int[]{x + 30, x + 20, x + 30}, new int[]{y + 5, y + 17, y + 17}, 3);
        Polygon body = new Polygon(new int[]{x + 15, x + 20, x + 20, x + 15}, new int[]{y + 10, y + 10, y + 25, y + 25}, 4);

        g.setColor(new Color(80, 255, 80));
        g.fillPolygon(wing1);
        g.fillPolygon(wing2);
        g.fillPolygon(body);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2f));
        g.drawPolygon(wing1);
        g.drawPolygon(wing2);
        g.drawPolygon(body);
    }
}