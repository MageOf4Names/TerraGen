public interface ITileGrid {
    ITile getTile(int x, int y);

    ITile getTile(Point location);

    int getWidth();

    int getHeight();

    float getTileSize();

    void setTileSize(float scale);
}
