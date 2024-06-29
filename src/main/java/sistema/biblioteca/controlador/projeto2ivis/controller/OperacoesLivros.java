package sistema.biblioteca.controlador.projeto2ivis.controller;

import sistema.biblioteca.controlador.projeto2ivis.model.*;

public interface OperacoesLivros {
    //MÉTODOS DOS LIVROS
    boolean adicionarLivro(String titulo, String autor, String assunto, int anoLancamento, int qtdEstoque);
    boolean removerLivroPorTitulo(String tituloLivro);
    void pesquisarLivroPorTitulo(String tituloLivro);
    void exibirListaLivros();
}
