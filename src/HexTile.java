import java.awt.*;
import java.io.Serializable;

public class HexTile extends Tile implements Serializable {
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
