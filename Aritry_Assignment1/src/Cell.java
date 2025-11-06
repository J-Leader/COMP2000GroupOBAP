import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;


public class Cell<T> extends Rectangle implements Drawable {
    private T data;
    private int row;
    private int col;
    private boolean highlighted = false;

    public Cell(int x, int y, int w, int h, int row, int col, T data) {
        super(x, y, w, h);
        this.row = row;
        this.col = col;
        this.data = data;
    }

    public T getData() { return data; }
    public void setData(T d) { data = d; }
    public int getRow() { return row; }
    public int getCol() { return col; }

    public void setHighlighted(boolean h) { highlighted = h; }
    public boolean isHighlighted() { return highlighted; }

    @Override
    public void draw(Graphics g) {
        if (highlighted) {
            Color prev = g.getColor();
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, width, height);
            g.setColor(prev);
        }
        g.drawRect(x, y, width, height);
    }

    
    @SuppressWarnings("unchecked")
    public void drawItems(Graphics g) {
        if (data instanceof CellInfo) {
            CellInfo info = (CellInfo) data;
            int i = 0;
            for (Item<?> it : info.items) {
                g.translate(x, y);
                it.draw(g);
                g.translate(-x, -y);
                i++;
            }
        }
    }
}
