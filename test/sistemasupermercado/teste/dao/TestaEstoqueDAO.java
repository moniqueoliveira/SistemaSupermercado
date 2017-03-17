
package sistemasupermercado.teste.dao;

import java.sql.SQLException;
import sistemasupermercado.dao.EstoqueDAOImpl;
import sistemasupermercado.dominio.Estoque;
import sistemasupermercado.interfaces.dao.EstoqueDAO;

public class TestaEstoqueDAO {
    public static void main(String[] args) throws SQLException {
        Estoque estoque = new Estoque();
        EstoqueDAO dao = new EstoqueDAOImpl();
        
        estoque.setProduto(1);
        estoque.setUnidade(1);
        
        estoque = dao.pesquisar(estoque);
        System.out.println(estoque.getQuantidade() + "..." + estoque.getValorTotal());
    }
}
