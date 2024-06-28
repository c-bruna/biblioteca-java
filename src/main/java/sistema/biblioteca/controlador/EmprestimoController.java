package sistema.biblioteca.controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sistema.biblioteca.App;
import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;
import sistema.biblioteca.controlador.projeto2ivis.controller.ControlleUsuarios;
import sistema.biblioteca.controlador.projeto2ivis.controller.ControlleLivros;
import sistema.biblioteca.controlador.projeto2ivis.model.*;

import java.io.IOException;

public class EmprestimoController {

    @FXML
    private TextField tituloLivroTextField;

    @FXML
    private TextField cpfUsuarioTextField;

    private BibliotecaController controlleEmprestimo;



    public EmprestimoController() { controlleEmprestimo = new BibliotecaController(); }

    @FXML
    private void handleCadastrarButtonAction(ActionEvent event) {
        String tituloLivro = tituloLivroTextField.getText();
        String cpfUsuario = cpfUsuarioTextField.getText();

        if(tituloLivro.isEmpty() || cpfUsuario.isEmpty()){
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }

        Usuario user = ControlleUsuarios.buscarUsuarioPorCpf(cpfUsuario);
        Livro lv = ControlleLivros.buscarLivroPorTitulo(tituloLivro);

        boolean sucesso = controlleEmprestimo.adicionarEmprestimoLivro(user.getCpf(),lv.getTitulo());

        if (sucesso) {
            System.out.println("Empréstimo cadastrado com sucesso!");
            limparCampos();
            controlleEmprestimo.salvarEstadoBiblioteca();
        } else {
            System.out.println("Erro ao cadastrar empréstimo. Verifique se o livro e usuários estão cadastrados.");
        }

    }

    @FXML
    private void trocarTelaMenu() throws IOException {
        App.trocarLayout("menu.fxml");
    }

    private void limparCampos() {
        tituloLivroTextField.clear();
        cpfUsuarioTextField.clear();
    }
}
