import java.io.Serializable;

public class GameContainer implements Serializable {
    private Game game;
    private GameRenderer gameRenderer;

    public GameContainer(Game game, GameRenderer gameRenderer) {
        this.game = game;
        this.gameRenderer = gameRenderer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameRenderer getGameRenderer() {
        return gameRenderer;
    }

    public void setGameRenderer(GameRenderer gameRenderer) {
        this.gameRenderer = gameRenderer;
    }
}
