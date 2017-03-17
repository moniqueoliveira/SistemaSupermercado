
package sistemasupermercado.teste.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import sistemasupermercado.dao.PrecoProdutoDAOImpl;
import sistemasupermercado.dominio.PrecoProduto;
import sistemasupermercado.interfaces.dao.PrecoProdutoDAO;

public class TestaPrecoProdutoDAO {
    public static void main(String[] args) throws SQLException {
        PrecoProduto precoProduto = new PrecoProduto();
        PrecoProdutoDAO dao = new PrecoProdutoDAOImpl();
        
        precoProduto.setProduto(1);
        precoProduto.setUnidade(1);
        precoProduto.setValor(new BigDecimal(5.00));
        
        Calendar data = Calendar.getInstance();
        data.setTimeInMillis(System.currentTimeMillis());
        precoProduto.setData(data);
        
        dao.inserir(precoProduto);
    }
}
