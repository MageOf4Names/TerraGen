import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Maintains the grid and tokens
 */
public class Map implements Serializable, ITileGrid {
    public ITileGrid grid;

    public ArrayList<Token> tokens = new ArrayList<Token>();

    public Map(ITileGrid grid) {
        this.grid = grid;
    }

    /**
     * Get the tile at the given x, y grid coordinates
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public ITile getTile(int x, int y) {
        return grid.getTile(x, y);
    }

    /**
     * Get the tile at the given x, y grid coordinates
     *
     * @param location
     * @return
     */
    @Override
    public ITile getTile(Point location) {
        return grid.getTile(location);
    }

    public int getWidth() {
        return grid.getWidth();
    }

    public int getHeight() {
        return grid.getHeight();
    }

    /**
     * Get the pixel tile size
     *
     * @return
     */
    @Override
    public float getTileSize() {
        return grid.getTileSize();
    }

    /**
     * Sets the pixel tile size
     *
     * @param scale
     */
    @Override
    public void setTileSize(float scale) {
        grid.setTileSize(scale);
    }

    public float getScale() {
        return grid.getTileSize();
    }

    public Token addToken(int x, int y, Token token) {
        tokens.add(token);
        token.setLocation(grid, x, y);
        return token;
    }

    public Token addToken(int x, int y) {
        return addToken(x, y, new Token());
    }

    public Token getClosestToken(Point pos) {
        Token best = null;
        float bestDist = Integer.MAX_VALUE;

        for (var token : tokens) {
            var dist = pos.distance(token.getLocation());

            if (best == null || dist < bestDist) {
                best = token;
                bestDist = dist;
            }
        }

        return best;
    }

    public void draw(Graphics2D g) {
        var c = g.getColor();
        float scale = grid.getTileSize();

        // Draw map outline
        g.setColor(Color.lightGray);
        g.drawRect(0, 0, (int) (getWidth() * scale), (int) (getHeight() * scale));

        // Draw tiles
        grid.draw(g);

        // Draw each token
        for (var t : tokens) {
            t.draw(g, scale);
        }

        g.setColor(c);
    }

    /**
     * Gets the closest tile to the given point
     *
     * @param pos   location
     * @param scale
     * @return ITile
     */
    @Override
    public ITile getClosestTile(Point pos, float scale) {
        return grid.getClosestTile(pos, scale);
    }

    @Override
    public ITile getClosestTile(int x, int y) {
        return grid.getClosestTile(x, y);
    }
}
