import java.awt.*;

public interface ITileGrid {
    ITile getTile(int x, int y);

    ITile getTile(Point location);

    int getWidth();

    int getHeight();

    float getTileSize();

    void setTileSize(float scale);

    void draw(Graphics2D g);

    /**
     * Gets the closest tile to the given point
     * @param pos location
     * @return ITile
     */
    ITile getClosestTile(Point pos, float scale);
}
