package ch.puzzle.lejos;

import ch.puzzle.lejos.helper.Roboter;

public class Demo {

	public static void main(String[] args) {
		Roboter roboter = new Roboter();

		// HIER STARTEN DIE ANWEISUNGEN
		
		// Roboter fahr vorwärts
		
		roboter.fahrVorwaerts();
		roboter.warteBisKlebeband();
		
		// HIER ENDEN DIE ANWEISUNGEN
	}

}
