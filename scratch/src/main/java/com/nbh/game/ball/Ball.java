package com.nbh.game.ball;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball
{
    /** Diese Klasse enthält alle Funktionen, die für die Bewegung, Konstruktion,
	Positionsbestimmung, Graphik und Abschiesen des Ball - Objektes von Bedeutung sind. */

    // Deklaration der Variablen
    private int pos_x;				// Variable für die X - Position des Balles
    private int pos_y; 				// Variable für die Y - Position des Balles
    private int x_speed;			// Geschwindigkeit in x - Richtung
    private final int y_speed;			// Geschwindigkeit in y - Richtung
    private final int radius;				// Radius des Balles

    private final int first_x;			// Start x - Position
    private final int first_y;			// Start y - Position

    private final int maxspeed;			// Gibt den Maximalen Speed des Balles an

    // Deklaration der Konstanten (Grenzen des Applets bei einer Gesamtgröße von 380 x 380)
    private final int x_leftout = 10;
    private final int x_rightout = 370;
    private final int y_upout = 45;
    private final int y_downout = 370;

    // Farbe des Balles
    Color color;

    // AudioClip out
    AudioClip out;

    // Refferenz auf das Playerobjekt des Spiels
    Player player;

    // Erzeugen des zum Erzeugen von Zufallszahlen nötigen Objektes
    Random rnd = new Random ();

    // Construktor
    public Ball (final int radius, final int x, final int y, final int vx, final int vy, final int ms, final Color color, final AudioClip out, final Player player)
    {
        // Initialisierung der Variablen
        this.radius = radius;

        this.pos_x = x;
        this.pos_y = y;

        this.first_x = x;
        this.first_y = y;

        this.x_speed = vx;
        this.y_speed = vy;

        this.maxspeed = ms;

        this.color = color;

        this.out = out;

        this.player = player;

    }

    // Move - Methode, berechnet die Bewegung des Balls
    public void move ()
    {
        this.pos_x += this.x_speed;
        this.pos_y += this.y_speed;
        this.isOut();
    }

    /** Methode wird nach einem Treffer des Balls aufgerufen, dabei wird x und y übergeben um Ball an die
	Ausgangsposition zurückzusetzten */
    public void ballWasHit ()
    {
        // Zurücksetzen der Position
        this.pos_x = this.first_x;
        this.pos_y = this.first_y;

        // Bestimmung einer neuen Flugrichtung in x - Richtung per Zufall
        this.x_speed = (this.rnd.nextInt ()) % this.maxspeed;
    }

    /** Methode zur Berechnung, ob der Ball getroffen werden konnte oder nicht.
	Dies wird mit Hilfe des Skalarproduktes berechnet. Hierbei wird zunächst
	der Abstandsvektor zwischen Ball und Mausereigniss und dann seine Länge bestimmt.
	Ist diese kleiner als 15, dann gilt der Ball als getroffen und true wid als
	Argument übergeben. Ansonsten ist der Wert der Funktion false */
    public boolean userHit (final int maus_x, final int maus_y)
    {
        // Bestimmen der Verbindungsvektoren
        final double x = maus_x - this.pos_x;
        final double y = maus_y - this.pos_y;

        // Berechnen der Distanz
        final double distance = Math.sqrt ((x*x) + (y*y));

        // Wenn Distanz kleiner als 15 gilt Ball als getroffen
        if (distance < 15)
        {
            this.player.addScore (10*Math.abs(this.x_speed) + 10);
            return true;
        } else {
            return false;
        }
    }

    /** Test ob sich der Ball im Aus befindet, wird von Main in jedem Threaddurchlauf
	aufgerufen */
    private boolean isOut ()
    {
        // Ball im Linken Aus
        if (this.pos_x < this.x_leftout)
        {
            // Setzen der x - Position
            this.pos_x = this.first_x;
            this.pos_y = this.first_y;

            // Abspielen des Audioclips
            this.out.play();

            // Neue Flugrichtung
            this.x_speed = (this.rnd.nextInt ()) % this.maxspeed;

            // Leben verlieren
            this.player.looseLife();

            // Rückgabewert
            return true;
        }
        // Ball im rechten Aus
        else if (this.pos_x > this.x_rightout)
        {
            // Setzen der x - Position
            this.pos_x = this.first_x;
            this.pos_y = this.first_y;

            // Abspielen des Audioclips
            this.out.play();

            // Neue Flugrichtung
            this.x_speed = (this.rnd.nextInt ()) % this.maxspeed;

            // Leben verlieren
            this.player.looseLife();

            // Rückgabewert
            return true;
        }
        // Ball im oberen Aus
        else if (this.pos_y < this.y_upout)
        {
            // Setzen der x - Position
            this.pos_x = this.first_x;
            this.pos_y = this.first_y;

            // Abspielen des Audioclips
            this.out.play();

            // Neue Flugrichtung
            this.x_speed = (this.rnd.nextInt ()) % this.maxspeed;

            // Leben verlieren
            this.player.looseLife();

            // Rückgabewert
            return true;
        }
        // Ball im unteren Aus
        else if (this.pos_y > this.y_downout)
        {
            // Setzen der x - Position
            this.pos_x = this.first_x;
            this.pos_y = this.first_y;

            // Abspielen des Audioclips
            this.out.play();

            // Neue Flugrichtung
            this.x_speed = (this.rnd.nextInt ()) % this.maxspeed;

            // Leben verlieren
            this.player.looseLife();

            // Rückgabewert
            return true;
        } else {
            return false;
        }
    }

    // Diese Methode zeichnet den Ball in das Spielfeld
    public void DrawBall (final Graphics g)
    {
        g.setColor (this.color);
        g.fillOval (this.pos_x - this.radius, this.pos_y - this.radius, 2 * this.radius, 2 * this.radius);
    }

}
