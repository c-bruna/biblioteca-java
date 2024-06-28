package sistema.biblioteca.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;
import java.io.IOException;
import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;
import sistema.biblioteca.controlador.projeto2ivis.model.BancoDAO;
import sistema.biblioteca.controlador.projeto2ivis.model.*;

public class LivrosController {

    @FXML
    private TextField tituloTextField;

    @FXML
    private TextField autorTextField;

    @FXML
    private TextField assuntoTextField;

    @FXML
    private TextField anoLancamentoTextField;

    @FXML
    private TextField estoqueTextField;

    private BibliotecaController controlleLivros;

    public LivrosController() { controlleLivros = new BibliotecaController(); }

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    @FXML
    private void handleCadastrarButtonAction(ActionEvent event) {

        String titulo = tituloTextField.getText();
        String autor = autorTextField.getText();
        String assunto = assuntoTextField.getText();
        String anoLancamento = anoLancamentoTextField.getText();
        String estoque = estoqueTextField.getText();

        if (titulo.isEmpty() || autor.isEmpty() || assunto.isEmpty() || anoLancamento.isEmpty() || estoque.isEmpty() ) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        int anoLancamentoInt = Integer.parseInt(anoLancamento);
        int estoqueInt = Integer.parseInt(estoque);

        boolean sucesso = controlleLivros.adicionarLivro(titulo, autor, assunto, anoLancamentoInt, estoqueInt);

        if (sucesso) {
            System.out.println("Livro cadastrado com sucesso!");
            limparCampos();
            controlleLivros.salvarEstadoBiblioteca();
        } else {
            System.out.println("Erro ao cadastrar livro. Verifique se o livro já está cadastrado.");
        }
    }

    private void limparCampos() {
        tituloTextField.clear();
        autorTextField.clear();
        assuntoTextField.clear();
        anoLancamentoTextField.clear();
        estoqueTextField.clear();
    }


}
