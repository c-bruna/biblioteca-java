package sistema.biblioteca.model;

import java.time.LocalDate;

public class Professor extends Pessoa{

    String departamento;

    public Professor(Long matricula, String nome, String cpf, LocalDate dataNascimento, String departamento) {
        super(matricula, nome, cpf, dataNascimento);
        this.departamento = departamento;
        this.qtdMaxLivros = 5;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    
}
