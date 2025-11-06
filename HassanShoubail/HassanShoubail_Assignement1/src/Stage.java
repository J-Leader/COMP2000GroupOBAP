import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Stage {
  Grid grid;
  Itemlists<Animal> animals;
  Itemlists<House> houses;

  public Stage() {
    grid = new Grid();
    animals = new Itemlists<>();
    animals.addAllitems(new Cat(grid.cellAtColRow(0, 0).get()));
    animals.addAllitems(new Dog(grid.cellAtColRow(2, 15).get()));
    animals.addAllitems(new Bird(grid.cellAtColRow(12, 9).get()));  
    houses = new Itemlists<>();
    houses.addAllitems(new ScratchPost(grid.cellAtColRow(13,8).get()));
    houses.addAllitems(new Birdcage(grid.cellAtColRow(7, 12).get()));
    houses.addAllitems(new DogHouse(grid.cellAtColRow(4, 6).get()));  
  }

  public void paint(Graphics g, Point mouseLoc) {
    grid.paint(g, mouseLoc);
    for(int i = 0; i < animals.getSize(); i++) {
      animals.getitem(i).paint(g);
    }
    for(int i = 0; i < houses.getSize(); i++) {
      houses.getitem(i).paint(g);
    }
    Optional<Cell> underMouse = grid.cellAtPoint(mouseLoc);
    if(underMouse.isPresent()) {
      Cell hoverCell = underMouse.get();
      g.setColor(Color.DARK_GRAY);
      g.drawString(String.valueOf(hoverCell.col) + String.valueOf(hoverCell.row), 740, 30);
    }
  }
}
