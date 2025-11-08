package rvb.actors;

import rvb.grid.Cell;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bird extends Actor {
    private final List<Polygon> shapes = new ArrayList<>();

    public Bird(Cell c) {
        super(c);
        Point loc = new Point(c.x, c.y);
        Polygon wing1 = new Polygon(new int[]{loc.x+5, loc.x+15, loc.x+5},
                                    new int[]{loc.y+5, loc.y+17, loc.y+17},3);
        Polygon wing2 = new Polygon(new int[]{loc.x+30, loc.x+20, loc.x+30},
                                    new int[]{loc.y+5, loc.y+17, loc.y+17},3);
        Polygon body = new Polygon(new int[]{loc.x+15, loc.x+20, loc.x+20, loc.x+15},
                                   new int[]{loc.y+10, loc.y+10, loc.y+25, loc.y+25},4);
        shapes.add(wing1); shapes.add(wing2); shapes.add(body);
    }

    @Override
    public void paint(Graphics2D g) {
        g.setColor(Color.GREEN);
        shapes.forEach(g::fillPolygon);
    }
}

