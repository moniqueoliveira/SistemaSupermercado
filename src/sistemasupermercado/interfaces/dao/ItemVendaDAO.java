
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.ItemVenda;

public interface ItemVendaDAO extends DAO<ItemVenda> {
    
    public boolean excluirItens(int idVenda) throws SQLException;
    
    public List<ItemVenda> listar(int idVenda) throws SQLException;

}
