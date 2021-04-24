import java.awt.*;
import java.io.Serializable;

public abstract class Tile implements ITile, Serializable {
    // This tile's location
    protected Point location;

    // The color of this tile
    protected Color color = Color.white;

    public Tile(Point location) {
        this.location = location;
    }

    @Override
    public Point getGridLocation() {
        return location;
    }

    /**
     * Set the color of this tile
     *
     * @param color
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Get the color of this tile
     *
     * @return
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Get the pixel location of this tile
     *
     * @param scale
     * @return
     */
    @Override
    public Point getPixelLocation(float scale) {
        int x = (int) (location.x * scale);
        int y = (int) (location.y * scale);

        return new Point(x, y);
    }
}
