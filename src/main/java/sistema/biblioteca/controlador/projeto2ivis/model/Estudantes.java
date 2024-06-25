package sistema.biblioteca.controlador.projeto2ivis.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Estudantes extends Usuario implements Serializable {
    private String curso;

    public Estudantes(String nome, String cpf, String matricula, LocalDate dadaNascimento, int qtdEmprestimosAtivos, String curso) {
        super(nome, cpf, matricula, dadaNascimento, qtdEmprestimosAtivos);
        this.curso = curso;
    }

    public String getCurso() { return curso; }

    public void setCurso(String curso) { this.curso = curso; }

    @Override
    public void exibirDetalhesUsuario(){
        super.exibirDetalhesUsuario();
        System.out.println("Curso: " + getCurso());
    }
}
