module chromatynk.chromatynk_g6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens chromatynk.chromatynk_g6 to javafx.fxml;
    exports chromatynk.chromatynk_g6;
}