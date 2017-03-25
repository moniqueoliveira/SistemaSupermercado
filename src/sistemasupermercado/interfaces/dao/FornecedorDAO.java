
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Fornecedor;


public interface FornecedorDAO extends DAOSemListar<Fornecedor> {
    
    public List<Fornecedor> listar(String pesquisaPor, String texto) throws SQLException;

    public Integer obterUltimoID() throws SQLException;
    
}
