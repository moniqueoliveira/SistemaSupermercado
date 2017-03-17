
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Estoque;

public interface EstoqueDAO {
    
    public boolean inserir(Estoque obj) throws SQLException;
    
    public boolean alterar(Estoque obj) throws SQLException;
    
    public Estoque pesquisar(Estoque obj) throws SQLException;
    
    public List<Estoque> listar(String filtro) throws SQLException;
}
