package com.nbh.tutorials.sounds;

import javax.sound.midi.*;

public class ListInstruments{
    
    
    public static void main(String[] args){
        Synthesizer synth=null;
        try{
            synth =  MidiSystem.getSynthesizer();
            System.out.println("1");
            Instrument[] instrument = synth.getAvailableInstruments();
            System.out.println("2");
            for (int i=0; i<instrument.length; i++){
                System.out.println(i + "   " + instrument[i].getName());
            }
            System.out.println("3");
        }
        catch(Exception e){
            System.err.println(e);
        }
        finally{
         synth.close();   
        }
    }
}
