module com.oop.group20.group20_simulationofnewspaperonlineprinted {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.oop.group20.group20_simulationofnewspaperonlineprinted to javafx.fxml;
    exports com.oop.group20.group20_simulationofnewspaperonlineprinted;
}