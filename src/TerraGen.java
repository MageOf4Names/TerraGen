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
    protected String bg;

    private Server server;
    private Client client;

    public TerraGen() {
        super("TerraGen");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        setLayout(new BorderLayout());

        game = new Game();
        int scale = game.getScale();
        int height = 10;
        int width = 10;

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

    /**
     * Returns the window to the main screen
     */
    public void mainScreen(){
        setVisible(false);
        window = new TerraGen();
        window.setVisible(true);
        client = null;
        server = null;
        gameRenderer = null;
    }

    /**
     * Start new game as a host
     */
    public void hostGame() {
        addUsers();
        gameRenderer = new GameRenderer(game, bg);
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

    /**
     * Connect to an existing game
     */
    public void joinGame() {
        //addUsers(); // joining the game will eventually not need to initialize tokens, so this bit is temporary
        //gameRenderer = new GameRenderer(game);
        //add(gameRenderer);
        //pack();

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

    public void setBackground(String bgNew) {
        bg = bgNew;
    }

    private void addUsers() {
        Map map = window.game.getMap();
    }

    public void loadClientGame(NetworkContainer networkContainer) {
        GameContainer container = (GameContainer) networkContainer.getData();
        gameRenderer = container.getGameRenderer();
        game = container.getGame();
        add(gameRenderer);

        Menu menu = new Menu();
        add(menu, BorderLayout.EAST);
        pack();
    }
}
