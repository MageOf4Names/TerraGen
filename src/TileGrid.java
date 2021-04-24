import java.io.Serializable;

public abstract class TileGrid implements ITileGrid, Serializable {
    protected float scale = 1;

    public TileGrid(float scale) {
        this.scale = scale;
    }

    /**
     * Get the pixel tile size
     *
     * @return
     */
    @Override
    public float getTileSize() {
        return scale;
    }

    /**
     * Sets the pixel tile size
     *
     * @param scale
     */
    @Override
    public void setTileSize(float scale) {
        this.scale = scale;
    }
}
