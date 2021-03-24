import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTileGridTest {
    SquareTileGrid g1 = new SquareTileGrid(10, 10, 50);

    @Test
    void getTileSize() {
        assertEquals(g1.getTileSize(), 50);
    }

    @Test
    void setTileSize() {
        g1.setTileSize(999);
        assertEquals(g1.getTileSize(), 999);
        g1.setTileSize(50);
    }

    @Test
    void getTile() {
        var t = g1.getTile(1,1);
        assertEquals(t.getLocation(), new Point(1,1));
    }

    @Test
    void getWidth() {
        assertEquals(g1.getWidth(), 10);
    }

    @Test
    void getHeight() {
        assertEquals(g1.getHeight(), 10);
    }

    @Test
    void getClosestTile() {
        var t =g1.getClosestTile(new Point(155,155), g1.getTileSize());
        assertEquals(t, g1.getTile(3,3));
    }
}