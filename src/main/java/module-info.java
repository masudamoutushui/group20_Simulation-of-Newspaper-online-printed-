module com.oop.group20.group20_simulationofnewspaperonlineprinted {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.oop.group20.group20_simulationofnewspaperonlineprinted to javafx.fxml;
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted;
    opens  com.oop.group20.group20_simulationofnewspaperonlineprinted.Muaaz to java.base,javafx.fxml;
}