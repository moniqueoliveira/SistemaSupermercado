
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.ProdutoRetirado;

public interface ProdutoRetiradoDAO {
    
    public void fecharConexao() throws SQLException;
    
    public boolean inserir(ProdutoRetirado obj) throws SQLException;
    
    public ProdutoRetirado pesquisar(ProdutoRetirado obj) throws SQLException;
    
    public List<ProdutoRetirado> listar(String pesquisarPor, String texto, int idUnidade) throws SQLException;
    
}
