import java.awt.*;

public class HexTile extends Tile {
    public HexTile(Point location) {
        super(location);
    }

    @Override
    public Point getCenter() {
        return location;
    }

    @Override
    public void draw(Graphics2D g, float scale) {

    }
}
