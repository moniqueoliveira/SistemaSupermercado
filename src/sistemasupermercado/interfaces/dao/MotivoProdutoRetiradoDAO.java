
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.MotivoProdutoRetirado;

public interface MotivoProdutoRetiradoDAO extends DAOSemListar<MotivoProdutoRetirado> {
    
    public List<MotivoProdutoRetirado> listar(String pesquisaPor, String texto) throws SQLException;
    
}
