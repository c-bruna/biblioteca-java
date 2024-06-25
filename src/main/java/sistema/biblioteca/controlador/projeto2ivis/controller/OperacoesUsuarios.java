package sistema.biblioteca.controlador.projeto2ivis.controller;

import java.time.LocalDate;

public interface OperacoesUsuarios {

    //ADICIONANDO USUÁRIOS
    boolean adicionarProfessor(String nome, String cpf, String matricula, LocalDate dadaNascimento, String departamento);
    boolean adicionarEstudante(String nome, String cpf, String matricula, LocalDate dadaNascimento, String curso);
    boolean adicionarBibliotecario(String nome, String cpf, String matricula, LocalDate dadaNascimento, String login, String senha);
    void removerUsuario(String cpfUsuario);

    //BUSCAR E EXIBIR USUÁRIOS
    void exibirListaUsuarios();

    void exibeDetalhesUsuarioPorCPF(String cpf);
    void exibirEmprestimosUsuarioPorCPF(String cpf);

    //AUTENTICACAO DO BIBLIOTECÁRIO
    public boolean verificarBibliotecarioLogin(String Login);
    public boolean verificarBibliotecarioSenha(String Senha);
    void inicializarBibliotecarioAdmin();
}
