import java.awt.*;

public interface IToken {
    void setLocation(Point location);

    void setLocation(ITileGrid grid, int x, int y);

    Point getLocation();

    void setColor(Color color);

    Color getColor();

    float getScale();

    void setScale(float scale);

    void draw(Graphics2D g, float scale);
}
