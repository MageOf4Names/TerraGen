import java.awt.*;

public class Token implements IToken {
    protected Point pos = new Point(0, 0);

    // Token size multiplier
    protected float scale;

    protected Color color;

    public Token(float scale, Color color) {
        this.scale = scale;
        this.color = color;
    }

    @Override
    public void setLocation(ITileGrid grid, int x, int y) {
        var tile = grid.getTile(x, y);
        pos = tile.getPixelCenterLocation(grid.getTileSize());
    }

    @Override
    public void setLocation(Point location) {
        pos = location;
    }

    public Point getLocation() {
        return pos;
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

        int x = pos.x - (int)(size / 2);
        int y = pos.y - (int)(size / 2);

        // Draw circle outline
        g.setColor(Color.BLACK);
        g.fillOval(x, y, (int) size, (int) size);

        // Draw filled circle
        g.setColor(color);
        g.fillOval(x + 3, y + 3, (int) size - 6, (int) size - 6);

        g.setColor(c);
    }
}
