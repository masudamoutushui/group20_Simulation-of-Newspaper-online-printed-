module com.oop.group20.group20_simulationofnewspaperonlineprinted {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.oop.group20.group20_simulationofnewspaperonlineprinted to javafx.fxml;
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted;

    // UPDATED: export and open Muaaz package to javafx.graphics and javafx.fxml
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz to javafx.graphics, javafx.fxml;
    opens com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz to javafx.graphics, javafx.fxml, javafx.base;

}
