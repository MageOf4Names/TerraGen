import javax.swing.*;
import java.awt.*;

public class GameRenderer extends JComponent {
    protected Game game;

    public GameRenderer(Game game) {
        this.game = game;

        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.draw((Graphics2D) g);
    }
}
