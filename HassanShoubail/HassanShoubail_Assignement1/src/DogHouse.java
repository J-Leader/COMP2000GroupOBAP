import java.awt.Color;
import java.awt.Polygon;
import java.util.ArrayList;

public class DogHouse extends House {
  public DogHouse(Cell inLoc) {
    loc = inLoc;
    color = Color.CYAN;
    display = new ArrayList<Polygon>();
    
    Polygon roof = new Polygon();
    roof.addPoint(loc.x + 5, loc.y + 12);
    roof.addPoint(loc.x + 17, loc.y + 0);
    roof.addPoint(loc.x + 30, loc.y + 12);
    Polygon front = new Polygon();
    front.addPoint(loc.x + 5, loc.y + 12);
    front.addPoint(loc.x + 30, loc.y + 12);
    front.addPoint(loc.x + 30, loc.y + 30);
    front.addPoint(loc.x + 5, loc.y + 30);
    display.add(roof);
    display.add(front);
  }
}
