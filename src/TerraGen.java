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
        int scale, height, width;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        game = new Game();
        scale = game.getScale();
        height = game.map.getHeight();
        width = game.map.getWidth();

        // Set window to 720p
        setPreferredSize(new Dimension(scale * width + 200, scale * height + 38));

        gameRenderer = new GameRenderer(game);
        add(gameRenderer);

        pack();
    }

    public static void main(String[] args) {
        TerraGen window = new TerraGen();

        window.setVisible(true);
    }
}
