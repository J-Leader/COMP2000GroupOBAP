import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class Birdcage extends House {
  public Birdcage(Cell inLoc) {
    loc = inLoc;
    color = Color.ORANGE; // cage color
    display = new ArrayList<Polygon>();

    Polygon base = new Polygon();
    base.addPoint(loc.x + 0,  loc.y + 30);
    base.addPoint(loc.x + 36, loc.y + 30);
    base.addPoint(loc.x + 36, loc.y + 34);
    base.addPoint(loc.x + 0,  loc.y + 34);
    Polygon body = new Polygon();
    body.addPoint(loc.x + 0,  loc.y + 15);
    body.addPoint(loc.x + 36, loc.y + 15);
    body.addPoint(loc.x + 36, loc.y + 30);
    body.addPoint(loc.x + 0,  loc.y + 30);
    Polygon dome = new Polygon();
    dome.addPoint(loc.x + 0,  loc.y + 15);
    dome.addPoint(loc.x + 9,  loc.y + 8);
    dome.addPoint(loc.x + 18, loc.y + 5); 
    dome.addPoint(loc.x + 27, loc.y + 8);
    dome.addPoint(loc.x + 36, loc.y + 15);
    Polygon bar1 = new Polygon();
    bar1.addPoint(loc.x + 6,  loc.y + 15);
    bar1.addPoint(loc.x + 8,  loc.y + 15);
    bar1.addPoint(loc.x + 8,  loc.y + 30);
    bar1.addPoint(loc.x + 6,  loc.y + 30);
    Polygon bar2 = new Polygon();
    bar2.addPoint(loc.x + 14, loc.y + 15);
    bar2.addPoint(loc.x + 16, loc.y + 15);
    bar2.addPoint(loc.x + 16, loc.y + 30);
    bar2.addPoint(loc.x + 14, loc.y + 30);
    Polygon bar3 = new Polygon();
    bar3.addPoint(loc.x + 20, loc.y + 15);
    bar3.addPoint(loc.x + 22, loc.y + 15);
    bar3.addPoint(loc.x + 22, loc.y + 30);
    bar3.addPoint(loc.x + 20, loc.y + 30);
    Polygon bar4 = new Polygon();
    bar4.addPoint(loc.x + 28, loc.y + 15);
    bar4.addPoint(loc.x + 30, loc.y + 15);
    bar4.addPoint(loc.x + 30, loc.y + 30);
    bar4.addPoint(loc.x + 28, loc.y + 30);
    display.add(base);
    display.add(body);
    display.add(dome);
    display.add(bar1);
    display.add(bar2);
    display.add(bar3);
    display.add(bar4);

  }
}