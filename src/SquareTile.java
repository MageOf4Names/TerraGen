import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class SquareTile extends Tile implements Serializable {
    public SquareTile(Point location) {
        super(location);

        // Generate a random color
        Random ran = new Random();
        int r = 255 - ran.nextInt(30);
        int g = 255 - ran.nextInt(30);
        int b = 255 - ran.nextInt(30);

        color = new Color(r, g, b);
    }

    /**
     * Get the pixel offset to the center of this tile from its location
     *
     * @param scale
     * @return
     */
    @Override
    public Point getCenterOffset(float scale) {
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
