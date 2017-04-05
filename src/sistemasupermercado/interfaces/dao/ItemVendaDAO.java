
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.ItemVenda;

public interface ItemVendaDAO extends DAO<ItemVenda> {
    
    public List<ItemVenda> listar(String filtro) throws SQLException;

}
