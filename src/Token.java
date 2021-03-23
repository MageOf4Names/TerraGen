import java.awt.*;

public class Token implements IToken {
    protected ITile tile = null;

    // Token size multiplier
    protected float scale = 1f;

    protected Color color;

    public Token(ITileGrid grid, Point location, float scale, Color color) {
        setLocation(grid, location);
        this.scale = scale;
        this.color = color;
    }

    public Token(Tile tile, float scale, Color color) {
        setTile(tile);
        this.scale = scale;
        this.color = color;
    }

    public void setLocation(ITileGrid grid,  Point location) {
        tile = grid.getClosestTile(location);
    }

    @Override
    public ITile getTile() {
        return tile;
    }

    public void setTile(ITile tile) {
        this.tile = tile;
    }

    public Point getLocation() {
        return tile.getLocation();
    }

    @Override
    public Point getPixelLocation(float scale) {
        return tile.getPixelLocation(scale);
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void draw(Graphics2D g, float scale) {
        var size = scale * this.scale;

        var c = g.getColor();
        var pos = getPixelLocation(scale);

        // Draw circle outline
        g.setColor(Color.BLACK);
        g.fillOval(pos.x, pos.y, (int)size, (int)size);

        // Draw filled circle
        g.setColor(color);
        g.fillOval(pos.x + 3, pos.y + 3, (int)size - 6, (int)size - 6);

        g.setColor(c);
    }
}
