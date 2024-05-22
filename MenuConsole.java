package projetosjava;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuConsole {
    private static final String FILENAME = "livros.txt";
    private static List<Livro> livros = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        loadLivrosFromFile();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Listar livros");
            System.out.println("2. Adicionar livro");
            System.out.println("3. Atualizar livro");
            System.out.println("4. Deletar livro");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            switch (choice) {
                case 1:
                    listarLivros();
                    break;
                case 2:
                    adicionarLivro(scanner);
                    break;
                case 3:
                    atualizarLivro(scanner);
                    break;
                case 4:
                    deletarLivro(scanner);
                    break;
                case 5:
                    salvarLivrosToFile();
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void loadLivrosFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Livro livro = Livro.fromString(line);
                livros.add(livro);
                if (livro.getId() >= nextId) {
                    nextId = livro.getId() + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar os livros do arquivo. Iniciando com uma lista vazia.");
        }
    }

    private static void salvarLivrosToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {
            for (Livro livro : livros) {
                bw.write(livro.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os livros no arquivo.");
        }
    }

    private static void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros para listar.");
        } else {
            System.out.println("Livros:");
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private static void adicionarLivro(Scanner scanner) {
        System.out.print("Título do livro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor do livro: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de publicação do livro: ");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        Livro livro = new Livro(nextId++, titulo, autor, anoPublicacao);
        livros.add(livro);
        salvarLivrosToFile();
        System.out.println("Livro adicionado com sucesso.");
    }

    private static void atualizarLivro(Scanner scanner) {
        System.out.print("ID do livro a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        Livro livro = procurarLivroPorId(id);
        if (livro != null) {
            System.out.print("Novo título do livro: ");
            String novoTitulo = scanner.nextLine();
            System.out.print("Novo autor do livro: ");
            String novoAutor = scanner.nextLine();
            System.out.print("Novo ano de publicação do livro: ");
            int novoAnoPublicacao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer de entrada

            livro.setTitulo(novoTitulo);
            livro.setAutor(novoAutor);
            livro.setAnoPublicacao(novoAnoPublicacao);
            salvarLivrosToFile();
            System.out.println("Livro atualizado com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void deletarLivro(Scanner scanner) {
        System.out.print("ID do livro a ser deletado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer de entrada

        Livro livro = procurarLivroPorId(id);
        if (livro != null) {
            livros.remove(livro);
            salvarLivrosToFile();
            System.out.println("Livro deletado com sucesso.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static Livro procurarLivroPorId(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }
}