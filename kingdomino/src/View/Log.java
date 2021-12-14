package View;

import Model.GameObserver;
import Model.GameStrategy;
import Model.PlayerStrategy;

public class Log implements GameObserver {
    @Override
    public void notify(GameStrategy strategy, PlayerStrategy player) {
        System.out.println("Nous sommes en mode : "+ strategy.getnamegame()+"\nIl y'a "+player.getnbBoard()+" joueurs pour cette partie");
    }

    //notifier aussi si la place est possible ou non!
}
