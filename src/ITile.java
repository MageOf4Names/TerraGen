import java.awt.*;

public interface ITile {
    Point getCenter();

    Point getLocation();

    void setColor(Color color);

    Color getColor();

    void draw(Graphics2D g, float scale);
}
