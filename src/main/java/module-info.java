module chromatynk.chromatynk_g6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires java.desktop;
    requires javafx.swing;
    requires org.antlr.antlr4.runtime;


    opens chromatynk.chromatynk_g6 to javafx.fxml;
    exports chromatynk.chromatynk_g6;
    exports chromatynk.chromatynk_g6.controller;
    exports chromatynk.chromatynk_g6.interpreter;
    opens chromatynk.chromatynk_g6.controller to javafx.fxml;
    opens chromatynk.chromatynk_g6.interpreter to javafx.fxml;
}