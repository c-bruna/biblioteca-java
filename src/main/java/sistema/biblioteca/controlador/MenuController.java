package sistema.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sistema.biblioteca.App;

import java.io.IOException;

public class MenuController {

    @FXML
    private void trocarTelaAdicionarUsuario() throws IOException {
            App.trocarLayout("SelecionarTipoUsuario.fxml");
    }

    private void trocarTelaAdicionarLivro() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    private void trocarTelaAdicionarEmprestimo() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    private void trocarTelaRemoverUsuario() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    private void trocarTelaRemoverLivro() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    private void trocarTelaExibirUsuarios() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    private void trocarTelaExibirLivros() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    private void trocarTelaExibirEmprestimos() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    }
