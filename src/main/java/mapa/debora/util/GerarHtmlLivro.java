package mapa.debora.util;

import jakarta.servlet.http.HttpServletRequest;
import mapa.debora.model.Livro;

import java.util.List;

/**
 * Classe utilitária que gera dinamicamente o código HTML para exibir
 * a lista de livros cadastrados no sistema.
 */
public class GerarHtmlLivro {

    /**
     * Método que monta uma String com o HTML representando os livros na lista.
     *
     * @param request Objeto HttpServletRequest para acessar o contexto da aplicação (ex: caminho do servlet)
     * @param livros Lista de objetos Livro a serem exibidos
     * @return String contendo o HTML gerado para a exibição dos livros
     */
    public static String gerar(HttpServletRequest request, List<Livro> livros) {
        StringBuilder htmlLivros = new StringBuilder();

        // Adiciona um título para a seção de livros
        htmlLivros.append("<h2>Livros cadastrados:</h2>");

        // Para cada livro da lista, cria um bloco HTML com seus detalhes e um botão para exclusão
        for (Livro livro : livros) {
            htmlLivros
                    .append("<div class=\"lista\">") // container para cada livro

                    // Exibe as informações do livro formatadas com tags <p> e negrito para os rótulos
                    .append("<p><strong>Título:</strong> ").append(livro.getTitulo()).append("</p>")
                    .append("<p><strong>Autor:</strong> ").append(livro.getAutor()).append("</p>")
                    .append("<p><strong>Ano:</strong> ").append(livro.getAno()).append("</p>")
                    .append("<p><strong>ISBN:</strong> ").append(livro.getIsbn()).append("</p>")
                    .append("<p><strong>ID:</strong> ").append(livro.getId()).append("</p>")

                    // Cria um formulário para excluir o livro usando método POST
                    // O formulário inclui campos ocultos para informar a ação e o ID do livro a ser removido
                    .append("<form class=\"delete-form\" method='post' action='")
                    .append(request.getContextPath()).append("/livros' style='display:inline;'>")
                    .append("<input type='hidden' name='action' value='delete' />")
                    .append("<input type='hidden' name='id' value='").append(livro.getId()).append("' />")

                    // Botão que submete o formulário para excluir o livro
                    .append("<button type='submit'>EXCLUIR</button>")
                    .append("</form>")
                    .append("</div>"); // fim do container do livro
        }

        // Retorna o HTML completo como String
        return htmlLivros.toString();
    }
}
