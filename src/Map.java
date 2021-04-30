import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Maintains the grid and tokens
 */
public class Map implements Serializable, ITileGrid {
    public ITileGrid grid;

    public ArrayList<Token> tokens = new ArrayList<>();

    public Map(ITileGrid grid) {
        this.grid = grid;
    }

    /**
     * Get the tile at the given x, y grid coordinates
     *
     * @param x X grid coordinate
     * @param y Y grid coordinate
     * @return Tile at the given coordinate
     */
    @Override
    public ITile getTile(int x, int y) {
        return grid.getTile(x, y);
    }

    /**
     * Get the tile at the given x, y grid coordinates
     *
     * @param location Coordinate of the selection
     * @return A single tile.
     */
    @Override
    public ITile getTile(Point location) {
        return grid.getTile(location);
    }

    /**
     * Get the number of tiles high the grid is
     *
     * @return Width of the grid
     */
    public int getWidth() {
        return grid.getWidth();
    }

    /**
     * Get the number of tiles wide the grid is
     *
     * @return Height of the grid
     */
    public int getHeight() {
        return grid.getHeight();
    }

    /**
     * Get the pixel tile size
     *
     * @return Size of a single tile
     */
    @Override
    public float getTileSize() {
        return grid.getTileSize();
    }

    /**
     * Sets the pixel tile size
     */
    @Override
    public void setTileSize(float scale) {
        grid.setTileSize(scale);
    }

    /**
     * Get the pixel tile size
     *
     * @return Scale of a single tile
     */
    public float getScale() {
        return grid.getTileSize();
    }

    /**
     * Adds a token to this map
     *
     * @param x X grid coordinate
     * @param y Y grid coordinate
     * @param token Token to add
     * @return New token
     */
    public Token addToken(int x, int y, Token token) {
        tokens.add(token);
        token.setLocation(grid, x, y);
        return token;
    }

    /**
     * Adds a new token to this map
     *
     * @param x X grid coordinate
     * @param y Y grid coordinate
     * @param color Token color
     * @param token Token to be added.
     * @return New token on the grid
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
     * @param x X grid coordinate
     * @param y Y grid coordinate
     * @param color Token color
     * @return New token to be added to the grid
     */
    public Token addToken(int x, int y, String color) {
        return addToken(x, y, color, new Token());
    }

    /**
     * Adds a new token to this map
     *
     * @param x X coordinate of the grid for the new token
     * @param y Y coordinate of the grid for the new token
     * @return Adds a new token to the map
     */
    public Token addToken(int x, int y) {
        return addToken(x, y, new Token());
    }

    /**
     * Gets the closest token to the given point
     *
     * @param pos Point to compare against
     * @return Token closest to the mouse click
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
     * @param scale Scale of the tiles
     * @return ITile
     */
    @Override
    public ITile getClosestTile(Point pos, float scale) {
        return grid.getClosestTile(pos, scale);
    }

    /**
     * Gets the closest tile to the given point
     *
     * @param x the x coordinate of the mouse click
     * @param y the y coordinate of the mouse click
     * @return Returns the closest tile to the mouse click
     */
    @Override
    public ITile getClosestTile(int x, int y) {
        return grid.getClosestTile(x, y);
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    /**
     * Used to notify map that it needs to remove a token based off Client network container
     */
    public void removeToken() {
        int index = TerraGen.window.getClient().getNetworkContainer().getKey();
        tokens.remove(index);
        TerraGen.window.repaint();
    }
}