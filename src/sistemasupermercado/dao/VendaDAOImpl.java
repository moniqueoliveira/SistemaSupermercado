
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
import sistemasupermercado.dominio.Venda;
import sistemasupermercado.interfaces.dao.VendaDAO;

public class VendaDAOImpl implements VendaDAO {
    private final Connection conexao;

    public VendaDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(Venda obj) throws SQLException {
        String sql = "insert into vendas (id_sessao, data, finalizada) values (?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getSessao().getIdSessao());
        
        Timestamp timestamp = new Timestamp(obj.getData().getTimeInMillis());
        pstm.setTimestamp(2, timestamp);
        
        pstm.setBoolean(3, obj.isFinalizada());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(Venda obj) throws SQLException {
       String sql = "update vendas set finalizada = ? where id_venda = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setBoolean(1, obj.isFinalizada());
        pstm.setInt(0, obj.getIdVenda());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
    @Override
    public Venda pesquisar(Venda obj) throws SQLException {
        Venda venda = null;
        String sql = "select * from vendas where id_venda = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdVenda());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            venda = new Venda();
            venda.setIdVenda(rs.getInt("id_venda"));
            venda.setFinalizada(rs.getBoolean("finalizada"));
            venda.setSessao(rs.getInt("id_sessao"));
            
            Timestamp timestamp = rs.getTimestamp("data");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp.getTime());
            venda.setData(calendar);
        }
        pstm.close();
        return venda;
    }

    @Override
    public List<Venda> listar(String filtro) throws SQLException {
        List<Venda> vendas = new ArrayList<>();
        String sql = "select * from vendas " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Venda venda = new Venda();
            venda.setIdVenda(rs.getInt("id_venda"));
            venda.setFinalizada(rs.getBoolean("finalizada"));
            venda.setSessao(rs.getInt("id_sessao"));
            
            Timestamp timestamp = rs.getTimestamp("data");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp.getTime());
            venda.setData(calendar);
            
            vendas.add(venda);
        }
        pstm.close();
        return vendas;
    }
    
}
