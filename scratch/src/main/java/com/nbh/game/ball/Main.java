package com.nbh.game.ball;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

/** Diese Klasse enthält alle für die Implementierung eines Applets wichtigen Methoden
(init, start, stop), desweiteren die Methoden run (zur Implementierung des Interfaces Run-
abble), sowie die Graphikmethoden paint und update. Die Klasse realisiert die Dopppel-
pufferung in der Methode update, sie hört auf Mausereignisse (mouseDown) und enthält
alle für den Ablauf des Spiels wichtigen Anweisungen */

public class Main extends Applet implements Runnable
{
    // Deklarationen der Variablen
    private int speed;				// Threadgeschwindigkeit

    boolean isStoped = true;		// Zeigt an, ob das Spiel gestopt ist (true) oder läuft (false)

    // Deklaration der Objektreferenzen
    private Player player;			// Refferenz auf das Spielerobjekt
    private Ball redball;			// Refferenz auf den roten Ball
    private Ball blueball;			// Refferenz auf den blauen Ball

    // Thread
    Thread th;						// Thread in dem das Spiel läuft

    // Audiodateien
    AudioClip shotnoise;	// Speichert die Wav - Datei Gun, die nach Schuss abgespielt wird
    AudioClip hitnoise;		// Speichert die Wav - Datei hit, die nach einem Treffer abgespielt wird
    AudioClip outnoise;		// Speichert die Wav - Datei error, die nach einem Treffer abgespielt wird

    // Neue Schrift
    Font f = new Font ("Serif", Font.BOLD, 20);

    // Fadenkreuzmauszeiger
    Cursor c;				// Variable für Cursor

    // Variablen für die Doppelpufferung
    private Image dbImage;
    private Graphics dbg;

    // Init - Methode
    @Override
    public void init ()
    {
        // Mauszeiger wird zu Fadenkreuz
        this.c = new Cursor (Cursor.CROSSHAIR_CURSOR);
        this.setCursor (this.c);

        // Neue Hintergrundfarbe
        final Color superblue = new Color (0, 0, 255);

        // Setzen der Hintergrundfarbe
        this.setBackground (Color.black);

        // Setzten der Schrift
        this.setFont (this.f);

        // Speed wird von Parameter speed des Applets bestimmt
        if (this.getParameter ("speed") != null)
        {
            this.speed = Integer.parseInt(this.getParameter("speed"));
        } else {
            this.speed = 15;
        }

        // Laden der Bilder und Audiodateien und einmaliges Abspielen, um längere Ladezeiten während des Spiels zu vermeiden
        this.hitnoise = this.getAudioClip (this.getCodeBase() , "gun.au");
        this.hitnoise.play();
        this.hitnoise.stop();
        this.shotnoise = this.getAudioClip (this.getCodeBase() , "miss.au");
        this.shotnoise.play();
        this.shotnoise.stop();
        this.outnoise = this.getAudioClip (this.getCodeBase() , "error.au");
        this.outnoise.play();
        this.outnoise.stop();

        // Initialisierung der Spielobjekte
        this.player = new Player ();
        this.redball = new Ball (10, 190, 250, 1, -1, 4, Color.red, this.outnoise, this.player);
        this.blueball = new Ball (10, 190, 150, 1, 1, 3, Color.blue, this.outnoise, this.player);
    }


    // Start - Methode, hier beginnt das Applet zu laufen
    @Override
    public void start ()
    {
        // Schaffen eines neuen Threads, in dem das Spiel läuft
        this.th = new Thread (this);
        this.th.start ();
    }

    // Stop - Methode, hier wird das Applet gestopt
    @Override
    public void stop ()
    {
        this.th.stop();
    }

    // Auffangen des Mausereignisses mouseDown
    @Override
    public boolean mouseDown (final Event e, final int x, final int y)
    {
        // Spiel läuft
        if (!this.isStoped)
        {
            // Test ob roter Ball getroffen wurde
            if (this.redball.userHit (x, y))
            {
                // Abspielen der Audiodatei
                this.hitnoise.play();

                // Ball zu Startwert zurücksetzten
                this.redball.ballWasHit ();
            }
            // Test ob blauer Ball getroffen wurde
            if (this.blueball.userHit (x, y))
            {
                // Abspielen der Audiodatei
                this.hitnoise.play();

                // Ball zu Startwert zurücksetzten
                this.blueball.ballWasHit ();
            }
            else
            {
                // Abspielen des normalen Schussgeräusches
                this.shotnoise.play();
            }

        }
        // Wenn Spiel noch nicht gestartet ist, oder wieder gestartet wird
        else if (this.isStoped && e.clickCount == 2)
        {
            // Alle wichtigen Werte zurücksetzen
            this.isStoped = false;
            this.init ();
        }

        return true;
    }

    // Implementierung der Runmethode
    @Override
    public void run ()
    {
        // Erniedrigen der ThreadPriority um zeichnen zu erleichtern
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        while (true)
        {
            if (this.player.getLives() >= 0 && !this.isStoped)
            {
                this.redball.move();
                this.blueball.move();
            }

            this.repaint();

            try
            {
                // Stoppen des Threads für 10 Millisekunden
                Thread.sleep (this.speed);
            }
            catch (final InterruptedException ex)
            {
                // do nothing
            }

            // Zurücksetzen der ThreadPriority auf Maximalwert
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    // Paint - Methode
    @Override
    public void paint (final Graphics g)
    {
        // Wenn noch Leben übrig sind
        if (this.player.getLives() >= 0)
        {
            // Setzen der Farbe
            g.setColor (Color.yellow);

            // Punktestand und übrige Leben
            g.drawString ("Score: " + this.player.getScore(), 10, 40);
            g.drawString ("Lives: " + this.player.getLives(), 300, 40);

            // Zeichnen der Bälle
            this.redball.DrawBall(g);
            this.blueball.DrawBall(g);

            // Startbildschirm
            if (this.isStoped)
            {
                g.setColor (Color.yellow);
                g.drawString ("Doubleclick on Applet to start Game!", 40, 200);
            }
        }
        // Wenn alle Leben verbraucht sind
        else if (this.player.getLives() < 0)
        {
            g.setColor (Color.yellow);

            // Erreichte Punkte und game over
            g.drawString ("Game over!", 130, 100);
            g.drawString ("You scored " + this.player.getScore() + " Points!", 90, 140);

            // Bewertung der Punkte
            if (this.player.getScore() < 300) {
                g.drawString ("Well, it could be better!", 100, 190);
            } else if (this.player.getScore() < 600 && this.player.getScore() >= 300) {
                g.drawString ("That was not so bad", 100, 190);
            } else if (this.player.getScore() < 900 && this.player.getScore() >= 600) {
                g.drawString ("That was really good", 100, 190);
            } else if (this.player.getScore() < 1200 && this.player.getScore() >= 900) {
                g.drawString ("You seem to be very good!", 90, 190);
            } else if (this.player.getScore() < 1500 && this.player.getScore() >= 1200) {
                g.drawString ("That was nearly perfect!", 90, 190);
            } else if (this.player.getScore() >= 1500) {
                g.drawString ("You are the Champingon!",100, 190);
            }

            g.drawString ("Doubleclick on the Applet, to play again!", 20, 220);

            this.isStoped = true;	// Zurücksetzen der isStoped Variablen, um wieder neu beginnen zu können
        }
    }

    // Update - Methode, Realisierung der Doppelpufferung zur Reduzierung des Bildschirmflackerns
    @Override
    public void update (final Graphics g)
    {
        // Initialisierung des DoubleBuffers
        if (this.dbImage == null)
        {
            this.dbImage = this.createImage (this.getSize().width, this.getSize().height);
            this.dbg = this.dbImage.getGraphics ();
        }

        // Bildschirm im Hintergrund löschen
        this.dbg.setColor (this.getBackground ());
        this.dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

        // Auf gelöschten Hintergrund Vordergrund zeichnen
        this.dbg.setColor (this.getForeground());
        this.paint (this.dbg);

        // Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
        g.drawImage (this.dbImage, 0, 0, this);
    }
}


