package com.nbh.game.ball;


/** Die Klasse Player verwaltet die Variablen, die die Punkte und die Leben des Spielers
ausmachen. Ebenso verfügt sie über Methoden mit denen Punkte und Leben abgezogen bzw.
hinzugefügt werden können */

public class Player
{
    // Deklaration der Variablen
    private int score;			// Punkte des Spielers
    private int lives;			// Leben des Spielers

    /* Konstruktor */
    public Player()
    {
        this.lives = 10;
        this.score = 0;
    }

    /* Liefert die Punkte zurück */
    public int getScore ()
    {
        return this.score;
    }

    /* Liefert die Leben zurück */
    public int getLives ()
    {
        return this.lives;
    }

    /* Fügt Punkte hinzu */
    public void addScore (final int plus)
    {
        this.score += plus;
    }

    /* zieht Leben ab */
    public void looseLife ()
    {
        this.lives --;
    }
}