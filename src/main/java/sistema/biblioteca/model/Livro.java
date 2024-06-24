package sistema.biblioteca.model;
import java.io.Serializable;

public class Livro implements Serializable {
    String autor;
    int anoLancamento;
    String titulo;
    String assunto;
    int estoque;

    public Livro(String autor, int anoLancamento, String titulo, String assunto, int estoque) {
        this.autor = autor;
        this.anoLancamento = anoLancamento;
        this.titulo = titulo;
        this.assunto = assunto;
        this.estoque = estoque;

    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAssunto() {
        return assunto;
    }
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
