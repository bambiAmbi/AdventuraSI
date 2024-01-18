package cz.vse.ambr00.adventurasi;

public class Brneni extends Vybaveni{
    int ochrana;
    String typBrneni;

    /**
     * @param ochrana
     * @param typBrneni
     * @param vzacnost
     * @param nazev
     */
    public Brneni(int ochrana, String typBrneni, int vzacnost, String nazev) {
        this.ochrana = ochrana;
        this.typBrneni = typBrneni;
        this.vzacnost = vzacnost;
        this.nazev = nazev;
    }

    /**
     * @return
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * @return
     */
    public int getOchrana() {
        return ochrana;
    }

    /**
     * @return
     */
    public String getTypBrneni() {
        return typBrneni;
    }

    /**
     * @return
     */
    public int getVzacnost() {
        return vzacnost;
    }

    @Override
    public void Info() {
        System.out.println("Brnění: " + getNazev() + " | Ochrana: " + getOchrana() + " | Vzácnost: " + getVzacnost() + " | Typ: " + getTypBrneni());
    }
}

