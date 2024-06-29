package sistema.biblioteca.controlador.projeto2ivis.controller;

import java.time.LocalDate;
import sistema.biblioteca.controlador.projeto2ivis.model.*;

/**
 * Classe BibliotecaController é responsável pela coordenação entre diferentes controladores dentro da aplicação de uma biblioteca,
 * como controle de livros, usuários e empréstimos, além de lidar com a serialização/deserialização de dados.
 * Esta classe centraliza a lógica de negócios, permitindo que outras partes da aplicação interajam com a biblioteca através de métodos previamente definidos.
 */

public class BibliotecaController {

    /**
     * Instâncias dos controladores para livros, usuários, empréstimos e serialização.
     */
    private final ControlleLivros livroController;
    private final ControlleUsuarios usuarioController;
    private final ControlleEmprestimos emprestimoController;
    private final BancoDAO biblioteca;

    /** Instancia as subclasses controles */
    public BibliotecaController() {
        this.livroController = new ControlleLivros();
        this.usuarioController = new ControlleUsuarios();
        this.emprestimoController = new ControlleEmprestimos();
        this.biblioteca = BancoDAO.getInstance();
    }

    // MÉTODOS PARA CONTROLE DE LIVROS

    /**
     * Adiciona um novo livro à biblioteca.
     * @param titulo O título do livro.
     * @param autor O autor do livro.
     * @param assunto O assunto do livro.
     * @param anoLancamento O ano de lançamento do livro.
     * @param qtdEstoque A quantidade em estoque do livro.
     */
    public boolean adicionarLivro(String titulo, String autor, String assunto, int anoLancamento, int qtdEstoque) {
        boolean bool = livroController.adicionarLivro(titulo, autor, assunto, anoLancamento, qtdEstoque);
        return bool;
    }

    /**
     * Remove um livro pelo título.
     * @param titulo O título do livro a ser removido.
     */
    public boolean removerLivroPorTitulo (String titulo){
        boolean bool = livroController.removerLivroPorTitulo(titulo);
        return bool;
    }
    /**
     * Pesquisa um livro pelo título.
     * @param titulo O título do livro a ser pesquisado.
     */
    public void pesquisarLivroPorTitulo(String titulo) {
        livroController.pesquisarLivroPorTitulo(titulo);
    }

    /** Exibe a lista de livros. */
    public void exibirListaLivros() {
        livroController.exibirListaLivros();
    }

    // MÉTODOS PARA CONTROLE DE USUÁRIOS

    /**
     * Adiciona um novo estudante.
     * @param nome O nome do estudante.
     * @param cpf O CPF do estudante.
     * @param matricula A matrícula do estudante.
     * @param dataNascimento A data de nascimento do estudante.
     * @param curso O curso do estudante.
     * @return true se adicionar, se false, caso não consiga.
     */
    public boolean adicionarEstudante(String nome, String cpf, String matricula, LocalDate dataNascimento, String curso) {
        boolean bool = usuarioController.adicionarEstudante(nome, cpf, matricula, dataNascimento, curso);
        return bool;
    }

    /**
     * Adiciona um novo professor.
     *
     * @param nome O nome do professor.
     * @param cpf O CPF do professor.
     * @param matricula A matrícula do professor.
     * @param dataNascimento A data de nascimento do professor.
     * @param departamento O departamento do professor.
     */
    public boolean adicionarProfessor(String nome, String cpf, String matricula, LocalDate dataNascimento, String departamento) {
        boolean bool = usuarioController.adicionarProfessor(nome, cpf, matricula, dataNascimento, departamento);
        return bool;
    }

    /**
     * Adiciona um novo bibliotecário.
     *
     * @param nome O nome do bibliotecário.
     * @param cpf O CPF do bibliotecário.
     * @param matricula A matrícula do bibliotecário.
     * @param dataNascimento A data de nascimento do bibliotecário.
     * @param login O login do bibliotecário.
     * @param senha A senha do bibliotecário.
     */
    public boolean adicionarBibliotecario(String nome, String cpf, String matricula, LocalDate dataNascimento, String login, String senha) {
        boolean bool = usuarioController.adicionarBibliotecario(nome, cpf, matricula, dataNascimento, login, senha);
        return bool;
    }

    /**
     * Busca um usuário pelo CPF.
     * @param cpf O CPF do usuário.
     * @return O usuário correspondente ao CPF.
     */
    public Usuario buscarUsuarioPorCpf(String cpf) {
        return ControlleUsuarios.buscarUsuarioPorCpf(cpf);
    }

    /** Exibe a lista de usuários. */
    public void exibirListaUsuarios() {
        usuarioController.exibirListaUsuarios();
    }

    /**
     * Verifica a situação de um usuário pelo CPF.
     * @param cpf O CPF do usuário.
     */
    public void exibirDetalhesUsuarioPorCPF(String cpf) {
        usuarioController.exibeDetalhesUsuarioPorCPF(cpf);
    }

    /**
     * Remover um usuário a partir do seu cpf
     *@param cpfUsuario O CPF do usuário.
     */
    public boolean removerUsuario(String cpfUsuario){
        boolean bool = usuarioController.removerUsuario(cpfUsuario);
        return bool;
    }

    /**
     * Exibe os empréstimos de um usuário pelo CPF.
     * @param cpf O CPF do usuário.
     */
    public void exibirEmprestimosUsuarioPorCPF(String cpf) {
        usuarioController.exibirEmprestimosUsuarioPorCPF(cpf);
    }

    /** Inicializa o programa com um bibliotecário padrão admin. */
    public void inicializarBibliotecarioAdmin() {
        usuarioController.inicializarBibliotecarioAdmin();
    }

    /**
     * Verifica se o login do bibliotecário está correto.
     * @param login O login do bibliotecário.
     * @return true se o login estiver correto, caso contrário false.
     */
    public boolean verificarBibliotecarioLogin(String login) {
        return usuarioController.verificarBibliotecarioLogin(login);
    }

    /**
     * Verifica se a senha do bibliotecário está correta.
     * @param senha A senha do bibliotecário.
     * @return true se a senha estiver correta, caso contrário false.
     */
    public boolean verificarBibliotecarioSenha(String senha) {
        return usuarioController.verificarBibliotecarioSenha(senha);
    }

    // MÉTODOS PARA CONTROLE DE EMPRÉSTIMOS

    /**
     * Adiciona um empréstimo de um livro.
     * @param cpfUsuario O usuário que está emprestando o livro.
     * @param tituloLivro O livro a ser emprestado.
     */
    public boolean adicionarEmprestimoLivro(String cpfUsuario, String tituloLivro) {
        boolean bool = emprestimoController.adicionarEmprestimoLivro(cpfUsuario, tituloLivro);
        return bool;
    }

    /**
     * Devolve um livro emprestado.
     * @param cpfUsuario O usuário que está devolvendo o livro.
     * @param tituloLivro O livro a ser devolvido.
     */
    public boolean devolverEmprestimoLivro(String cpfUsuario, String tituloLivro) {
        boolean bool = emprestimoController.devolverEmprestimoLivro(cpfUsuario, tituloLivro);
        return bool;
    }

    /** Exibe a lista de empréstimos. */
    public void exibirListaEmprestimos() {
        emprestimoController.exibirListaEmprestimos();
    }

    // MÉTODOS PARA CONTROLE DE SERIALIZAÇÃO

    /** Carrega todos os arquivos binários da biblioteca (usuários, livros e empréstimos)*/
    public void carregarDadosBiblioteca(){
        ControlleSerializacao.carregarUsuarios();
        ControlleSerializacao.carregarLivros();
        ControlleSerializacao.carregarEmprestimos();
    }

    /** Salva o estado completo da biblioteca (usuários, empréstimos e livros) em arquivo binário. */
    public void salvarEstadoBiblioteca() {
        ControlleSerializacao.salvarUsuarios();
        ControlleSerializacao.salvarLivros();
        ControlleSerializacao.salvarEmprestimos();
    }

    /**Reinicia o banco de dados da biblioteca*/
    public void reiniciarBancoDados(){
        biblioteca.getUsuarios().clear();
        biblioteca.getLivros().clear();
        biblioteca.getEmprestimos().clear();
        inicializarBibliotecarioAdmin();
        ControlleSerializacao.salvarUsuarios();
        ControlleSerializacao.salvarLivros();
        ControlleSerializacao.salvarEmprestimos();
    }

    /**
     * Busca um livro específico pelo seu título.
     *
     * @param tituloLivro Título do livro a ser buscado.
     * @return O livro correspondente ou null se não encontrado.
     */
    public Livro buscarLivroPorTitulo(String tituloLivro){
        Livro lv = livroController.buscarLivroPorTitulo(tituloLivro);
        return lv;
    }

}
