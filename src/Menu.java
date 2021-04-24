import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Menu extends JPanel {
    JButton newToken;
    JButton removeToken;

    public Menu() {
        setVisible(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBounds(0,0,200,TerraGen.window.getHeight());
        setMaximumSize(new Dimension(200,TerraGen.window.getHeight()));

        buildDefaultMenu();
    }

    public void buildDefaultMenu() {

        JLabel title = new JLabel("Menu");
        this.add(title);

        // whitespace
        Box.Filler filler1 = new Box.Filler(new Dimension(50,50), new Dimension(100, 20), new Dimension(100, 50));
        this.add(filler1);

        // button to add a token to the field
        newToken = addTokenButton();
        newToken.setVisible(true);
        this.add(newToken);

        // button to remove a token from the field
        removeToken = deleteTokenButton();
        removeToken.setVisible(true);
        this.add(removeToken);

    }

    private JButton addTokenButton() {
        newToken = new JButton("Add Token");
        newToken.addActionListener(e1 -> {
            AtomicInteger x = new AtomicInteger(1);
            AtomicInteger y = new AtomicInteger(1);
            this.remove(this.newToken);
            this.remove(this.removeToken);

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
                this.remove(finalizeToken);
                addTokenPanel.remove(tokenScaleLabel);
                addTokenPanel.remove(tokenSizeLabel);
                addTokenPanel.remove(tokenScale);
                addTokenPanel.remove(filler2);
                addTokenPanel.remove(coordinatesLabel);
                addTokenPanel.remove(coordinatesPanel);

                var t = TerraGen.window.game.addToken(x.intValue() - 1, y.intValue() - 1);

                this.add(newToken);
                this.add(removeToken);
                TerraGen.window.pack();
            });

            addTokenPanel.add(tokenScaleLabel);
            addTokenPanel.add(tokenScale);
            addTokenPanel.add(tokenSizeLabel);
            addTokenPanel.add(filler2);
            addTokenPanel.add(coordinatesLabel);
            addTokenPanel.add(coordinatesPanel);

            this.add(addTokenPanel);
            this.add(finalizeToken);
            TerraGen.window.pack();
        });

        return newToken;
    }

    private JButton deleteTokenButton() {
        final Token[] selected = {null};
        AtomicInteger index = new AtomicInteger();
        AtomicReference<ArrayList<JButton>> buttons = new AtomicReference<>(new ArrayList<JButton>());

        AtomicReference<JButton> back = new AtomicReference<>(new JButton());

        JButton deleteToken = new JButton("Delete Token");
        deleteToken.addActionListener(e -> {
            this.remove(this.newToken);
            this.remove(this.removeToken);
            TerraGen.window.pack();

            int i = 1;
            for (Token token : TerraGen.window.game.getMap().tokens) {
                JButton tokenButton = new JButton("Token " + i);
                int finalI = i;
                tokenButton.addActionListener(e1 -> {
                    selected[0] = token;
                    index.set(finalI - 1);

                    //set all other tokens "selected" tag as false
                    for (Token token1 : TerraGen.window.game.getMap().tokens) {
                        token1.selected = false;
                    }

                    selected[0].selected = true;
                    TerraGen.window.repaint();

                });
                buttons.get().add(tokenButton);
                i++;
            }
            for (JButton button : buttons.get()) {
                this.add(button);
            }

            JButton remove = new JButton("Delete selected tile");
            remove.addActionListener(e2 -> {
                TerraGen.window.game.getMap().tokens.remove(index.get());

                for (JButton button : buttons.get()) {
                    this.remove(button);
                }
                this.remove(remove);
                this.remove(back.get());

                buttons.set(new ArrayList<>());

                this.add(this.newToken);
                this.add(this.removeToken);
                TerraGen.window.repaint();
            });

            back.set(new JButton("back"));
            back.get().addActionListener(e3 -> {
                for (JButton button : buttons.get()) {
                    this.remove(button);
                }
                buttons.set(new ArrayList<>());

                //set all other tokens "selected" tag as false
                for (Token token1 : TerraGen.window.game.getMap().tokens) {
                    token1.selected = false;
                }

                this.remove(remove);
                this.remove(back.get());
                this.add(this.newToken);
                this.add(this.removeToken);
                TerraGen.window.pack();
            });

            this.add(remove);
            this.add(back.get());
            TerraGen.window.pack();
        });

        return deleteToken;
    }
}
