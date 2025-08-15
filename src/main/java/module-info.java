module com.oop.group20.group20_simulationofnewspaperonlineprinted {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.oop.group20.group20_simulationofnewspaperonlineprinted to javafx.fxml;
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted;

    exports com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz to javafx.graphics, javafx.fxml;
    opens com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz to javafx.graphics, javafx.fxml, javafx.base;

}
