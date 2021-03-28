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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        // Set window to 720p
        setPreferredSize(new Dimension(1280, 720));
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


        game = new Game();

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

        game = new Game();

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


}
