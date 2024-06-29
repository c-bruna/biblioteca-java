package sistema.biblioteca.controlador.projeto2ivis.controller;

public interface OperacoesEmprestimos {
    //MÉTODOS EMPRÉSTIMOS
    boolean adicionarEmprestimoLivro(String cpfUsuario, String tituloLivro);
    boolean devolverEmprestimoLivro(String cpfUsuario, String tituloLivro);
    void exibirListaEmprestimos();
}
