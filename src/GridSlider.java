import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.lang.*;

/**
 * Creates a window with a slider which sets the size of the grid (between 5x5 and 15x15)
 * and sets the shape of the grid (Hexagonal or Square)
 */
public class GridSlider extends JComponent {
    private final ArrayList<Component> components = new ArrayList<>();
    private static int gridScale = 10; // default size of the grid is 10x10 tiles
    private int tileShape = 1; // tileShape = 1 if shape is set to square, tileShape = 2 if shape is set to hexagonal

    public GridSlider() {
        setLayout(new FlowLayout());

        JLabel title = new JLabel("Map creator");
        title.setFont(new Font("", Font.BOLD, 25));
        add(title);

        // whitespace
        Box.Filler filler1 = new Box.Filler(
                new Dimension(50,50),
                new Dimension(1280, 100),
                new Dimension(1280, 400));

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
        sliderButton.addActionListener(e -> startGame());

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
        Box.Filler filler2 = new Box.Filler(
                new Dimension(50,50),
                new Dimension(1280, 100),
                new Dimension(1280, 400));

        // slider to decide between square or hexagonal grid
        JLabel tileShapeLabel1 = new JLabel("Tile shape is:");
        JLabel tileShapeLabel2 = new JLabel("Square");

        // button to set the slider to square grid
        JButton setSquare = new JButton("Square");
        setSquare.setBounds(200,200, 50, 50);
        setSquare.addActionListener(e -> {
            tileShape = 1;
            tileShapeLabel2.setText("Square");
        });

        // button to set the slider to hexagonal grid
        JButton setHex = new JButton("Hexagonal");
        setHex.setBounds(200,200,50,50);
        setHex.addActionListener(e -> {
            tileShape = 2;
            tileShapeLabel2.setText("Hexagonal");
        });

        // whitespace
        Box.Filler filler3 = new Box.Filler(
                new Dimension(50,50),
                new Dimension(1280, 100),
                new Dimension(1280, 400));

        // panel for the size of the grid
        JPanel sizeSlider = new JPanel();
        sizeSlider.setLayout(new FlowLayout());
        sizeSlider.add(decreaseScale);
        sizeSlider.add(gridSlider);
        sizeSlider.add(increaseScale);

        // panel for the shape of the tiles
        JPanel tileShapePanel = new JPanel();
        tileShapePanel.add(setSquare);
        tileShapePanel.add(setHex);
        tileShapePanel.setLayout(new FlowLayout());

        // panel to hold all the filler, sliders and buttons
        JPanel sliderFrame = new JPanel();
        sliderFrame.setLayout(new FlowLayout());
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
        components.add(title);
        add(sliderFrame);
    }

    /**
     * retrieve the scale of the grid (is it 5x5, 10x10, 13x13, ... ?)
     *
     * @return scale of the grid
     */
    public static int getGridScale() {
        return gridScale;
    }

    /**
     * hides components so that the next screen will be the board
     */
    private void hideComponents() {
        for (Component c : components) {
            c.setVisible(false);
        }
    }

    /**
     * start the game according to the settings, and resize the window to fit it all
     */
    private void startGame() {
        hideComponents();
        if (tileShape == 1) {
            TerraGen.window.getGame().setMap(new Map(new SquareTileGrid(gridScale, gridScale, TerraGen.window.getGame().getMap().getScale())));
            TerraGen.window.setPreferredSize(new Dimension(TerraGen.window.getGame().getMap().getWidth() * (int) TerraGen.window.getGame().getMap().getScale() + 15 + 200, TerraGen.window.getGame().getMap().getHeight() * (int) TerraGen.window.getGame().getMap().getScale() + 40));
        } else if (tileShape == 2) {
            TerraGen.window.getGame().setMap(new Map(new HexTileGrid(gridScale, gridScale, TerraGen.window.getGame().getMap().getScale())));
            TerraGen.window.setPreferredSize(new Dimension(TerraGen.window.getGame().getMap().getWidth() * (int) TerraGen.window.getGame().getMap().getScale() + 42 + 200,  (int) (TerraGen.window.getGame().getMap().getHeight() * Math.sqrt(3) * 0.5 *  TerraGen.window.getGame().getMap().getScale()) + 84));
        }
        TerraGen.window.hostGame();
    }
}
