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
    private static int gridScale = 20; // default size of the grid is 10x10 tiles
    private static int tileScale = 30; // default size of a single tile is 50 pixels
    private int tileShape = 1; // tileShape = 1 if shape is set to square, tileShape = 2 if shape is set to hexagonal

    public GridSlider() {
        setLayout(new FlowLayout());

        JLabel title = new JLabel("Map creator");
        title.setFont(new Font("", Font.BOLD, 25));
        add(title);

        // whitespace
        Box.Filler filler1 = new Box.Filler(
                new Dimension(50,50),
                new Dimension(1280, 50),
                new Dimension(1280, 400));


        // Initialize sliders
        JSlider scaleSlider = new JSlider(20,40, tileScale);
        JSlider gridSlider = new JSlider(10,30,gridScale);

        // Slider to adjust size of grid scale
        JLabel gridPosition = new JLabel("Grid Scale: " + gridScale + "x" + gridScale);
        gridSlider.addChangeListener(event -> {
            gridScale = gridSlider.getValue();
            gridPosition.setText("Grid Scale: " + gridSlider.getValue() + "x" + gridSlider.getValue());
            scaleSlider.setValue(40 - (gridSlider.getValue() - 10));
        });

        // Slider to adjust size of tile scale
        JLabel tilePosition = new JLabel("Tile Scale: " + tileScale);
        scaleSlider.addChangeListener(event -> {
            tileScale = scaleSlider.getValue();
            tilePosition.setText("Tile Scale: " + scaleSlider.getValue());
            gridSlider.setValue(30 - (scaleSlider.getValue() - 20));
        });

        // button to finalize grid scale and draw board
        JButton sliderButton = new JButton("Generate Map");
        sliderButton.setBounds(200,200, 50, 50);
        sliderButton.addActionListener(e -> startGame());

        // button to decrease slider value by 1
        JButton decreaseGridScale = new JButton("-");
        decreaseGridScale.setBounds(200,200, 50, 50);
        decreaseGridScale.addActionListener(e -> {
            if (gridScale > 10) {
                gridScale = gridScale - 1;
                gridSlider.setValue(gridScale);
                gridPosition.setText("Grid Scale: " + gridSlider.getValue() + "x" + gridSlider.getValue());
            }
        });

        // button to increase slider value by 1
        JButton increaseGridScale = new JButton("+");
        increaseGridScale.setBounds(200,200, 50, 50);
        increaseGridScale.addActionListener(e -> {
            if (gridScale < 35) {
                gridScale = gridScale + 1;
                gridSlider.setValue(gridScale);
                gridPosition.setText("Grid Scale: " + gridSlider.getValue() + "x" + gridSlider.getValue());
            }
        });

        // button to decrease tile scale value by 1
        JButton decreaseTileScale = new JButton("-");
        decreaseTileScale.setBounds(200,200, 50, 50);
        decreaseTileScale.addActionListener(e -> {
            if (tileScale > 20) {
                tileScale = tileScale - 1;
                scaleSlider.setValue(tileScale);
                tilePosition.setText("Tile Scale: " + scaleSlider.getValue());
            }
        });

        // button to increase tile scale value by 1
        JButton increaseTileScale = new JButton("+");
        increaseTileScale.setBounds(200,200, 50, 50);
        increaseTileScale.addActionListener(e -> {
            if (tileScale < 40) {
                tileScale = tileScale + 1;
                scaleSlider.setValue(tileScale);
                tilePosition.setText("Tile Scale: " + scaleSlider.getValue());
            }
        });

        // whitespace
        Box.Filler filler2 = new Box.Filler(
                new Dimension(50,50),
                new Dimension(1280, 30),
                new Dimension(1280, 400));

        // whitespace
        Box.Filler filler3 = new Box.Filler(
                new Dimension(50,50),
                new Dimension(1280, 50),
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
        Box.Filler filler4 = new Box.Filler(
                new Dimension(50,50),
                new Dimension(1280, 100),
                new Dimension(1280, 400));

        // panel for the size of the grid
        JPanel sizeSlider = new JPanel();
        sizeSlider.setLayout(new FlowLayout());
        sizeSlider.add(decreaseGridScale);
        sizeSlider.add(gridSlider);
        sizeSlider.add(increaseGridScale);

        // panel for the scale of each tile
        JPanel tileSlider = new JPanel();
        tileSlider.setLayout(new FlowLayout());
        tileSlider.add(decreaseTileScale);
        tileSlider.add(scaleSlider);
        tileSlider.add(increaseTileScale);

        // panel for the shape of the tiles
        JPanel tileShapePanel = new JPanel();
        tileShapePanel.add(setSquare);
        tileShapePanel.add(setHex);
        tileShapePanel.setLayout(new FlowLayout());

        // panel to hold all the filler, sliders and buttons
        JPanel sliderFrame = new JPanel();
        sliderFrame.setLayout(new FlowLayout());
        sliderFrame.add(filler1);
        sliderFrame.add(gridPosition);
        sliderFrame.add(sizeSlider);
        sliderFrame.add(filler2);
        sliderFrame.add(tilePosition);
        sliderFrame.add(tileSlider);
        sliderFrame.add(filler3);
        sliderFrame.add(tileShapeLabel1);
        sliderFrame.add(tileShapeLabel2);
        sliderFrame.add(tileShapePanel);
        sliderFrame.add(filler4);
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
     * retrieve the scale of an individual tile
     *
     * @return scale of the grid
     */
    public static int getTileScale() {
        return tileScale;
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
        int height, width;
        Map map = TerraGen.window.getGame().getMap();
        /* for a square grid */
        if (tileShape == 1) {
            height = gridScale * tileScale + 39;
            width = gridScale * tileScale + 173;
            TerraGen.window.getGame().setMap(new Map(new SquareTileGrid(gridScale, gridScale, tileScale)));
            TerraGen.window.setPreferredSize(new Dimension(width, height));
        }
        /* for a hexagonal grid */
        else if (tileShape == 2) {
            height = (int) (gridScale * Math.sqrt(3) * 0.5 *  tileScale) + 39 + tileScale / 2;
            width = gridScale * (int) tileScale + tileScale / 2 + 173;
            TerraGen.window.getGame().setMap(new Map(new HexTileGrid(gridScale, gridScale, tileScale)));
            TerraGen.window.setPreferredSize(new Dimension(width,  height));
        }
        TerraGen.window.hostGame();
    }
}
