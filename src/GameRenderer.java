import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameRenderer extends JComponent {
    protected Game game;

    protected ArrayList<PhantomToken> phantomTokens = new ArrayList<>();

    public GameRenderer(Game game) {
        this.game = game;

        setLayout(new BorderLayout());

        var p = new PhantomToken(TerraGen.window.game.getMap().grid, TerraGen.window.game.getMap().tokens.get(0));
        add(p);
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
