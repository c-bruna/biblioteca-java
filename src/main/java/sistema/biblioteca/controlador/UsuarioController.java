package sistema.biblioteca.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.controller.BibliotecaController;

import java.io.IOException;
import java.time.LocalDate;

public class UsuarioController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cpfTextField;

    @FXML
    private TextField matriculaTextField;

    @FXML
    private DatePicker dataNascimentoDatePicker;

    @FXML
    private TextField cursoOUdeparmanetoTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField senhaTextField;

    @FXML
    private Label labelAvisoCamposVazios;

    @FXML
    private Label lbCadastroValido;

    @FXML
    private Label lbCadastroInvalido;


    private BibliotecaController controlleUsuarios;

    public UsuarioController() {
        controlleUsuarios = new BibliotecaController();
    }

    @FXML
    private void handleCadastrarButtonAction(ActionEvent event) {
        String nome = nomeTextField.getText();
        String cpf = cpfTextField.getText();
        String matricula = matriculaTextField.getText();
        LocalDate dataNascimento = dataNascimentoDatePicker.getValue();
        String curso = cursoOUdeparmanetoTextField.getText();

        if (nome.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || dataNascimento == null || curso.isEmpty()) {
            labelAvisoCamposVazios.setText("Por favor, preencha todos os campos.");
            return;
        }

        boolean sucesso = controlleUsuarios.adicionarEstudante(nome, cpf, matricula, dataNascimento, curso);

        if (sucesso) {
            lbCadastroValido.setText("Estudante cadastrado com sucesso!");
            limparCamposEstudanteProfessor();
            controlleUsuarios.salvarEstadoBiblioteca();
        } else {
            lbCadastroInvalido.setText("Erro ao cadastrar estudante. Verifique se o CPF já está cadastrado.");
        }
    }

    @FXML
    private void handleCadastrarProfessorButtonAction(ActionEvent event) {
        String nome = nomeTextField.getText();
        String cpf = cpfTextField.getText();
        String matricula = matriculaTextField.getText();
        LocalDate dataNascimento = dataNascimentoDatePicker.getValue();
        String departamento = cursoOUdeparmanetoTextField.getText(); // Campo 'cursoTextField' será utilizado para departamento dos professores

        if (nome.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || dataNascimento == null || departamento.isEmpty()) {
            labelAvisoCamposVazios.setText("Por favor, preencha todos os campos.");
            return;
        }

        boolean sucesso = controlleUsuarios.adicionarProfessor(nome, cpf, matricula, dataNascimento, departamento);

        if (sucesso) {
            lbCadastroValido.setText("Professor cadastrado com sucesso!");
            limparCamposEstudanteProfessor();
            controlleUsuarios.salvarEstadoBiblioteca();
        } else {
            lbCadastroInvalido.setText("Erro ao cadastrar professor. Verifique se o CPF já está cadastrado.");
        }
    }

    @FXML
    private void handleCadastrarBibliotecarioButtonAction(ActionEvent event) {
        String nome = nomeTextField.getText();
        String cpf = cpfTextField.getText();
        String matricula = matriculaTextField.getText();
        LocalDate dataNascimento = dataNascimentoDatePicker.getValue();
        String login = loginTextField.getText();
        String senha = senhaTextField.getText();


        if (nome.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || dataNascimento == null || login.isEmpty() || senha.isEmpty()) {
            labelAvisoCamposVazios.setText("Por favor, preencha todos os campos.");
            return;
        }

        boolean sucesso = controlleUsuarios.adicionarBibliotecario(nome, cpf, matricula, dataNascimento, login, senha);

        if (sucesso) {
            lbCadastroValido.setText("Bibliotecário cadastrado com sucesso!");
            limparCamposBibliotecario();
            controlleUsuarios.salvarEstadoBiblioteca();
        } else {
            lbCadastroInvalido.setText("Erro ao cadastrar bibliotecário. Verifique se o CPF já está cadastrado.");
        }
    }


    //SELECIONAR TIPO USUÁRIO
    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    @FXML
    private void trocarTelaCadastrarEstudante() throws IOException {
        App.trocarLayout("cadastrar_estudante.fxml");
    }

    @FXML
    private void trocarTelaCadastrarProfessor() throws IOException {
        App.trocarLayout("cadastrar_professor.fxml");
    }

    @FXML
    private void trocarTelaCadastrarBibliotecario() throws IOException {
        App.trocarLayout("cadastrar_bibliotecario.fxml");
    }

    private void limparCamposEstudanteProfessor() {
        nomeTextField.clear();
        cpfTextField.clear();
        matriculaTextField.clear();
        dataNascimentoDatePicker.setValue(null);
        cursoOUdeparmanetoTextField.clear();
    }

    private void limparCamposBibliotecario() {
        nomeTextField.clear();
        cpfTextField.clear();
        matriculaTextField.clear();
        dataNascimentoDatePicker.setValue(null);
        cursoOUdeparmanetoTextField.clear();
        loginTextField.clear();
        senhaTextField.clear();
    }



}
