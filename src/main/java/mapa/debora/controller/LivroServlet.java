package mapa.debora.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapa.debora.exception.LivroException;
import mapa.debora.model.Livro;
import mapa.debora.util.GerarHtmlLivro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet responsável por controlar as operações relacionadas aos livros:
 * - Listar livros (GET)
 * - Cadastrar novo livro (POST)
 * - Excluir livro (POST com ação delete)
 *
 * Mapeado para a URL /livros
 */
@WebServlet(name = "LivroServlet", value = "/livros")
public class LivroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Lista que armazena os livros cadastrados na sessão do servlet
    private List<Livro> livros;

    /**
     * Construtor padrão que chama o construtor da superclasse HttpServlet.
     */
    public LivroServlet() {
        super();
    }

    /**
     * Método de inicialização do servlet.
     * Inicializa a lista de livros vazia quando o servlet é carregado.
     */
    @Override
    public void init() {
        livros = new ArrayList<>();
    }

    /**
     * Trata requisições GET para listar os livros.
     * Gera o HTML para exibir os livros e encaminha para a JSP da view.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Gera o conteúdo HTML da lista de livros usando uma classe utilitária
        String htmlLivros = GerarHtmlLivro.gerar(request, livros);

        // Armazena o HTML gerado como atributo da requisição para uso na JSP
        request.setAttribute("htmlLivros", htmlLivros);

        // Encaminha a requisição para a página JSP responsável pela exibição
        request.getRequestDispatcher("/view/index.jsp").forward(request, response);
    }

    /**
     * Trata requisições POST para ações como cadastro e exclusão de livros.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtém o parâmetro "action" para saber qual ação realizar (excluir ou cadastrar)
        String action = request.getParameter("action");

        // Se a ação for "delete", exclui o livro pelo ID informado
        if ("delete".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idParse = Integer.parseInt(id);
                // Remove da lista o livro que tiver o mesmo ID
                livros.removeIf(livro -> livro.getId() == idParse);
            }
            // Redireciona para a lista atualizada de livros
            response.sendRedirect(request.getContextPath() + "/livros");
            return;
        }

        try {
            // Recupera os parâmetros enviados pelo formulário de cadastro
            String titulo = request.getParameter("titulo");
            String autor = request.getParameter("autor");
            String anoStr = request.getParameter("ano");
            String isbn = request.getParameter("isbn");

            // Verifica se o campo ISBN foi preenchido
            if (isbn == null || isbn.trim().isEmpty()) {
                throw new LivroException("O campo ISBN é obrigatório.");
            }

            // Remove traços do ISBN para facilitar a validação
            isbn = isbn.replaceAll("-", "").trim();

            // Valida se o ISBN tem 13 dígitos e começa com 978 ou 979 (prefixos válidos internacionais)
            if (!isbn.matches("^97[89]\\d{10}$")) {
                throw new LivroException("ISBN inválido. Use o formato de 13 dígitos, começando com 978 ou 979.");
            }

            // Cria um novo objeto Livro e seta seus atributos (título e autor convertidos para maiúsculas)
            Livro livro = new Livro();
            livro.setTitulo(titulo.toUpperCase());
            livro.setAutor(autor.toUpperCase());
            livro.setAno(Integer.parseInt(anoStr)); // Converte o ano para inteiro
            livro.setIsbn(isbn);

            // Valida os dados do livro (regras adicionais dentro da classe Livro)
            livro.validar();

            // Adiciona o livro à lista
            livros.add(livro);

            // Redireciona para a listagem atualizada de livros
            response.sendRedirect(request.getContextPath() + "/livros");

        } catch (NumberFormatException e) {
            // Caso o ano não seja um número válido, exibe mensagem de erro na página
            request.setAttribute("mensagemErro", "Ano deve ser um número.");
            request.setAttribute("htmlLivros", GerarHtmlLivro.gerar(request, livros));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);

        } catch (LivroException e) {
            // Em caso de erro de validação dos dados do livro, exibe a mensagem na página
            request.setAttribute("mensagemErro", e.getMessage());
            request.setAttribute("htmlLivros", GerarHtmlLivro.gerar(request, livros));
            request.getRequestDispatcher("/view/index.jsp").forward(request, response);

        } catch (Exception e) {
            // Para qualquer outra exceção inesperada, lança uma RuntimeException
            throw new RuntimeException(e);
        }
    }
}
