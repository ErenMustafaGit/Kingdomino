package Model;

public interface GameObserver {
    void update(GameContext game);
    void preview(GameContext game);
}
