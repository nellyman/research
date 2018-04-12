package com.nbh.gui.game.ball;


/** Die Klasse Player verwaltet die Variablen, die die Punkte und die Leben des Spielers
ausmachen. Ebenso verf�gt sie �ber Methoden mit denen Punkte und Leben abgezogen bzw.
hinzugef�gt werden k�nnen */

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

    /* Liefert die Punkte zur�ck */
    public int getScore ()
    {
        return this.score;
    }

    /* Liefert die Leben zur�ck */
    public int getLives ()
    {
        return this.lives;
    }

    /* F�gt Punkte hinzu */
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