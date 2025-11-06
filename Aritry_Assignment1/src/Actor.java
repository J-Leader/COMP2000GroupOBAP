import java.awt.Graphics;


public abstract class Actor {
    protected TerrainWeatherCell location;

    public Actor(TerrainWeatherCell loc) {
        this.location = loc;
    }

    public TerrainWeatherCell getLocation() { return location; }

    public abstract void paint(Graphics g);

    
    public void reactToCell(TerrainWeatherCell c) {
        if (c == null) return;
        CellInfo info = c.getData();
        System.out.println(getClass().getSimpleName() + " sees " + info.terrain + " & " + info.weather + " at " + c.getRow() + "," + c.getCol());
    }
}
