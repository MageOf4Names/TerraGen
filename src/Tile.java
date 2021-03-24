import java.awt.*;

public abstract class Tile implements ITile {
    protected Point location;

    protected Color color = Color.white;

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

    @Override
    public Point getPixelLocation(float scale) {
        int x = (int)(location.x * scale);
        int y = (int)(location.y * scale);

        return new Point(x, y);
    }

    @Override
    public Point getPixelCenterLocation(float scale) {
        Point c = getCenter(scale);
        Point pos = getPixelLocation(scale);

        return new Point(pos.x + c.x, pos.y + c.y);
    }
}
