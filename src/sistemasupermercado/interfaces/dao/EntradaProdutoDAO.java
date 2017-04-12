
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.EntradaProduto;

public interface EntradaProdutoDAO {

    public void fecharConexao() throws SQLException;
    
    public boolean inserir(EntradaProduto obj) throws SQLException;
    
    public EntradaProduto pesquisar(EntradaProduto obj) throws SQLException;
    
    public List<EntradaProduto> listar(String pesquisarPor, String texto);
    
}
