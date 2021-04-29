import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainScreen extends JComponent {
    private ArrayList<Component> components = new ArrayList<>();

    public MainScreen() {

        //Box.Filler filler = new Box.Filler(new Dimension(50,50), new Dimension(1280, 300), new Dimension(1280, 400));
        Image myImage = null;
        try {
            myImage = ImageIO.read(getClass().getResource("backgrounds/TerraGen.png"));
            ImageIcon myImageIcon = new ImageIcon(myImage);

            JLabel label = new JLabel(myImageIcon);
            components.add(label);
        } catch (IOException e) {
            e.printStackTrace();
        }



        JButton hostButton = new JButton("Host");
        hostButton.setBounds(200,200, 50, 50);
        hostButton.addActionListener(e -> hostGame());

        JButton joinButton = new JButton("Join");
        joinButton.setBounds(200,200, 50, 50);
        joinButton.addActionListener(e -> joinGame());
        setLayout(new FlowLayout());

        //components.add(filler);
        components.add(hostButton);
        components.add(joinButton);
        addComponents();


    }

    private void joinGame() {
        hideComponents();
        TerraGen.window.joinGame();
    }

    private void hostGame() {
        hideComponents();
        // call for the GridSlider window
        TerraGen.window.add(new GridSlider());
        TerraGen.window.pack();
    }

    public void switchGUIState() {
        hideComponents();
        //TerraGen.window.guiState = GUIState.GAME;
        TerraGen.window.hostGame();
    }

    private void addComponents() {
        for (Component c : components) {
            add(c);
        }
    }

    private void hideComponents() {
        for (Component c : components) {
            c.setVisible(false);
        }
    }
}
