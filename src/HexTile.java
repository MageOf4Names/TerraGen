import java.awt.*;

public class HexTile extends Tile {
    public HexTile(Point location) {
        super(location);
    }

    @Override
    public Point getCenter(float scale) {
        return getLocation();
    }

    @Override
    public void draw(Graphics2D g, float scale) {
        Point pos = getPixelLocation(scale);

        Polygon p = new Polygon();

        g.drawOval(pos.x, pos.y, 5, 5);
    }
}
