import java.util.*;

/**
 * Die Dialogklasse für die Klasse Artikel um mit dem Anwender zu kommunizieren
 * 
 * @author Mert & Daniel
 * @version 1.0
 */
public class ArtikelDialog {
    private Artikel artikel1;
    private Scanner input = new Scanner(System.in);

    /**
     * Klassenkonstanten
     */
    private static final int ANLEGEN            = 1;
    private static final int ZUGANG             = 2;
    private static final int ABGANG             = 3;
    private static final int SET_BEZEICHNUNG    = 4;
    private static final int SET_BESTAND        = 5;
    private static final int ENDE               = 0;

    private static final String ARTIKEL_NICHT_VORHANDEN = ("Es wurde noch kein Artikel angelegt!");

    /**
     * Konstruktor
     */
    public ArtikelDialog() {}

    /**
     * Hauptschleife des Testprogramms;
     * Erstellt Laufzeit-Ausnahmen wenn die Methoden einlesenFunktion und ausfuehrenFunktion keine gültigen Eingaben bekommen;
     * printStackTrace-Methode gibt Ausführungsstack auf die Standardausgabe System.out aus.
     */
    public void start() {
        artikel1 = null;
        int funktion = -1;

        while(funktion != ENDE) {
            try {
                funktion = einlesenFunktion();
                ausfuehrenFunktion(funktion);
            } catch(IllegalArgumentException e) {
                System.out.println(e);
            } catch(InputMismatchException e) {
                System.out.println(e);
                input.nextLine();
            } catch(Exception e) {
                System.out.println(e);
                e.printStackTrace(System.out);
            }
            // Zeigt den aktuellen Bestand des angelegten Artikels an
            // wenn ein Artikel schon angelegt wurde,
            // ansonsten wird nichts angezeigt
            if(artikel1 != null) {
                System.out.println("Der aktuelle Zustand des Artikels: \n" + artikel1);
            }
        }
    }

    /**
     * Main-Methode zum Erzeugen des ArtikelDialog-Objektes
     * und zum Anstarten der Testschleife
     */
    public static void main(String[] args) {
        new ArtikelDialog().start();
    }

    /**
     * Mit dieser Methode wird die Auswahl an Funktionen angezeigt
     * 
     * @return auszuführende Funktion
     */
    private int einlesenFunktion() {
        System.out.print(
            ANLEGEN         + ": Anlegen; " +
            ZUGANG          + ": Zugang; " +
            ABGANG          + ": Abgang; " +
            SET_BEZEICHNUNG + ": Neue Bezeichnung; " +
            SET_BESTAND     + ": Neuer Bestand; " +
            ENDE            + ": Beenden -> ");

        return input.nextInt();
    }

    /**
     * Die ausgewählte Funktion wird mit dieser Methode ausgeführt
     * 
     * @param funktion
     */
    private void ausfuehrenFunktion(int funktion) {
        if(funktion == ANLEGEN) {
            artikel1 = anlegenArtikel();

        } else if(funktion == ZUGANG) {
            artikel1.bucheZugang(einlesenZugang());

        } else if(funktion == ABGANG) {
            artikel1.bucheAbgang(einlesenAbgang());

        } else if(funktion == SET_BEZEICHNUNG) {
            artikel1.setBezeichnung(einlesenBezeichnung());

        } else if(funktion == SET_BESTAND) {
            artikel1.setBestand(einlesenBestand());

        } else if(funktion == ENDE) {
            System.out.println("Programmende!");

        } else {
            System.out.println("Falsche Funktion!");
        }
    }

    /**
     * Methode um ein Artikel anzulegen.
     * 
     * @return angelegter Artikel
     */
    private Artikel anlegenArtikel() {
        int artikelNr;
        String bezeichnung;
        int bestand;

        System.out.println("ArtikelNr: ");
        artikelNr = input.nextInt();
        System.out.println("Bezeichnung: ");
        bezeichnung = input.next();
        System.out.println("Bestand: ");
        bestand = input.nextInt();

        return new Artikel(artikelNr, bezeichnung, bestand);
    }

    /**
     * Zugang einlesen und buchen
     * 
     * @return Ergebnis nach dem Zugang
     */
    private int einlesenZugang() {
        int zugang;

        if(artikel1 == null) {
            throw new RuntimeException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Zugang: ");
        zugang = input.nextInt();
        return zugang;
    }

    /**
     * Abgang einlesen und ausführen
     * 
     * @return Ergebnis nach dem Abgang
     */
    private int einlesenAbgang() {
        int abgang;

        if(artikel1 == null) {
            throw new RuntimeException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Abgang: ");
        abgang = input.nextInt();
        return abgang;
    }

    /**
     * Neue Bezeichnung für einen bereits angelegten Artikel anlegen
     * 
     * @return neue Bezeichnung des Artikels
     */
    private String einlesenBezeichnung() {
        String bezeichnung;

        if(artikel1 == null) {
            throw new RuntimeException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Neue Bezeichnung: ");
        bezeichnung = input.next();
        return bezeichnung;
    }

    /**
     * Neuen Bestand für ein bereits angelegten Artikel anlegen
     * 
     * @return neuer Bestand
     */
    private int einlesenBestand() {
        int bestand;

        if(artikel1 == null) {
            throw new RuntimeException(ARTIKEL_NICHT_VORHANDEN);
        }

        System.out.println("Neuer Bestand: ");
        bestand = input.nextInt();
        return bestand;
    }
}