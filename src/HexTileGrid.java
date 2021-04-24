import java.awt.*;
import java.io.Serializable;

public class HexTileGrid extends TileGrid implements Serializable {
    protected HexTile[] tiles;

    protected int width, height;

    public HexTileGrid(int width, int height, float scale) {
        super(scale);

        this.width = width;
        this.height = height;

        tiles = new HexTile[width * height];

        // Populate the grid with new square tiles
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x * width + y] = new HexTile(new Point(x, y));
            }
        }
    }

    public HexTileGrid(int width, int height) {
        this(width, height, 1);
    }

    /**
     * Get the number of tiles high the grid is
     *
     * @return
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Get the number of tiles wide the grid is
     *
     * @return
     */
    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics2D g) {
        for (HexTile tile : tiles) {
            tile.draw(g, scale);
        }
    }

    /**
     * Gets the closest tile to the given point
     *
     * @param pos location
     * @return ITile
     */
    @Override
    public HexTile getClosestTile(Point pos, float scale) {
        // The current best tile
        int bestDist = Integer.MAX_VALUE;
        HexTile best = null;

        for (HexTile tile : tiles) {
            int dist = pos.distance(tile.getPixelCenterLocation(scale));

            // Check if this tile is closer
            if (best == null || dist < bestDist) {
                // Update the best tile
                best = tile;
                bestDist = dist;
            }
        }

        return best;
    }

    /**
     * Gets the closest tile to the given point
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public ITile getClosestTile(int x, int y) {
        return getClosestTile(new Point(x, y), getTileSize());
    }

    /**
     * Get the tile at the given x, y grid coordinates
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public HexTile getTile(int x, int y) {
        // Check that the location is inside the grid
        if (x < 0 || x >= width || y < 0 || y >= height)
            return null;

        return tiles[x * width + y];
    }

    /**
     * Get the tile at the given x, y grid coordinates
     *
     * @param location
     * @return
     */
    @Override
    public HexTile getTile(Point location) {
        return getTile(location.getX(), location.getY());
    }
}
