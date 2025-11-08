package rvb.actors;

import rvb.grid.Cell;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cat extends Actor {
    private final List<Polygon> shapes = new ArrayList<>();

    public Cat(Cell c) {
        super(c);
        Point loc = new Point(c.x, c.y);
        Polygon ear1 = new Polygon(new int[]{loc.x+11, loc.x+15, loc.x+7},
                                   new int[]{loc.y+5, loc.y+15, loc.y+15},3);
        Polygon ear2 = new Polygon(new int[]{loc.x+22, loc.x+26, loc.x+18},
                                   new int[]{loc.y+5, loc.y+15, loc.y+15},3);
        Polygon face = new Polygon(new int[]{loc.x+5, loc.x+29, loc.x+17},
                                   new int[]{loc.y+15, loc.y+15, loc.y+30},3);
        shapes.add(ear1); shapes.add(ear2); shapes.add(face);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.BLUE);
        shapes.forEach(g::fillPolygon);
    }
}