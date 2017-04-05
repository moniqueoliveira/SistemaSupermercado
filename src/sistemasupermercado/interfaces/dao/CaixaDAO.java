
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Caixa;

public interface CaixaDAO {
    
    public void fecharConexao() throws SQLException;
    
    public boolean inserir(Caixa obj) throws SQLException;
    
    public boolean excluir(Caixa obj) throws SQLException;
    
    public List<Caixa> listar(String filtro) throws SQLException;
    
}
