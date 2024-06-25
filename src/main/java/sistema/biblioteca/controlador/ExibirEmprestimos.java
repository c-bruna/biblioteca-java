package sistema.biblioteca.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;
import sistema.biblioteca.controlador.projeto2ivis.model.BancoDAO;
import sistema.biblioteca.controlador.projeto2ivis.model.Emprestimo;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExibirEmprestimos {

    @FXML
    private TableView<Emprestimo> tableViewEmprestimos;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnEmprestimoUsuario;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnEmprestimoLivro;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnEmprestimoDataInicial;

    @FXML
    private TableColumn<Emprestimo, String> tableColumnEmprestimoDataPrevista;

    private ObservableList<Emprestimo> emprestimosData;

    BibliotecaController biblioteca = new BibliotecaController();

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    private void initialize() {
        biblioteca.carregarDadosBiblioteca();
        tableColumnEmprestimoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsuario().getNome()));
        tableColumnEmprestimoLivro.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLivro().getTitulo()));
        tableColumnEmprestimoDataInicial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataEmprestimo().format(dateFormatter)));
        tableColumnEmprestimoDataPrevista.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataDevolucaoPrevista().format(dateFormatter)));

        carregarTableViewLivros();
    }

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    public void carregarTableViewLivros() {
        List<Emprestimo> listEmprestimos = BancoDAO.getInstance().getEmprestimos();
        emprestimosData = FXCollections.observableArrayList(listEmprestimos);
        tableViewEmprestimos.setItems(emprestimosData);
    }
}
