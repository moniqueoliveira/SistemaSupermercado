
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Venda;

public interface VendaDAO {
    
    public void fecharConexao() throws SQLException;
    
    public boolean inserir(Venda obj) throws SQLException;
    
    public boolean alterar(Venda obj) throws SQLException;
    
    public Venda pesquisar(Venda obj) throws SQLException;
    
    public List<Venda> listar(String filtro) throws SQLException;
    
}
