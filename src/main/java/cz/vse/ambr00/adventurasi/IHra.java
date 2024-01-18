package cz.vse.ambr00.adventurasi;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public interface IHra {

   public Souboj aktualniSouboj(TextField textField, TextArea textArea) throws InterruptedException;

 /*public Souboj aktualniSouboj() throws InterruptedException;*/


   public String vyberPostavy(String vstup);

    public String zpracujPrikaz(String radek);

    public String vratUvitani();

    public String castPribehu(String cast);

    public String dalsiDialog(int vstup) throws InterruptedException;

    public String zivotyHrace();

    public String pocetElixiruZivota();

    public String pocetElixiruSily();

    public String infoBrneniOchrana();

    public String infoBrneniNazev();

    public String infoBrneniVzacnost();

    public String infoZbranNazev();

    public String infoZbranPoskozeni();

    public String infoZbranVzacnost();

    public boolean probihaSouboj();


}
