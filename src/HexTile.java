import java.awt.*;
import java.io.Serializable;

public class HexTile extends Tile implements Serializable {
    public HexTile(Point location) {
        super(location);
    }

    @Override
    public Point getCenter(float scale) {
        float r = scale / 2;
        /* Conditional branch for tiling offset (odd rows are offset by a radius) */
        if (this.location.y % 2 == 0) {
            return new Point(this.location.x * scale + r, this.location.y * scale + r);
        } else {
            return new Point((this.location.x + 1) * scale, this.location.y * scale + r);
        }
    }

    @Override
    public void draw(Graphics2D g, float scale) {
        Point center = getCenter(scale);
        var c = g.getColor();

        int r = (int) (scale / 2);
        int x = center.x - r;
        int y = center.y - r;
        int s = (int) (scale / 1.73205);
        int t = (int) (r / 1.73205);
        int tmpX, tmpY;

        int[] pX = {x+t,x+s+t,x+s+t+t,x+s+t,x+t,x};
        int[] pY = {y,y,y+r,y+r+r,y+r+r,y+r};

        /* Rotates original points 90 degrees to tile correctly */
        for (int i = 0; i < 6; i++) {
            tmpX = pX[i] - center.x;
            tmpY = pY[i] - center.y;
            pX[i] = -1 * tmpY + center.x;
            pY[i] = (int) (tmpX + center.y - this.location.y * 13 * scale / 80);
        }

        // Creates a new polygon object to be drawn
        Polygon p = new Polygon(pX, pY, 6);

        // Draw fill color
        g.setColor(color);
        g.fillPolygon(p);

        // Draw outline
        g.setColor(Color.BLACK);
        g.drawPolygon(p);

        g.setColor(c);
    }
}
