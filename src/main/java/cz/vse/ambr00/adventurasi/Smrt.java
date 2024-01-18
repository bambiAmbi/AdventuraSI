package cz.vse.ambr00.adventurasi;

public class Smrt {
    stavHrace Stav;
    enum stavHrace {
        ZIVY,
        MRTVY
    }

    /**
     * @param Zivoty
     * @return
     */

    //Kontrola zda je hrac mrtvy
    public static stavHrace Smrt(float Zivoty) {
        if (Zivoty <= 0) {
            return Hra.Stav = stavHrace.MRTVY;

        }
        return Hra.Stav = stavHrace.ZIVY;
    }
}
