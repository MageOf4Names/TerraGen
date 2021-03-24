import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTileTest {
    Point p1 = new Point(0,0);
    Point p2 = new Point(1,1);
    Point p3 = new Point(3,7);

    SquareTile s1 = new SquareTile(p1);
    SquareTile s2 = new SquareTile(p2);
    SquareTile s3 = new SquareTile(p3);

    @Test
    void getLocation() {
        assertEquals(s1.getLocation(), p1);
        assertEquals(s2.getLocation(), p2);
        assertEquals(s3.getLocation(), p3);
    }

    @Test
    void getPixelLocation() {
        assertEquals(s1.getPixelLocation(50), p1);
        assertEquals(s2.getPixelLocation(50), p2.multiply(50));
        assertEquals(s3.getPixelLocation(50), p3.multiply(50));
    }

    @Test
    void getPixelCenterLocation() {
    }

    @Test
    void getCenter() {
    }
}