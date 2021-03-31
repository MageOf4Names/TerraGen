import javax.swing.*;
import java.awt.*;
import java.time.Year;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu extends JPanel {

    public Menu() {
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(0,0,200,TerraGen.window.getHeight());
        setMaximumSize(new Dimension(200,TerraGen.window.getHeight()));

        JLabel title = new JLabel("Menu");
        add(title);

        // whitespace
        Box.Filler filler1 = new Box.Filler(new Dimension(50,50), new Dimension(100, 20), new Dimension(100, 50));
        add(filler1);

        // button to add a token to the field
        JButton newToken = new JButton("Add Token");
        newToken.addActionListener(e1 -> {
            AtomicInteger x = new AtomicInteger(1);
            AtomicInteger y = new AtomicInteger(1);
            remove(newToken);

            AtomicInteger tokenSize = new AtomicInteger(1);

            // panel for the adding token components
            JPanel addTokenPanel = new JPanel();
            addTokenPanel.setLayout(new BoxLayout(addTokenPanel, BoxLayout.Y_AXIS));

            JLabel tokenScaleLabel = new JLabel("Choose Token Scale:     ");
            JLabel tokenSizeLabel = new JLabel("" + tokenSize);

            // slider for deciding token scale
            JSlider tokenScale = new JSlider(1,2,1);
            tokenScale.addChangeListener(e2 -> {
                tokenSize.set(tokenScale.getValue());
                tokenSizeLabel.setText("" + tokenSize);
            });
            tokenScale.setPreferredSize(new Dimension(5,5));

            // whitespace
            Box.Filler filler2 = new Box.Filler(new Dimension(50,20), new Dimension(50, 20), new Dimension(50, 20));

            // Fields for determining coordinates
            JLabel coordinatesLabel = new JLabel("coordinates: (" + x + ", " + y + ")");
            JLabel xLabel = new JLabel("x");
            JLabel yLabel = new JLabel("y");
            JButton decreaseX = new JButton("-"); // button to decrease xCoordinate
            decreaseX.addActionListener(e4 -> {
                if (x.get() > 1) {
                    x.set(x.get() - 1);
                    coordinatesLabel.setText("coordinates: (" + x + ", " + y + ")");
                }
            });
            JButton increaseX = new JButton("+"); // button to increase xCoordinate
            increaseX.addActionListener(e4 -> {
                if (x.get() < GridSlider.getGridScale()) {
                    x.set(x.get() + 1);
                    coordinatesLabel.setText("coordinates: (" + x + ", " + y + ")");
                }
            });

            JButton decreaseY = new JButton("-"); // button to decrease yCoordinate
            decreaseY.addActionListener(e4 -> {
                if (y.get() > 1) {
                    y.set(y.get() - 1);
                    coordinatesLabel.setText("coordinates: (" + x + ", " + y + ")");
                }
            });
            JButton increaseY = new JButton("+"); // button to increase yCoordinate
            increaseY.addActionListener(e4 -> {
                if (y.get() < GridSlider.getGridScale()) {
                    y.set(y.get() + 1);
                    coordinatesLabel.setText("coordinates: (" + x + ", " + y + ")");
                }
            });

            JPanel coordinatesPanel = new JPanel();
            coordinatesPanel.setLayout(new BoxLayout(coordinatesPanel, BoxLayout.Y_AXIS));
            coordinatesPanel.setMaximumSize(new Dimension(200,100));

            JPanel xCoordinatePanel = new JPanel();
            xCoordinatePanel.add(decreaseX);
            xCoordinatePanel.add(xLabel);
            xCoordinatePanel.add(increaseX);
            coordinatesPanel.add(xCoordinatePanel);

            JPanel yCoordinatePanel = new JPanel();
            yCoordinatePanel.add(decreaseY);
            yCoordinatePanel.add(yLabel);
            yCoordinatePanel.add(increaseY);
            coordinatesPanel.add(yCoordinatePanel);

            JButton finalizeToken = new JButton("Finalize token");
            finalizeToken.addActionListener(e3 -> {
                remove(finalizeToken);
                addTokenPanel.remove(tokenScaleLabel);
                addTokenPanel.remove(tokenSizeLabel);
                addTokenPanel.remove(tokenScale);
                addTokenPanel.remove(filler2);
                addTokenPanel.remove(coordinatesLabel);
                addTokenPanel.remove(coordinatesPanel);

                var t = TerraGen.window.game.addToken(x.intValue(), y.intValue());

                add(newToken);
                TerraGen.window.pack();
            });

            addTokenPanel.add(tokenScaleLabel);
            addTokenPanel.add(tokenScale);
            addTokenPanel.add(tokenSizeLabel);
            addTokenPanel.add(filler2);
            addTokenPanel.add(coordinatesLabel);
            addTokenPanel.add(coordinatesPanel);

            add(addTokenPanel);
            add(finalizeToken);
            TerraGen.window.pack();
        });
        newToken.setVisible(true);
        add(newToken);
    }
}
