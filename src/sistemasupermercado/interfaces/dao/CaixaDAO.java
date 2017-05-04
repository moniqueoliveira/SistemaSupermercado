
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Caixa;

public interface CaixaDAO extends DAO<Caixa>{
    
    public List<Caixa> listar(int idUnidade) throws SQLException;
    
}
