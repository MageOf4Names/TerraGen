import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
        BufferedImage bg = null;
        /* Attempts to set the background image */
        try {
            bg = ImageIO.read(new File("src/backgrounds/mainBackground.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(bg, 0, 0, null);

        game.draw((Graphics2D) g);
    }
}
