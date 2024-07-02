package sistema.biblioteca.controlador.model;

import java.util.ArrayList;

public class BancoDAO {
    ArrayList<Usuario> usuarios;
    ArrayList<Livro> livros;
    ArrayList<Emprestimo> emprestimos;

    private static BancoDAO Biblioteca;

    private BancoDAO(){
        usuarios = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    }

    public static BancoDAO getInstance(){
        if(Biblioteca == null){
            Biblioteca = new BancoDAO();
        }
        return Biblioteca;
    }

    public void adicionarUsuarios(Usuario a){
      usuarios.add(a);
    }

    public ArrayList<Usuario> getUsuarios() { return usuarios; }

    public ArrayList<Livro> getLivros() { return livros; }

    public ArrayList<Emprestimo> getEmprestimos() { return emprestimos; }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public void setEmprestimos(ArrayList<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public ArrayList<Bibliotecario> getBibliotecarios() {
        ArrayList<Bibliotecario> bibliotecarios = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            if (usuario instanceof Bibliotecario) {
                bibliotecarios.add((Bibliotecario) usuario);
            }
        }
        return bibliotecarios;
    }

}
