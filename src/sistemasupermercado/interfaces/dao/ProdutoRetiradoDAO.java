
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.ProdutoRetirado;

public interface ProdutoRetiradoDAO extends DAO<ProdutoRetirado> {

    public List<ProdutoRetirado> listar(String filtro) throws SQLException;
    
}
