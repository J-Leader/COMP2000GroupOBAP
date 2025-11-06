import java.awt.Graphics;


public abstract class Item<T> implements Drawable {
    protected T payload;
    protected String name;

    public Item(String name, T payload) {
        this.name = name;
        this.payload = payload;
    }

    public String getName() { return name; }
    public T getPayload() { return payload; }

    draw(Graphics)
}
