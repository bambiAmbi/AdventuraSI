package cz.vse.ambr00.adventurasi;

public class Nepritel {
    float zivoty;
    int poskozeni;
    int obrana;
    String typNepritele;
    int obtiznost;
    String jmeno;

    /**
     * @param zivoty
     * @param poskozeni
     * @param obrana
     * @param typNepritele
     * @param obtiznost
     * @param jmeno
     */
    public Nepritel(int zivoty, int poskozeni, int obrana, String typNepritele, int obtiznost, String jmeno) {
        this.zivoty = zivoty;
        this.poskozeni = poskozeni;
        this.obrana = obrana;
        this.typNepritele = typNepritele;
        this.obtiznost = obtiznost;
        this.jmeno = jmeno;
    }
}
