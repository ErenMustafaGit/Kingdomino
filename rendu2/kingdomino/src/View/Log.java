package View;

import Model.GameContext;
import Model.GameObserver;
import Model.GameMode;
import Model.PlayerStrategy;

public class Log implements GameObserver {
    @Override
    public void update(GameContext gameContext) {
        System.out.println("Tour du joueur " + gameContext.getKingTurn().getColor() );
    }

    @Override
    public void updateEnd(GameContext gameContext) {

    }

    //notifier aussi si la place est possible ou non!
}
