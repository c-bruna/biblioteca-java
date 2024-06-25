package sistema.biblioteca.controlador.projeto2ivis.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Bibliotecario extends Usuario implements Serializable {
    private String Login;
    private String Senha;

    public Bibliotecario(String nome, String cpf, String matricula, LocalDate dadaNascimento, int qtdEmprestimosAtivos, String login, String senha) {
        super(nome, cpf, matricula, dadaNascimento, qtdEmprestimosAtivos);
        Login = login;
        Senha = senha;
    }

    public String getLogin() { return Login; }

    public void setLogin(String login) { Login = login; }

    public String getSenha()  { return Senha; }

    public void setSenha(String senha) { Senha = senha; }

    @Override
    public void exibirDetalhesUsuario() {
        super.exibirDetalhesUsuario(); // Chama o método da superclasse para exibir detalhes básicos
        System.out.println("Login: " + getLogin()); // Exibe detalhes específicos para bibliotecário
    }

}
