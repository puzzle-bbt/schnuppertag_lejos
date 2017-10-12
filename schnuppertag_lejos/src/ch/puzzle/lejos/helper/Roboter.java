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
 * <p>
 * Diese Klasse ist dazu da, den Roboter f&uuml;r den
 * "M&auml;dchen-Informatik-Los" Tag zu steuern. Die Klasse ist optimiert
 * f&uuml;r den NXJ Roboter und unterst&uuml;tzt den Ultraschall und den
 * Touch-Sensor.
 * </p>
 */
public class Roboter {

	
	/**
	 * DifferentialPilot ist eine Hilfsklasse von der NXJ API. Hier werden die
	 * Ausmasse des gebauten Roboters konfiguriert. Der Roboter kann damit dann
	 * gesteuert werden.
	 */
	private DifferentialPilot pilot;

	/**
	 * Der Ber&uuml;hrungs-Sensor, der im Roboter eingebaut ist. Der Roboter
	 * kann so Ber&uuml;hrungen wahrnehmen.
	 */
	private TouchSensor touch;

	/**
	 * Der Ultraschall Sensor. Hiermit kann der Roboter "sehen" - resp. die
	 * Distanz zu Objekten herausfinden.
	 */
	private UltrasonicSensor ultrasonic;

	/**
	 * Der Lichtsensor Kann zB auf den Boden gerichtet werden. je nach
	 * Helligkeit des untergrunds der reflektiert wird liefert der Lichtsensor
	 * einen Wert zurück.
	 */
	// if Tom
	//private ColorSensor light;

	// if jerry;
	private LightSensor light;

	/**
	 * 
	 * 
	 */
	private SoundSensor sound;

	/**
	 * <p>
	 * Konstruktor.
	 * </p>
	 * 
	 * <p>
	 * Hier wird der Roboter mit seinen Sensoren erstellt.
	 * </p>
	 */
	
	public Roboter() {
		ultrasonic = new UltrasonicSensor(SensorPort.S3);
		pilot = new DifferentialPilot(0.88f, 4.72f, Motor.C, Motor.A);
		sound = new SoundSensor(SensorPort.S1, true);
		// if Tom
		//light = new ColorSensor(SensorPort.S2);

		// if Jerry
		light = new LightSensor(SensorPort.S2);
		
		
	}

	/**
	 * <p>
	 * Fahre mit der aktuell eingestellten Geschwindigkeit vorw&auml;rts. Der
	 * Roboter stoppt erst, wenn er einen neuen Befehl erh&auml;lt.
	 * </p>
	 * 
	 * <p>
	 * Die Geschwindigkeit, mit der der Roboter f&auml;hrt, ist abh&auml;ngig
	 * von den fr&uuml;her eingestellten Werten. Sind noch keine expliziten
	 * Geschwindigkeiten konfiguriert worden, so f&auml;hrt der Roboter in der
	 * Standard Geschwindigkeit.
	 * </p>
	 */
	public void fahrVorwaerts() {
		Motor.A.forward();
		Motor.C.forward();
	}

	/**
	 * <p>
	 * Fahre vorw&auml;rts. Der Wert, der in der Klammer mitgegeben wird, ist
	 * die Geschwindigkeit, mit der der Roboter fahren soll.
	 * </p>
	 * 
	 * <p>
	 * Der Roboter stoppt erst, wenn er einen neuen Befehl erh&auml;lt.
	 * </p>
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
	 * <p>
	 * Fahre r&uuml;ckw&auml;rts. Der Wert, der in der Klammer mitgegeben wird,
	 * ist die Geschwindigkeit, mit der der Roboter fahren soll.
	 * </p>
	 * 
	 * <p>
	 * Der Roboter stoppt erst, wenn er einen neuen Befehl erh&auml;lt.
	 * </p>
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
	 * <p>
	 * Fahre mit der aktuell eingestellten Geschwindigkeit r&uuml;ckw&auml;rts.
	 * Der Roboter stoppt erst, wenn er einen neuen Befehl erh&auml;lt.
	 * </p>
	 * 
	 * <p>
	 * Die Geschwindigkeit, mit der der Roboter f&auml;hrt, ist abh&auml;ngig
	 * von den fr&uuml;her eingestellten Werten. Sind noch keine expliziten
	 * Geschwindigkeiten konfiguriert worden, so f&auml;hrt der Roboter in der
	 * Standard Geschwindigkeit.
	 * </p>
	 */
	public void fahrRueckwaerts() {
		Motor.A.backward();
		Motor.C.backward();
	}

	/**
	 * <p>
	 * Fahre eine bestimmte Distanz vorw&auml;rts. Die Distanz muss in der
	 * Klammer geschrieben werden. Nachdem der Roboter die Distanz gefahren hat,
	 * stoppt er und wartet auf die n&auml;chste Instruktion.
	 * </p>
	 * 
	 * @param distanz
	 *            die Distanz, die der Roboter fahren soll.
	 */
	public void fahrDistanz(int distanz) {
		pilot.travel(distanz);
	}

	/**
	 * <p>
	 * Fahre eine bestimmte Distanz rueckwaerts Die Distanz muss in der
	 * Klammer geschrieben werden. Nachdem der Roboter die Distanz gefahren hat,
	 * stoppt er und wartet auf die n&auml;chste Instruktion.
	 * </p>
	 * 
	 * @param distanz
	 *            die Distanz, die der Roboter fahren soll.
	 */
	public void fahrDistanzRueckwaerts(int distanz) {
		pilot.travel(-distanz);
	}
	/**
	 * <p>
	 * Fahre eine Kurve. Der Winkel, der gefahren werden soll, muss in die
	 * Klammer geschrieben werden. Der Roboter dreht sich gegen Rechts.
	 * </p>
	 * 
	 * @param winkel
	 *            der Winkel, der der Roboter fahren soll.
	 */
	public void fahrKurveRechts(int winkel) {
		pilot.rotate(winkel / 2);
	}

	/**
	 * <p>
	 * Fahre eine Kurve. Der Winkel, der gefahren werden soll, muss in die
	 * Klammer geschrieben werden. Der Roboter dreht sich gegen Links.
	 * </p>
	 * 
	 * @param winkel
	 *            der Winkel, der der Roboter fahren soll.
	 */
	public void fahrKurveLinks(int winkel) {
		pilot.rotate(-winkel / 2);
	}
	
	public void warteBisLautstaerke(int lautstaerke) {
		while(lautstaerke>sound.readValue()){
		}
	}

	
	/**
	 * <p>
	 * Sag mir, ob der Roboter aktuell am Fahren ist. Wenn der Roboter am Fahren
	 * ist, dann wird hier <code>true</code> ("Ja") zur&uuml;ckgegeben,
	 * ansonsten <code>false</code> ("Nein").
	 * </p>
	 * 
	 * @return <code>true</code>, wenn der Roboter aktuell f&auml;hrt,
	 *         <code>false</code> sonst
	 */
	public boolean roboterFaehrt() {
		return pilot.isMoving();
	}

	/**
	 * <p>
	 * Stoppe den Roboter, egal was er gerade am machen ist.
	 * </p>
	 */
	public void stop() {
		pilot.stop();
	}

	/**
	 * <p>
	 * Sag mir, wie schnell der Roboter aktuell gerade f&auml;hrt.
	 * </p>
	 * 
	 * @return Geschwindigkeit, mit der der Roboter aktuell gerade f&auml;hrt.
	 */
	public double holeGeschwindigkeit() {
		return pilot.getTravelSpeed();
	}

	/**
	 * <p>
	 * Ver&auml;ndere die Geschwindigkeit des Roboters. Die neue Geschwindigkeit
	 * muss in die Klammer geschrieben werden.
	 * </p>
	 * 
	 * @param geschwindigkeit
	 *            die neue Geschwindigkeit des Roboters.
	 */
	public void setzeGeschwindigkeit(double geschwindigkeit) {
		pilot.setTravelSpeed(geschwindigkeit);
	}

	/**
	 * <p>
	 * Fahre vorw&auml;rts, bis der Roboter ein Objekt "sieht". Die Distanz, bis
	 * er halten soll, muss in Klammer angegeben werden.
	 * </p>
	 * 
	 * @param distanz
	 *            die Distanz, bis zu der der Roboter vor ein Objekt fahren
	 *            soll.
	 */
	public void warteAufObjekt(int distanz) {
		ultrasonic.continuous();
		while (ultrasonic.getDistance() > distanz) {
			
		}
	}

	/**
	 * Der Roboter wartet, bis ein Knopf gedr&uuml;ckt wird.
	 */
	public void warteBisKnopfGedruecktWird() {
		Button.waitForAnyPress();
	}

	/**
	 * Der Roboter führt die momentane aktion weiter bis er auf dem boden ein
	 * Klebeband sieht.
	 */
	public void warteAufKlebeband() {
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
	public void fahreLautstaerkeBisKlebeband() {
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
	 * Mache "Beep".
	 */
	public void beep() {
		Sound.beep();
	}

	/**
	 * Mache "Buzz".
	 */
	public void buzz() {
		Sound.buzz();
	}


	
	public void fahrFuerMiliSekunden(int milisekunden) {
		pilot.forward();
		Delay.msDelay(milisekunden);
		pilot.stop();
	}


public void warteFuerMiliSekunden(int ms){
	long ms2 = (long) ms;
	try {
		pilot.wait(ms2);	
	} catch (Exception e) {
		System.out.println(e);
	}
	
}
public void weiterFuerMiliSekunden(int ms) {
	Delay.msDelay(ms);
}
}