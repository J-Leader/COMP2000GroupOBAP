package rvb.actors;

import rvb.grid.Cell;
import java.awt.*;

public class Dog extends Actor {

    public Dog(Cell cell) {
        super(cell);
    }

    @Override
    public void paint(Graphics2D g) {
        Cell c = getCell();
        if (c == null) return;

        int x = c.x;
        int y = c.y;

        Polygon ear1 = new Polygon(new int[]{x + 5, x + 15, x + 5}, new int[]{y + 5, y + 5, y + 15}, 3);
        Polygon ear2 = new Polygon(new int[]{x + 20, x + 30, x + 30}, new int[]{y + 5, y + 5, y + 15}, 3);
        Polygon face = new Polygon(new int[]{x + 8, x + 27, x + 27, x + 8}, new int[]{y + 7, y + 7, y + 25, y + 25}, 4);

        g.setColor(new Color(255, 220, 0));
        g.fillPolygon(ear1);
        g.fillPolygon(ear2);
        g.fillPolygon(face);

        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2f));
        g.drawPolygon(ear1);
        g.drawPolygon(ear2);
        g.drawPolygon(face);
    }
}