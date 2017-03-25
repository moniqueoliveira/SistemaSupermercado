
package sistemasupermercado.exceptions;

public class RetornoDeAlteracaoDeDadosInesperadoException extends RuntimeException {

    public RetornoDeAlteracaoDeDadosInesperadoException() {
        super("O retorno da alteração, exclusão ou inserção de informações no banco de dados foi diferente de 1.");
    }

    public RetornoDeAlteracaoDeDadosInesperadoException(String message) {
        super(message);
    }
    
}
