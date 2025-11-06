import java.awt.Point;
import java.util.Optional;


public class Grid {
    private TerrainWeatherCell[][] cells;
    private final int rows, cols, cellSize, offset;

    public Grid(int rows, int cols, int cellSize, int offset) {
        this.rows = rows; this.cols = cols; this.cellSize = cellSize; this.offset = offset;
        cells = new TerrainWeatherCell[rows][cols];

        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = offset + c * cellSize;
                int y = offset + r * cellSize;
                TerrainType t;
                if ((r + c) % 11 == 0) t = TerrainType.MOUNTAIN;
                else if ((r + c) % 5 == 0) t = TerrainType.WATER;
                else if ((r + c) % 3 == 0) t = TerrainType.SAND;
                else t = TerrainType.GRASS;

                WeatherType w;
                if ((r * c) % 13 == 0) w = WeatherType.SNOWY;
                else if ((r + c) % 4 == 0) w = WeatherType.RAINY;
                else w = WeatherType.SUNNY;

                CellInfo info = new CellInfo(t, w);
                
                if (t == TerrainType.GRASS && (r + c) % 7 == 0) info.addItem(new Seed());
                
                if (t == TerrainType.SAND && (r + c) % 11 == 0) info.addItem(new Bone());
                if (t == TerrainType.WATER && (r + c) % 5 == 0) info.addItem(new Fish());

                cells[r][c] = new TerrainWeatherCell(x, y, cellSize, r, c, info);
            }
        }
    }

    public TerrainWeatherCell getCell(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) return null;
        return cells[row][col];
    }

    public void paint(java.awt.Graphics g) {
        for (int r = 0; r < rows; r++) for (int c = 0; c < cols; c++) cells[r][c].draw(g);
    }

    public void updateHighlight(Point p) {
        if (p == null) {
            for (TerrainWeatherCell[] row : cells) for (TerrainWeatherCell cell : row) cell.setHighlighted(false);
            return;
        }
        for (TerrainWeatherCell[] row : cells) for (TerrainWeatherCell cell : row) {
            boolean inside = false;
            try { inside = cell.contains(p); } catch (Exception ex) { inside = false; }
            cell.setHighlighted(inside);
        }
    }

    public Optional<TerrainWeatherCell> cellAtPoint(Point p) {
        if (p == null) return Optional.empty();
        int px = p.x - offset, py = p.y - offset;
        if (px < 0 || py < 0) return Optional.empty();
        int col = px / cellSize, row = py / cellSize;
        if (row < 0 || row >= rows || col < 0 || col >= cols) return Optional.empty();
        TerrainWeatherCell c = cells[row][col];
        if (c.contains(p)) return Optional.of(c);
        return Optional.empty();
    }
}
