import java.awt.*;

public class Token implements IToken {
    protected ITile tile;

    protected float scale = 1;

    protected Color color;

    @Override
    public ITile getTile() {
        return tile;
    }

    @Override
    public void setTile(ITile tile) {
        this.tile = tile;
    }

    @Override
    public Point getLocation() {
        return tile.getLocation();
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
}
