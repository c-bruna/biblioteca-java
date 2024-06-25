package sistema.biblioteca.controlador.projeto2ivis.controller;

import java.util.NoSuchElementException;
import sistema.biblioteca.controlador.projeto2ivis.model.*;


/**
 * Classe ControlleLivros implementa a interface OperacoesLivros para gerenciar as operações de livros na biblioteca.
 * Esta classe permite adicionar, remover e pesquisar livros, além de exibir a lista de livros cadastrados.
 */
public class ControlleLivros implements OperacoesLivros {

    /**
     * Implementação do controlador de livros que centraliza as operações relacionadas aos livros.
     */
    private static BancoDAO biblioteca;

    /**
     * Instância do BancoDAO usada para acessar os dados da biblioteca.
     */
    public ControlleLivros() { biblioteca = BancoDAO.getInstance(); }


    /**
     * Adiciona um novo livro à biblioteca.
     *
     * @param titulo Título do livro.
     * @param autor Autor do livro.
     * @param assunto Assunto do livro.
     * @param anoLancamento Ano de lançamento do livro.
     * @param qtdEstoque Quantidade de estoque do livro.
     */
    @Override
    public boolean adicionarLivro(String titulo, String autor, String assunto, int anoLancamento, int qtdEstoque) {
        Livro lv = new Livro(titulo,autor, assunto, anoLancamento, qtdEstoque);
        if(buscarLivroPorTitulo(titulo) != null){
            System.out.println("Livro já cadastrado.");
            return false;
        }
        biblioteca.getLivros().add(lv);
        lv.setEstado(EstadoLivro.disponivel);
        ControlleSerializacao.salvarLivros();
        return true;
    }

    /**
     * Remove um livro da biblioteca pelo título.
     */
    @Override
    public void removerLivroPorTitulo(String titulo) {
        Livro lv = buscarLivroPorTitulo(titulo);
        if( lv == null){
            throw  new IllegalArgumentException("\nElemento não localizado");
        }
        else if (lv.getEstado() == EstadoLivro.emprestado) {
            throw new IllegalStateException("O livro esta emprestado e nao pode ser removido.");
        }

        biblioteca.getLivros().remove(lv);
        ControlleSerializacao.salvarLivros();
    }

    /**
     * Pesquisa um livro pelo seu título e exibe seus detalhes.
     *
     * @param titulo Título do livro a ser pesquisado.
     */
    @Override
    public void pesquisarLivroPorTitulo(String titulo) {
        try{
            for(Livro lv: biblioteca.getLivros()){
                if(lv.getTitulo().equals(titulo)){
                    lv.exibirDetalhesLivro();
                }
            }
        } catch (Exception e){
            throw new NoSuchElementException("o livro " + titulo + " nao foi encontrado");
        }
    }

    /**
     * Busca um livro específico pelo seu título.
     *
     * @param tituloLivro Título do livro a ser buscado.
     * @return O livro correspondente ou null se não encontrado.
     */
    public Livro buscarLivroPorTitulo(String tituloLivro) {
        try{
            for(Livro lv: biblioteca.getLivros()){
                if(lv.getTitulo().equals(tituloLivro)){
                    return lv;
                }
            }
        } catch (Exception e){
            throw new NoSuchElementException("o livro " + tituloLivro + " nao foi encontrado");
        }

        return null;
    }

    /**
     * Exibe a lista de livros cadastrados na biblioteca.
     */
    @Override
    public void exibirListaLivros() {
        if(biblioteca.getLivros().isEmpty()){
            System.out.println("Nao ha livros cadastrados");
        }
        else {
            System.out.println("\nLista de Livros:");
            for (Livro lv : biblioteca.getLivros()) {
                System.out.println("\n-------------------------------------");
                lv.exibirDetalhesLivro();
            }
        }
    }


}
