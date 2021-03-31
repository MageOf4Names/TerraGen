import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    public ITileGrid grid;

    public ArrayList<Token> tokens = new ArrayList<Token>();

    public Map(ITileGrid grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return grid.getWidth();
    }

    public int getHeight() {
        return grid.getHeight();
    }

    public float getScale() {
        return grid.getTileSize();
    }

    public Token addToken(int x, int y, Token token){
        tokens.add(token);
        token.setLocation(grid, x, y);
        return token;
    }

    public Token addToken(int x, int y){
        return addToken(x, y, new Token());
    }

    public void draw(Graphics2D g)
    {
        var c = g.getColor();
        float scale = grid.getTileSize();

        // Draw map outline
        g.setColor(Color.lightGray);
        g.drawRect(0,0, (int)(getWidth() * scale), (int)(getHeight() * scale));

        // Draw tiles
        grid.draw(g);

        // Draw each token
        for (var t : tokens) {
            t.draw(g, scale);
        }

        g.setColor(c);
    }
}
