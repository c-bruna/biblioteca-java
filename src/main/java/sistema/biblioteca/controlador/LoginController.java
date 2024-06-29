package sistema.biblioteca.controlador;

import java.io.IOException;
import javafx.scene.control.*;
import sistema.biblioteca.App;
import javafx.fxml.FXML;
import sistema.biblioteca.controlador.projeto2ivis.model.BancoDAO;
import sistema.biblioteca.controlador.projeto2ivis.model.Bibliotecario;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    private PasswordField psenha;

    @FXML
    private TextField tlogin;

    @FXML
    private Label labelCredencial;

    @FXML
    private Label nada;

    BancoDAO banco = BancoDAO.getInstance();

    @FXML
    private void trocarTela() throws IOException {
        boolean credenciaisValidas = false;

        for (Bibliotecario user : banco.getBibliotecarios()) {
            if (tlogin.getText().equalsIgnoreCase(user.getLogin()) && psenha.getText().equalsIgnoreCase(user.getSenha())) {
                credenciaisValidas = true;
                break;
            }
        }

        if (credenciaisValidas) {
            App.trocarLayout("menu.fxml");
        } else {
            labelCredencial.setText("Credenciais inválidas.");
        }
    }

}