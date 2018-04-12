   package com.nbh.core.sounds;

   import javax.sound.midi.MidiChannel;
   import javax.sound.midi.Synthesizer;
   import javax.sound.midi.MidiSystem;
   import javax.sound.midi.MidiUnavailableException;

   public class SingleNoteChannel {

      private MidiChannel channel;

      public SingleNoteChannel() {
        try {
          Synthesizer synth =
                          MidiSystem.getSynthesizer();
          synth.open();
          channel = synth.getChannels()[0];
        } catch (MidiUnavailableException e) {
          e.printStackTrace();
        }
      }

      public void playNote(int note) {
        channel.noteOn(note, 70);       // the 70 is the 'velocity', how hard the key is hit (volume).
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        channel.noteOff(note, 70);
      }

      public static void main(String[] args) {
        new SingleNoteChannel().playNote(60);
      }
   }
