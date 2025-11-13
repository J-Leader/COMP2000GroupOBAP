package rvb.actors;

import rvb.grid.Cell;
import java.awt.*;

public class Cat extends Actor {

    public Cat(Cell cell) {
        super(cell);
    }

    @Override
    public void paint(Graphics2D g) {
        Cell c = getCell();
        if (c == null) return;

        int x = c.x;
        int y = c.y;

        Polygon ear1 = new Polygon(new int[]{x + 11, x + 15, x + 7}, new int[]{y + 5, y + 15, y + 15}, 3);
        Polygon ear2 = new Polygon(new int[]{x + 22, x + 26, x + 18}, new int[]{y + 5, y + 15, y + 15}, 3);
        Polygon face = new Polygon(new int[]{x + 5, x + 29, x + 17}, new int[]{y + 15, y + 15, y + 30}, 3);

        g.setColor(new Color(0, 190, 255));
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