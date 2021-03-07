public class HexGrid implements ITileGrid {
    protected HexTile[] hexTiles;

    protected int width, height;

    public HexGrid(int width, int height) {
        this.width = width;
        this.height = height;

        hexTiles=new HexTile[width * height];
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
        return hexTiles[x * width + y];
    }

    @Override
    public HexTile getTile(Point location) {
        return getTile(location.getX(), location.getY());
    }
}
