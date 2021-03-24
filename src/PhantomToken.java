import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Handles dragging toke across the map
public class PhantomToken extends JComponent {
    private ITile tile;
    private ITileGrid grid;

    private Point pos;

    public PhantomToken(){

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

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
