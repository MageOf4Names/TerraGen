import java.awt.*;

public class Token implements IToken {
    private ITile tile;

    private Color color;

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
}
