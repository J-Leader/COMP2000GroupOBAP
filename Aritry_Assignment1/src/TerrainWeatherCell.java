import java.awt.Graphics;
import java.awt.Color;


public class TerrainWeatherCell extends Cell<CellInfo> implements Interactive {
    public TerrainWeatherCell(int x, int y, int size, int row, int col, CellInfo info) {
        super(x, y, size, size, row, col, info);
    }

    @Override
    public void draw(Graphics g) {
        
        CellInfo info = getData();
        Color prev = g.getColor();

        
        switch (info.terrain) {
            case GRASS: g.setColor(new Color(200, 255, 180)); break;
            case WATER: g.setColor(new Color(180, 220, 255)); break;
            case SAND:  g.setColor(new Color(255, 240, 180)); break;
            case MOUNTAIN: g.setColor(new Color(220, 220, 220)); break;
            default: g.setColor(Color.LIGHT_GRAY); break;
        }
        g.fillRect(x, y, width, height);

            switch (info.weather) {
            case SUNNY: g.setColor(new Color(255, 255, 170, 60)); break;
            case RAINY: g.setColor(new Color(100, 150, 255, 60)); break;
            case SNOWY: g.setColor(new Color(255, 255, 255, 80)); break;
            default: g.setColor(new Color(0,0,0,0)); break;
        }
        g.fillRect(x, y, width, height);

        
        if (isHighlighted()) {
            g.setColor(new Color(120, 120, 120, 120));
            g.fillRect(x, y, width, height);
        }

        
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        drawItems(g);

        g.setColor(prev);
    }

    
    @Override
    public void interact(Actor actor) {
        
        CellInfo info = getData();
        if (info == null) return;
        
        for (int i = 0; i < info.items.size(); i++) {
            Item<?> it = info.items.get(i);
            if (it instanceof Seed && actor instanceof Bird) {
                System.out.println("Bird collected a seed at " + getRow() + "," + getCol());
                info.items.remove(i);
                break;
            }
        }
    }
}
