import java.awt.*;

public class SquareTileGrid extends TileGrid {
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
        return null;
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

    }
}
