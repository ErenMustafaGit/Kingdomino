package Model;

public interface GameObserver {
    void notify(GameStrategy strategy, PlayerStrategy player);
}
