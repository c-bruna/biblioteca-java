package sistema.biblioteca.controller;

import java.time.LocalDate;

interface OperacoesInterface {

    //Operações de leitura e escrita em arquivos binarios
    void lerDadosArquivos();
    void salvarDadosArquivos();
    
    //Operações realizadas em livros
    boolean realizarEmprestimo(String cpf, String tituloLivro);
    boolean devolverEmprestimo(String cpf, String tituloLivro);
    void listarLivros();
    boolean pesquisarLivros(String tituloLivro);
    boolean cadastrarLivro(String autor, int anoLancamento, String titulo, String assunto, int estoque);
    boolean excluirLivro(String tituloLivro);

    //Operações realizadas em pessoas (Bibliotecario, professor e aluno)
    boolean cadastrarUsuarioBibliotecario(Long matricula, String nome, String cpf, LocalDate dataNascimento, String login, String senha);
    boolean cadastrarUsuarioEstudante(Long matricula, String nome, String cpf, LocalDate dataNascimento, String curso);
    boolean cadastrarUsuarioProfessor(Long matricula, String nome, String cpf, LocalDate dataNascimento, String departamento);
    public boolean pesquisarUsuario(String cpf);
    void listarUsuarios();
    void listarEmprestimoUsuario(String cpf);
    void verificarSituacaoUsuario(String cpf);
    public boolean excluirUsuario(String cpf);

}