package sistema.biblioteca.model;

import java.time.LocalDate;

public class Bibliotecario extends Pessoa{
    String login;
    String senha;

    public Bibliotecario(Long matricula, String nome, String cpf, LocalDate dataNascimento, String login, String senha) {
        super(matricula, nome, cpf, dataNascimento);
        this.login = login;
        this.senha = senha;
        this.qtdMaxLivros = 5;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
   
    public String getLogin() {
        return login;
    }
    public String getSenha() {
        return senha;
    }
}
