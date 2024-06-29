package sistema.biblioteca.controlador.projeto2ivis.controller;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import sistema.biblioteca.controlador.projeto2ivis.model.*;

/**
 * Classe ControlleUsuarios responsável por gerenciar operações relacionadas aos usuários da biblioteca,
 * incluindo estudantes, professores e bibliotecários.
 * Esta classe implementa a interface OperacoesUsuarios, definindo métodos para adicionar novos usuários,
 * buscar um usuário pelo CPF, exibir lista de usuários, verificar situação de um usuário pelo CPF,
 * exibir empréstimos de um usuário pelo CPF, inicializar um bibliotecário administrativo se necessário,
 * e verificar o login e senha de um bibliotecário.
 */
public class ControlleUsuarios implements OperacoesUsuarios{

    /**
     * Instância única do banco de dados utilizado para armazenar informações sobre os usuários.
     */
    private static BancoDAO biblioteca;
    private static final BibliotecaController instancia_bibliotecaController = new BibliotecaController();

    /**
     * Construtor padrão que inicializa a instância do banco de dados.
     */
    public ControlleUsuarios() { biblioteca = BancoDAO.getInstance(); }


    /**
     * Adiciona um novo professor ao banco de dados.
     * @param nome Nome completo do professor.
     * @param cpf CPF do professor.
     * @param matricula Matrícula do professor.
     * @param dadaNascimento Data de nascimento do professor.
     * @param departamento Departamento do professor.
     */
    @Override
    public boolean adicionarProfessor(String nome, String cpf, String matricula, LocalDate dadaNascimento, String departamento) {

        if (buscarUsuarioPorCpf(cpf) != null) {
            System.out.println("Já há um usuario com este cpf cadastrado.");
            return false;
        }

        Professores professor = new Professores(nome, cpf, matricula, dadaNascimento, 0, departamento);
        biblioteca.getUsuarios().add(professor);
        ControlleSerializacao.salvarUsuarios();
        return true;
    }

    /**
     * Adiciona um novo estudante ao banco de dados.
     * @param nome Nome completo do estudante.
     * @param cpf CPF do estudante.
     * @param matricula Matrícula do estudante.
     * @param dadaNascimento Data de nascimento do estudante.
     * @param curso Curso de estudo do estudante.
     */
    @Override
    public boolean adicionarEstudante(String nome, String cpf, String matricula, LocalDate dadaNascimento, String curso) {

        if (buscarUsuarioPorCpf(cpf) != null) {
            System.out.println("Já há um usuario com este cpf cadastrado.");
            return false;
        }

        Estudantes estudante = new Estudantes(nome, cpf, matricula, dadaNascimento, 0, curso);
        biblioteca.getUsuarios().add(estudante);
        ControlleSerializacao.salvarUsuarios();
        return true;
    }

    /**
     * Adiciona um novo bibliotecário ao banco de dados.
     * @param nome Nome completo do bibliotecário.
     * @param cpf CPF do bibliotecário.
     * @param matricula Matrícula do bibliotecário.
     * @param dadaNascimento Data de nascimento do bibliotecário.
     * @param login Login do bibliotecário.
     * @param senha Senha do bibliotecário.
     */
    @Override
    public boolean adicionarBibliotecario(String nome, String cpf, String matricula, LocalDate dadaNascimento, String login, String senha) {

        if (buscarUsuarioPorCpf(cpf) != null) {
            System.out.println("Já há um usuario com este cpf cadastrado.");
            return false;
        }

        Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, matricula, dadaNascimento, 0, login, senha);
        biblioteca.getUsuarios().add(bibliotecario);
        ControlleSerializacao.salvarUsuarios();
        return true;
    }

    /**
     * Remove um usuário da biblioteca pelo CPF.
     * Se o usuário possui empréstimos ativos, todos os empréstimos serão devolvidos antes da remoção do usuário.
     *
     * @param cpfUsuario O CPF do usuário a ser removido.
     * @return
     */
    @Override
    public boolean removerUsuario(String cpfUsuario) {
        Usuario user = ControlleUsuarios.buscarUsuarioPorCpf(cpfUsuario);
        if(user == null){ throw new NoSuchElementException("Usuário nao cadastrado."); }

        if(user.getQtdEmprestimosAtivos() > 0){
            System.out.println("Não é possível remover usuário, pois possui empréstimos ativos");
            return false;
        }

        biblioteca.getUsuarios().remove(user);
        ControlleSerializacao.salvarUsuarios();
        return true;
    }

    /**
     * Busca um usuário pelo seu CPF.
     * @param cpf CPF do usuário.
     * @return O usuário correspondente ao CPF fornecido ou null se não encontrado.
     */
    public static Usuario buscarUsuarioPorCpf(String cpf) {
        for (Usuario user : biblioteca.getUsuarios() ) {
            if (user.getCpf().equals(cpf)) {
                return user;
            }
        }
        return null;
    }


    /**
     * Exibe a lista de usuários cadastrados na biblioteca.
     */
    @Override
    public void exibirListaUsuarios() {

        if(biblioteca.getUsuarios().isEmpty()){
            System.out.println("Nao ha usuarios cadastrados");
        }
        else {
            System.out.println("\nLista de Usuários:");
            for (Usuario user : biblioteca.getUsuarios()) {
                System.out.println("\n-------------------------------------");
                if (user instanceof Estudantes) {
                    System.out.println("Estudante:");
                }
                if (user instanceof Professores) {
                    System.out.println("Professor:");
                }
                if (user instanceof Bibliotecario) {
                    System.out.println("Bibliotecário:");
                }
                user.exibirDetalhesUsuario();
            }
        }

    }

    /**
     * Verifica a situação de um usuário pelo seu CPF.
     * @param cpf CPF do usuário.
     * @return Detalhes do usuário
     */
    @Override
    public void exibeDetalhesUsuarioPorCPF(String cpf) {
        try{
            for(Usuario user: biblioteca.getUsuarios()){
                if(user.getCpf().equals(cpf)){
                    user.exibirDetalhesUsuario();
                }
            }
        } catch (Exception e){
            throw new NoSuchElementException("o usuário de CPF " + cpf + " nao foi encontrado");
        }
    }

    /**
     * Exibe os empréstimos de um usuário pelo seu CPF.
     * @param cpf CPF do usuário.
     */
    @Override
    public void exibirEmprestimosUsuarioPorCPF(String cpf) {
        Usuario user = buscarUsuarioPorCpf(cpf);
        if (user == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("Empréstimos do usuário " + user.getNome() + " (CPF: " + cpf + "):");

        if(user.getQtdEmprestimosAtivos() == 0){
            System.out.println("Este usuário nao possui emprestimos ativos");
        }

        for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
            if (emprestimo.getUsuario().equals(user)) {
                System.out.println("\n########################################");
                System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
                System.out.println("Data do Empréstimo: " + emprestimo.getDataEmprestimo());
                System.out.println("Data de Devolução Prevista: " + emprestimo.getDataDevolucaoPrevista());
            }
        }

    }

    /**
     * Inicializa um bibliotecário administrativo caso ainda não exista.
     */
    public void inicializarBibliotecarioAdmin() {
        String loginAdmin = "admin";
        String senhaAdmin = "admin";
        boolean bibliotecarioExistente = false;

        for (Usuario usuario : biblioteca.getUsuarios()) {
            if (usuario instanceof Bibliotecario) {
                bibliotecarioExistente = true;
                break;
            }
        }

        if (!bibliotecarioExistente) {
            Bibliotecario bibliotecario = new Bibliotecario("Administrador", "000.000.000-00","0000", LocalDate.of(2000,1,1),0, loginAdmin, senhaAdmin);
            biblioteca.getUsuarios().add(bibliotecario);
            ControlleSerializacao.salvarUsuarios();
        }
    }

    /**
     * Verifica se um login corresponde ao de um bibliotecário.
     * @param Login Login do bibliotecário.
     * @return Verdadeiro se o login existe, falso caso contrário.
     */
    @Override
    public boolean verificarBibliotecarioLogin(String Login) {
        for(Usuario user: biblioteca.getUsuarios()){
            if( user instanceof Bibliotecario){
                if(((Bibliotecario) user).getLogin().equals(Login)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Verifica se uma senha corresponde à de um bibliotecário.
     * @param Senha Senha do bibliotecário.
     * @return Verdadeiro se a senha existe, falso caso contrário.
     */
    @Override
    public boolean verificarBibliotecarioSenha(String Senha) {
        for(Usuario user: biblioteca.getUsuarios()){
            if( user instanceof Bibliotecario){
                if(((Bibliotecario) user).getSenha().equals(Senha)){
                    return true;
                }
            }
        }
        return false;
    }

}
