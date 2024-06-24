package sistema.biblioteca.model;
import java.io.Serializable;
import java.time.LocalDate;

public class Emprestimo implements Serializable{
    Pessoa pessoa;
    Livro livro;
    LocalDate dataEmprestimo;
    LocalDate dataDevolucao;



    public Emprestimo(Pessoa pessoa, Livro livro) {
        this.pessoa = pessoa;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now();

        if(pessoa instanceof Estudante){
            this.dataDevolucao = dataEmprestimo.plusDays(15);
        }else{
            this.dataDevolucao = dataEmprestimo.plusDays(30);
        }
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public Livro getLivro() {
        return livro;
    }
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

}
