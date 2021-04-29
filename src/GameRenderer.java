import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GameRenderer extends JComponent implements Serializable {
    protected Game game;
    private String bg;

    public GameRenderer(Game game) {
        this.game = game;
        this.bg = "caveBackground.png";

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

    public GameRenderer(Game game, String bg) {
        this.game = game;
        this.bg = bg;

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

    public void setBGImage(String bgNew) {
        System.out.println(bgNew + "in GameRender");
        this.bg = bgNew;
    }

    public void registerMouse() {
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
        super.paintComponent(g);
        BufferedImage background = null;
        /* Attempts to set the background image */
        try {
            if (bg != null) {
                //TerraGen/src/backgrounds/stoneBackground.png (alternate path)
                background = ImageIO.read(new File("src/backgrounds/" + bg));
                g.drawImage(background, 0, 0, null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        game.draw((Graphics2D) g);
    }
}
