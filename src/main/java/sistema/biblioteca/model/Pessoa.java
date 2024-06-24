package sistema.biblioteca.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa implements Serializable{
    Long matricula;
    String nome;
    String cpf;
    LocalDate dataNascimento;
    LocalDate suspensoAte; //Essa variavel especifica a data maxima de suspensão de um usuario, caso ele atrase um livro;
    int qtdMaxLivros;
    int nunLivrosEmprestados; //Essa variavel especifica quantos livros o usuário pediu emprestado;


    public Pessoa(Long matricula, String nome, String cpf, LocalDate dataNascimento) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.suspensoAte = LocalDate.parse("1970-01-01", formatter);
        this.nunLivrosEmprestados = 0;
    }

    public Long getMatricula() {
        return matricula;
    }
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getSuspensoAte() {
        return suspensoAte;
    }

    public void setSuspensoAte(LocalDate suspensoAte) {
        this.suspensoAte = suspensoAte;
    }
    
    public int getQtdMaxLivros() {
        return qtdMaxLivros;
    }

    public void setQtdMaxLivros(int qtdMaxLivros) {
        this.qtdMaxLivros = qtdMaxLivros;
    }

    public int getNunLivrosEmprestados() {
        return nunLivrosEmprestados;
    }

    public void setNunLivrosEmprestados(int nunLivrosEmprestados) {
        this.nunLivrosEmprestados = nunLivrosEmprestados;
    }
    
}