package sistema.biblioteca.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;

import java.io.IOException;

public class RemoverUsuarioController {

    @FXML
    private TextField cpfTextField;

    @FXML
    private Button salvarButton;

    @FXML
    private Button menuButton;

    private BibliotecaController controleUsuarios;

    public RemoverUsuarioController() {
        controleUsuarios = new BibliotecaController();
    }

    @FXML
    private void initialize() {
        // Inicialização, se necessário
    }

    @FXML
    private void handleSalvarButtonAction(ActionEvent event) {
        String cpf = cpfTextField.getText();

        if (cpf.isEmpty()) {
            System.out.println("Por favor, preencha o campo CPF.");
            return;
        }

        boolean sucesso = controleUsuarios.removerUsuario(cpf); // Corrigido: Passando a String cpf

        if (sucesso) {
            System.out.println("Usuário removido com sucesso!");
            cpfTextField.clear();
        } else {
            System.out.println("Erro ao remover usuário. Verifique se o CPF está correto.");
        }
    }

    @FXML
    private void handleMenuButtonAction(ActionEvent event) throws IOException {
        App.trocarLayout("menu.fxml");
    }
}