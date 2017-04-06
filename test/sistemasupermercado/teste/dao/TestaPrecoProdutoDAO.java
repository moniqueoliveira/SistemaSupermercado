
package sistemasupermercado.teste.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import sistemasupermercado.dao.PrecoProdutoDAOImpl;
import sistemasupermercado.dominio.PrecoProduto;
import sistemasupermercado.interfaces.dao.PrecoProdutoDAO;

public class TestaPrecoProdutoDAO {
    public static void main(String[] args) throws SQLException {
        //testaInserir();
        testaComparacaoDatas();
    }

    private static void testaComparacaoDatas() {
        PrecoProduto precoProduto1 = new PrecoProduto();
        PrecoProduto precoProduto2 = new PrecoProduto();
        
        Calendar data = Calendar.getInstance();
        Date date = new Date(2017, 05, 03);
        data.setTime(date);
        precoProduto1.setData(data);
        
        data.setTimeInMillis(System.currentTimeMillis());
        precoProduto2.setData(data);
        
        System.out.println(precoProduto1.getData().after(precoProduto2.getData()));
        
        
    }
    
    public void testaInserir() throws SQLException {
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
