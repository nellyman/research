package com.nbh.core.sounds;

/**
 * Alternate to SingleNoteChannel.
 *
 * Creates ShortMessage objects and sends them to a receiver to play them.
 *
 ***/
  import javax.sound.midi.ShortMessage;
   import javax.sound.midi.InvalidMidiDataException;
   import javax.sound.midi.Receiver;
   import javax.sound.midi.Synthesizer;
   import javax.sound.midi.MidiSystem;
   import javax.sound.midi.MidiUnavailableException;

   public class SingleNoteSynthesiser {

      private ShortMessage message = 
                                    new ShortMessage();
      private Receiver receiver;

      private SingleNoteSynthesiser() {
        try {
          Synthesizer synth = 
                           MidiSystem.getSynthesizer();
          synth.open();
          receiver = synth.getReceiver();
        } catch (MidiUnavailableException e) {
          e.printStackTrace();
        }
      }
  
      public void playNote(int note) {
        setShortMessage(note, ShortMessage.NOTE_ON);
        receiver.send(message, -1);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
       e.printStackTrace(); 
        }
        setShortMessage(note, ShortMessage.NOTE_OFF);
        receiver.send(message, -1);
      }

      private void setShortMessage(
                               int note, int onOrOff) {
        try {
          message.setMessage(onOrOff, 0, note, 70);
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
      }

      public static void main(String[] args) {
        new SingleNoteSynthesiser().playNote(60);
      }
   }
