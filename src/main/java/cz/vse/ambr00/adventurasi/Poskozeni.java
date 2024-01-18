package cz.vse.ambr00.adventurasi;

public class Poskozeni {
    /**
     * @param Atribut
     * @param poskozeniZbrane
     * @return
     */
    public static float Poskozeni(int Atribut, int poskozeniZbrane) {
        return (float) ((Atribut * poskozeniZbrane) * 0.8);
    }

    /**
     * @param Poskozeni
     * @return
     */

    //Poskozeni
    public static float Poskozeni(int Poskozeni) {
        return (float) (Poskozeni * 1.2);
    }
}
