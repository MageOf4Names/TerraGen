import java.util.ArrayList;

public class Map {
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
}
