package sistema.biblioteca.controlador.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Professores extends Usuario implements Serializable {
    private String departamento;

    public Professores(String nome, String cpf, String matricula, LocalDate dadaNascimento, int qtdEmprestimosAtivos, String departamento) {
        super(nome, cpf, matricula, dadaNascimento, qtdEmprestimosAtivos);
        this.departamento = departamento;
    }

    public String getDepartamento() { return departamento; }

    public void setDepartamento(String departamento) { this.departamento = departamento; }

    @Override
    public void exibirDetalhesUsuario() {
        super.exibirDetalhesUsuario(); // Chama o método da superclasse para exibir detalhes básicos
        System.out.println("Departamento: " + departamento); // Exibe detalhes específicos para professor
    }

}
