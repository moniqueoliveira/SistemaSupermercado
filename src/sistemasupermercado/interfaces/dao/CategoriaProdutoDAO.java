
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.CategoriaProduto;

public interface CategoriaProdutoDAO extends DAOSemListar<CategoriaProduto>{
    
    public List<CategoriaProduto> listar(String pesquisaPor, String texto) throws SQLException;
    
}
