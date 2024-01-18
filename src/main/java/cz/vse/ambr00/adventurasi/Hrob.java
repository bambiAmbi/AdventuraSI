package cz.vse.ambr00.adventurasi;

import java.time.LocalDate;

public class Hrob {
    //Datum pro hrob
    static LocalDate datum = LocalDate.now();
    static LocalDate posundatumu = datum.minusYears(1300);

    //Vypsani hrobu po umrti
    public static void Hrob() {
        System.out.println("░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
                "░░░░░░░░░███████████░░░░░░░░░\n" +
                "░░░░░░░███░░░░░░░░░███░░░░░░░\n" +
                "░░░░░░██░░░░░░░░░░░░░██░░░░░░\n" +
                "░░░░░░█░░░░░░░░░░░░░░░█░░░░░░\n" +
                "░░░░░██░░░░░░░░░░░░░░░██░░░░░\n" +
                "░░░░░█░░░░░Zemřel:░░░░░█░░░░░\n" +
                "░░░░░█░░░░" + posundatumu + "░░░█░░░░░\n" +
                "░░░░░█░░░░░░░░░░░░░░░░░█░░░░░\n" +
                "░░░░░███████████████████░░░░░\n" +
                "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n");
    }

}
