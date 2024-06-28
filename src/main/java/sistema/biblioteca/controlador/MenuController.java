package sistema.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sistema.biblioteca.App;

import java.io.IOException;

public class MenuController {

    @FXML
    private void trocarTelaAdicionarUsuario() throws IOException {
            App.trocarLayout("selecionarUsuario.fxml");
    }

    @FXML
    private void trocarTelaAdicionarLivro() throws IOException {
        App.trocarLayout("cadastrar_livro.fxml");
    }

    @FXML
    private void trocarTelaAdicionarEmprestimo() throws IOException {
        App.trocarLayout("cadastrar_emprestimo.fxml");
    }

    @FXML
    private void trocarTelaDevolverEmprestimo() throws IOException {
        App.trocarLayout("devolver_emprestimo.fxml");
    }

    @FXML
    private void trocarTelaRemoverUsuario() throws IOException {
        App.trocarLayout("remover_usuario.fxml");
    }

    @FXML
    private void trocarTelaRemoverLivro() throws IOException {
        App.trocarLayout("remover_livro.fxml");
    }

    @FXML
    private void trocarTelaExibirUsuarios() throws IOException {
        App.trocarLayout("exibir_usuarios.fxml");
    }

    @FXML
    private void trocarTelaExibirLivros() throws IOException {
        App.trocarLayout("exibir_livros.fxml");
    }

    @FXML
    private void trocarTelaExibirEmprestimos() throws IOException {
        App.trocarLayout("exibir_emprestimos.fxml");
    }

    @FXML
    private void trocarTelaPesquisarLivros() throws IOException {
        App.trocarLayout("pesquisar_livro.fxml");
    }

    @FXML
    private void trocarTelaSituacaoUsuario() throws IOException {
        App.trocarLayout("situacao_usuario.fxml");
    }

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("exibir_emprestimos.fxml");
    }

    }
