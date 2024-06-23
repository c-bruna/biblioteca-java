module sistema.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;

    opens sistema.biblioteca to javafx.fxml;
    exports sistema.biblioteca;

    opens sistema.biblioteca.controlador to javafx.fxml;
    exports sistema.biblioteca.controlador;
}
