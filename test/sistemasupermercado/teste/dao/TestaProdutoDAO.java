
package sistemasupermercado.teste.dao;

import java.sql.SQLException;
import sistemasupermercado.dao.ProdutoDAOImpl;
import sistemasupermercado.dominio.Produto;
import sistemasupermercado.interfaces.dao.ProdutoDAO;

public class TestaProdutoDAO {
    public static void main(String[] args) throws SQLException {
        Produto produto = new Produto();
        ProdutoDAO dao = new ProdutoDAOImpl();
        
        produto.setCodigo(1);
        produto = dao.pesquisar(produto);
        
        System.out.println(produto.isVendaFracionada());
    }
}
