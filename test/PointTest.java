import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    @Test
    void getX() {
        for (int x = -100; x < 100; x++) {
            for (int y = -100; y < 100; y++) {
                var p = new Point(x, y);

                assertEquals(p.getX(), x);
            }
        }
    }

    @Test
    void setX() {
        for (int x = -100; x < 100; x++) {
            for (int y = -100; y < 100; y++) {
                var p = new Point(x, y);

                p.setY(y);
                assertEquals(p.getX(), x);

                p.setX(x);
                assertEquals(p.getX(), x);
            }
        }
    }

    @Test
    void getY() {
        for (int x = -100; x < 100; x++) {
            for (int y = -100; y < 100; y++) {
                var p = new Point(x, y);

                assertEquals(p.getY(), y);
            }
        }
    }

    @Test
    void setY() {
        for (int x = -100; x < 100; x++) {
            for (int y = -100; y < 100; y++) {
                var p = new Point(x, y);

                p.setX(x);
                assertEquals(p.getX(), x);

                p.setY(y);
                assertEquals(p.getX(), x);
            }
        }
    }

    @Test
    void add() {
        for (int x1 = -10; x1 < 10; x1++) {
            for (int x2 = -10; x2 < 10; x2++) {
                for (int y1 = -10; y1 < 10; y1++) {
                    for (int y2 = -10; y2 < 10; y2++) {
                        var p1 = new Point(x1, y1);
                        var p2 = new Point(x2, y2);

                        var p3 = new Point(x1 + x2, y1 + y2);
                        var p4 = p1.add(p2);

                        assertTrue(p3.equals(p4));
                    }
                }
            }
        }
    }

    @Test
    void testEquals() {
        var p1 = new Point(0, 0);
        var p2 = new Point(0, 0);
        var p3 = new Point(1, 10);
        var p4 = new Point(-10, 1);

        assertTrue(p1.equals(p2));
        assertTrue(p1.equals(p1));

        assertFalse(p1.equals(p3));
        assertFalse(p1.equals(p4));
        assertFalse(p3.equals(p4));
    }

    @Test
    void distance() {
        var p0 = new Point(0,0);
        var p1 = new Point(1,1);
        var p2 = new Point(4,4);

        assertEquals(5, p0.distance(p2));
        assertEquals(1, p0.distance(p1));
        assertEquals(4, p1.distance(p2));
    }
}