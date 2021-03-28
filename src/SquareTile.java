import java.awt.*;
import java.io.Serializable;

public class SquareTile extends Tile implements Serializable {
    public SquareTile(Point location) {
        super(location);
    }

    @Override
    public Point getCenter(float scale) {
        int a = (int) (scale / 2);
        return new Point(a, a);
    }

    @Override
    public void draw(Graphics2D g, float scale) {
        Point pos = getPixelLocation(scale);

        var c = g.getColor();

        // Draw fill color
        g.setColor(color);
        g.fillRect(pos.x, pos.y, (int) scale, (int) scale);

        // Draw outline
        g.setColor(Color.BLACK);
        g.drawRect(pos.x, pos.y, (int) scale, (int) scale);

        g.setColor(c);
    }
}
