/**
 * Klasse "Artikel" für den Zweck der einfachen Bestandsführung
 *
 * @author Mert & Daniel
 * @version 1.0
 */
public class Artikel {
    /**
     * Klassenkonstanten
     */
    private static final String ARTIKELNUMMER =
        "Artikelnummer ist ungültig, da die Eingabe nicht 4-stellig und positiv ist!";

    private static final String ARTIKELBEZEICHNUNG =
        "Artikelbezeichnung ist ungültig, da die Eingabe leer ist!";

    private static final String ARTIKELBESTAND =
        "Artikelbestand ist ungültig, da die Eingabe < 0 ist!";

    private static final String ZUGANG_NEGATIV =
        "Die eingegebene Menge ist ungültig, da sie <= 0 ist!";
        
    private static final String ABGANG_NEGATIV =
        "Die eingegebene Menge ist ungültig, da sie <= 0 ist!";

    private static final String ABGANG_ZU_HOCH =
        "Der Abgang ist größer als die Größe des Bestandes!";
        
    private static final int MINDESTBESTAND = 0;
    
    private static final int KLEINSTE_ARTIKELNR = 1000;
    
    private static final int GRÖSSTE_ARTIKELNR = 9999;

    /**
     * Attribute
     */
    private int     artikelNr;
    private String  bezeichnung;
    private int     bestand;

    /**
     * Erster Konstruktor mit 3 Parametern
     * 
     * @param artikelNr ; darf nicht < 1000 || > 9999 sein
     * @param bezeichnung ; darf nicht leer sein
     * @param bestand ; muss >= 0 sein
     */
    public Artikel(int artikelNr, String bezeichnung, int bestand) {
        if(artikelNr < KLEINSTE_ARTIKELNR || artikelNr > GRÖSSTE_ARTIKELNR) {
            throw new IllegalArgumentException(ARTIKELNUMMER);
        }

        if(bezeichnung == null || bezeichnung.trim().isEmpty()) {
            throw new IllegalArgumentException(ARTIKELBEZEICHNUNG);
        }

        if(bestand < MINDESTBESTAND) {
            throw new IllegalArgumentException(ARTIKELBESTAND);
        }

        this.artikelNr 	    = artikelNr;
        this.bezeichnung    = bezeichnung.trim();
        this.bestand 	    = bestand;
    }

    /**
     * Zweiter Konstruktor mit 2 Parametern
     * 
     * @param artikelNr ; darf nicht < 1000 || > 9999 sein
     * @param bezeichnung ; darf nicht leer sein
     */
    public Artikel(int artikelNr, String bezeichnung) {
        this(artikelNr, bezeichnung, MINDESTBESTAND);
    }

    /**
     * Gibt die Artikelnummer zurück
     * 
     * @return Artikelnummer
     */
    public int getArtikelNr() {
        return artikelNr;
    }

    /**
     * Gibt die Artikelbezeichnung zurück
     * 
     * @return Artikelbezeichnung
     */
    public String getBezeichnung() {
        return bezeichnung;
    }

    /**
     * Gibt den Artikelbestand zurück
     * 
     * @return Artikelbestand
     */
    public int getBestand() {
        return bestand;
    }

    /**
     * Legt die Artikelbezeichnung fest
     * 
     * @param bezeichnung
     */
    public void setBezeichnung(String bezeichnung) {
        if(bezeichnung == null || bezeichnung.trim().isEmpty()) {
            throw new IllegalArgumentException(ARTIKELBEZEICHNUNG);
        }

        this.bezeichnung = bezeichnung;
    }
    
    /**
     * Legt den Artikelbestand fest
     * 
     * @param bestand
     */
    public void setBestand(int bestand) {
        if(bestand < MINDESTBESTAND) {
            throw new IllegalArgumentException(ARTIKELBESTAND);
        }
    }
    
    /**
     * Methode bucheZugang: bucht den Zugang und erhöht den Bestand
     * 
     * @param menge ; darf nicht <= 0 sein
     */
    public void bucheZugang(int zugang) {
        if(zugang <= 0) {
            throw new IllegalArgumentException(ZUGANG_NEGATIV);
        }

        bestand += zugang;
    }

    /**
     * Methode buche Abgang: bucht den Abgang und verringert den Bestand max. bis 0
     * 
     * @param menge ; darf nicht <= 0 oder größer als der Bestand sein
     */
    public void bucheAbgang(int abgang) {
        if(abgang <= 0) {
            throw new IllegalArgumentException(ABGANG_NEGATIV);
        }

        if(bestand - abgang < MINDESTBESTAND) {
            throw new IllegalArgumentException(ABGANG_ZU_HOCH);
        }

        bestand -= abgang;
    }

    /**
     * toString Methode, um ein Artikel-Objekt als Zeichenkette aufbereiten zu können
     */
    public String toString() {
        return("Artikel: " + artikelNr +
            " Bezeichnung: " + bezeichnung +
            " Bestand: " + bestand);
    }
}