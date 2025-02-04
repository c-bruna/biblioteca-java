package sistema.biblioteca.controlador.controller;

import java.io.*;
import java.util.ArrayList;
import sistema.biblioteca.controlador.model.*;

/**
 * Classe ControlleSerializacao é responsável por gerenciar a serialização e deserialização de dados da biblioteca,
 * incluindo usuários, livros e empréstimos, para armazenamento em arquivos binários.
 *
 * Utiliza a abordagem Singleton para garantir que apenas uma instância do BancoDAO seja utilizada em toda a aplicação.
 */

public class ControlleSerializacao {

    /**
     * Controlador de serialização que centraliza as operações de salvamento e recuperação de dados da biblioteca.
     */
    private static BancoDAO biblioteca = BancoDAO.getInstance();

    /**
     * Instância única do BancoDAO usado para acessar os dados da biblioteca.
     */
    public ControlleSerializacao() { biblioteca = BancoDAO.getInstance(); }

    /**
     * Salva os dados dos usuários em um arquivo binário chamado Usuarios.bin.
     */
    public static void salvarUsuarios() {
        try {
            FileOutputStream arqSerial = new FileOutputStream("src/main/java/sistema/biblioteca/controlador/bin/Usuarios.bin");
            ObjectOutputStream usuarios = new ObjectOutputStream(arqSerial);
            usuarios.writeObject(biblioteca.getUsuarios());
            usuarios.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar usuarios: " + e.getMessage());
        }

    }

    /**
     * Carrega os dados dos usuários de um arquivo binário chamado Usuarios.bin.
     */
    public static void carregarUsuarios() {
        File arquivo = new File("src/main/java/sistema/biblioteca/controlador/bin/Usuarios.bin");
        if(arquivo.exists()) {
            try (FileInputStream arquivoEntrada = new FileInputStream("src/main/java/sistema/biblioteca/controlador/bin/Usuarios.bin");
                 ObjectInputStream entrada = new ObjectInputStream(arquivoEntrada)) {
                BancoDAO.getInstance().setUsuarios((ArrayList<Usuario>) entrada.readObject());

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo de usuarios nao encontrado.");
            } catch (IOException e) {
                System.out.println("Erro ao ler arquivo de usuarios: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Classe de usuario nao encontrada durante a desserializacao.");
            }
        }
    }

    /**
     * Salva os dados dos livros em um arquivo binário chamado Livros.bin.
     */
    public static void salvarLivros() {

        try {
            FileOutputStream arqSerial = new FileOutputStream("src/main/java/sistema/biblioteca/controlador/bin/Livros.bin");
            ObjectOutputStream livros = new ObjectOutputStream(arqSerial);
            livros.writeObject(biblioteca.getLivros());
            livros.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar livros: " + e.getMessage());
        }

    }

    /**
     * Carrega os dados dos livros de um arquivo binário chamado Livros.bin.
     */
    public static void carregarLivros() {
        File arquivo = new File("src/main/java/sistema/biblioteca/controlador/bin/Livros.bin");
        if(arquivo.exists()) {
            try (FileInputStream arquivoEntrada = new FileInputStream("src/main/java/sistema/biblioteca/controlador/bin/Livros.bin");
                 ObjectInputStream entrada = new ObjectInputStream(arquivoEntrada)) {
                BancoDAO.getInstance().setLivros((ArrayList<Livro>) entrada.readObject());

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo de livros nao encontrado.");
            } catch (IOException e) {
                System.out.println("Erro ao ler arquivo de livros: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Classe de livro nao encontrada durante a desserializacao.");
            }
        }
    }

    /**
     * Salva os dados dos empréstimos em um arquivo binário chamado Emprestimos.bin.
     */
    public static void salvarEmprestimos() {
        try {
            FileOutputStream arqSerial = new FileOutputStream("src/main/java/sistema/biblioteca/controlador/bin/Emprestimos.bin");
            ObjectOutputStream emprestimos = new ObjectOutputStream(arqSerial);
            emprestimos.writeObject(biblioteca.getEmprestimos());
            emprestimos.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar emprestimos: " + e.getMessage());
        }
    }

    /**
     * Carrega os dados dos empréstimos de um arquivo binário chamado Emprestimos.bin.
     */
    public static void carregarEmprestimos() {
        File arquivo = new File("src/main/java/sistema/biblioteca/controlador/bin/Emprestimos.bin");
        if(arquivo.exists()) {
            try (FileInputStream arquivoEntrada = new FileInputStream("src/main/java/sistema/biblioteca/controlador/bin/Emprestimos.bin");
                 ObjectInputStream entrada = new ObjectInputStream(arquivoEntrada)) {
                BancoDAO.getInstance().setEmprestimos((ArrayList<Emprestimo>) entrada.readObject());

            } catch (FileNotFoundException e) {
                System.out.println("Arquivo de emprestimos não encontrado.");
            } catch (IOException e) {
                System.out.println("Erro ao ler arquivo de emprestimos: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Classe de emprestimos nao encontrada durante a desserializacao.");
            }
        }
    }


}
