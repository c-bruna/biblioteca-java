package sistema.biblioteca.controlador;

import javafx.fxml.FXML;
import sistema.biblioteca.App;

import java.io.IOException;

public class UsuarioController {

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    @FXML
    private void trocarTelaCadastrarEstudante() throws IOException {
        App.trocarLayout("cadastrar_estudante.fxml");
    }

    @FXML
    private void trocarTelaCadastrarProfessor() throws IOException {
        App.trocarLayout("cadastrar_professor.fxml");
    }

    @FXML
    private void trocarTelaCadastrarBibliotecario() throws IOException {
        App.trocarLayout("cadastrar_bibliotecario.fxml");
    }


}
