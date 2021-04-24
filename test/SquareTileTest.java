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
        assertEquals(s1.getGridLocation(), p1);
        assertEquals(s2.getGridLocation(), p2);
        assertEquals(s3.getGridLocation(), p3);
    }

    @Test
    void getPixelLocation() {
        assertEquals(s1.getPixelLocation(50), p1);
        assertEquals(s2.getPixelLocation(50), p2.multiply(50));
        assertEquals(s3.getPixelLocation(50), p3.multiply(50));
    }

    @Test
    void getPixelCenterLocation() {
        assertEquals(new Point(25,25), s1.getPixelCenterLocation(50));
        assertEquals(new Point(75,75), s2.getPixelCenterLocation(50));
        assertEquals(new Point(175,375), s3.getPixelCenterLocation(50));
    }

    @Test
    void getCenter() {
        assertEquals(s1.getCenterOffset(50), new Point(25,25));
        assertEquals(s2.getCenterOffset(50), new Point(25,25));
        assertEquals(s2.getCenterOffset(50), new Point(25,25));
    }
}