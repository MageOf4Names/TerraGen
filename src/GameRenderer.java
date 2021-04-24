import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameRenderer extends JComponent {
    protected Game game;

    public GameRenderer(Game game) {
        this.game = game;

        setLayout(new BorderLayout());

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                game.OnMouseClick(mouseEvent);
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

    @Override
    protected void paintComponent(Graphics g) {
        /*
        Game newGame = TerraGen.window.getClient().getGameQueue().peek();
        if (newGame != null)
            //System.out.println("newgame not null");
            game = newGame;
        */
        super.paintComponent(g);

        game.draw((Graphics2D) g);
    }
}
