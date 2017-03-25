
package sistemasupermercado.exceptions;

public class PesquisaNulaException extends RuntimeException {

    public PesquisaNulaException(String message) {
        super(message);
    }

    public PesquisaNulaException() {
        super("A pesquisa não retornou resultados.");
    }
    
}
