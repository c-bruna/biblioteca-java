package sistema.biblioteca.dao;

import sistema.biblioteca.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO implements Serializable{

    List<Pessoa> pessoas;
    List<Livro> livros;
    List<Emprestimo> emprestimos;

    public List<Pessoa> getPessoas() {
        return pessoas;
    }


    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }


    public List<Livro> getLivros() {
        return livros;
    }


    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }


    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }


    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }


    public static void setInstance(BancoDAO instance) {
        BancoDAO.instance = instance;
    }

    private static BancoDAO instance;

    private BancoDAO(){
        pessoas = new ArrayList<>();
        livros = new ArrayList<>();
        emprestimos = new ArrayList<>();
    };


    public static BancoDAO getInstance(){
        if(instance == null){
            return new BancoDAO();
        }
        return instance;
    }

    public void adicionarPessoa(Pessoa pessoa){
        pessoas.add(pessoa);
    }

    public void adicionarLivro(Livro livro){
        livros.add(livro);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

    public void excluirPessoa(Pessoa pessoa){
        pessoas.remove(pessoa);
    }

    public void excluirLivro(Livro livro){
        livros.remove(livro);
    }

    public void excluirEmprestimo(Emprestimo emprestimo){
        emprestimos.remove(emprestimo);
    }

    public void salvarDados(){
        salvarPessoas();
        salvarLivros();
        salvarEmprestimos();
    }

    public void lerDadosArquivo(){
        lerArquivoPessoas();
        lerArquivoLivros();
        lerArquivoEmprestimos();
    }

    private void salvarPessoas(){
        try{
            FileOutputStream filePessoas = new FileOutputStream("arquivosSaida/pessoas.bin");
            ObjectOutputStream objetoPessoas = new ObjectOutputStream(filePessoas);
            objetoPessoas.writeObject(pessoas);
            filePessoas.close();
        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarLivros(){
        try{
            FileOutputStream fileLivro = new FileOutputStream("arquivosSaida/livros.bin");
            ObjectOutputStream objetoLivros = new ObjectOutputStream(fileLivro);
            objetoLivros.writeObject(livros);
            fileLivro.close();
        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void salvarEmprestimos(){
        try{
            FileOutputStream fileEmprestimos = new FileOutputStream("arquivosSaida/empretimos.bin");
            ObjectOutputStream objetoEmprestimos = new ObjectOutputStream(fileEmprestimos);
            objetoEmprestimos.writeObject(emprestimos);
            fileEmprestimos.close();
        
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    @SuppressWarnings("unchecked")
    private void lerArquivoPessoas(){
        try{
            FileInputStream filePessoas = new FileInputStream("arquivosSaida/pessoas.bin");
            ObjectInputStream objetoPessoas = new ObjectInputStream(filePessoas);
            setPessoas((ArrayList<Pessoa>)objetoPessoas.readObject());
            filePessoas.close();
        
        }catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void lerArquivoLivros(){
        try{
            FileInputStream fileLivro = new FileInputStream("arquivosSaida/livros.bin");
            ObjectInputStream objetoLivros = new ObjectInputStream(fileLivro);
            setLivros((ArrayList<Livro>)objetoLivros.readObject());
            fileLivro.close();
        
        }catch (IOException e) {
            e.printStackTrace();

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    private void lerArquivoEmprestimos(){
        try{
            FileInputStream fileEmprestimos = new FileInputStream("arquivosSaida/empretimos.bin");
            ObjectInputStream objetoEmprestimos = new ObjectInputStream(fileEmprestimos);
            setEmprestimos((ArrayList<Emprestimo>)objetoEmprestimos.readObject());
            fileEmprestimos.close();
        
        }catch (IOException e) {
            e.printStackTrace();

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    
}
