import java.awt.*;

public interface ITile {
    Point getCenter();

    Point getLocation();

    Point getPixelLocation(float scale);

    Point getPixelCenterLocation(float scale);

    void setColor(Color color);

    Color getColor();

    void draw(Graphics2D g, float scale);
}
