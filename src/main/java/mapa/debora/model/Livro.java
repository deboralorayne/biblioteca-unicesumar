package mapa.debora.model;

import mapa.debora.exception.LivroException;

/**
 * Classe que representa um livro no sistema da biblioteca.
 * Contém atributos básicos de um livro e métodos para validar seus dados.
 */
public class Livro {

    // Contador estático usado para gerar um ID único sequencial para cada livro criado
    static int contador = 1;

    // Atributos do livro
    private int id;         // Identificador único do livro
    private String titulo;  // Título do livro
    private String autor;   // Autor do livro
    private int ano;        // Ano de publicação do livro
    private String isbn;    // Código ISBN do livro (deve ter 13 dígitos e prefixo nacional)

    /**
     * Construtor padrão que apenas atribui um ID único ao livro.
     * O ID é gerado automaticamente incrementando o contador estático.
     */
    public Livro() {
        this.id = contador++;
    }

    /**
     * Construtor que inicializa o livro com título, ISBN, ano e autor.
     * Também gera o ID único automaticamente.
     *
     * @param titulo Título do livro
     * @param isbn   Código ISBN fornecido manualmente
     * @param ano    Ano de publicação
     * @param autor  Autor do livro
     */
    public Livro(String titulo, String isbn, int ano, String autor) {
        this.id = contador++;  // Gera o ID único
        this.titulo = titulo;
        this.isbn = isbn;
        this.ano = ano;
        this.autor = autor;
    }

    // Métodos getters e setters para acessar e modificar os atributos do livro

    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return this.ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Método que valida os dados do livro para garantir que estão corretos e completos.
     * Lança uma exceção LivroException caso algum campo não atenda às regras.
     *
     * Regras de validação:
     * - ISBN não pode ser vazio, deve conter exatamente 13 números e começar com "97885".
     * - Título não pode ser vazio.
     * - Autor não pode ser vazio.
     * - Ano deve estar entre 1000 e 2100.
     *
     * @throws LivroException se alguma validação falhar.
     */
    public void validar() throws LivroException {
        // Verifica se o ISBN está vazio ou nulo
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new LivroException("ISBN não pode ser vazio.");
        }

        // Verifica se o ISBN possui exatamente 13 dígitos numéricos (sem traços ou letras)
        if (!isbn.matches("^\\d{13}$")) {
            throw new LivroException("ISBN deve conter exatamente 13 números (sem traços).");
        }

        // Verifica se o ISBN começa com o prefixo nacional "97885"
        if (!isbn.startsWith("97885")) {
            throw new LivroException("ISBN deve começar com o prefixo nacional '97885'.");
        }

        // Verifica se o título está vazio ou nulo
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new LivroException("Título não pode ser vazio.");
        }

        // Verifica se o autor está vazio ou nulo
        if (autor == null || autor.trim().isEmpty()) {
            throw new LivroException("Autor não pode ser vazio.");
        }

        // Verifica se o ano está dentro do intervalo válido
        if (ano < 1000 || ano > 2100) {
            throw new LivroException("Ano deve estar entre 1000 e 2100.");
        }
    }
}
