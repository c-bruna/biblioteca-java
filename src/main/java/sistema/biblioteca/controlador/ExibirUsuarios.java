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

public class ExibirUsuarios {

    @FXML
    private TableView<Usuario> tableViewUsuarios;

    @FXML
    private TableColumn<Usuario, String> tableColumnUsuarioNome;

    @FXML
    private TableColumn<Usuario, String> tableColumnUsuarioCPF;

    @FXML
    private Label labelTipoUsuario;

    @FXML
    private Label labelUsuarioNome;

    @FXML
    private Label labelUsuarioCPF;

    @FXML
    private Label labelUsuarioMatricula;

    @FXML
    private Label labelUsuarioDataNascimento;

    @FXML
    private Label labelUsuarioQtdEmprestimos;

    @FXML
    private Label labelCursoDepartamentoLogin;

    @FXML
    private Label labelCDouL;

    @FXML
    private ObservableList<Usuario> usuarioData;

    @FXML
    BibliotecaController biblioteca = new BibliotecaController();

    @FXML
    private void initialize() {
        biblioteca.carregarDadosBiblioteca();
        tableColumnUsuarioNome.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        tableColumnUsuarioCPF.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCpf()));

        carregarTableViewUsuarios();

        tableViewUsuarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTableViewUsuarios(newValue));
    }

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    @FXML
    public void carregarTableViewUsuarios() {
        List<Usuario> listPessoas = BancoDAO.getInstance().getUsuarios();
        usuarioData = FXCollections.observableArrayList(listPessoas);
        tableViewUsuarios.setItems(usuarioData);
    }

    @FXML
    private void selecionarItemTableViewUsuarios(Usuario user) {
        if (user != null) {
            if(user instanceof Estudantes){
                labelTipoUsuario.setText("Estudante");
                labelCursoDepartamentoLogin.setText("Curso");
                labelCDouL.setText(((Estudantes) user).getCurso());
            }else if(user instanceof Professores){
                labelTipoUsuario.setText("Professor");
                labelCursoDepartamentoLogin.setText("Departamento");
                labelCDouL.setText(((Professores) user).getDepartamento());
            }else if(user instanceof Bibliotecario){
                labelTipoUsuario.setText("Bibliotecário");
                labelCursoDepartamentoLogin.setText("Login");
                labelCDouL.setText(((Bibliotecario) user).getLogin());
            }
            labelUsuarioNome.setText(user.getNome());
            labelUsuarioCPF.setText(user.getCpf());
            labelUsuarioMatricula.setText(String.valueOf(user.getMatricula()));
            labelUsuarioDataNascimento.setText(user.getDadaNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            labelUsuarioQtdEmprestimos.setText(String.valueOf(user.getQtdEmprestimosAtivos()));
        } else {
            labelUsuarioNome.setText("");
            labelUsuarioCPF.setText("");
            labelUsuarioMatricula.setText("");
            labelUsuarioDataNascimento.setText("");
            labelUsuarioQtdEmprestimos.setText("");
        }
    }
}
