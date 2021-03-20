import java.awt.*;

public class HexTileGrid extends TileGrid {
    protected HexTile[] tiles;

    protected int width, height;

    public HexTileGrid(int width, int height, float scale) {
        super(scale);

        this.width = width;
        this.height = height;

        tiles = new HexTile[width * height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x * width + y] = new HexTile(new Point(x, y));
            }
        }
    }

    public HexTileGrid(int width, int height) {
        this(width, height, 1);
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

    @Override
    public HexTile getTile(int x, int y) {
        return tiles[x * width + y];
    }

    @Override
    public HexTile getTile(Point location) {
        return getTile(location.getX(), location.getY());
    }
}
