import java.util.ArrayList;
import java.util.List;


public class CellInfo {
    public TerrainType terrain;
    public WeatherType weather;
    public List<Item<?>> items;

    public CellInfo(TerrainType terrain, WeatherType weather) {
        this.terrain = terrain;
        this.weather = weather;
        this.items = new ArrayList<>();
    }

    public void addItem(Item<?> item) {
        if (item != null) items.add(item);
    }
}
