package projetosjava;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;

    public Livro(int id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    @Override
    public String toString() {
        return id + "," + titulo + "," + autor + "," + anoPublicacao;
    }

    public static Livro fromString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String titulo = parts[1];
        String autor = parts[2];
        int anoPublicacao = Integer.parseInt(parts[3]);
        return new Livro(id, titulo, autor, anoPublicacao);
    }
}
