package Accessories;

import java.io.*;
import com.sun.speech.freetts.*;

public class VoiceThread implements Runnable {

	// Voice Variables
	private static final String VOICENAME = "kevin16";

	String text = "Bulbasaur is a great pokemon, mate";

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Voice voice;
		VoiceManager vm = VoiceManager.getInstance();
		voice = vm.getVoice(VOICENAME);
		voice.allocate();
		try {
			voice.speak("Bulbasaur. Seed Pokey Mawn. Grass and Poison type Pokey Mawn.");
		} catch (Exception ex) {

		}
	}
}
