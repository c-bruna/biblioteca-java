package sistema.biblioteca.controlador.projeto2ivis.view;

import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;
import java.time.LocalDate;
import sistema.biblioteca.controlador.projeto2ivis.model.*;

public class Main {

    public static void main(String[] args) {
        BibliotecaController biblioteca = new BibliotecaController();

        biblioteca.carregarDadosBiblioteca();
        biblioteca.inicializarBibliotecarioAdmin();
        //A partir dessa função é iniciado um bibliotecário com Login "admin" e senha "admin"

        // Inicializando 5 livros
        biblioteca.adicionarLivro("Extraordinário", "Raquel J. Palacio", " Literatura infantil", 2012, 4);
        biblioteca.adicionarLivro("Como falar mais alto?", "Karlos Macedo", "Fonoaudialogia", 2018, 2);
        biblioteca.adicionarLivro("Ele & eu", "Dário Cortez", "Romance", 2013, 3);
        biblioteca.adicionarLivro("Harry Potter", "J.K Rowling", "Fantasia", 1998, 2);
        biblioteca.adicionarLivro("Percy Jackson", "Rick Riordan", "Fantasia", 2005, 4);

        // Inicializando 5 usuários (estudantes, professores, bibliotecários)
        biblioteca.adicionarEstudante("Jennifer", "404.687.956-03", "E001", LocalDate.of(1967,4,25), "TI");
        biblioteca.adicionarEstudante("Ivis", "123.456.789-00", "E002", LocalDate.of(1998, 2, 2), "Filosofia");
        biblioteca.adicionarProfessor("Eiji Adachi", "000.222.444-66", "P001", LocalDate.of(1975, 5, 5), "Ti");
        biblioteca.adicionarProfessor("Isabel Dillman", "111.333.555-77", "P002", LocalDate.of(1980, 8, 8), "Educação");
        biblioteca.adicionarBibliotecario("Felipe Martins", "000.000.000-01", "B001", LocalDate.of(1995, 9, 9), "admin", "senha");

        // Inicializando 5 empréstimos
        try {
            Usuario user1 = biblioteca.buscarUsuarioPorCpf("123.456.789-00");
            Usuario user2 = biblioteca.buscarUsuarioPorCpf("000.000.000-01");
            Livro lv1 = biblioteca.buscarLivroPorTitulo("Como falar mais alto?");
            Livro lv2 = biblioteca.buscarLivroPorTitulo("Extraordinário");
            Livro lv3 = biblioteca.buscarLivroPorTitulo("Ele & eu");
            biblioteca.adicionarEmprestimoLivro(user1.getCpf(), lv1.getTitulo());
            biblioteca.adicionarEmprestimoLivro(user2.getCpf(), lv2.getTitulo());
            biblioteca.adicionarEmprestimoLivro(user1.getCpf(), lv3.getTitulo());
        } catch (Exception e) {
            System.out.println("Erro ao inicializar empréstimos: " + e.getMessage());
        }

        // Chamando a classe BibliotecaView para iniciar a interação com o usuário
        BibliotecaView.main(args);
    }
}
