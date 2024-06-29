package sistema.biblioteca.controlador;

import java.io.IOException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import sistema.biblioteca.App;
import javafx.fxml.FXML;
import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;
import sistema.biblioteca.controlador.projeto2ivis.model.BancoDAO;
import sistema.biblioteca.controlador.projeto2ivis.model.Bibliotecario;
import sistema.biblioteca.controlador.projeto2ivis.model.Livro;
import sistema.biblioteca.controlador.projeto2ivis.model.Usuario;

public class LoginController {

    @FXML
    private PasswordField psenha;

    @FXML
    private TextField tlogin;

    BancoDAO banco = BancoDAO.getInstance();

    @FXML
    private void trocarTela() throws IOException {

        for( Bibliotecario user : banco.getBibliotecarios()){
            if(tlogin.getText().equalsIgnoreCase(user.getLogin()) && psenha.getText().equalsIgnoreCase(user.getSenha())){
                App.trocarLayout("menu.fxml");
            }
         }
        }
}