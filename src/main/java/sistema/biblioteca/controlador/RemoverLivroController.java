package sistema.biblioteca.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.controller.BibliotecaController;

import java.io.IOException;

public class RemoverLivroController {

    @FXML
    private Label lbAvisoCamposVazios;

    @FXML
    private Label lbRemoverValido;

    @FXML
    private Label lbRemoverInvalido;

    @FXML
    private TextField nomeLivroTextField;

    private BibliotecaController controleLivros;

    public RemoverLivroController() {
        controleLivros = new BibliotecaController();
    }


    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        String nomeLivro = nomeLivroTextField.getText();

        if (nomeLivro.isEmpty()) {
            lbAvisoCamposVazios.setText("Por favor, preencha o titulo do Livro.");
            return;
        }

        boolean sucesso = controleLivros.removerLivroPorTitulo(nomeLivro);

        if (sucesso) {
            lbRemoverValido.setText("Livro removido com sucesso!");
            nomeLivroTextField.clear();
        } else {
            lbRemoverInvalido.setText("Erro ao remover livro. Verifique se o nome do livro está correto.");
        }
    }

    @FXML
    private void handleMenuButtonAction(ActionEvent event) throws IOException {
        App.trocarLayout("menu.fxml");
    }
}