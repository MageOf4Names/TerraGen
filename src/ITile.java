import java.awt.*;

public interface ITile {
    /**
     * Get the pixel offset to the center of this tile from its location
     *
     * @param scale
     * @return
     */
    Point getCenterOffset(float scale);

    /**
     * Get the grid location of this tile
     *
     * @return
     */
    Point getGridLocation();

    /**
     * Get the pixel location of this tile
     *
     * @param scale
     * @return
     */
    Point getPixelLocation(float scale);

    /**
     * Get the pixel center location of this tile
     *
     * @param scale
     * @return
     */
    default Point getPixelCenterLocation(float scale) {
        Point c = getCenterOffset(scale);
        Point pos = getPixelLocation(scale);

        return new Point(pos.x + c.x, pos.y + c.y);
    }

    /**
     * Sets the color of this tile
     *
     * @param color
     */
    void setColor(Color color);

    /**
     * Gets the color of this tile
     *
     * @return
     */
    Color getColor();

    /**
     * Render this tile on screen
     *
     * @param g     The graphics to render to
     * @param scale
     */
    void draw(Graphics2D g, float scale);
}
