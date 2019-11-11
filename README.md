# Setup
## Projekt auschecken:
`git clone https://gitlab.puzzle.ch/schnuppertag`

## Benötigte Plugins:
* leJOS NXJ

## Konfiguration
Die Roboter verwenden einen anderen Lichtsensor.
Dies muss im Konstruktor der Roboter Klasse abgeändert werden, je nach dem, welcher Roboter verwendet wird.
* Roboter Jerry: ColorSensor
* Roboter ET: LightSensor

## Notizen
Falls sich auf dem Zukunftstag2 Laptop eclipse nicht über die Verknüpfung öffnen lässt, muss dieses über die Command Line geöffnet werden.

`./eclipse/java-neon/eclipse/eclipse --consoleLog`
