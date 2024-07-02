package sistema.biblioteca.controlador.model;
import java.io.Serializable;
import java.time.LocalDate;


public class Usuario implements Serializable {
    protected String nome;
    protected String cpf;
    protected String matricula;
    protected LocalDate dadaNascimento;
    protected int qtdEmprestimosAtivos;

    public Usuario(String nome, String cpf, String matricula, LocalDate dadaNascimento, int qtdEmprestimosAtivos) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.dadaNascimento = dadaNascimento;
        this.qtdEmprestimosAtivos = qtdEmprestimosAtivos;
    }


    //GETTERS E SETTERS

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getMatricula() { return matricula; }

    public void setMatricula(String matricula) { this.matricula = matricula; }

    public LocalDate getDadaNascimento() { return dadaNascimento; }

    public void setDadaNascimento(LocalDate dadaNascimento) { this.dadaNascimento = dadaNascimento; }

    public int getQtdEmprestimosAtivos() { return qtdEmprestimosAtivos; }

    public void setQtdEmprestimosAtivos(int qtdEmprestimosAtivos) { this.qtdEmprestimosAtivos = qtdEmprestimosAtivos; }


    //MÉTODOS
    public void exibirDetalhesUsuario() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Data de Nascimento: " + dadaNascimento);
        System.out.println("Quantidade de Empréstimos Ativos: " + qtdEmprestimosAtivos);
    }


}
