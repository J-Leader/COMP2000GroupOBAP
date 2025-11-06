import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;


public class GridPanel extends JPanel {
    private Stage stage;
    private Grid grid;
    private Point lastMouse = null;

    public GridPanel(int rows, int cols, int cellSize, int offset) {
        stage = new Stage(rows, cols, cellSize, offset);
        grid = stage.getGrid();

        
        stage.addActor(new Cat(grid.getCell(2,2)));
        stage.addActor(new Dog(grid.getCell(5,8)));
        stage.addActor(new Bird(grid.getCell(10,12)));

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lastMouse = e.getPoint();
                grid.updateHighlight(lastMouse);
                Optional<TerrainWeatherCell> maybe = grid.cellAtPoint(lastMouse);
                if (maybe.isPresent()) {
                    stage.reactActorsToCell(maybe.get());
                }
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lastMouse = null;
                grid.updateHighlight(null);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        stage.paint(g);

        
        int infoX = 740, infoY = 30;
        g.drawString("COMP2000 2025 — Terrain+Weather demo", infoX, infoY);
        g.drawString("Hover cells to see actor reactions and info", infoX, infoY + 20);

        Optional<TerrainWeatherCell> maybe = grid.cellAtPoint(lastMouse);
        if (maybe.isPresent()) {
            TerrainWeatherCell c = maybe.get();
            CellInfo info = c.getData();
            g.drawString("Cell: " + c.getRow() + "," + c.getCol(), infoX, infoY + 60);
            g.drawString("Terrain: " + info.terrain, infoX, infoY + 80);
            g.drawString("Weather: " + info.weather, infoX, infoY + 100);
            g.drawString("Items: " + info.items.size(), infoX, infoY + 120);
        } else {
            g.drawString("No cell under mouse", infoX, infoY + 60);
        }
    }
}
