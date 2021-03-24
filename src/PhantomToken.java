import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Handles dragging toke across the map
public class PhantomToken extends JComponent {
    private IToken token;
    private ITileGrid grid;

    private Point pos;

    public PhantomToken(ITileGrid grid, IToken token) {
        this.grid = grid;
        this.token = token;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                var tile = grid.getTile(0, 0);
                var pos = tile.getPixelLocation(grid.getTileSize());

                var x = mouseEvent.getX() - pos.x;
                var y = mouseEvent.getY() - pos.y;

                tile = grid.getClosestTile(new Point(x, y), grid.getTileSize());
                token.setLocation(grid, tile);
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }
}
