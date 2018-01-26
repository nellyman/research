package com.nbh.patterns.command;

/**
 * @author uinxh
 * In the code example the Command pattern completely 
 * decouples the object that invokes the operation -- (Switch )
 * -- from the ones having the knowledge to perform it -- Light
 * and Fan. This gives us a lot of flexibility: the object 
 * issuing a request must know only how to issue it; it doesn't
 * need to know how the request will be carried out. 
 * 
 */

class Fan {
		public void startRotate() {
				System.out.println("Fan is rotating");
		}
		public void stopRotate() {
				System.out.println("Fan is not rotating");
		}
}


class Light {

	// this should record state
		public void turnOn( ) {
				System.out.println("Light is on ");
		}
		public void turnOff( ) {
				System.out.println("Light is off");
		}
}


class Switch {
		private Command UpCommand, DownCommand;
		public Switch( Command Up, Command Down) {
				UpCommand = Up; // concrete Command registers itself with the invoker 
				DownCommand = Down;
		}
		void flipUp( ) { // invoker calls back concrete Command, which executes the Command on the receiver 
						UpCommand . execute ( ) ;


		}
		void flipDown( ) {
						DownCommand . execute ( );
		}
}


class LightOnCommand implements Command {
		private Light myLight;
		public LightOnCommand ( Light L) {
				myLight  =  L;
		}
		public void execute( ) {
				myLight . turnOn( );
		}
}


class LightOffCommand implements Command {
		private Light myLight;
		public LightOffCommand ( Light L) {
				myLight  =  L;
		}
		public void execute( ) {
				myLight . turnOff( );
		}
}


class FanOnCommand implements Command {
		private Fan myFan;
		public FanOnCommand ( Fan F) {
				myFan  =  F;
		}
		public void execute( ) {
				myFan . startRotate( );
		}
}


class FanOffCommand implements Command {
		private Fan myFan;

		public FanOffCommand ( Fan F) {
				myFan  =  F;
		}
		public void execute( ) {
				myFan . stopRotate( );
		}
}


public class TestCommand {

	public static void main(String[] args) {
		
		Light  testLight = new Light( );
		LightOnCommand lightOn = new LightOnCommand(testLight);
		LightOffCommand lightOff = new LightOffCommand(testLight);
		Switch lightSwitch = new Switch( lightOn,lightOff);
		lightSwitch.flipUp( );
		lightSwitch.flipDown( );
		
		Fan testFan = new Fan( );
		FanOnCommand fanOn = new FanOnCommand(testFan);
		FanOffCommand fanOff = new FanOffCommand(testFan);
		Switch fanSwitch = new Switch( fanOn,fanOff);
		fanSwitch.flipUp( );
		fanSwitch.flipDown( );
	}
}               




