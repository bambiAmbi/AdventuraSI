package cz.vse.ambr00.adventurasi;

public class Zbran extends Vybaveni implements poskozeniSeZbrani {
    int poskozeni;
    String typZbrane;

    /**
     * @param poskozeni
     * @param typZbrane
     * @param vzacnost
     * @param nazev
     */
    public Zbran(int poskozeni, String typZbrane, int vzacnost, String nazev) {
        this.poskozeni = poskozeni;
        this.typZbrane = typZbrane;
        this.vzacnost = vzacnost;
        this.nazev = nazev;
    }

    public Object clone() throws CloneNotSupportedException{
        return (Zbran)super.clone();
    }

    /**
     * @return
     */
    public int getPoskozeni() {
        return poskozeni;
    }

    /**
     * @return
     */
    public String getTypZbrane() {
        return typZbrane;
    }

    /**
     * @return
     */
    public int getVzacnost() {
        return vzacnost;
    }

    /**
     * @return
     */
    public String getNazev() {
        return nazev;
    }

    @Override
    public void Info() {
        System.out.println("Zbraň: " + getNazev() + " | Poškození: " + getPoskozeni() + " | Vzácnost: " + getVzacnost() + " | Typ: " + getTypZbrane());

    }


    @Override
    public void poskozeniSeZbrani() {
        System.out.println("Nyní můžeš udělit takové poškození: ");
    }
}
