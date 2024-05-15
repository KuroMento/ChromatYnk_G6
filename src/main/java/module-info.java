module chromatynk.chromatynk_g6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.antlr.antlr4.runtime;


    opens chromatynk.chromatynk_g6 to javafx.fxml;
    exports chromatynk.chromatynk_g6;
}