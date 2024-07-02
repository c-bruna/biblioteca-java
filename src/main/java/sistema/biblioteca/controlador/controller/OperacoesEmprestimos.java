package sistema.biblioteca.controlador.controller;

public interface OperacoesEmprestimos {
    //MÉTODOS EMPRÉSTIMOS
    boolean adicionarEmprestimoLivro(String cpfUsuario, String tituloLivro);
    boolean devolverEmprestimoLivro(String cpfUsuario, String tituloLivro);
    void exibirListaEmprestimos();
}
