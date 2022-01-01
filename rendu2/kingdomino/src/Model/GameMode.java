package Model;

public interface GameMode {

    public int calculateScore(PlayerBoard p);
    String getnamegame();

    public boolean isHarmony(PlayerBoard p);
    public boolean isKingdomMiddle(PlayerBoard p);

}
