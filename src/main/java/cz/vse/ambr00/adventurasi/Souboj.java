package cz.vse.ambr00.adventurasi;

import cz.vse.ambr00.adventurasi.main.HomeController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


import static cz.vse.ambr00.adventurasi.Hra.*;



public class Souboj {

    public Souboj(Nepritel nepritel1, Nepritel nepritel2, Zbran pouzivanaZbran, Brneni pouzivaneBrneni, TextField textField, TextArea textArea, int hlavniAtribut, int zivoty, int maximalniZivoty, int pocetElixiruZdravi, int pocetElixiruSily, Elixir elixirZdravi, Elixir elixirSily) {
    }
    static Nepritel aktualniNepritel;
    static Nepritel aktualniNepritel2;
    static Zbran aktualniZbran;
    static int aktualniAtribut;
    static float aktualniZivotyNepritele;
    static float aktualniZivotyNepritele2;
    static float aktualniMaxZivotyNepritele;
    static float aktualniMaxZivotyNepritele2;
    static int pocetNepratel;
    static int smrtPostavy = 0;
    static String vstup;
    static float aktualniZivoty;
    static float aktualniMaxZivoty;
    static int aktualniMaxAtribut;
    static int aktualniElixiryZdravi;
    static int aktualniElixirySily;



    public static boolean probihaSouboj(){
        if(pocetNepratel == 0 || smrtPostavy == 1) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean jedenNepritel() {
        if(aktualniNepritel2 == null) {
            return true;
        } else {
            return false;
        }

    }


    public static void zautoceniNaHrace(Label zivotyHrace, Label info) {
        Nepritel nepritel = aktualniNepritel;
        Nepritel nepritel2 = aktualniNepritel2;
        float zivoty = aktualniZivoty;
        float zivotyNepritele = aktualniZivotyNepritele;
        float zivotyNepritele2;
        float poskozeni = Poskozeni.Poskozeni(nepritel.poskozeni);
        float poskozeni2;

        if(nepritel2 == null) {
            zivotyNepritele2 = 0;
            poskozeni2 = 0;
        } else {
            zivotyNepritele2 = aktualniZivotyNepritele2;
            poskozeni2 = Poskozeni.Poskozeni(nepritel2.poskozeni);

        }

        if(zivotyNepritele >= 0){

        boolean zasahHrace = MoznostZasahu.moznostZasahu(zivotyNepritele, aktualniMaxZivotyNepritele);
        info.setText("Nyní je na řadě nepřítel");

        if(zasahHrace) {
            zivoty = zivoty - poskozeni;
            aktualniZivoty = zivoty;


            zivotyHrace.setText(Float.toString(zivoty));
            info.setText("Nepritel tě zasáhl za: " + poskozeni);
            if(zivoty <=0) {
                info.setText("Jsi mrtvý!");
                Stage stage = (Stage) zivotyHrace.getScene().getWindow();
                stage.close();
            }
        } else {
            info.setText("Nepřítel tě netrefil");
        }
        }

        if(zivotyNepritele2 >= 0){

            boolean zasahHrace2 = MoznostZasahu.moznostZasahu(zivotyNepritele2, aktualniMaxZivotyNepritele2);
            info.setText("Nyní je na řadě nepřítel");

            if(zasahHrace2) {
                zivoty = aktualniZivoty;
                zivoty = zivoty - poskozeni2;
                aktualniZivoty = zivoty;


                zivotyHrace.setText(Float.toString(zivoty));
                info.setText("Nepritel tě zasáhl za: " + poskozeni2);
                if(zivoty <=0) {
                    info.setText("Jsi mrtvý!");
                    Stage stage = (Stage) zivotyHrace.getScene().getWindow();
                    stage.close();
                    System.exit(0);
                }
            } else {
                info.setText("Nepřítel tě netrefil");
            }
        }
    }

    public static void konecSouboje(Button button1, Button button2) throws InterruptedException {
        if(button1.isDisabled() && button2.isDisabled()){
            Stage stage = (Stage) button1.getScene().getWindow();
            stage.close();
            HomeController.hra.dalsiDialog(5);
        }
    }

    public static void zautocNaNepritele(Label zivotyNepritele, Label info, Button zautoc, Button zautoc2) {
        float zivoty = aktualniZivotyNepritele;
        float poskozeni = Poskozeni.Poskozeni(aktualniAtribut, aktualniZbran.poskozeni);

        boolean zasahHrace = MoznostZasahu.moznostZasahu(zivoty, aktualniMaxZivoty);
        if(zasahHrace) {
            zivoty = (zivoty - poskozeni) / aktualniNepritel.obrana;
            aktualniZivotyNepritele = zivoty;
            info.setText("Zasáhl jsi nepřítele za: " + poskozeni);
            if (zivoty > 0) {
                zivotyNepritele.setText(Float.toString(zivoty));
            } else {
                info.setText("Zabil si nepřítele, dobrá práce!");
                zivotyNepritele.setText("Nepritel Zabit");
                zautoc.setDisable(true);
            }
        } else {
            info.setText("Nezasáhl jsi nepřítele");
        }
    }

    public static void zautocNaNepritele2(Label zivotyNepritele, Label info, Button zautoc, Button zautoc2) throws InterruptedException {
        float zivoty = 0;
        float poskozeni = 0;
        boolean zasahHrace = false;

        if(!(jedenNepritel())) {
            zivoty = aktualniNepritel2.zivoty;
            poskozeni = Poskozeni.Poskozeni(aktualniAtribut, aktualniZbran.poskozeni);
            zasahHrace = MoznostZasahu.moznostZasahu(zivoty, aktualniMaxZivoty);
        }

        if(zasahHrace) {
            zivoty = (zivoty - poskozeni) / aktualniNepritel.obrana;
            aktualniZivotyNepritele2 = zivoty;
            info.setText("Zasáhl jsi nepřítele za: " + poskozeni);
            if (zivoty > 0) {
                zivotyNepritele.setText(Float.toString(zivoty));
            } else {
                info.setText("Zabil si nepřítele, dobrá práce!");
                zivotyNepritele.setText("Nepritel Zabit");
                zautoc.setDisable(true);
            }
        } else {
            info.setText("Nezasáhl jsi nepřítele");
        }
    }


    public static String nazevNepritele1() {
        return aktualniNepritel.jmeno;
    }

    public static float zivotyNepritele1() { return aktualniNepritel.zivoty;}

    public static String nazevNepritele2() {
        return aktualniNepritel2.jmeno;
    }

    public static float zivotyNepritele2(){
        return aktualniNepritel2.zivoty;
    }

    public static void pouzitiElixiruZdravi(Label label, Label info) {
        if(aktualniElixiryZdravi == 0) {

            info.setText("Nemáš žádné elixíry zdraví");

        } else {
            aktualniZivoty = PouzitiElixiru.pouzitiElixiruZdravi(aktualniZivoty, aktualniMaxZivoty);
            label.setText(Float.toString(aktualniZivoty));
            aktualniElixiryZdravi--;
            pocetElixiruZdravi--;
        }

    }

    public static void pouzitiElixiruSily(Label info) {
        if(aktualniElixirySily == 0) {
            info.setText("Nemáš žádné elixíry síly");
        } else {
            aktualniAtribut = PouzitiElixiru.pouzitiElixiruSily(aktualniAtribut, 20);
        }
    }


    public static Souboj Souboj(Nepritel nepritel, Nepritel nepritel2, Zbran zbran, Brneni brneni, TextField vyber, TextArea textArea, final int Atribut, final float Zivoty, int maxZivoty, int pocetElixiruZ, int pocetElixiruS, Elixir ElixirZ, Elixir ElixirS) throws InterruptedException {
        aktualniNepritel = nepritel;
        aktualniNepritel2 = nepritel2;
        aktualniAtribut = Atribut;
        aktualniZbran = zbran;
        aktualniZivotyNepritele = nepritel.zivoty;
        aktualniZivoty = Zivoty;
        aktualniMaxZivotyNepritele = nepritel.zivoty;
        aktualniMaxZivoty = Zivoty;
        aktualniElixiryZdravi = pocetElixiruZ;
        aktualniElixirySily = pocetElixiruS;
        float maxZivotyNepratele = nepritel.zivoty;
        float zivotyNepritele = nepritel.zivoty;
        float maxZivotyNepritele2 = 0;
        float zivotyNepritele2 = 0;

        if(nepritel2 == null) {
            aktualniMaxZivotyNepritele2 = 0;
            aktualniZivotyNepritele2 = 0;

        } else {
            aktualniMaxZivotyNepritele2 = nepritel2.zivoty;
            aktualniZivotyNepritele2 = nepritel2.zivoty;
        }



        //Jeden nebo dva nepratele
        if (nepritel != null && nepritel2 != null) {
            pocetNepratel = 2;
            maxZivotyNepritele2 = nepritel2.zivoty;
            zivotyNepritele2 = nepritel2.zivoty;
        } else {
            pocetNepratel = 1;
        }
        //System.out.println("Nachazíš se v souboji, nyní máš na výběr co uděláš:");
        /*textArea.appendText("Nachazíš se v souboji, nyní máš na výběr co uděláš:");
        textArea.appendText("Nyní máš: " + Zivoty + " životů");
        textArea.appendText("\n1. Zasáhnout nepřítele.\n" +
                "2. Podívat se do inventáře a vybavení.\n" +
                "3. Použít elixír.\n");
        textArea.appendText("_____________________________________________");*/


        //cyklus dokavad neni nepritel mrtvy


        float finalZivotyNepritele = zivotyNepritele2;
        float finalMaxZivotyNepritele = maxZivotyNepritele2;

        /*vyber.setOnAction(actionEvent ->{
            TextField tempUserInput = (TextField) actionEvent.getSource();


            float aktualniZivoty;
            int aktualniAtribut;
            float aktualniZivotyNepritele;
            float aktualniZivotyNepritele2;

            aktualniZivoty = Zivoty;
            aktualniAtribut = Atribut;
            aktualniZivotyNepritele = zivotyNepritele;
            aktualniZivotyNepritele2 = finalZivotyNepritele;
            aktualniNepritel = nepritel;

        while (pocetNepratel != 0 && smrtPostavy == 0) {



                try {
                    textArea.appendText("Nyní máš: " + aktualniZivoty + " životů");
                    textArea.appendText("\n1. Zasáhnout nepřítele.\n" +
                            "2. Podívat se do inventáře a vybavení.\n" +
                            "3. Použít elixír.\n");
                    textArea.appendText("\n_____________________________________________");

                    switch (tempUserInput.getText()) {
                        case "1":
                            boolean zasah = MoznostZasahu.moznostZasahu(aktualniZivoty, maxZivoty);
                            if (pocetNepratel == 1) {
                                if (zasah) {
                                    float poskozeni = Poskozeni.Poskozeni(aktualniAtribut, zbran.poskozeni);
                                    aktualniZivotyNepritele = (aktualniZivotyNepritele - poskozeni) / nepritel.obrana;
                                    textArea.appendText("Trefil si nepritele za:" + poskozeni);
                                    if (aktualniZivotyNepritele <= 0) {
                                        textArea.appendText("Nepritel zabit, dobra práce!");
                                        textArea.appendText("_____________________________________________");
                                        pocetNepratel--;
                                        break;
                                    }
                                } else {
                                    textArea.appendText("Netrefil jsi nepřítele!");
                                    textArea.appendText("_____________________________________________");

                                }
                                textArea.appendText("Nyni je na rade nepritel");
                                TimeUnit.SECONDS.sleep(3);
                                boolean zasahHrace = MoznostZasahu.moznostZasahu(aktualniZivotyNepritele, maxZivotyNepratele);
                                if (zasahHrace) {
                                    float zasahNepritele = Poskozeni.Poskozeni(nepritel.poskozeni);
                                    aktualniZivoty = aktualniZivoty - zasahNepritele;
                                    textArea.appendText("Nepratel te zasahl za:" + zasahNepritele);
                                    textArea.appendText("_____________________________________________");
                                    Smrt.Smrt(aktualniZivoty);
                                    if (Stav == Smrt.stavHrace.MRTVY) {
                                        textArea.appendText("Byl si zabit. Tvá cesta zde konci. Můžeš to zkusit znova.");
                                        textArea.appendText("_____________________________________________");
                                        smrtPostavy++;
                                        //System.out.println(smrtPostavy);
                                        System.exit(0);
                                        break;

                                    }

                                } else {
                                    textArea.appendText("Nepritel te netrefil!");
                                    textArea.appendText("_____________________________________________");

                                }
                                //Dva nepratele, moznost vybrat na ktereho chceme zautocit
                            } else if (pocetNepratel == 2) {
                                textArea.appendText("Nepříel 1: " + nepritel.jmeno + " | Poškození: " + nepritel.poskozeni + " | Životy: " + nepritel.zivoty + " | Obtížnost: " + nepritel.obtiznost);
                                textArea.appendText("Nepříel 2: " + nepritel2.jmeno + " | Poškození: " + nepritel2.poskozeni + " | Životy: " + nepritel2.zivoty + " | Obtížnost: " + nepritel2.obtiznost);
                                textArea.appendText("Kterého nepřítele chceš trefit:\n" +
                                        "1. Nepřítel 1\n" +
                                        "2. Nepřítel 2");
                                textArea.appendText("_____________________________________________");


                                switch (vyber.getText()) {
                                    case "1":
                                        if (zasah) {
                                            float poskozeni = Poskozeni.Poskozeni(aktualniAtribut, zbran.poskozeni);
                                            aktualniZivotyNepritele = (aktualniZivotyNepritele - poskozeni) / nepritel.obrana;
                                            textArea.appendText("Trefil si nepritele za:" + poskozeni);
                                            if (aktualniZivotyNepritele <= 0) {
                                                textArea.appendText("Nepritel 1 zabit, dobra práce!");
                                                textArea.appendText("_____________________________________________");
                                                pocetNepratel--;
                                                break;
                                            }
                                        } else {
                                            textArea.appendText("Netrefil jsi nepřítele!");
                                            textArea.appendText("_____________________________________________");
                                        }
                                        break;

                                    case "2":
                                        if (zasah) {
                                            float poskozeni = Poskozeni.Poskozeni(aktualniAtribut, zbran.poskozeni);
                                            aktualniZivotyNepritele2 = (aktualniZivotyNepritele2 - poskozeni) / nepritel2.obrana;
                                            textArea.appendText("Trefil si nepritele za:" + poskozeni);
                                            if (aktualniZivotyNepritele2 <= 0) {
                                                textArea.appendText("Nepritel zabit, dobra práce!");
                                                textArea.appendText("_____________________________________________");
                                                pocetNepratel--;
                                                break;
                                            }
                                        } else {
                                            textArea.appendText("Netrefil jsi nepřítele!");
                                            textArea.appendText("_____________________________________________");

                                        }
                                        break;
                                }

                                textArea.appendText("Nyni je řada na neprátelích");
                                TimeUnit.SECONDS.sleep(3);
                                boolean zasahHrace = MoznostZasahu.moznostZasahu(aktualniZivotyNepritele, maxZivotyNepratele);
                                if (zasahHrace && aktualniZivotyNepritele > 0) {
                                    float zasahNepritele = Poskozeni.Poskozeni(nepritel.poskozeni);
                                    aktualniZivoty = aktualniZivoty - zasahNepritele;
                                    textArea.appendText("Nepratel te zasahl za:" + zasahNepritele);
                                    textArea.appendText("_____________________________________________");
                                    Smrt.Smrt(aktualniZivoty);
                                    if (Stav == Smrt.stavHrace.MRTVY) {
                                        textArea.appendText("Byl si zabit. Tvá cesta zde konci. Můžeš to zkusit znova.");
                                        textArea.appendText("_____________________________________________");
                                        smrtPostavy++;
                                        //System.out.println(smrtPostavy);
                                        System.exit(0);
                                        break;

                                    }

                                } else {
                                    textArea.appendText("Nepritel te netrefil!");
                                    textArea.appendText("_____________________________________________");
                                }

                                boolean zasahHrace2 = MoznostZasahu.moznostZasahu(aktualniZivotyNepritele2, finalMaxZivotyNepritele);
                                if (zasahHrace2 && aktualniZivotyNepritele2 > 0) {
                                    float zasahNepritele = Poskozeni.Poskozeni(nepritel2.poskozeni);
                                    aktualniZivoty = aktualniZivoty - zasahNepritele;
                                    textArea.appendText("Nepratel te zasahl za:" + zasahNepritele);
                                    textArea.appendText("_____________________________________________");
                                    Smrt.Smrt(Zivoty);
                                    if (Stav == Smrt.stavHrace.MRTVY) {
                                        textArea.appendText("Byl si zabit. Tvá cesta zde konci. Můžeš to zkusit znova.");
                                        textArea.appendText("_____________________________________________");
                                        smrtPostavy++;
                                        Hrob.Hrob();
                                        System.exit(0);
                                        break;

                                    }

                                } else {
                                    textArea.appendText("Nepritel te netrefil!");
                                    textArea.appendText("_____________________________________________");

                                }
                            }

                            break;

                        //Inventar
                        case "2":
                            float poskozeni = Poskozeni.Poskozeni(aktualniAtribut, zbran.poskozeni);
                            textArea.appendText("Ve tvém inventáři se nachází tyto věci: \n");
                            //pouzivanaZbran.Info();
                            // pouzivaneBrneni.Info();
                            textArea.appendText("Počet elixirů zdraví: " + pocetElixiruZ + " | Počet elixírů síly: " + pocetElixiruS + "\n");
                            //System.out.println();
                            //pouzivanaZbran.poskozeniSeZbrani();
                            //System.out.println(poskozeni);
                            textArea.appendText("_____________________________________________\n");
                            break;

                        //Pouziti elixiru
                        case "3":

                            textArea.appendText("Jaký elixír si přejete použít:\n" +
                                    "1. Elixír zdraví\n" +
                                    "2. Elixír síly");

                            switch (vyber.getText()) {
                                case "1":
                                    if (pocetElixiruZ == 0) {
                                        textArea.appendText("Nemáš žádné elixíry zdravi!");
                                        textArea.appendText("_____________________________________________");
                                    } else {
                                        aktualniZivoty = PouzitiElixiru.pouzitiElixiruZdravi(aktualniZivoty, maxZivoty);
                                    }
                                    break;

                                case "2":
                                    if (pocetElixiruS == 0) {
                                        textArea.appendText("Nemáš zádné elixíry síly!");
                                        textArea.appendText("_____________________________________________");
                                    } else {

                                        aktualniAtribut += PouzitiElixiru.pouzitiElixiruSily(aktualniAtribut, 20);

                                    }

                                    break;
                            }
                            break;

                        default:
                            textArea.appendText("Špatně zadané číslo, možné pouze od 1 do 3");
                            continue;
                    }

                } catch (InputMismatchException | InterruptedException e) {
                    textArea.appendText("CHYBA");
                    vyber.getText();
                    continue;
                }


            }
        });*/
        return null;

    }

}
