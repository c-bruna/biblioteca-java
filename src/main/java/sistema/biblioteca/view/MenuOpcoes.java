package sistema.biblioteca.view;

import sistema.biblioteca.controller.Operacoes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuOpcoes {

    Operacoes operacoes = new Operacoes();

    public void menu() {

        operacoes.lerDadosArquivos();
        clearConsole();
        System.out.println("\n#########################################################");
        System.out.println("##--->               Biblioteca IMD                <---##");
        System.out.println("#########################################################");
        System.out.println("*                                                       *");
        System.out.println("*         Para começar insira seu login e senha         *");
        System.out.println("*                                                       *");
        System.out.println("*********************************************************\n");

        Scanner scan = new Scanner(System.in);
        String login = "", senha = "";

        while (!operacoes.fazerLoginBibliotecario(login, senha)) {
            System.out.print("LOGIN: ");
            login = scan.nextLine();
            System.out.print("SENHA: ");
            senha = scan.nextLine();
            System.out.println("**********  !!Senha Incorreta!!  **********");
        }

        if (operacoes.fazerLoginBibliotecario(login, senha)) {
            int escolhaMenuPrincipal = 4;
            do {
                clearConsole();
                System.out.println("\n#########################################################");
                System.out.println("##--->                  BEM-VINDO                  <---##");
                System.out.println("#########################################################");
                System.out.println("*                                                       *");
                System.out.println("*             Escolha uma das opções abaixo             *");
                System.out.println("*                                                       *");
                System.out.println("*********************************************************\n");

                System.out.println("1 - Menu de livros");
                System.out.println("2 - Menu de usuários");
                System.out.println("3 - Menu de Emprestimos");
                System.out.println("0 - Sair do sistema");
                System.out.print("Escolha: ");

                escolhaMenuPrincipal = scan.nextInt();
                scan.skip("\\R");

                switch (escolhaMenuPrincipal) {
                    case 1:
                        menuLivros(scan);
                        break;
                    case 2:
                        menuUsuarios(scan);
                        break;
                    case 3:
                        menuEmprestimos(scan);
                        break;
                }

            } while (escolhaMenuPrincipal != 0);

            scan.close();

            operacoes.salvarDadosArquivos();
        } 
    }

    public void menuLivros(Scanner scan) {
        int escolhaMenuLivros;
        do {
            clearConsole();
            System.out.println("\n#########################################################");
            System.out.println("##--->                  Menu Livros                <---##");
            System.out.println("#########################################################");
            System.out.println("*                                                       *");
            System.out.println("*             Escolha uma das opções abaixo             *");
            System.out.println("*                                                       *");
            System.out.println("*********************************************************\n");

            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Excluir livros");
            System.out.println("3 - Listar livros");
            System.out.println("4 - Pesquisar livro");
            System.out.println("0 - Sair do menu de livros");
            System.out.print("Escolha: ");

            escolhaMenuLivros = scan.nextInt();
            scan.skip("\\R");

            switch (escolhaMenuLivros) {
                case 1:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o livro ----");
                    String autor, titulo, assunto;
                    int estoque, anoLancamento;
                    System.out.print("Titulo do livro: ");
                    titulo = scan.nextLine();
                    System.out.print("Autor do livro: ");
                    autor = scan.nextLine();
                    System.out.print("Assunto do livro: ");
                    assunto = scan.nextLine();
                    System.out.print("Ano de lançamento do livro: ");
                    anoLancamento = scan.nextInt();
                    System.out.print("Estoque: ");
                    estoque = scan.nextInt();
                    operacoes.cadastrarLivro(autor, anoLancamento, titulo, assunto, estoque);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 2:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o livro ----");
                    String titulol;
                    System.out.print("Titulo do livro: ");
                    titulol = scan.nextLine();
                    operacoes.excluirLivro(titulol);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 3:
                    clearConsole();
                    operacoes.listarLivros();
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 4:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o livro ----");
                    String titulok;
                    System.out.print("Titulo do livro: ");
                    titulok = scan.nextLine();
                    operacoes.pesquisarLivros(titulok);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
            }

        } while (escolhaMenuLivros != 0);
    }

    public void menuUsuarios(Scanner scan) {
        int escolhaMenuUsuarios;
        do {
            clearConsole();
            System.out.println("\n#########################################################");
            System.out.println("##--->                Menu Usuários                <---##");
            System.out.println("#########################################################");
            System.out.println("*                                                       *");
            System.out.println("*             Escolha uma das opções abaixo             *");
            System.out.println("*                                                       *");
            System.out.println("*********************************************************\n");

            System.out.println("1 - Cadastrar usuario");
            System.out.println("2 - Excluir usuario");
            System.out.println("3 - Listar usuarios");
            System.out.println("4 - Pesquisar usuario");
            System.out.println("0 - Sair do menu usuários");
            System.out.print("Escolha: ");

            escolhaMenuUsuarios = scan.nextInt();
            scan.skip("\\R");

            switch (escolhaMenuUsuarios) {
                case 1:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o usuario ----");
                    System.out.println("Escolha o tipo de usuario");
                    System.out.println("1 - Estudante");
                    System.out.println("2 - Bibliotecario");
                    System.out.println("3 - Professor");
                    int escolhaTipoUsuario = scan.nextInt();
                    scan.skip("\\R");

                    Long matricula;
                    String nome, cpf;
                    LocalDate dataNascimento;

                    if (escolhaTipoUsuario == 1) {
                        String curso;

                        System.out.print("Nome do usuario: ");
                        nome = scan.nextLine();
                        System.out.print("CPF do usuario: ");
                        cpf = scan.nextLine();
                        System.out.print("Matricula do usuario: ");
                        matricula = scan.nextLong();
                        scan.skip("\\R");
                        System.out.print("Data de nascimento do Usuario - \"yyyy-MM-dd\": ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String dataString = scan.nextLine();
                        dataNascimento = LocalDate.parse(dataString, formatter);
                        System.out.print("Curso do usuario: ");
                        curso = scan.nextLine();

                        operacoes.cadastrarUsuarioEstudante(matricula, nome, cpf, dataNascimento, curso);

                    } else if (escolhaTipoUsuario == 2) {
                        String login, senha;
                        System.out.print("Nome do usuario: ");
                        nome = scan.nextLine();
                        System.out.print("CPF do usuario: ");
                        cpf = scan.nextLine();
                        System.out.print("Matricula do usuario: ");
                        matricula = scan.nextLong();
                        scan.skip("\\R");
                        System.out.print("Data de nascimento do Usuario - \"yyyy-MM-dd\": ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String dataString = scan.nextLine();
                        dataNascimento = LocalDate.parse(dataString, formatter);
                        System.out.print("Login do usuario: ");
                        login = scan.nextLine();
                        System.out.print("Senha do usuario: ");
                        senha = scan.nextLine();
                        operacoes.cadastrarUsuarioBibliotecario(matricula, nome, cpf, dataNascimento, login, senha);

                    } else if (escolhaTipoUsuario == 3) {
                        String departamento;
                        System.out.print("Nome do usuario: ");
                        nome = scan.nextLine();
                        System.out.print("CPF do usuario: ");
                        cpf = scan.nextLine();
                        System.out.print("Matricula do usuario: ");
                        matricula = scan.nextLong();
                        scan.skip("\\R");
                        System.out.print("Data de nascimento do Usuario - \"yyyy-MM-dd\": ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String dataString = scan.nextLine();
                        dataNascimento = LocalDate.parse(dataString, formatter);
                        System.out.print("Departament do usuario: ");
                        departamento = scan.nextLine();
                        operacoes.cadastrarUsuarioProfessor(matricula, nome, cpf, dataNascimento, departamento);
                    } else {
                        System.out.print("Escolha invalida!");
                    }
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 2:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o usuario ----");
                    System.out.print("CPF do usuario: ");
                    String cpfUser;
                    cpfUser = scan.nextLine();
                    operacoes.excluirUsuario(cpfUser);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 3:
                    clearConsole();
                    operacoes.listarUsuarios();
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 4:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o usuario ----");
                    System.out.print("CPF do usuario: ");
                    String cpfUserPesq;
                    cpfUserPesq = scan.nextLine();
                    operacoes.pesquisarUsuario(cpfUserPesq);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
            }
        } while (escolhaMenuUsuarios != 0);
    }

    public void menuEmprestimos(Scanner scan) {
        int escolhaMenuEmprestimos;
        do {
            clearConsole();
            System.out.println("\n#########################################################");
            System.out.println("##--->               Menu Empréstimos              <---##");
            System.out.println("#########################################################");
            System.out.println("*                                                       *");
            System.out.println("*             Escolha uma das opções abaixo             *");
            System.out.println("*                                                       *");
            System.out.println("*********************************************************\n");

            System.out.println("1 - Realizar emprestimo para usuario");
            System.out.println("2 - Devolver livro emprestado a usuario");
            System.out.println("3 - Verificar situação de usuário");
            System.out.println("4 - Listar emprestimos de usuário");
            System.out.println("0 - Sair do menu de emprestimos");
            System.out.print("Escolha: ");

            escolhaMenuEmprestimos = scan.nextInt();
            scan.skip("\\R");

            switch (escolhaMenuEmprestimos) {
                case 1:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o emprestimo ----");
                    String cpf, titulo;
                    System.out.print("CPF do usuario: ");
                    cpf = scan.nextLine();
                    System.out.print("Titulo do livro: ");
                    titulo = scan.nextLine();
                    operacoes.realizarEmprestimo(cpf, titulo);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 2:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o usuario ----");
                    String cpf2, titulo2;
                    System.out.print("CPF do usuario: ");
                    cpf2 = scan.nextLine();
                    System.out.print("Titulo do livro: ");
                    titulo2 = scan.nextLine();
                    operacoes.devolverEmprestimo(cpf2, titulo2);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 3:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o usuario ----");
                    String cpf3;
                    System.out.print("CPF do usuario: ");
                    cpf3 = scan.nextLine();
                    operacoes.verificarSituacaoUsuario(cpf3);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
                case 4:
                    clearConsole();
                    System.out.println(" ----- Insira as informações sobre o usuario ----");
                    String cpf4;
                    System.out.print("CPF do usuario: ");
                    cpf4 = scan.nextLine();
                    operacoes.listarEmprestimoUsuario(cpf4);
                    System.out.println("Aperte qualquer tecla para continuar...");
                    scan.nextLine();
                    break;
            }

        } while (escolhaMenuEmprestimos != 0);
    }

    // Apenas uma forma de limpar o console em java.
    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // para Windows, utiliza "cmd /c cls"
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para Unix/Linux/MacOS, utiliza "clear"
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }

        } catch (final Exception e) {
            // Trata exceções, se houver
            System.out.println("Erro ao tentar limpar o console: " + e.getMessage());
        }
    }
}
