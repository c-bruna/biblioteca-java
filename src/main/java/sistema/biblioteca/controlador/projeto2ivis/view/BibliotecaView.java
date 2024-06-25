package sistema.biblioteca.controlador.projeto2ivis.view;

import sistema.biblioteca.controlador.projeto2ivis.controller.BibliotecaController;
import java.time.LocalDate;
import java.util.Scanner;
import sistema.biblioteca.controlador.projeto2ivis.model.*;

public class BibliotecaView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BibliotecaController biblioteca = new BibliotecaController();

    public static void main(String[] args) {

        boolean password = false;
        while (!password) {
            System.out.println("AUTENTICAÇÃO DE USUÁRIO");
            System.out.print("Login: ");
            String login = scanner.nextLine();

            System.out.print("Senha: ");
            String senha = scanner.nextLine();

            if (biblioteca.verificarBibliotecarioLogin(login) && biblioteca.verificarBibliotecarioSenha(senha)) {
                password = true;
            } else {
                System.out.println("Login ou senha incorretos.");
                System.out.println("Digite '1' para tentar novamente ou '0' para sair.");
                String opcao = scanner.nextLine();
                if (opcao.equals("0")) {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Remover Livro por Título");
            System.out.println("3. Emprestar Livro");
            System.out.println("4. Devolver Livro");
            System.out.println("5. Exibir Lista de Usuários");
            System.out.println("6. Exibir Lista de Livros");
            System.out.println("7. Exibir Lista de Empréstimos");
            System.out.println("8. Pesquisar Livro por Título");
            System.out.println("9. Exibir detalhes de Usuário por CPF");
            System.out.println("10. Exibir Empréstimos de Usuário por CPF");
            System.out.println("11. Adicionar Usuário");
            System.out.println("12. Remover Usuário");
            System.out.println("13. Reiniciar Banco de Dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarLivro();
                    break;
                case 2:
                    removerLivroPorTitulo();
                    break;
                case 3:
                    emprestarLivro();
                    break;
                case 4:
                    devolverLivro();
                    break;
                case 5:
                    biblioteca.exibirListaUsuarios();
                    break;
                case 6:
                    biblioteca.exibirListaLivros();
                    break;
                case 7:
                    biblioteca.exibirListaEmprestimos();
                    break;
                case 8:
                    pesquisarLivroPorTitulo();
                    break;
                case 9:
                    exibirDetalhesUsuarioPorCPF();
                    break;
                case 10:
                    exibirEmprestimosUsuarioPorCPF();
                    break;
                case 11:
                    adicionarUsuario();
                    break;
                case 12:
                    removerUsuario();
                case 13:
                    reiniciarBancoDados();
                case 0:
                    biblioteca.salvarEstadoBiblioteca();
                    System.out.println("Saindo...");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            if (running) {
                running = perguntarSeDesejaContinuar();
            }
        }
    }


    private static boolean perguntarSeDesejaContinuar() {
        while (true) {
            System.out.println("\nDeseja voltar ao menu ou sair?");
            System.out.println("1. Voltar ao menu");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                biblioteca.salvarEstadoBiblioteca();
                System.out.println("Saindo...");
                return false;
            } else if (opcao == 1) {
                return true;
            } else if(opcao != 0 && opcao!= 1) {
                System.out.println("---------------------------------");
                System.out.println("Opção inválida. Digite 1 para voltar ao menu ou 0 para sair.");
            }
        }
    }

    private static void adicionarUsuario() {
        System.out.println("---------------------------------");
        System.out.println("\nEscolha o tipo de usuário:");
        System.out.println("1. Estudante");
        System.out.println("2. Professor");
        System.out.println("3. Bibliotecário");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        String nome, cpf, matricula;
        LocalDate dataNascimento;

        switch (opcao) {
            case 1:
                System.out.println("---------------------------------");
                System.out.print("\nNome do estudante: ");
                nome = scanner.nextLine();
                System.out.print("CPF: ");
                cpf = scanner.nextLine();
                System.out.print("Matricula: ");
                matricula = scanner.nextLine();
                System.out.print("Data de nascimento (YYYY-MM-DD): ");
                dataNascimento = LocalDate.parse(scanner.nextLine());
                System.out.print("Curso: ");
                String curso = scanner.nextLine();
                boolean sucessoEstudante = biblioteca.adicionarEstudante(nome, cpf, matricula, dataNascimento, curso);
                try {
                    if (sucessoEstudante) {
                        System.out.println("Estudante adicionado com sucesso.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;

            case 2:
                System.out.println("---------------------------------");
                System.out.print("\nNome do professor: ");
                nome = scanner.nextLine();
                System.out.print("CPF: ");
                cpf = scanner.nextLine();
                System.out.print("Matrícula: ");
                matricula = scanner.nextLine();
                System.out.print("Data de nascimento (YYYY-MM-DD): ");
                dataNascimento = LocalDate.parse(scanner.nextLine());
                System.out.print("Departamento: ");
                String departamento = scanner.nextLine();
                boolean sucessoProfessor = biblioteca.adicionarProfessor(nome, cpf, matricula, dataNascimento, departamento);
                try {
                    if (sucessoProfessor) {
                        System.out.println("Professor adicionado com sucesso.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;

            case 3:
                System.out.println("---------------------------------");
                System.out.print("\nNome do bibliotecário: ");
                nome = scanner.nextLine();
                System.out.print("CPF: ");
                cpf = scanner.nextLine();
                System.out.print("Matrícula: ");
                matricula = scanner.nextLine();
                System.out.print("Data de nascimento (YYYY-MM-DD): ");
                dataNascimento = LocalDate.parse(scanner.nextLine());
                System.out.print("Login: ");
                String login = scanner.nextLine();
                System.out.print("Senha: ");
                String senha = scanner.nextLine();
                boolean sucessoBibliotecario = biblioteca.adicionarBibliotecario(nome, cpf, matricula, dataNascimento, login, senha);
                try {
                    if (sucessoBibliotecario) {
                        System.out.println("Bibliotecário adicionado com sucesso.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                break;

            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void removerUsuario() {
        System.out.println("---------------------------------");
        System.out.print("\nCPF do Usuário: ");
        String cpf = scanner.nextLine();

        try {
            biblioteca.removerUsuario(cpf);
            System.out.println("Usuário removido com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void adicionarLivro() {
        System.out.println("---------------------------------");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Assunto: ");
        String assunto = scanner.nextLine();
        System.out.print("Ano de Lançamento: ");
        int anoLancamento = Integer.parseInt(scanner.nextLine());
        System.out.print("Quantidade em Estoque: ");
        int qtdEstoque = scanner.nextInt();
        scanner.nextLine();

        boolean sucessoLivro = biblioteca.adicionarLivro(titulo, autor, assunto, anoLancamento, qtdEstoque);
        try {
            if(sucessoLivro){
                System.out.println("Livro adicionado com sucesso");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removerLivroPorTitulo() {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        biblioteca.removerLivroPorTitulo(titulo);
    }

    private static void emprestarLivro() {
        System.out.println("---------------------------------");
        System.out.print("CPF do Usuário: ");
        String cpf = scanner.nextLine();
        Usuario usuario = biblioteca.buscarUsuarioPorCpf(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Título do Livro: ");
        String tituloLivro = scanner.nextLine();
        Livro livro = biblioteca.buscarLivroPorTitulo(tituloLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        boolean emprestimoSucesso = biblioteca.adicionarEmprestimoLivro(usuario.getCpf(), livro.getTitulo());
        try {
            if(emprestimoSucesso){
                System.out.println("Empréstimo realizado.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void devolverLivro() {
        System.out.println("---------------------------------");
        System.out.print("CPF do Usuário: ");
        String cpf = scanner.nextLine();
        Usuario usuario = biblioteca.buscarUsuarioPorCpf(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Título do Livro: ");
        String tituloLivro= scanner.nextLine();
        Livro livro = biblioteca.buscarLivroPorTitulo(tituloLivro);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        try {
            biblioteca.devolverEmprestimoLivro(usuario.getCpf(), livro.getTitulo());
            System.out.println("Livro devolvido.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void pesquisarLivroPorTitulo() {
        System.out.println("---------------------------------");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        try {
            biblioteca.pesquisarLivroPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exibirDetalhesUsuarioPorCPF() {
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        try {
            System.out.println("---------------------------------");
            biblioteca.exibirDetalhesUsuarioPorCPF(cpf);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void exibirEmprestimosUsuarioPorCPF() {
        System.out.println("---------------------------------");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        try {
            biblioteca.exibirEmprestimosUsuarioPorCPF(cpf);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void reiniciarBancoDados() {
        System.out.println("---------------------------------");
        System.out.println("Você tem certeza que deseja reiniciar o Banco de Dados? Ao fazer isso, todos os usuários, empréstimos e livros serão excluídos do sistema");
        System.out.println("Digite 1 para continuar com a reinicialização e qualquer outro número para voltar ao menu:");
        System.out.print("Digite sua opção: ");

        String opcao = scanner.nextLine();
        if (opcao.equals("1")) {
            System.out.println("Reiniciando o Banco de Dados...");
            biblioteca.reiniciarBancoDados();
        } else {
            System.out.println("Operação cancelada. Voltando ao menu principal.");
        }
    }


}
