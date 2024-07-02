package sistema.biblioteca.controlador.view;

import sistema.biblioteca.controlador.controller.BibliotecaController;
import sistema.biblioteca.controlador.model.Livro;
import sistema.biblioteca.controlador.model.Usuario;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        BibliotecaController biblioteca = new BibliotecaController();

        biblioteca.reiniciarBancoDados();

        biblioteca.carregarDadosBiblioteca();
        //A partir dessa função é iniciado um bibliotecário com Login "admin" e senha "admin"

        // Inicializando 5 livros
        biblioteca.adicionarLivro("Extraordinário", "Raquel J. Palacio", " Literatura infantil", 2012, 4);
        biblioteca.adicionarLivro("Como falar mais alto?", "Karlos Macedo", "Fonoaudialogia", 2018, 2);
        biblioteca.adicionarLivro("Ele & eu", "Dário Cortez", "Romance", 2013, 3);
        biblioteca.adicionarLivro("Harry Potter", "J.K Rowling", "Fantasia", 1998, 2);
        biblioteca.adicionarLivro("Percy Jackson", "Rick Riordan", "Fantasia", 2005, 4);

        // Inicializando 5 usuários (estudantes, professores, bibliotecários)
        biblioteca.adicionarBibliotecario("BibliotecárioAdmin", "000.000.000-00", "B000", LocalDate.of(1970,8,1), "admin", "admin");
        biblioteca.adicionarEstudante("Bruna Caroliny", "111.111.111-11", "E001", LocalDate.of(1967,4,25), "Engenharia Elétrica");
        biblioteca.adicionarEstudante("Ivis", "222.222.222-22", "E002", LocalDate.of(1998, 2, 2), "Filosofia");
        biblioteca.adicionarEstudante("Jennifer Kelly", "333.333.333-33", "E003", LocalDate.of(1975, 5, 5), "Matemática");
        biblioteca.adicionarBibliotecario("Felipe Martins", "444.444.444-44", "B001",LocalDate.of(1989, 7, 5),"admin", "senha123");
        // Inicializando 5 empréstimos
        try {
            biblioteca.adicionarEmprestimoLivro("111.111.111-11","Harry Potter");
            biblioteca.adicionarEmprestimoLivro("111.111.111-11", "Extraordinário");
            biblioteca.adicionarEmprestimoLivro("222.222.222-22","Percy Jackson");
            biblioteca.adicionarEmprestimoLivro("333.333.333-33", "Como falar mais alto?");
            biblioteca.adicionarEmprestimoLivro("333.333.333-33","Ele & eu");
        } catch (Exception e) {
            System.out.println("Erro ao inicializar empréstimos: " + e.getMessage());
        }

        // Chamando a classe BibliotecaView para iniciar a interação com o usuário
        BibliotecaView.main(args);
    }
}
