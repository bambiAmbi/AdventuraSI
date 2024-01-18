package cz.vse.ambr00.adventurasi;

import java.util.Random;

public class MoznostZasahu {
    static int nahodneCislo;

    /**
     * @param Zivoty
     * @param celkoveZivoty
     * @return
     */
    //Moznost zasahu, je mozne nepritele netrefit. Sance se meni na zaklade toho jak moc jste zraneni
    public static boolean moznostZasahu(float Zivoty, float celkoveZivoty) {
        Random ran = new Random();
        nahodneCislo = ran.nextInt(100);
        if (Zivoty > celkoveZivoty / 2) {
            if (Math.floor(nahodneCislo) > 0 && Math.floor(nahodneCislo) < 75) {
                return true;
            }
        } else if (Zivoty < celkoveZivoty / 2) {
            if (Math.floor(nahodneCislo) > 0 && Math.floor(nahodneCislo) < 50) {
                return true;
            }
        }
        return false;
    }
}
