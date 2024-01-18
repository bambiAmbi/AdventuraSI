package cz.vse.ambr00.adventurasi.main;

import cz.vse.ambr00.adventurasi.Hra;
import cz.vse.ambr00.adventurasi.Nepritel;
import cz.vse.ambr00.adventurasi.Souboj;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SoubojController {

    @FXML
    Label nepritel1;

    @FXML
    Label nepritel2;

    @FXML
    Label zivotyNepritel1;

    @FXML
    Label zivotyLabel;

    @FXML
    Label info;

    @FXML
    Label nepritelZivoty2;

    @FXML
    Button zautocit;

    @FXML
    Button zautocit2;

    public SoubojController() throws InterruptedException {
    }


    @FXML
    private void initialize() throws InterruptedException {
        //nepritel1.setText(souboj.nazevNepritele1());
    }

    @FXML
    private Hra hra = HomeController.hra;


    public void zautocit(ActionEvent actionEvent) throws InterruptedException {
        Souboj.zautocNaNepritele(zivotyNepritel1, info, zautocit, zautocit2);
        Souboj.zautoceniNaHrace(zivotyLabel, info);
        Souboj.konecSouboje(zautocit, zautocit2);
    }

    public void zautocit2(ActionEvent actionEvent) throws InterruptedException {
        Souboj.zautocNaNepritele2(nepritelZivoty2, info, zautocit2, zautocit);
        Souboj.zautoceniNaHrace(zivotyLabel, info);
        Souboj.konecSouboje(zautocit, zautocit2);
    }

    public void pouzitiElixiruZdravi() {
        Souboj.pouzitiElixiruZdravi(zivotyLabel, info);
    }

    public void pouzitiElixiruSily() {
        Souboj.pouzitiElixiruSily(info);
    }
}




