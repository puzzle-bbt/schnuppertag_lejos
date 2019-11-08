package ch.puzzle.lejos.helper;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * Diese Klasse ist dazu da, den Roboter für den
 * "Mädchen-Informatik-Los" Tag zu steuern. Die Klasse ist optimiert
 * für den NXJ Roboter und unterstützt den Ultraschall und den
 * Touch-Sensor.
 */
public class Roboter {

	/**
	 * DifferentialPilot ist eine Hilfsklasse von der NXJ API. Hier werden die
	 * Ausmasse des gebauten Roboters konfiguriert. Der Roboter kann damit dann
	 * gesteuert werden.
	 */
	private DifferentialPilot pilot;

	/**
	 * Der Berührungs-Sensor, der im Roboter eingebaut ist. Der Roboter
	 * kann so Berührungen wahrnehmen.
	 */
	private TouchSensor beruehrungsSensor;

	/**
	 * Der Ultraschall Sensor. Hiermit kann der Roboter "sehen" - resp. die
	 * Distanz zu Objekten herausfinden.
	 */
	private UltrasonicSensor distanzZuObjektSensor;

	/**
	 * Der Lichtsensor kann zB auf den Boden gerichtet werden. je nach
	 * Helligkeit des Untergrunds der reflektiert wird liefert der Lichtsensor
	 * einen Wert zurück.
	 */
	// if Tom
	private ColorSensor light;
	
	// if Jerry
	//private LightSensor light;

	/**
	 * Der Soundsensor. Hiermit kann der Roboter "hören".
	 * Zum Beispiel hört er wenn jemand klatscht.
	 */
	private SoundSensor sound;

	/**
	 * Das ist ein Konstruktor.
	 * 
	 * Hier wird der Roboter mit seinen Sensoren erstellt.
	 */
	public Roboter() {
		distanzZuObjektSensor = new UltrasonicSensor(SensorPort.S3);
		pilot = new DifferentialPilot(0.88f, 4.72f, Motor.C, Motor.A);
		sound = new SoundSensor(SensorPort.S1, true);
		light = new ColorSensor(SensorPort.S2);
	}

	/**
	 * Fahre mit der aktuell eingestellten Geschwindigkeit vorwärts. Der
	 * Roboter stoppt erst, wenn er einen neuen Befehl erhält.
	 * 
	 * Die Geschwindigkeit, mit der der Roboter fährt, ist abhängig
	 * von den früher eingestellten Werten. Sind noch keine expliziten
	 * Geschwindigkeiten konfiguriert worden, so fährt der Roboter in der
	 * Standard Geschwindigkeit.
	 */
	public void fahrVorwaerts() {
		Motor.A.forward();
		Motor.C.forward();
	}

	/**
	 * Fahre vorwärts. Der Wert, der in der Klammer mitgegeben wird, ist
	 * die Geschwindigkeit, mit der der Roboter fahren soll.
	 * 
	 * Der Roboter stoppt erst, wenn er einen neuen Befehl erhält.
	 * 
	 * @param geschwindigkeit
	 *            die Geschwindigkeit, mit der der Roboter fahren soll.
	 */
	public void fahrVorwaerts(int geschwindigkeit) {
		Motor.A.setSpeed(geschwindigkeit);
		Motor.C.setSpeed(geschwindigkeit);
		Motor.A.forward();
		Motor.C.forward();
	}

	/**
	 * Fahre rückwärts. Der Wert, der in der Klammer mitgegeben wird,
	 * ist die Geschwindigkeit, mit der der Roboter fahren soll.
	 * 
	 * Der Roboter stoppt erst, wenn er einen neuen Befehl erhält.
	 * 
	 * @param geschwindigkeit
	 *            die Geschwindigkeit, mit der der Roboter fahren soll.
	 */
	public void fahrRueckwaerts(int geschwindigkeit) {
		Motor.A.setSpeed(geschwindigkeit);
		Motor.C.setSpeed(geschwindigkeit);
		Motor.A.backward();
		Motor.C.backward();
	}

	/**
	 * Fahre mit der aktuell eingestellten Geschwindigkeit rückwärts.
	 * Der Roboter stoppt erst, wenn er einen neuen Befehl erhält.
	 * 
	 * Die Geschwindigkeit, mit der der Roboter fährt, ist abhängig
	 * von den früher eingestellten Werten. Sind noch keine expliziten
	 * Geschwindigkeiten konfiguriert worden, so fährt der Roboter in der
	 * Standard Geschwindigkeit.
	 */
	public void fahrRueckwaerts() {
		Motor.A.backward();
		Motor.C.backward();
	}

	/**
	 * Fahre eine bestimmte Distanz vorwärts. Die Distanz muss in der
	 * Klammer geschrieben werden. Nachdem der Roboter die Distanz gefahren hat,
	 * stoppt er und wartet auf die nächste Instruktion.
	 * 
	 * @param distanz
	 *            die Distanz, die der Roboter fahren soll.
	 */
	public void fahrDistanz(int distanz) {
		pilot.travel(distanz);
	}

	/**
	 * Fahre eine bestimmte Distanz rueckwaerts Die Distanz muss in der Klammer
	 * geschrieben werden. Nachdem der Roboter die Distanz gefahren hat, stoppt
	 * er und wartet auf die nächste Instruktion.
	 * 
	 * @param distanz
	 *            die Distanz, die der Roboter fahren soll.
	 */
	public void fahrDistanzRueckwaerts(int distanz) {
		pilot.travel(-distanz);
	}

	/**
	 * Fahre eine Kurve. Der Winkel, der gefahren werden soll, muss in die
	 * Klammer geschrieben werden. Der Roboter dreht sich gegen Rechts.
	 * 
	 * @param winkel
	 *            der Winkel, der der Roboter fahren soll.
	 */
	public void dreheNachRechts(int winkel) {
		pilot.rotate(winkel / 2);
	}

	/**
	 * Fahre eine Kurve. Der Winkel, der gefahren werden soll, muss in die
	 * Klammer geschrieben werden. Der Roboter dreht sich gegen Links.
	 * 
	 * @param winkel
	 *            der Winkel, der der Roboter fahren soll.
	 */
	public void dreheNachLinks(int winkel) {
		pilot.rotate(-winkel / 2);
	}

	public void warteBisLautstaerke(int lautstaerke) {
		while (lautstaerke > sound.readValue()) {
		}
	}

	/**
	 * Sag mir, ob der Roboter aktuell am Fahren ist. Wenn der Roboter am Fahren
	 * ist, dann wird hier true ("Ja") zurückgegeben,
	 * ansonsten false ("Nein").
	 * 
	 * @return true, wenn der Roboter aktuell fährt, sonst
	 *         false
	 */
	public boolean faehrtDerRoboter() {
		return pilot.isMoving();
	}

	/**
	 * Stoppe den Roboter, egal was er gerade am machen ist.
	 */
	public void stoppeRoboter() {
		pilot.stop();
	}

	/**
	 * Sag mir, wie schnell der Roboter aktuell gerade fährt.
	 * 
	 * @return Geschwindigkeit, mit der der Roboter aktuell gerade fährt.
	 */
	public double gibGeschwindigkeitAus() {
		return pilot.getTravelSpeed();
	}

	/**
	 * Verändere die Geschwindigkeit des Roboters. Die neue Geschwindigkeit
	 * muss in die Klammer geschrieben werden.
	 * 
	 * @param geschwindigkeit
	 *            die neue Geschwindigkeit des Roboters.
	 */
	public void setzeNeueGeschwindigkeit(double geschwindigkeit) {
		pilot.setTravelSpeed(geschwindigkeit);
	}

	/**
	 * Fahre vorwärts, bis der Roboter ein Objekt "sieht". Die Distanz, bis
	 * er halten soll, muss in Klammer angegeben werden.
	 * 
	 * @param distanz
	 *            die Distanz, bis zu der der Roboter
	 *            vor ein Objekt fahren soll.
	 */
	public void halteWennDistanzZuObjekt(int distanz) {
		distanzZuObjektSensor.continuous();
		while (distanzZuObjektSensor.getDistance() > distanz) {

		}
	}

	/**
	 * Der Roboter wartet, bis ein Knopf gedrückt wird.
	 */
	public void warteBisEinKnopfGedruecktWird() {
		Button.waitForAnyPress();
	}

	/**
	 * Der Roboter führt die momentane Aktion weiter bis er auf dem Boden ein
	 * Klebeband sieht.
	 */
	public void warteBisKlebeband() {
		light.setFloodlight(true);
		while (true) {
			int licht = (int) light.getLightValue();
			if (licht > 40) {
				break;
			}
		}
		light.setFloodlight(false);

	}

	/**
	 * der Roboter fährt schneller wenn es lauter ist.
	 */
	public void fahreSchnellerWennLautstaerkeLauterBisKlebeband() {
		light.setFloodlight(true);
		while (true) {
			int speed = sound.readValue();
			fahrVorwaerts(speed * 5);
			int licht = (int) light.getLightValue();
			if (licht > 40) {
				break;
			}
		}
		light.setFloodlight(false);
	}

	/**
	 * Mache Geräusch "Beep".
	 */
	public void macheBeep() {
		Sound.beep();
	}

	/**
	 * Mache Geräusch "Buzz".
	 */
	public void macheBuzz() {
		Sound.buzz();
	}

	/**
	 * Roboter fährt für Anzahl Milisekunden.
	 */
	public void fahrFuerMiliSekunden(int milisekunden) {
		pilot.forward();
		Delay.msDelay(milisekunden);
		pilot.stop();
	}

	/**
	 * Roboter wartet für Anzahl Milisekunden
	 */
	public void warteFuerMiliSekunden(int milisekunden) {
		long milisekunden2 = (long) milisekunden;
		try {
			pilot.wait(milisekunden2);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/**
	 * Roboter fährt weiter für Anzahl Milisekunden
	 */
	public void fahrWeiterFuerMiliSekunden(int milisekunden) {
		Delay.msDelay(milisekunden);
	}
	
	
	
}