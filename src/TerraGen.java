import javax.swing.*;
import java.awt.*;

/*
   Application main window
 */
public class TerraGen extends JFrame {
    protected GameRenderer gameRenderer;

    protected Game game;

    public TerraGen() {
        super("TerraGen");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        // Set window to 720p
        setPreferredSize(new Dimension(1280, 720));

        game = new Game();

        gameRenderer = new GameRenderer(game);
        add(gameRenderer);

        pack();
    }

    public static void main(String[] args) {
        TerraGen window = new TerraGen();

        window.setVisible(true);
    }
}
