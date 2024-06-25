package sistema.biblioteca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;

import java.io.IOException;
import java.time.LocalDate;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void trocarLayout(String menu) throws IOException {
        Parent root = FXMLLoader.load(App.class.getResource(menu));
        scene.setRoot(root);
    }

    public static void main(String[] args) {
        BibliotecaController biblioteca = new BibliotecaController();
        biblioteca.carregarDadosBiblioteca();
        biblioteca.salvarEstadoBiblioteca();
        launch();
    }
}
