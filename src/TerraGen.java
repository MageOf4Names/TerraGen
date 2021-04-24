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
        setLayout(new BorderLayout());

        game = new Game();
        scale = game.getScale();
        height = 10;
        width = 10;

        // Set window to 720p
        setPreferredSize(new Dimension(scale * width + 200, scale * height + 38));
        MainScreen mainScreen = new MainScreen();
        add(mainScreen);
        pack();
    }

    public static void main(String[] args) {
        window = new TerraGen();

        window.setVisible(true);
    }

    /*
    public void pushGameChange() {
        try {
            client.pushGameChange(TerraGen.window.game.getMap().getTokens().indexOf(this), NetworkType.TOKEN, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */

    public void hostGame() {
        addUsers();
        gameRenderer = new GameRenderer(game);
        add(gameRenderer, BorderLayout.CENTER);
        pack();

        try {
            server = new Server(game);
            client = new Client("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Menu menu = new Menu();
        add(menu, BorderLayout.EAST);
    }

    public void joinGame() {
        addUsers(); // joining the game will eventually not need to initialize tokens, so this bit is temporary
        gameRenderer = new GameRenderer(game);
        add(gameRenderer);
        pack();

        try {
            client = new Client("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client getClient() {
        return client;
    }

    public Game getGame() {
        return this.game;
    }

    private void addUsers() {
        Map map = window.game.getMap();
        var t1 = window.game.addToken(2, 2);
        t1.setColor(Color.BLUE);

        var t2 = window.game.addToken(4, 4);
        t2.setColor(Color.ORANGE);
        t2.setScale(2);

        var t3 = window.game.addToken(0, 0);
        t3.setLocationNoNetworking(new Point(345, 123));
        t3.setColor(Color.BLACK);
    }
}
