package rvb.actors;

import rvb.grid.Cell;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Dog extends Actor {
    private final List<Polygon> shapes = new ArrayList<>();

    public Dog(Cell c) {
        super(c);
        Point loc = new Point(c.x, c.y);
        Polygon ear1 = new Polygon(new int[]{loc.x+5, loc.x+15, loc.x+5},
                                   new int[]{loc.y+5, loc.y+5, loc.y+15},3);
        Polygon ear2 = new Polygon(new int[]{loc.x+20, loc.x+30, loc.x+30},
                                   new int[]{loc.y+5, loc.y+5, loc.y+15},3);
        Polygon face = new Polygon(new int[]{loc.x+8, loc.x+27, loc.x+27, loc.x+8},
                                   new int[]{loc.y+7, loc.y+7, loc.y+25, loc.y+25},4);
        shapes.add(ear1); shapes.add(ear2); shapes.add(face);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.YELLOW);
        shapes.forEach(g::fillPolygon);
    }
}


