import java.awt.*;

public abstract class Tile implements ITile {
    protected Point location;

    protected Color color;

    public Tile(Point location) {
        this.location = location;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
