public abstract class TileGrid implements ITileGrid {
    protected float scale = 1;

    public TileGrid(float scale) {
        this.scale = scale;
    }

    @Override
    public float getTileSize() {
        return scale;
    }

    @Override
    public void setTileSize(float scale) {
        this.scale = scale;
    }
}
