module com.oop.group20.group20_simulationofnewspaperonlineprinted {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    // --- Add access for your main package ---
    opens com.oop.group20.group20_simulationofnewspaperonlineprinted to javafx.fxml;
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted;

    // --- Keep access for the 'jerin' package ---
    opens com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin to javafx.fxml;
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted.jerin;

    // --- Add access for the 'Muaaz' package ---
    opens com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz to javafx.fxml;
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz;

    opens com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho to javafx.fxml,java.base;
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted.shoscho;

    // --- ✅ Fix: allow FXMLLoader to access ReporterController ---
    opens controller to javafx.fxml;
    exports controller;
}
