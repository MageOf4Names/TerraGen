public class HexGrid implements ITileGrid {
    protected HexTile[] tiles;

    protected int width, height;

    public HexGrid(int width, int height) {
        this.width = width;
        this.height = height;

        tiles = new HexTile[width * height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x * width + y] = new HexTile(new Point(x, y));
            }
        }
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
    public HexTile getTile(int x, int y) {
        return tiles[x * width + y];
    }

    @Override
    public HexTile getTile(Point location) {
        return getTile(location.getX(), location.getY());
    }
}
