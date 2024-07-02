package sistema.biblioteca.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.controller.BibliotecaController;
import sistema.biblioteca.controlador.model.*;
import sistema.biblioteca.controlador.controller.ControlleLivros;
import sistema.biblioteca.controlador.controller.ControlleUsuarios;

import java.io.IOException;

public class DevolverEmprestimoController {

    @FXML
    private TextField cpfUsuarioTextField;

    @FXML
    private TextField tituloTextField;

    @FXML
    private Label lbAvisoCamposVazios;

    @FXML
    private Label lbDevolverValido;

    @FXML
    private Label lbDevolverInvalido;


    private BibliotecaController controleEmprestimos;

    public DevolverEmprestimoController() {
        controleEmprestimos = new BibliotecaController();
    }


    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        String cpfUsuario = cpfUsuarioTextField.getText();
        String titulo = tituloTextField.getText();

        if (cpfUsuario.isEmpty() || titulo.isEmpty()) {
            lbAvisoCamposVazios.setText("Por favor, preencha todos os campos.");
            return;
        }

        Usuario user = ControlleUsuarios.buscarUsuarioPorCpf(cpfUsuario);
        Livro lv = ControlleLivros.buscarLivroPorTitulo(titulo);

        boolean sucesso = controleEmprestimos.devolverEmprestimoLivro(user.getCpf(), lv.getTitulo());

        if (sucesso) {
            lbDevolverValido.setText("Empréstimo devolvido com sucesso!");
            cpfUsuarioTextField.clear();
            tituloTextField.clear();
        } else {
            lbDevolverInvalido.setText("Erro ao devolver empréstimo. Verifique se os dados estão corretos.");
        }
    }

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

}