package rvb.actors;

import rvb.grid.Cell;
import java.awt.*;

public abstract class Actor {
    protected Cell cell;
    public Actor(Cell c) { this.cell = c; }
    public void setCell(Cell c) { this.cell = c; }
    public Cell getCell() { return cell; }
    public abstract void paint(Graphics2D g);
}
