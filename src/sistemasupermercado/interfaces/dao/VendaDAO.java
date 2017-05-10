
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Venda;

public interface VendaDAO extends DAO<Venda>{
    
    public Integer obterUltimoID() throws SQLException;
    
    public List<Venda> listar(int idSessao) throws SQLException;
    
}
