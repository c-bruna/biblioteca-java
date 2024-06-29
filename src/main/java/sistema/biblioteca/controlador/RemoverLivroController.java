package sistema.biblioteca.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;

import java.io.IOException;

public class RemoverLivroController {

    @FXML
    private TextField nomeLivroTextField;

    @FXML
    private Button salvarButton;

    @FXML
    private Button menuButton;

    private BibliotecaController controleLivros;

    public RemoverLivroController() {
        controleLivros = new BibliotecaController();
    }

    @FXML
    private void initialize() {
        // Inicialização, se necessário
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        String nomeLivro = nomeLivroTextField.getText();

        if (nomeLivro.isEmpty()) {
            System.out.println("Por favor, preencha o campo Nome do Livro.");
            return;
        }

        boolean sucesso = controleLivros.removerLivroPorTitulo(nomeLivro);

        if (sucesso) {
            System.out.println("Livro removido com sucesso!");
            nomeLivroTextField.clear();
        } else {
            System.out.println("Erro ao remover livro. Verifique se o nome do livro está correto.");
        }
    }

    @FXML
    private void handleMenuButtonAction(ActionEvent event) throws IOException {
        App.trocarLayout("menu.fxml");
    }
}