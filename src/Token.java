import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;

public class Token implements IToken, Serializable {
    protected Point pos = new Point(0, 0);

    // Token size multiplier
    protected float scale = 1;

    Color color;

    public boolean selected = false;

    public Token() {
    }

    public Token(float scale, Color color) {
        this.scale = scale;
        this.color = color;
    }

    /**
     * Set the grid location of this token
     * @param grid
     * @param x
     * @param y
     */
    @Override
    public void setLocation(ITileGrid grid, int x, int y) {
        var tile = grid.getTile(x, y);
        pos = tile.getPixelCenterLocation(grid.getTileSize());
    }

    /**
     * Sets the location of this token
     * @param location
     */
    @Override
    public void setLocation(Point location) {
        pos = location;
        try {
            if (TerraGen.window != null && TerraGen.window.getClient() != null)
                TerraGen.window.getClient().pushGameChange(TerraGen.window.game.getMap().getTokens().indexOf(this), NetworkType.TOKEN, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLocationNoNetworking(Point location) {
        pos.setX(location.getX());
        pos.setY(location.getY());
    }

    /**
     * Get the location of this token
     * @return
     */
    public Point getLocation() {
        return pos;
    }

    public void update(){
        setToken();
    }

    public void setToken(){
        Token token = (Token) TerraGen.window.getClient().getNetworkContainer().getData();
        setColor(token.getColor());
        setLocationNoNetworking(token.getLocation());
        setScale(token.getScale());
        TerraGen.window.repaint();
    }

    /**
     * Sets the color of this token
     * @param color
     */
    @Override
    public void setColor(Color color) {
        this.color = color;
    }


    /**
     * setColor: Takes in a string input and runs through some try/catch statements to convert to Color
     *
     * @param color
     */
    public void setColor(String color) {
        if (color == null) {
            this.color = Color.black;
        } try {
            this.color = Color.decode(color);
        } catch (NumberFormatException nfe) {
            try {
                final Field f = Color.class.getField(color);
                this.color = (Color) f.get(null);
            } catch (Exception e) {
                this.color = Color.black;
            }
        }
    }

    /**
     * Gets the color of this token
     * @return
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * Gets the scale of this token
     * @return
     */
    @Override
    public float getScale() {
        return scale;
    }

    /**
     * Sets the scale of this token
     * @param scale
     */
    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public void draw(Graphics2D g, float scale) {
        var size = scale * this.scale;

        var c = g.getColor();

        int x = pos.x - (int) (size / 2);
        int y = pos.y - (int) (size / 2);

        // Draw circle outline
        g.setColor(Color.BLACK);
        if (selected)
            g.setColor(Color.yellow);
        g.fillOval(x, y, (int) size, (int) size);

        // Draw filled circle
        g.setColor(color);
        g.fillOval(x + 3, y + 3, (int) size - 6, (int) size - 6);

        g.setColor(c);
    }
}