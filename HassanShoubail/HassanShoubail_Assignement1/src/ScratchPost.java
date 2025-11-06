import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class ScratchPost extends House {
  public ScratchPost(Cell inLoc) {
    loc = inLoc;
    color = Color.BLACK;
    display = new ArrayList<Polygon>();
    Polygon base = new Polygon();
    base.addPoint(loc.x, loc.y + 30);
    base.addPoint(loc.x + 35, loc.y + 30);
    base.addPoint(loc.x + 35, loc.y + 35);
    base.addPoint(loc.x, loc.y + 35);
    Polygon pole = new Polygon();
    pole.addPoint(loc.x + 15, loc.y + 5);
    pole.addPoint(loc.x + 25, loc.y + 5);
    pole.addPoint(loc.x + 25, loc.y + 30);
    pole.addPoint(loc.x + 15, loc.y + 30);
    Polygon top = new Polygon();
    top.addPoint(loc.x + 10, loc.y);
    top.addPoint(loc.x + 30, loc.y);
    top.addPoint(loc.x + 30, loc.y + 5);
    top.addPoint(loc.x + 10, loc.y + 5);
    Polygon door = new Polygon();
    display.add(base);
    display.add(pole);
    display.add(top);
  }
}
