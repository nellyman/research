package com.nbh.game.alien;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class BallbewegungReverse extends Applet implements Runnable
{
    // Initialisierung der Variablen
    int x_pos = 30;			// x - Position des Balles
    int y_pos = 100;		// y - Position des Balles
    int x_speed = 1;		// Geschwindigkeit des Balles in x - Richtung
    int radius = 20;		// Radius des Balles
    int appletsize_x = 300; // Gr��e des Applets in x - Richtung
    int appletsize_y = 300;	// Gr��e des Applets in y - Richtung

    // Variablen f�r die Doppelpufferung
    private Image dbImage;
    private Graphics dbg;

    @Override
    public void init()
    {
        this.setBackground (Color.blue);
    }

    @Override
    public void start ()
    {
        // Schaffen eines neuen Threads, in dem das Spiel l�uft
        final Thread th = new Thread (this);
        // Starten des Threads
        th.start ();
    }

    @Override
    public void stop()
    {

    }

    @Override
    public void destroy()
    {

    }

    @Override
    public void run ()
    {
        // Erniedrigen der ThreadPriority um zeichnen zu erleichtern
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        // Solange true ist l�uft der Thread weiter
        while (true)
        {
            // Wenn der Ball den rechten Rand ber�hrt, dann prallt er ab
            if (this.x_pos > this.appletsize_x - this.radius)
            {
                // �ndern der Richtung des Balles
                this.x_speed = -1;
            }
            // Ball br�hrt linken Rand und prallt ab
            else if (this.x_pos < this.radius)
            {
                // �ndern der Richtung des Balles
                this.x_speed = +1;
            }

            // Ver�ndern der x- Koordinate
            this.x_pos += this.x_speed;

            // Neuzeichnen des Applets
            this.repaint();

            try
            {
                // Stoppen des Threads f�r in Klammern angegebene Millisekunden
                Thread.sleep (20);
            }
            catch (final InterruptedException ex)
            {
                // do nothing
            }

            // Zur�cksetzen der ThreadPriority auf Maximalwert
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    /** Update - Methode, Realisierung der Doppelpufferung zur Reduzierung des Bildschirmflackerns */
    @Override
    public void update (final Graphics g)
    {
        // Initialisierung des DoubleBuffers
        if (this.dbImage == null)
        {
            this.dbImage = this.createImage (this.getSize().width, this.getSize().height);
            this.dbg = this.dbImage.getGraphics ();
        }

        // Bildschirm im Hintergrund l�schen
        this.dbg.setColor (this.getBackground ());
        this.dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

        // Auf gel�schten Hintergrund Vordergrund zeichnen
        this.dbg.setColor (this.getForeground());
        this.paint (this.dbg);

        // Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
        g.drawImage (this.dbImage, 0, 0, this);
    }

    @Override
    public void paint (final Graphics g)
    {
        g.setColor  (Color.red);

        g.fillOval (this.x_pos - this.radius, this.y_pos - this.radius, 2 * this.radius, 2 * this.radius);
    }
}
