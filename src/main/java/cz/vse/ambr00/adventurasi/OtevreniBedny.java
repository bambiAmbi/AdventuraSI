package cz.vse.ambr00.adventurasi;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class OtevreniBedny {
    public static void otevreniBedny(HashMap zbrane, HashMap brneni, Zbran soucasnaZbran, Brneni soucasneBrneni, int ElixirS, int ElixirZ, int hranice, Scanner vyber) {
        //Chceme vybrat typ vybaveni, brneni nebo zbran, ze ktere nasledne budeme vybirat náhodne veci
        Random ran1 = new Random();
        Zbran zbran = null;
        Brneni zbroj = null;
        Zbran pouzivanaZbran = null;
        int typ = ran1.nextInt(2);
        int vybraneVybaveni = ran1.nextInt(hranice);

        //Vypsani noveho stavajiciho vybaveni pro porovnani, jak pro zbran tak pro brneni
        if (typ == 1) {
            zbran = (Zbran) zbrane.get(vybraneVybaveni);
            System.out.println("Našel jsi v truhle tuto zbraň: " + zbran.getNazev() + " | Poškození: " + zbran.getPoskozeni() + " | Vzácnost: " + zbran.getVzacnost() + " | Typ: " + zbran.getTypZbrane());
            System.out.println("Tvá stávající zbraň je tato:   " + soucasnaZbran.getNazev() + " | Poškození: " + soucasnaZbran.getPoskozeni() + " | Vzácnost: " + soucasnaZbran.getVzacnost() + " | Typ: " + soucasnaZbran.getTypZbrane());
            System.out.println("\nPřejete si zbraň vyměnit?");
            System.out.println("_____________________________________________");
            System.out.println("1. Ano\n" +
                    "2. Ne");

            if (vyber.nextInt() == 1) {
                pouzivanaZbran = zbran;
            }

        } else {
            zbroj = (Brneni) brneni.get(vybraneVybaveni);
            System.out.println("Našel jsi v truhle toto Brnění: " + zbroj.getNazev() + " | Poškození: " + zbroj.getOchrana() + " | Vzácnost: " + zbroj.getVzacnost() + " | Typ: " + zbroj.getTypBrneni());
            System.out.println("Tvé stávající brnění je toto:   " + soucasneBrneni.getNazev() + " | Poškození: " + soucasneBrneni.getOchrana() + " | Vzácnost: " + soucasneBrneni.getVzacnost() + " | Typ: " + soucasneBrneni.getTypBrneni());
            System.out.println("\nPřejete si brnění vyměnit?");
            System.out.println("_____________________________________________");
            System.out.println("1. Ano\n" +
                    "2. Ne");

            if (vyber.nextInt() == 1) {
                soucasneBrneni = zbroj;
            }
        }
    }
}
