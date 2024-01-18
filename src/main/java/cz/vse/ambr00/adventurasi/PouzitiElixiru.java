package cz.vse.ambr00.adventurasi;

public class PouzitiElixiru {
    //Pouziti elixiru zdravi
    public static float pouzitiElixiruZdravi(float Zivoty, float maxZivoty) {
        if (Hra.elixirZdravi.regeneraceZivotu + Zivoty >= maxZivoty) {
            return Zivoty = maxZivoty;
        }
        return Zivoty = Zivoty + Hra.elixirZdravi.regeneraceZivotu;
    }

    //Pouziti elixiru sily
    public static int pouzitiElixiruSily(int Atribut, int maxAtribut) {
        if (Hra.elixirSily.zvyseniSily + Atribut >= maxAtribut) {
            return maxAtribut;
        } else {
            return Atribut += Hra.elixirSily.zvyseniSily;
        }
    }
}
