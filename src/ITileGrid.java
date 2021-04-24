import java.awt.*;

public interface ITileGrid {
    /**
     * Get the tile at the given x, y grid coordinates
     *
     * @param x
     * @param y
     * @return
     */
    ITile getTile(int x, int y);

    /**
     * Get the tile at the given x, y grid coordinates
     *
     * @param location
     * @return
     */
    ITile getTile(Point location);

    /**
     * Get the number of tiles high the grid is
     *
     * @return
     */
    int getWidth();

    /**
     * Get the number of tiles wide the grid is
     *
     * @return
     */
    int getHeight();

    /**
     * Get the pixel tile size
     *
     * @return
     */
    float getTileSize();

    /**
     * Sets the pixel tile size
     *
     * @param scale
     */
    void setTileSize(float scale);

    void draw(Graphics2D g);

    /**
     * Gets the closest tile to the given point
     *
     * @param pos location
     * @return ITile
     */
    ITile getClosestTile(Point pos, float scale);

    /**
     * Gets the closest tile to the given point
     *
     * @param x
     * @param y
     * @return
     */
    ITile getClosestTile(int x, int y);
}
