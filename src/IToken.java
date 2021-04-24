import java.awt.*;

public interface IToken {
    /**
     * Sets the location of this token
     * @param location
     */
    void setLocation(Point location);

    /**
     * Set the grid location of this token
     * @param grid
     * @param x
     * @param y
     */
    void setLocation(ITileGrid grid, int x, int y);

    /**
     * Get the location of this token
     * @return
     */
    Point getLocation();

    /**
     * Sets the color of this token
     * @param color
     */
    void setColor(Color color);

    /**
     * Gets the color of this token
     * @return
     */
    Color getColor();

    /**
     * Gets the scale of this token
     * @return
     */
    float getScale();

    /**
     * Sets the scale of this token
     * @param scale
     */
    void setScale(float scale);

    void draw(Graphics2D g, float scale);
}
