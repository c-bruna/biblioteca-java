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
    private TableView<Emprestimo> tableViewEmprestimos;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnEmprestimoUsuario;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnEmprestimoLivro;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnDevolucao;

    @FXML
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private TextField PesquisarTituloLivro;

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
    private ObservableList<Emprestimo> livroEmprestimosData;

    @FXML
    private void handlePesquisarButtonAction(ActionEvent event) {
        String nomeLivro = PesquisarTituloLivro.getText();

        if (nomeLivro.isEmpty()) {
            System.out.println("Por favor, preencha o campo Nome do Livro.");
            return;
        }

        Livro lv = ControlleLivros.buscarLivroPorTitulo(nomeLivro);

        if (lv == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        List<Emprestimo> emprestimosLivro = ControlleLivros.buscarEmprestimospPorLivro(nomeLivro);
        livroEmprestimosData = FXCollections.observableArrayList(emprestimosLivro);
        tableViewEmprestimos.setItems(livroEmprestimosData);

        tableColumnEmprestimoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario().getNome()));
        tableColumnEmprestimoLivro.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLivro().getTitulo()));
        tableColumnDevolucao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataDevolucaoPrevista().format(dateFormatter)));

        labelLivroTitulo.setText(lv.getTitulo());
        labelLivroAutor.setText(lv.getAutor());
        labelLivroCategoria.setText(lv.getAssunto());
        labelLivroAno.setText(String.valueOf(lv.getAnoLancamento()));
        labelUsuarioQtdEmEstoque.setText(String.valueOf(lv.getQtdEstoque()));
    }


    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }


}
