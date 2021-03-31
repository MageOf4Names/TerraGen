import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.*;

/**
 * Creates a window with a slider which sets the size of the grid (between 5x5 and 15x15)
 *
 * bugs:
 *  none
 */
public class GridSlider extends JComponent {
    private final ArrayList<Component> components = new ArrayList<>();
    private static int gridScale = 10;
    private int tileShape = 1; // tileShape = 1 if shape is set to square, tileShape = 2 if shape is set to hexagonal

    public GridSlider() {
        setLayout(new FlowLayout());

        JLabel title = new JLabel("Map creator");
        title.setFont(new Font("", Font.BOLD, 25));

        // whitespace
        Box.Filler filler1 = new Box.Filler(new Dimension(50,50), new Dimension(1280, 100), new Dimension(1280, 400));

        // Slider to adjust size of grid scale
        JSlider gridSlider = new JSlider(5,15,gridScale);
        JLabel position = new JLabel("grid scale: " + gridScale + "x" + gridScale);
        gridSlider.addChangeListener(event -> {
            gridScale = gridSlider.getValue();
            position.setText("grid scale: " + gridSlider.getValue() + "x" + gridSlider.getValue());
        });

        // button to finalize grid scale and draw board
        JButton sliderButton = new JButton("Apply to Grid");
        sliderButton.setBounds(200,200, 50, 50);
        sliderButton.addActionListener(e -> hostGame());

        // button to decrease slider value by 1
        JButton decreaseScale = new JButton("-");
        decreaseScale.setBounds(200,200, 50, 50);
        decreaseScale.addActionListener(e -> {
            if (gridScale > 5) {
                gridScale = gridScale - 1;
                gridSlider.setValue(gridScale);
                position.setText("grid scale: " + gridSlider.getValue() + "x" + gridSlider.getValue());
            }
        });

        // button to increase slider value by 1
        JButton increaseScale = new JButton("+");
        increaseScale.setBounds(200,200, 50, 50);
        increaseScale.addActionListener(e -> {
            if (gridScale < 15) {
                gridScale = gridScale + 1;
                gridSlider.setValue(gridScale);
                position.setText("grid scale: " + gridSlider.getValue() + "x" + gridSlider.getValue());
            }
        });

        // whitespace
        Box.Filler filler2 = new Box.Filler(new Dimension(50,50), new Dimension(1280, 100), new Dimension(1280, 400));

        // slider to decide between square or hexagonal grid
        JSlider tileSlider = new JSlider(1,2,1);
        JLabel tileShapeLabel1 = new JLabel("Tile shape is:");
        JLabel tileShapeLabel2 = new JLabel("Square");
        if (tileSlider.getValue() == 1) {
            tileShapeLabel2.setText("Square");
        } else if (tileSlider.getValue() == 2) {
            tileShapeLabel2.setText("Hexagonal");
        }
        tileSlider.addChangeListener(e -> {
            tileShape = tileSlider.getValue();
            if (tileShape == 1) {
                tileShapeLabel2.setText("Square");
            } else if(tileShape == 2) {
                tileShapeLabel2.setText("Hexagonal");
            }
        });

        // button to set the slider to square grid
        JButton setSquare = new JButton("Square");
        setSquare.setBounds(200,200, 50, 50);
        setSquare.addActionListener(e -> {
            tileShape = 1;
            tileSlider.setValue(1);
            tileShapeLabel2.setText("Square");
        });

        // button to set the slider to hexagonal grid
        JButton setHex = new JButton("Hexagonal");
        setHex.setBounds(200,200,50,50);
        setHex.addActionListener(e -> {
            tileShape = 2;
            tileSlider.setValue(2);
            tileShapeLabel2.setText("Hexagonal");
        });

        // whitespace
        Box.Filler filler3 = new Box.Filler(new Dimension(50,50), new Dimension(1280, 100), new Dimension(1280, 400));

        // panel for the size of the grid
        JPanel sizeSlider = new JPanel();
        sizeSlider.setLayout(new FlowLayout());
        sizeSlider.add(decreaseScale);
        sizeSlider.add(gridSlider);
        sizeSlider.add(increaseScale);

        // panel for the shape of the tiles
        JPanel tileShapePanel = new JPanel();
        tileShapePanel.add(setSquare);
        tileShapePanel.add(tileSlider);
        tileShapePanel.add(setHex);
        tileShapePanel.setLayout(new FlowLayout());

        // panel to hold all the sliders and buttons
        JPanel sliderFrame = new JPanel();
        sliderFrame.setLayout(new FlowLayout());
        sliderFrame.add(title);
        sliderFrame.add(filler1);
        sliderFrame.add(position);
        sliderFrame.add(sizeSlider);
        sliderFrame.add(filler2);
        sliderFrame.add(tileShapeLabel1);
        sliderFrame.add(tileShapeLabel2);
        sliderFrame.add(tileShapePanel);
        sliderFrame.add(filler3);
        sliderFrame.add(sliderButton);
        sliderFrame.setLayout(new BoxLayout(sliderFrame, BoxLayout.Y_AXIS));

        components.add(sliderFrame);
        addComponents();
    }

    public static int getGridScale() {
        return gridScale;
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
        Map map = TerraGen.window.game.getMap();
        hideComponents();
        if (tileShape == 1) {
            TerraGen.window.game.setMap(new Map(new SquareTileGrid(gridScale, gridScale, map.getScale())));
            TerraGen.window.setPreferredSize(new Dimension(map.getWidth() * (int) map.getScale() + 15, map.getHeight() * (int) map.getScale() + 40));
        } else if (tileShape == 2) {
            TerraGen.window.game.setMap(new Map(new HexTileGrid(gridScale, gridScale, map.getScale())));
            TerraGen.window.setPreferredSize(new Dimension(map.getWidth() * (int) map.getScale() + 42,  (int) (map.getHeight() * Math.sqrt(3) * 0.5 *  map.getScale()) + 84));
        }
        TerraGen.window.hostGame();
    }
}
