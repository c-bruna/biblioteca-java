package sistema.biblioteca.controlador;

import java.io.IOException;

import sistema.biblioteca.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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