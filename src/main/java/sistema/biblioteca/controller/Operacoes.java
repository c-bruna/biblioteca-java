package sistema.biblioteca.controller;

import sistema.biblioteca.dao.BancoDAO;
import sistema.biblioteca.model.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Operacoes implements OperacoesInterface {

    BancoDAO banco = BancoDAO.getInstance();

    @Override
    public boolean realizarEmprestimo(String cpf, String tituloLivro) {

        Livro livro = buscaLivro(tituloLivro);
        Pessoa pessoa = buscaPessoa(cpf);

        if (livro == null && pessoa == null) {
            System.out.println("Pessoa e livro não encontrados!!!");
            return false;

        } else if (livro == null) {
            System.out.println("livro não encontrado!!!");
            return false;

        } else if (pessoa == null) {
            System.out.println("Usuário não encontrado!!!");
            return false;

        } else {
            if (pessoaSemPendencia(pessoa)) {

                if (pessoa instanceof Estudante) {
                    if (pessoa.getNunLivrosEmprestados() >= 3) {
                        System.out.println("O usuário já possui o número máximo de emprestimos permitidos");
                        return false;
                    }
                }

                if (pessoa instanceof Professor || pessoa instanceof Bibliotecario) {
                    if (pessoa.getNunLivrosEmprestados() >= 5) {
                        System.out.println("O usuário já possui o número máximo de emprestimos permitidos");
                        return false;
                    }
                }

                if (buscaEmprestimo(tituloLivro, cpf) == null) {
                    Emprestimo emprestimo = new Emprestimo(pessoa, livro);
                    banco.adicionarEmprestimo(emprestimo);
                    pessoa.setNunLivrosEmprestados(pessoa.getNunLivrosEmprestados() + 1);
                    System.out.println("Emprestimo realizado com sucesso");
                    return true;
                } else {
                    System.out.println("Esse livro já está emprestado para esse usuário");
                    return false;
                }
            } else {
                System.out.println("Esse usuário possui débitos na biblioteca");
                return false;
            }
        }
    }

    @Override
    public boolean devolverEmprestimo(String cpf, String tituloLivro) {
        Livro livro = buscaLivro(tituloLivro);
        Pessoa pessoa = buscaPessoa(cpf);

        if (livro == null && pessoa == null) {
            System.out.println("Pessoa e livro não encontrados!!!");
            return false;
        } else if (livro == null) {
            System.out.println("livro não encontrado!!!");
            return false;
        } else if (pessoa == null) {
            System.out.println("Usuário não encontrado!!!");
            return false;
        } else {
            Emprestimo emprestimo = buscaEmprestimo(tituloLivro, cpf);
            if (emprestimo == null) {
                System.out.println("O emprestimo buscado não exixte no sistema");
                return false;
            } else {
                if (emprestimo.getDataDevolucao().isBefore(LocalDate.now())) {
                    long diferencaEmDias = ChronoUnit.DAYS.between(emprestimo.getDataDevolucao(), LocalDate.now());
                    LocalDate suspencao = LocalDate.now().plusDays(diferencaEmDias);
                    pessoa.setSuspensoAte(suspencao);
                }
                banco.getEmprestimos().remove(emprestimo);
                pessoa.setNunLivrosEmprestados(pessoa.getNunLivrosEmprestados() - 1);
                return true;
            }
        }
    }

    @Override
    public boolean pesquisarLivros(String tituloLivro) {
        Livro livro = buscaLivro(tituloLivro);
        if (livro == null) {
            System.out.println("livro não encontrado!!!");
            return false;
        } else {
            System.out.println("\n############  LIVRO  ############");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Assunto: " + livro.getAssunto());
            System.out.println("Lançamento: " + livro.getAnoLancamento());
            System.out.println("Estoque disponível: " + livro.getEstoque());
            System.out.println("#################################\n");
            return true;
        }
    }

    @Override
    public void listarLivros() {
        System.out.println("\n############  LIVROS LISTADOS  ############");

        List<Livro> livros = banco.getLivros();

        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados na biblioteca");
        }

        for (Livro livro : livros) {
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Assunto: " + livro.getAssunto());
            System.out.println("Lançamento: " + livro.getAnoLancamento());
            System.out.println("Estoque disponível: " + livro.getEstoque());
            System.out.println("#################################");
        }
    }

    @Override
    public boolean cadastrarLivro(String autor, int anoLancamento, String titulo, String assunto, int estoque) {

        if (buscaLivro(titulo) != null) {
            System.out.println("O livro já foi cadastrado na biblioteca!!!");
            return false;
        }
        Livro livro = new Livro(autor, anoLancamento, titulo, assunto, estoque);
        banco.getLivros().add(livro);
        System.out.println("Livro cadastrado com sucesso!!!");
        return true;

    }

    @Override
    public boolean excluirLivro(String tituloLivro) {
        Livro livro = buscaLivro(tituloLivro);
        if (livro == null) {
            System.out.println("O livro não foi encontrado para exclusão");
            return false;
        }
        banco.getLivros().remove(livro);
        System.out.println("O livro excluido com sucesso!");
        return true;

    }

    @Override
    public boolean excluirUsuario(String cpf) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa == null) {
            System.out.println("A Pessoa não foi encontrado para exclusão");
            return false;
        }
        banco.getPessoas().remove(pessoa);
        System.out.println("Pessoa excluida com sucesso!");
        return true;

    }

    @Override
    public boolean cadastrarUsuarioBibliotecario(Long matricula, String nome, String cpf, LocalDate dataNascimento,
            String login, String senha) {
        if (buscaPessoa(cpf) != null) {
            System.out.println("Já existe um usuário cadastrado com esse CPF");
            return false;
        }
        Bibliotecario bibliotecario = new Bibliotecario(matricula, nome, cpf, dataNascimento, login, senha);
        banco.getPessoas().add(bibliotecario);
        return true;
    }

    @Override
    public boolean cadastrarUsuarioEstudante(Long matricula, String nome, String cpf, LocalDate dataNascimento,
            String curso) {
        if (buscaPessoa(cpf) != null) {
            System.out.println("Já existe um usuário cadastrado com esse CPF");
            return false;
        }
        Estudante estudante = new Estudante(matricula, nome, cpf, dataNascimento, curso);
        banco.getPessoas().add(estudante);
        return true;
    }

    @Override
    public boolean cadastrarUsuarioProfessor(Long matricula, String nome, String cpf, LocalDate dataNascimento,
            String departamento) {

        if (buscaPessoa(cpf) != null) {
            System.out.println("Já existe um usuário cadastrado com esse CPF");
            return false;
        }
        Professor professor = new Professor(matricula, nome, cpf, dataNascimento, departamento);
        banco.getPessoas().add(professor);
        return true;
    }

    @Override
    public void listarUsuarios() {
        List<Pessoa> pessoas = banco.getPessoas();
        if (pessoas.isEmpty()) {
            System.out.println("Não existem usuários cadastrados nessa biblioteca!!");
        }

        for (Pessoa p : pessoas) {
            System.out.println("\n############## BIBLIOTECÁRIOS ##############");
            if (p instanceof Bibliotecario) {
                Bibliotecario b = (Bibliotecario) p;
                System.out.println("Nome: " + b.getNome());
                System.out.println("CPF: " + b.getCpf());
                System.out.println("Matricula: " + b.getMatricula());
                System.out.println("Data de Nascimento: " + b.getDataNascimento());
                System.out.println("Login: " + b.getLogin());
                System.out.println("Login: " + b.getSenha());
                System.out.println("__________________________________________");
            }
        }

        for (Pessoa p : pessoas) {
            System.out.println("\n############## PROFESSORES ##############");
            if (p instanceof Professor) {
                Professor pr = (Professor) p;
                System.out.println("Nome: " + pr.getNome());
                System.out.println("CPF: " + pr.getCpf());
                System.out.println("Matricula: " + pr.getMatricula());
                System.out.println("DataNascimento: " + pr.getDataNascimento());
                System.out.println("Departamento: " + pr.getDepartamento());
                System.out.println("__________________________________________");
            }
        }

        for (Pessoa p : pessoas) {
            System.out.println("\n############## ESTUDANTES ##############");
            if (p instanceof Estudante) {
                Estudante e = (Estudante) p;
                System.out.println("Nome: " + e.getNome());
                System.out.println("CPF: " + e.getCpf());
                System.out.println("Matricula: " + e.getMatricula());
                System.out.println("DataNascimento: " + e.getDataNascimento());
                System.out.println("Curso: " + e.getCurso());
                System.out.println("__________________________________________");
            }
        }
    }

    @Override
    public boolean pesquisarUsuario(String cpf) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa == null) {
            System.out.println("Pessoa não encontrada!!!");
            return false;
        } else {
            if (pessoa instanceof Bibliotecario) {
                Bibliotecario b = (Bibliotecario)pessoa;
                System.out.println("\n############  BIBLIOTECÁRIO  ############");
                System.out.println("Nome: " + b.getNome());
                System.out.println("CPF: " + b.getCpf());
                System.out.println("Matricula: " + b.getMatricula());
                System.out.println("Login: " + b.getLogin());
                System.out.println("Senha: " + b.getSenha());
                System.out.println("Numeros de emprestimos: " + b.getNunLivrosEmprestados());
                System.out.println("#################################\n");
                return true;
            }
            if (pessoa instanceof Estudante) {
                Estudante b = (Estudante)pessoa;
                System.out.println("\n############  ESTUDANTE  ############");
                System.out.println("Nome: " + b.getNome());
                System.out.println("CPF: " + b.getCpf());
                System.out.println("Matricula: " + b.getMatricula());
                System.out.println("Login: " + b.getCurso());
                System.out.println("Numeros de emprestimos: " + b.getNunLivrosEmprestados());
                System.out.println("#################################\n");
                return true;
            } if (pessoa instanceof Professor) {
                Professor b = (Professor)pessoa;
                System.out.println("\n############  PROFESSOR  ############");
                System.out.println("Nome: " + b.getNome());
                System.out.println("CPF: " + b.getCpf());
                System.out.println("Matricula: " + b.getMatricula());
                System.out.println("Login: " + b.getDepartamento());
                System.out.println("Numeros de emprestimos: " + b.getNunLivrosEmprestados());
                System.out.println("#################################\n");
                return true;
            }
            return false;

        }
    }

    @Override
    public void listarEmprestimoUsuario(String cpf) {
        Pessoa pessoa = buscaPessoa(cpf);
        if (pessoa == null) {
            System.out.println("O usuário não foi localizado!");
            return;
        }
        int contador = 0;
        System.out.println("\n############ Lista empréstimos ############");
        for (Emprestimo e : banco.getEmprestimos()) {
            if (e.getPessoa().getCpf().equals(cpf)) {
                System.out.println("Usuario: " + e.getPessoa().getNome());
                System.out.println("Livro: " + e.getLivro().getTitulo());
                System.out.println("Emprestado em : " + e.getDataEmprestimo());
                System.out.println("Devolução prevista em : " + e.getDataDevolucao());
                System.out.println("__________________________________________");
                contador += 1;
            }
        }
        if (contador == 0) {
            System.out.println("O usuário não possui livros emprestados");
            System.out.println("________________________________________");
        }

    }

    @Override
    public void verificarSituacaoUsuario(String cpf) {
        Pessoa p = buscaPessoa(cpf);
        if (p == null) {
            System.out.println("Usuario não localizado!!!");
        }
        if (!pessoaSemPendencia(p)) {
            System.out.println("Usuario suspenso até: " + p.getSuspensoAte());
        } else {
            System.out.println("Usuário sem pendências");
            ;
        }
    }

    // Métodos auxiliares

    private Pessoa buscaPessoa(String cpf) {
        for (Pessoa p : banco.getPessoas()) {
            if (p.getCpf().equalsIgnoreCase(cpf)) {
                return p;
            }
        }
        return null;
    }

    private Bibliotecario buscaBibliotecarioPorLogin(String login) {
        for (Pessoa p : banco.getPessoas()) {
            if (p instanceof Bibliotecario) {
                Bibliotecario b = (Bibliotecario) p;
                if (b.getLogin().equalsIgnoreCase(login)) {
                    return b;
                }
            }

        }
        return null;
    }

    private Livro buscaLivro(String livro) {
        for (Livro l : banco.getLivros()) {
            if (l.getTitulo().equalsIgnoreCase(livro)) {
                return l;
            }
        }
        return null;
    }

    private Emprestimo buscaEmprestimo(String livro, String cpf) {
        for (Emprestimo e : banco.getEmprestimos()) {
            if (e.getLivro().getTitulo().equalsIgnoreCase(livro) && e.getPessoa().getCpf().equalsIgnoreCase(cpf)) {
                return e;
            }
        }
        return null;
    }

    public boolean pessoaSemPendencia(Pessoa p) {
        if (p.getSuspensoAte().isAfter(LocalDate.now()) || p.getSuspensoAte().isEqual(LocalDate.now())) {
            return false;
        } else {
            return true;
        }
    }

    public boolean fazerLoginBibliotecario(String login, String senha) {
        Bibliotecario b = buscaBibliotecarioPorLogin(login);
        if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("admin")) {
            return true;
        } else if (b != null && b.getSenha().equals(senha)) {
            return true;
        }
        return false;
    }

    @Override
    public void lerDadosArquivos() {
       banco.lerDadosArquivo();
    }

    @Override
    public void salvarDadosArquivos() {
        banco.salvarDados();
    }

}
