package Model;

public interface GameObserver {
    void notify(GameMode strategy, PlayerStrategy player);
}
