package sistema.biblioteca.controlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sistema.biblioteca.controlador.projeto2ivis.controller.ControlleUsuarios;

import java.time.LocalDate;

public class CadastrarEstudanteController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cpfTextField;

    @FXML
    private TextField matriculaTextField;

    @FXML
    private DatePicker dataNascimentoDatePicker;

    @FXML
    private TextField cursoTextField;

    @FXML
    private Button cadastrarButton;

    private ControlleUsuarios controlleUsuarios;

    public CadastrarEstudanteController() {
        controlleUsuarios = new ControlleUsuarios();
    }

    @FXML
    private void initialize() {
        // Inicialização, se necessário
    }

    @FXML
    private void handleCadastrarButtonAction(ActionEvent event) {
        String nome = nomeTextField.getText();
        String cpf = cpfTextField.getText();
        String matricula = matriculaTextField.getText();
        LocalDate dataNascimento = dataNascimentoDatePicker.getValue();
        String curso = cursoTextField.getText();

        if (nome.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || dataNascimento == null || curso.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        boolean sucesso = controlleUsuarios.adicionarEstudante(nome, cpf, matricula, dataNascimento, curso);

        if (sucesso) {
            System.out.println("Estudante cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar estudante. Verifique se o CPF já está cadastrado.");
        }
    }
}

