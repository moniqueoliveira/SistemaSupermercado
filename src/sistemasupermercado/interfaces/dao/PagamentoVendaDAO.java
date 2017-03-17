
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import sistemasupermercado.dominio.PagamentoVenda;

public interface PagamentoVendaDAO {
    
    public boolean inserir(PagamentoVenda obj) throws SQLException;
    
    public boolean alterar(PagamentoVenda obj) throws SQLException;
    
    public boolean excluir(PagamentoVenda obj) throws SQLException;
    
    public PagamentoVenda pesquisar(PagamentoVenda obj) throws SQLException;
    
}
