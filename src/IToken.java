import java.awt.*;

public interface IToken {
    ITile getTile();

    void setTile(ITile tile);

    void setLocation(ITileGrid grid, Point location);

    Point getLocation();

    Point getPixelLocation(float scale);

    void setColor(Color color);

    Color getColor();

    float getScale();

    void setScale(float scale);

    void draw(Graphics2D g, float scale);
}
