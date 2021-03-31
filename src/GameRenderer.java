import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameRenderer extends JComponent {
    protected Game game;

    protected ArrayList<PhantomToken> phantomTokens = new ArrayList<>();

    public GameRenderer(Game game) {
        this.game = game;

        setLayout(new BorderLayout());

        var token = TerraGen.window.game.getClosestToken(new Point(0, 0));
        var p = new PhantomToken(TerraGen.window.game.getMap().grid, token);
        add(p);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.draw((Graphics2D) g);
    }
}
