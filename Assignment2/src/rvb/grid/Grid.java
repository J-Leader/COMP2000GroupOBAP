package rvb.grid;

import java.awt.*;
import java.util.Optional;

public class Grid {
    public static final int COLS = 20, ROWS = 20, SIZE = 35;
    public static final int OFFSET_X = 10, OFFSET_Y = 10;
    private final Cell[][] cells = new Cell[COLS][ROWS];

    public Grid() {
        for (int c = 0; c < COLS; c++)
            for (int r = 0; r < ROWS; r++)
                cells[c][r] = new Cell(c, r,
                        OFFSET_X + c * SIZE, OFFSET_Y + r * SIZE, SIZE);
    }

    public void paint(Graphics2D g, Point mouse) {
        for (int c = 0; c < COLS; c++)
            for (int r = 0; r < ROWS; r++) {
                Cell cell = cells[c][r];
                cell.setHighlight(mouse != null && cell.contains(mouse));
                cell.paint(g);
            }
    }

    public Optional<Cell> cellAtPoint(Point p) {
        if (p == null) return Optional.empty();
        for (int c = 0; c < COLS; c++)
            for (int r = 0; r < ROWS; r++)
                if (cells[c][r].contains(p)) return Optional.of(cells[c][r]);
        return Optional.empty();
    }
    public Optional<Cell> cellAtColRow(int c, int r) {
        if (c < 0 || c >= COLS || r < 0 || r >= ROWS)
            return Optional.empty();
        return Optional.of(cells[c][r]);
    }

    public Cell[][] rawCells() { return cells; }
}
