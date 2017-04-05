
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dominio.Unidade;

public interface UnidadeDAO extends DAO<Unidade> {

    public List<Unidade> listar(String filtro) throws SQLException;

}
