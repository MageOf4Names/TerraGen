import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Creates a window with a slider which sets the size of the grid (between 5x5 and 15x15)
 *
 * bugs:
 *  none
 */
public class GridSlider extends JComponent {
    private final ArrayList<Component> components = new ArrayList<>();
    private final JSlider gridSlider;
    private int gridScale = 10;

    public GridSlider() {
        Box.Filler filler = new Box.Filler(new Dimension(50,50), new Dimension(1280, 300), new Dimension(1280, 400));
        gridSlider = new JSlider(5,15,gridScale);
        JLabel position = new JLabel("grid scale: " + gridScale + "x" + gridScale);

        gridSlider.addChangeListener(event -> {
            gridScale = gridSlider.getValue();
            position.setText("grid scale: " + gridSlider.getValue() + "x" + gridSlider.getValue());
        });

        JButton sliderButton = new JButton("Apply Grid Scale");
        sliderButton.setBounds(200,200, 50, 50);
        sliderButton.addActionListener(e -> hostGame());
        setLayout(new FlowLayout());

        components.add(filler);
        components.add(gridSlider);
        components.add(position);
        components.add(sliderButton);
        addComponents();
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

    private void hostGame() {
        hideComponents();
        Game.map = new Map(new SquareTileGrid(gridScale, gridScale, Game.map.getScale()));
        System.out.println("Created new Map with scale: " + gridScale);
        TerraGen.window.hostGame();
    }
}
