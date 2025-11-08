package rvb.grid;

import java.awt.*;

public class Cell extends Rectangle {
    public final int col, row;
    private boolean highlight;

    public Cell(int c, int r, int x, int y, int size) {
        super(x, y, size, size);
        this.col = c; this.row = r;
    }

    public void setHighlight(boolean b) { highlight = b; }

    public void paint(Graphics2D g) {
        g.setColor(highlight ? Color.GRAY : Color.DARK_GRAY);
        g.drawRect(x, y, width, height);
    }

    @Override
    public String toString() {
        return "(" + col + "," + row + ")";
    }
}
