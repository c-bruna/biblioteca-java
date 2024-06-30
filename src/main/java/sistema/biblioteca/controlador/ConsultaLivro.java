package sistema.biblioteca.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.projeto2ivis.model.*;
import sistema.biblioteca.controlador.projeto2ivis.controller.ControlleLivros;

import java.io.IOException;
import java.security.PrivateKey;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    @FXML
    private void PesquisarLivroPorTitulo() {
        String nomeLivro = tituloTextField.getText();
        Livro lv = ControlleLivros.buscarLivroPorTitulo(nomeLivro);

        if(lv != null) {
            labelLivroTitulo.setText(lv.getTitulo());
            labelLivroAutor.setText(lv.getAutor());
            labelLivroCategoria.setText(lv.getAssunto());
            labelLivroAno.setText(String.valueOf(lv.getAnoLancamento()));
            labelUsuarioQtdEmEstoque.setText(String.valueOf(lv.getQtdEstoque()));
        }else{
            labelLivroTitulo.setText("");
            labelLivroAutor.setText("");
            labelLivroCategoria.setText("");
            labelLivroAno.setText("");
            labelUsuarioQtdEmEstoque.setText("");
        }
    }
}
