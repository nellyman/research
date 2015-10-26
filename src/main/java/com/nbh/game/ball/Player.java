
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
		lives = 10;
		score = 0;
	}

	/* Liefert die Punkte zur�ck */
	public int getScore ()
	{
		return score;
	}

	/* Liefert die Leben zur�ck */
	public int getLives ()
	{
		return lives;
	}

	/* F�gt Punkte hinzu */
	public void addScore (int plus)
	{
		score += plus;
	}

	/* zieht Leben ab */
	public void looseLife ()
	{
		lives --;
	}
}