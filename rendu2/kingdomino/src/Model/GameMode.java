package Model;

public interface GameMode {

    public int calculateScore(PlayerBoard p);
    String getnamegame();

    public boolean isHarmony(PlayerBoard p); //Pour connaitre si le plateau est harmonieux
    public boolean hasHarmony(); //Pour savoir si le jeu actuel a activé le mode de jeu

    public boolean isKingdomMiddle(PlayerBoard p);

}
