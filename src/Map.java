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

    /**
     * Get the number of tiles high the grid is
     *
     * @return
     */
    public int getWidth() {
        return grid.getWidth();
    }

    /**
     * Get the number of tiles wide the grid is
     *
     * @return
     */
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

    /**
     * Get the pixel tile size
     *
     * @return
     */
    public float getScale() {
        return grid.getTileSize();
    }

    /**
     * Adds a token to this map
     *
     * @param x
     * @param y
     * @param token Token to add
     * @return
     */
    public Token addToken(int x, int y, Token token) {
        tokens.add(token);
        token.setLocation(grid, x, y);
        return token;
    }

    /**
     * Adds a new token to this map
     *
     * @param x
     * @param y
     * @param color
     * @param token
     * @return
     */
    public Token addToken(int x, int y, String color, Token token) {
        token.setColor(color);
        token.setLocation(grid, x, y);
        tokens.add(token);
        return token;
    }

    /**
     * Adds a new token to this map
     *
     * @param x
     * @param y
     * @param color
     * @return
     */
    public Token addToken(int x, int y, String color) {
        return addToken(x, y, color, new Token());
    }

    /**
     * Adds a new token to this map
     *
     * @param x
     * @param y
     * @return
     */
    public Token addToken(int x, int y) {
        return addToken(x, y, new Token());
    }

    /**
     * Gets the closest token to the given point
     *
     * @param pos Point to compare against
     * @return
     */
    public Token getClosestToken(Point pos) {
        Token best = null;
        float bestDist = Integer.MAX_VALUE;

        for (var token : tokens) {
            var dist = pos.distance(token.getLocation());

            // Check if token was closer
            if (best == null || dist < bestDist) {
                // Update best token
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

    /**
     * Gets the closest tile to the given point
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public ITile getClosestTile(int x, int y) {
        return grid.getClosestTile(x, y);
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}