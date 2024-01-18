package cz.vse.ambr00.adventurasi;

public abstract class Vybaveni {
    int vzacnost;
    String nazev;

    public int getVzacnost() {
        return vzacnost;
    }

    public String getNazev() {
        return nazev;
    }

    public void setVzacnost(int vzacnost) {
        this.vzacnost = vzacnost;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void Info() {
        System.out.println("Název: " + nazev + " | Vzácnost: " + vzacnost);
    }
}
