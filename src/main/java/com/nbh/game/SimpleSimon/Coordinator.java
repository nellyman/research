package com.nbh.game.SimpleSimon;

/*
 * Coordinator.java
 *
 * Created on 19 February 2003, 21:18
 */

/**
 *
 * @author  nhardwick
 */

import java.util.*;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Coordinator implements LightListener{
    
    private Vector sequence;
    private JPanel simonPanel;
    private Vector guess;
    private int sequenceNumber=1;
    private int level=SimpleSimon.LEVEL_EASY;
    Light[] lights = new Light[level];
    private boolean receiveFlag=false;
    
    
    /** Creates a new instance of Coordinator */
    public Coordinator(int level, JPanel p) {
        this.level=level;
        this.simonPanel = p;
        sequence=new Vector();
        guess=new Vector();
        start();
    }
    
    public Coordinator(JPanel p){
        this(SimpleSimon.LEVEL_EASY, p);
    }
    
    public void start(){
        // create the lights...
        for (int i=0;i<level;i++){
            Light l= new Light(i+1);
            l.addListener(this);
            lights[i] = l;
            System.out.println("Created light "+l.getNumber());
        }
        simonPanel.repaint();
        // start sequence ...
        //doSequence();
    }
    
    public void checkLights(int x, int y){
        for (int i=0;i<level;i++){
            Light l = lights[i];
            l.isLightClicked(x,y);
        }
    }
    
    public void updateLights(Graphics g){
        for (int i=0;i<level;i++){
            Light l =(Light)lights[i];
            l.update(g);
        }        
        
    }
    
    
    public void lightTriggered(Light light){
        // need to match the light number
        // to the sequence number.
        // if incorrect flash all lights, game over !
        // if correct then call do sequence again...
        if (receiveFlag){
            guess.addElement(new Integer(light.getNumber()));
            int theGuess = light.getNumber();
            if (guess.size()<=sequence.size()){ // haven't reached the end of the game yet !!
                int theSequenceNumber = ((Integer)sequence.elementAt(guess.size())).intValue();
                if (theGuess==theSequenceNumber){
                    // correct !!
                }
                else{
                    // incorrect !
                }
            }
            
        }
    }
    
    public void doSequence() {
        
        receiveFlag=false;
        guess.clear();
        // Add to the sequence.
        System.out.println(" Cleared guess cache. Receive flag is off.. ");
        int i=0;
        do{
            // get a random number.
            // Random rnd  = new java.util.Random(); 
            // int lightToFlash = rnd.nextInt(level);
            int lightToFlash=1;
            System.out.println(" Going to flash "+lightToFlash);
            sequence.addElement(new Integer(lightToFlash));
        }while(++i<sequence.size());
        // flash the lights..
        i=0;
        while(i<=sequence.size()){
            int lightToFlash = ((Integer)sequence.elementAt(i)).intValue();
            lights[lightToFlash].trigger();
            // need to call 'update' on the panel...
            simonPanel.repaint();            
        }
        receiveFlag=true;
        // now we can receive the inputs...
    }
    
}
