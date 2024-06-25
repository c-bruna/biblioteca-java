package sistema.biblioteca.controlador.projeto2ivis.controller;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import sistema.biblioteca.controlador.projeto2ivis.model.*;

/**
 * Classe ControlleEmprestimos implementa a interface OperacoesEmprestimos para gerenciar as operações de empréstimos de livros na biblioteca.
 * Esta classe permite adicionar, devolver e listar empréstimos, além de buscar um empréstimo específico por usuário e livro.
 */
public class ControlleEmprestimos implements OperacoesEmprestimos {

    /**
     * Instância do BancoDAO usada para acessar os dados da biblioteca.
     */
    private static BancoDAO biblioteca;
    private static final BibliotecaController instancia_bibliotecaController = new BibliotecaController();
    /**
     * Construtor que obtém a instância única do BancoDAO.
     */
    public ControlleEmprestimos() { biblioteca = BancoDAO.getInstance(); }

    /**
     * Adiciona um novo empréstimo de livro para um usuário.
     *
     * @param cpfUsuario O usuário que está fazendo o empréstimo.
     * @param tituloLivro O livro que será emprestado.
     */
    @Override
    public boolean adicionarEmprestimoLivro(String cpfUsuario, String tituloLivro) {

        Usuario user = ControlleUsuarios.buscarUsuarioPorCpf(cpfUsuario);
        Livro lv = instancia_bibliotecaController.buscarLivroPorTitulo(tituloLivro);

        if(user == null){ throw new NoSuchElementException("Usuário nao cadastrado."); }

        if(lv == null){ throw new NoSuchElementException("Livro nao cadastrado."); }

        if (lv.getEstado() == EstadoLivro.emprestado) {
            throw new IllegalStateException("Livro não está disponível para empréstimo.");
        }

        int diasDeEmprestimo = 0;

        if( user instanceof Estudantes){
            if( user.getQtdEmprestimosAtivos() >= 3){
                System.out.println("Usuário já atingiu o limite de empréstimos.");
                return false;
            }
            diasDeEmprestimo = 15;
        }

        if (user instanceof Bibliotecario || user instanceof Professores){
            if(user.getQtdEmprestimosAtivos() >= 5){
                System.out.println("Usuário já atingiu o limite de empréstimos.");
                return false;
            }
            diasDeEmprestimo = 30;
        }

        Emprestimo emprestimoExistente = buscarEmprestimo(user.getCpf(), lv.getTitulo());

        if (emprestimoExistente != null) {
            System.out.println("Empréstimo já cadastrado.");
            return false;
        }

        Emprestimo novoEmprestimo = new Emprestimo(user, lv, LocalDate.now(), LocalDate.now().plusDays(diasDeEmprestimo));

        user.setQtdEmprestimosAtivos(user.getQtdEmprestimosAtivos() + 1);
        lv.setQtdEstoque(lv.getQtdEstoque() - 1);
        if(lv.getQtdEstoque() == 0){ lv.setEstado(EstadoLivro.emprestado); }
        biblioteca.getEmprestimos().add(novoEmprestimo);
        instancia_bibliotecaController.salvarEstadoBiblioteca();
        return true;
    }

    /**
     * Devolve um livro que estava emprestado.
     * @param cpfUsuario O usuário que está devolvendo o livro.
     * @param tituloLivro O livro que está sendo devolvido.
     */
    public void devolverEmprestimoLivro(String cpfUsuario, String tituloLivro) {
        //Verificando se o usuário e livro estão cadastrados
        Usuario user = ControlleUsuarios.buscarUsuarioPorCpf(cpfUsuario);
        Livro lv = instancia_bibliotecaController.buscarLivroPorTitulo(tituloLivro);

        if(user == null){
            throw new NoSuchElementException("Usuário nao cadastrado.");
        }

        if(lv == null){
            throw new NoSuchElementException("Livro nao cadastrado.");
        }

        Emprestimo emprestimo = ControlleEmprestimos.buscarEmprestimo(user.getCpf(), lv.getTitulo());

        if(emprestimo == null){
            throw new NoSuchElementException("Emprestimo nao foi encontrado.");
        }

        lv.setEstado(EstadoLivro.disponivel);

        biblioteca.getEmprestimos().remove(emprestimo);
        user.setQtdEmprestimosAtivos(user.getQtdEmprestimosAtivos() - 1 );
        lv.setQtdEstoque(lv.getQtdEstoque() + 1);
        instancia_bibliotecaController.salvarEstadoBiblioteca();
    }


    /**
     * Exibe a lista de empréstimos registrados na biblioteca.
     */
    @Override
    public void exibirListaEmprestimos() {

        if(biblioteca.getEmprestimos().isEmpty()){
            System.out.println("Nao ha emprestimos cadastrados");
        }
        else {
            System.out.println("\nLista de Empréstimos:");

            for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
                System.out.println("\n-------------------------------------");
                System.out.println("Usuário: " + emprestimo.getUsuario().getNome());
                System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
                System.out.println("Data do Empréstimo: " + emprestimo.getDataEmprestimo());
                System.out.println("Data de Devolução Prevista: " + emprestimo.getDataDevolucaoPrevista());
            }
        }
    }

    /**
     * Busca um empréstimo específico por usuário e livro.
     *
     * @param cpfUsuario O usuário envolvido no empréstimo.
     * @param tituloLivro O livro envolvido no empréstimo.
     * @return O empréstimo correspondente ou lança uma exceção se não encontrado.
     */
    public static Emprestimo buscarEmprestimo(String cpfUsuario, String tituloLivro) {
        for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
            if (emprestimo.getUsuario().getCpf().equals(cpfUsuario) && emprestimo.getLivro().getTitulo().equals(tituloLivro)) {
                return emprestimo;
            }
        }
        return null;
    }


}
