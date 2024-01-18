module cz.vse.ambr00.adventurasi {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.ambr00.adventurasi to javafx.fxml;
    exports cz.vse.ambr00.adventurasi;
    exports cz.vse.ambr00.adventurasi.main;
    opens cz.vse.ambr00.adventurasi.main to javafx.fxml;
}