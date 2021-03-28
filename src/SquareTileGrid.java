import java.awt.*;
import java.io.Serializable;

public class SquareTileGrid extends TileGrid implements Serializable {
    protected SquareTile[] tiles;

    protected int width, height;

    public SquareTileGrid(int width, int height, float scale) {
        super(scale);

        this.width = width;
        this.height = height;

        tiles = new SquareTile[width * height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x * width + y] = new SquareTile(new Point(x, y));
            }
        }
    }

    public SquareTileGrid(int width, int height) {
        this(width, height, 1);
    }

    @Override
    public SquareTile getTile(int x, int y) {
        // Check that the location is inside the grid
        if(x < 0 || x >= width || y < 0 || y >= height)
            return null;

        return tiles[x * width + y];
    }

    @Override
    public SquareTile getTile(Point location) {
        return getTile(location.getX(), location.getY());
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics2D g) {
        for (SquareTile tile: tiles) {
            tile.draw(g, scale);
        }
    }

    @Override
    public SquareTile getClosestTile(Point pos, float scale) {
        // The current best tile
        int bestDist = Integer.MAX_VALUE;
        SquareTile best= null;

        for (SquareTile tile: tiles) {
            int dist = pos.distance(tile.getPixelCenterLocation(scale));

            // Check if this tile is closer
            if(best == null || dist < bestDist){
                // Update the best tile
                best = tile;
                bestDist = dist;
            }
        }

        return best;
    }
}
