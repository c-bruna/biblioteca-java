package sistema.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.projeto2ivis.model.*;
import sistema.biblioteca.controlador.projeto2ivis.controller.ControlleLivros;
import java.io.IOException;


public class ConsultaLivro {

    @FXML
    private TextField tituloTextField;

    @FXML
    private Label labelLivroTitulo;

    @FXML
    private Label labelLivroAutor;

    @FXML
    private Label labelLivroCategoria;

    @FXML
    private Label labelLivroAno;

    @FXML
    private Label labelUsuarioQtdEmEstoque;

    @FXML
    private Label lbLivroInvalido;

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    @FXML
    private void PesquisarLivroPorTitulo() {
        String nomeLivro = tituloTextField.getText();
        Livro lv = ControlleLivros.buscarLivroPorTitulo(nomeLivro);

        if(lv != null) {
            lbLivroInvalido.setText("");
            labelLivroTitulo.setText(lv.getTitulo());
            labelLivroAutor.setText(lv.getAutor());
            labelLivroCategoria.setText(lv.getAssunto());
            labelLivroAno.setText(String.valueOf(lv.getAnoLancamento()));
            labelUsuarioQtdEmEstoque.setText(String.valueOf(lv.getQtdEstoque()));
        }else{
            lbLivroInvalido.setText("Livro não encontrado");
            labelLivroTitulo.setText("");
            labelLivroAutor.setText("");
            labelLivroCategoria.setText("");
            labelLivroAno.setText("");
            labelUsuarioQtdEmEstoque.setText("");
        }
    }
}
