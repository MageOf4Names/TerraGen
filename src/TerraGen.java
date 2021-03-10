import javax.swing.*;
import java.awt.*;

/*
   Application main window
 */
public class TerraGen extends JFrame {
    protected TerraGenComponent component;

    public TerraGen() {
        super("TerraGen");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);

        // Set window to 720p
        setPreferredSize(new Dimension(1280, 720));

        component = new TerraGenComponent();
        add(component);

        pack();
    }

    public static void main(String[] args) {
        TerraGen window = new TerraGen();

        window.setVisible(true);
    }
}
