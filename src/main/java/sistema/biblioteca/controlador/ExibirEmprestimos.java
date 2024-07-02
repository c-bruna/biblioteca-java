package sistema.biblioteca.controlador;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.controller.BibliotecaController;
import sistema.biblioteca.controlador.model.BancoDAO;
import sistema.biblioteca.controlador.model.Emprestimo;

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

    @FXML
    private ObservableList<Emprestimo> emprestimosData;

    @FXML
    BibliotecaController biblioteca = new BibliotecaController();

    @FXML
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

    @FXML
    public void carregarTableViewLivros() {
        List<Emprestimo> listEmprestimos = BancoDAO.getInstance().getEmprestimos();
        emprestimosData = FXCollections.observableArrayList(listEmprestimos);
        tableViewEmprestimos.setItems(emprestimosData);
    }
}
