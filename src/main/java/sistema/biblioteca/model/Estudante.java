package sistema.biblioteca.model;

import java.time.LocalDate;

public class Estudante extends Pessoa{
    String curso;

    public Estudante(Long matricula, String nome, String cpf, LocalDate dataNascimento, String curso) {
        super(matricula, nome, cpf, dataNascimento);
        this.curso = curso;
        this.qtdMaxLivros = 3;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
