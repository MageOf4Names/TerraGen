import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
   Application main window
 */
public class TerraGen extends JFrame {
    protected GameRenderer gameRenderer;
    protected static TerraGen window;

    protected Game game;

    private Server server;
    private Client client;

    public TerraGen() {
        super("TerraGen");
        int scale, height, width;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        game = new Game();
        scale = game.getScale();
        height = Game.map.getHeight();
        width = Game.map.getWidth();

        // Set window to 720p
        setPreferredSize(new Dimension(scale * width + 200, scale * height + 38));
        MainScreen mainScreen = new MainScreen();
        add(mainScreen);
        pack();
    }

    public static void main(String[] args) {
        window = new TerraGen();

        //MainScreen mainScreen = new MainScreen();
        window.setVisible(true);
    }

    public void pushGameChange() {
        try {
            client.pushGameChange();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hostGame() {
        addUsers();
        gameRenderer = new GameRenderer(game);
        add(gameRenderer);
        pack();

        try {
            server = new Server(game);
            client = new Client("localhost", game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinGame() {
        addUsers(); // joining the game will eventually not need to initialize tokens, so this bit is temporary
        gameRenderer = new GameRenderer(game);
        add(gameRenderer);
        pack();

        try {
            client = new Client("localhost", game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }

    private void addUsers() {
        var t1 = new Token(1, Color.BLUE);
        Game.map.tokens.add(t1);
        t1.setLocation(Game.map.grid,2,2);

        var t2 = new Token(2, Color.orange);
        Game.map.tokens.add(t2);
        t2.setLocation(Game.map.grid, 4, 4);

        var t3 = new Token(1, Color.BLACK);
        Game.map.tokens.add(t3);
        t3.setLocation(new Point(345, 123));

        Game.map.grid.getTile(4, 2).setColor(Color.CYAN);
    }
}
