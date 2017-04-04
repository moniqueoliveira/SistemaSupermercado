
package sistemasupermercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.PrecoProduto;
import sistemasupermercado.interfaces.dao.PrecoProdutoDAO;

public class PrecoProdutoDAOImpl implements PrecoProdutoDAO {
    private final Connection conexao;

    public PrecoProdutoDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(PrecoProduto obj) throws SQLException {
        String sql = "insert into precos_produtos values (?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getUnidade().getIdUnidade());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        pstm.setBigDecimal(3, obj.getValor());
        
        Timestamp timestamp = new Timestamp(obj.getData().getTimeInMillis());
        pstm.setTimestamp(4, timestamp);
        
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(PrecoProduto obj) throws SQLException {
        String sql = "delete from precos_produtos where id_unidade =? and codigo = ? and data = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getUnidade().getIdUnidade());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        
        Timestamp timestamp = new Timestamp(obj.getData().getTimeInMillis());
        pstm.setTimestamp(3, timestamp);
        
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public PrecoProduto pesquisar(PrecoProduto obj) throws SQLException {
        PrecoProduto precoProduto = null;
        String sql = "select (valor) from precos_produtos where id_unidade = ?, codigo = ? and data = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getUnidade().getIdUnidade());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        
        Timestamp timestamp = new Timestamp(obj.getData().getTimeInMillis());
        pstm.setTimestamp(3, timestamp);
        
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            precoProduto = obj;
            precoProduto.setValor(rs.getBigDecimal("valor"));
        }
        pstm.close();
        return precoProduto;
    }

    @Override
    public List<PrecoProduto> listar(String filtro) throws SQLException {
        List<PrecoProduto> precos = new ArrayList<>();
        String sql = "select * from precos_produtos " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            PrecoProduto precoProduto = new PrecoProduto();
            precoProduto.setValor(rs.getBigDecimal("valor"));
            precoProduto.setProduto(rs.getInt("codigo"));
            precoProduto.setUnidade(rs.getInt("id_unidade"));
            
            Timestamp timestamp = rs.getTimestamp("data");
            Calendar data = Calendar.getInstance();
            data.setTimeInMillis(timestamp.getTime());
            
            precos.add(precoProduto);
        }
        pstm.close();
        return precos;
    }
    
}
