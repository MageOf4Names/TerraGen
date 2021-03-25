import java.awt.*;

public class Token implements IToken {
    protected ITile tile = null;

    // Token size multiplier
    protected float scale;

    protected Color color;

    public Token(float scale, Color color) {
        this.scale = scale;
        this.color = color;
    }

    @Override
    public void setLocation(ITileGrid grid, Point location) {
        tile = grid.getClosestTile(location, grid.getTileSize());
    }

    @Override
    public void setLocation(ITileGrid grid, ITile tile) {
        setLocation(grid, tile.getPixelCenterLocation(grid.getTileSize()));
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
        g.fillOval(pos.x, pos.y, (int) size, (int) size);

        // Draw filled circle
        g.setColor(color);
        g.fillOval(pos.x + 3, pos.y + 3, (int) size - 6, (int) size - 6);

        g.setColor(c);
    }
}
