import java.awt.*;

public class SquareTile extends Tile {
    public SquareTile(Point location) {
        super(location);
    }

    @Override
    public Point getCenter() {
        return location;
    }

    @Override
    public void draw(Graphics2D g) {

    }
}
