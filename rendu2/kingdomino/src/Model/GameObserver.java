package Model;

public interface GameObserver {
    void update(GameContext game);

    void updateEnd(GameContext gameContext);
}
