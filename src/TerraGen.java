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
        height = game.map.getHeight();
        width = game.map.getWidth();


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
