package sistema.biblioteca.controlador;

import java.io.IOException;

import javafx.scene.control.*;
import sistema.biblioteca.App;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private PasswordField psenha;

    @FXML
    private TextField tlogin;


    @FXML
    private void trocarTela() throws IOException {
        if(tlogin.getText().equalsIgnoreCase("admin")  && psenha.getText().equalsIgnoreCase("admin")){
            App.trocarLayout("menu.fxml");
        }
    }


}