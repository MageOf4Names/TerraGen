import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu extends JPanel {

    public Menu() {
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(0,0,200,TerraGen.window.getHeight());
        setMaximumSize(new Dimension(200,TerraGen.window.getHeight()));

        JLabel title = new JLabel("Menu");
        add(title);

        Box.Filler filler1 = new Box.Filler(new Dimension(50,50), new Dimension(100, 20), new Dimension(100, 50));
        add(filler1);

        JButton newToken = new JButton("Add Token");
        newToken.addActionListener(e -> {
            remove(newToken);

            AtomicInteger tokenSize = new AtomicInteger(1);
            JPanel addTokenPanel = new JPanel();
            addTokenPanel.setLayout(new BoxLayout(addTokenPanel, BoxLayout.Y_AXIS));

            JLabel tokenScaleLabel = new JLabel("Choose Token Scale:");
            JLabel tokenSizeLabel = new JLabel("" + tokenSize);

            JSlider tokenScale = new JSlider(1,2,1);
            tokenScale.addChangeListener(ee -> {
                tokenSize.set(tokenScale.getValue());
                tokenSizeLabel.setText("" + tokenSize);
            });
            tokenScale.setPreferredSize(new Dimension(5,5));



            addTokenPanel.add(tokenScaleLabel);
            addTokenPanel.add(tokenScale);
            addTokenPanel.add(tokenSizeLabel);
            add(addTokenPanel);
            TerraGen.window.pack();
        });
        newToken.setVisible(true);
        add(newToken);
    }
}
