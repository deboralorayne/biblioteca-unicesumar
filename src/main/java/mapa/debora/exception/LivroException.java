package mapa.debora.exception;

/**
 * Exceção personalizada usada para indicar erros específicos relacionados
 * às operações com livros no sistema, como validação de dados inválidos.
 *
 * Estende a classe Exception, permitindo lançar mensagens específicas.
 */
public class LivroException extends Exception {

    /**
     * Construtor que recebe a mensagem de erro detalhada e a repassa para
     * a classe base Exception para ser exibida ou tratada na aplicação.
     *
     * @param msg Mensagem descritiva do erro que ocorreu.
     */
    public LivroException(String msg) {
        super(msg);
    }
}
