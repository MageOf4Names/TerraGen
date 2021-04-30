import java.io.Serializable;

/**
 * Redundant class used to store both an instance of the Game and GameRender for sending between Client and Server.
 */
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
