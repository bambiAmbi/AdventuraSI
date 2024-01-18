package cz.vse.ambr00.adventurasi.main;

import cz.vse.ambr00.adventurasi.Hra;
import cz.vse.ambr00.adventurasi.Souboj;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HomeController {


    public ImageView helma;
    public ImageView brneni;
    public ImageView boty;
    public int cisloDialogu;
    public int aktualniCisloDialogu;
    public ImageView zbran;
    public String prikaz;


    @FXML
    private TextArea vystup;

    @FXML
    private TextField vstup;


    @FXML
    private Label zivoty;

    @FXML
    private Label elixiryZivota;

    @FXML
    private Label elixirySily;


    public HomeController() throws InterruptedException {
    }


    @FXML
    private void initialize() {
        vystup.appendText(hra.vratUvitani());

    }

    @FXML
    private void zpracujVstup() throws InterruptedException {
        prikaz = vstup.getText();
        vstup.clear();
        vystup.appendText("> " + prikaz + "\n");
        if(cisloDialogu == 0) {
            hra.zpracujPrikaz(prikaz);
            vystup.appendText(hra.vyberPostavy(prikaz));
            zivoty.setText(hra.zivotyHrace());
            elixirySily.setText(hra.pocetElixiruSily());
            elixiryZivota.setText(hra.pocetElixiruZivota());
            Tooltip.install(helma, new Tooltip("Jmeno: " + hra.infoBrneniNazev() + "\n Brneni: " + hra.infoBrneniOchrana() + " \n Vzacnost: " + hra.infoBrneniVzacnost()));
            Tooltip.install(brneni, new Tooltip("Jmeno: " + hra.infoBrneniNazev() + "\n Brneni: " + hra.infoBrneniOchrana() + " \n Vzacnost: " + hra.infoBrneniVzacnost()));
            Tooltip.install(boty, new Tooltip("Jmeno: " + hra.infoBrneniNazev() + "\n Brneni: " + hra.infoBrneniOchrana() + " \n Vzacnost: " + hra.infoBrneniVzacnost()));
            Tooltip.install(zbran, new Tooltip("Jmeno: " + hra.infoZbranNazev() + "\n Poskozeni: " + hra.infoZbranPoskozeni() + " \n Vzacnost: " + hra.infoZbranVzacnost()));
            cisloDialogu++;
        }


        vystup.appendText(hra.dalsiDialog(cisloDialogu));
        if (cisloDialogu == 1 || cisloDialogu == 3 || cisloDialogu == 5 || cisloDialogu == 12 || cisloDialogu == 19 || cisloDialogu == 21 || cisloDialogu == 23 || cisloDialogu == 25 || cisloDialogu == 27 || cisloDialogu == 29 || cisloDialogu == 31 || cisloDialogu == 33 || cisloDialogu == 38 || cisloDialogu == 40) {
            SoubojTed();
        }
        if (cisloDialogu == 4) {
            cisloDialogu = 6;
        } else if (cisloDialogu == 9) {
            cisloDialogu = 11;
        } else if (cisloDialogu == 36) {
            cisloDialogu = 38;
        } else {
            cisloDialogu++;
        }

    }


    @FXML
    public static Hra hra;

    static {
        try {
            hra = new Hra();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void SoubojTed() throws InterruptedException {
        vystup.appendText("Souboj spusten");
        Souboj souboj = hra.aktualniSouboj(vstup, vystup);


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("souboj.fxml"));
            loader.load();
            SoubojController soubojController = loader.getController();
            soubojController.nepritel1.setText(souboj.nazevNepritele1());
            soubojController.zivotyNepritel1.setText(Float.toString(souboj.zivotyNepritele1()));
            soubojController.zivotyLabel.setText(hra.zivotyHrace());
            soubojController.zautocit2.setDisable(souboj.jedenNepritel());
            if(!(souboj.jedenNepritel())) {
                soubojController.nepritel2.setText(souboj.nazevNepritele2());
                soubojController.nepritelZivoty2.setText(Float.toString(souboj.zivotyNepritele2()));
            }


            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("souboj.fxml"));
            fxmlLoader.load();
            Scene scene = new Scene(loader.getRoot());
            stage.setScene(scene);
            stage.setTitle("Souboj");
            stage.show();
            stage.setOnHiding(windowEvent -> {
                vystup.appendText("Stiskni Enter pro pokracovani");

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void vypisNapovedu(ActionEvent actionEvent) {
        vystup.appendText("Hra funguje na jednoduchém způsobu.\n" +
                " Uživatel zapne hru a je přivítán úvodním textem.\n" +
                " Následně má na výběr ze čtyř ras. Vybrat může pomocí čísel.\n" +
                " Každá rasa re reprezentovaná jedním číslem.\n" +
                " Během hry se musí hráč rozhodnout z více možností.\n" +
                " Opět vybírá z více možností. Během souboje opět vybírá z více možností.\n" +
                " Také má možnost buď zaútočit, kouknout se co má v inventáři,\n" +
                " což mu ukáže počet elixíru, brnění a zbraň a\n" +
                " jako poslední se vypíše kolik je nyní hráč schopen \n" +
                "udělit poškození nepřáteli. Takto probíhá celá hra.\n" +
                " Hra končí poražením finální postavy, což je král lupičů.");
    }

    public void novaHra(ActionEvent actionEvent) {
        cisloDialogu = 0;
        vystup.appendText("Hra je nyní restartována.\n" +
                "Stiskni enter pro pokracovani\n\n");

        vystup.appendText(hra.vratUvitani());

    }
}
