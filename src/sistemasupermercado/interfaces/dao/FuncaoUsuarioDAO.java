
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.FuncaoUsuario;

public interface FuncaoUsuarioDAO extends DAO<FuncaoUsuario> {

    public List<FuncaoUsuario> listar(String pesquisaPor, String texto) throws SQLException;
    
}
