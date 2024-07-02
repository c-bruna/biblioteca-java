package sistema.biblioteca.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.controller.BibliotecaController;
import sistema.biblioteca.controlador.model.BancoDAO;
import sistema.biblioteca.controlador.model.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExibirLivros {

    @FXML
    private TableView <Livro> tableViewLivros;

    @FXML
    private TableColumn <Livro, String> tableColumnTituloLivro;

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
    private ObservableList<Livro> livrosData;

    @FXML
    BibliotecaController biblioteca = new BibliotecaController();

    @FXML
    private void initialize() {
        biblioteca.carregarDadosBiblioteca();
        tableColumnTituloLivro.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));

        carregarTableViewLivros();

        tableViewLivros.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuarios(newValue));
    }

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    @FXML
    public void carregarTableViewLivros() {
        List<Livro> listLivros = BancoDAO.getInstance().getLivros();
        livrosData = FXCollections.observableArrayList(listLivros);
        tableViewLivros.setItems(livrosData);
    }

    @FXML
    private void selecionarItemTableViewUsuarios(Livro lv) {
        if (lv != null) {
            labelLivroTitulo.setText(lv.getTitulo());
            labelLivroAutor.setText(lv.getAutor());
            labelLivroCategoria.setText(String.valueOf(lv.getAssunto()));
            labelLivroAno.setText(String.valueOf(lv.getAnoLancamento()));
            labelUsuarioQtdEmEstoque.setText(String.valueOf(lv.getQtdEstoque()));
        } else {
            labelLivroTitulo.setText("");
            labelLivroAutor.setText("");
            labelLivroCategoria.setText("");
            labelLivroAno.setText("");
            labelUsuarioQtdEmEstoque.setText("");
        }
    }
}
