package sistema.biblioteca.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.controller.BibliotecaController;
import sistema.biblioteca.controlador.model.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class SituacaoUsuarioController {

    @FXML
    private TextField cpfTextField;

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
    private Label lbUsuarioInvalido;

    @FXML
    BibliotecaController biblioteca = new BibliotecaController();

    @FXML
    private void initialize() {
        biblioteca.carregarDadosBiblioteca();
    }

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    @FXML
    private void pesquisarUsuarioPorCPF() {
        String cpf = cpfTextField.getText();
        Usuario user = biblioteca.buscarUsuarioPorCpf(cpf);
        if (user != null) {
            lbUsuarioInvalido.setText("");
            if (user instanceof Estudantes) {
                labelTipoUsuario.setText("Estudante");
            } else if (user instanceof Professores) {
                labelTipoUsuario.setText("Professor");
            } else if (user instanceof Bibliotecario) {
                labelTipoUsuario.setText("Bibliotecário");
            }
            labelUsuarioNome.setText(user.getNome());
            labelUsuarioCPF.setText(user.getCpf());
            labelUsuarioMatricula.setText(String.valueOf(user.getMatricula()));
            labelUsuarioDataNascimento.setText(user.getDadaNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            labelUsuarioQtdEmprestimos.setText(String.valueOf(user.getQtdEmprestimosAtivos()));
        } else {
            lbUsuarioInvalido.setText("Usuário não encontrado");
            labelTipoUsuario.setText("");
            labelUsuarioNome.setText("");
            labelUsuarioCPF.setText("");
            labelUsuarioMatricula.setText("");
            labelUsuarioDataNascimento.setText("");
            labelUsuarioQtdEmprestimos.setText("");
        }
    }
}
