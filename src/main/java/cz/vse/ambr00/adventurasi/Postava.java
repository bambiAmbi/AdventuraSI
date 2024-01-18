package cz.vse.ambr00.adventurasi;

public class Postava {
    int sila;
    int inteligence;
    int vydrz;
    int houzevnatost;
    int zivoty;

    /**
     * @param sila
     * @param inteligence
     * @param vydrz
     * @param houzevnatost
     */
    public Postava(int sila, int inteligence, int vydrz, int houzevnatost) {
        this.sila = sila;
        this.inteligence = inteligence;
        this.vydrz = vydrz;
        this.houzevnatost = houzevnatost;
        this.zivoty = vydrz * 3;
    }
}
