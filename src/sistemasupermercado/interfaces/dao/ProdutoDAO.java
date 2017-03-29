
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Produto;

public interface ProdutoDAO extends DAOSemListar<Produto> {
    
    public List<Produto> listar(String pesquisaPor, String texto) throws SQLException;
    
}
