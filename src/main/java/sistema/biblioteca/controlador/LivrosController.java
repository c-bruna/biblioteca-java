package sistema.biblioteca.controlador;

import javafx.fxml.FXML;
import sistema.biblioteca.App;

import java.io.IOException;

public class LivrosController {
    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }
}
