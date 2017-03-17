
package sistemasupermercado.teste.dao;

import java.sql.SQLException;
import sistemasupermercado.dao.ProdutoRetiradoDAOImpl;
import sistemasupermercado.dominio.ProdutoRetirado;
import sistemasupermercado.interfaces.dao.ProdutoRetiradoDAO;

public class TestaProdutoRetiradoDAO {
    public static void main(String[] args) throws SQLException {
        ProdutoRetirado produtoRetirado = new ProdutoRetirado();
        ProdutoRetiradoDAO dao = new ProdutoRetiradoDAOImpl();
        
        produtoRetirado.setIdRetirada(1);
        produtoRetirado = dao.pesquisar(produtoRetirado);
        
        System.out.println(produtoRetirado.getMotivo().getIdMotivo());
    }
}
