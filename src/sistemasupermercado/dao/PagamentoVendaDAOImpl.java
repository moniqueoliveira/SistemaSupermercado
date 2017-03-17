
package sistemasupermercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.PagamentoVenda;
import sistemasupermercado.interfaces.dao.PagamentoVendaDAO;

public class PagamentoVendaDAOImpl implements PagamentoVendaDAO {
    private final Connection conexao;

    public PagamentoVendaDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(PagamentoVenda obj) throws SQLException {
        String sql = "insert into pagamentos_vendas values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        pstm.setBigDecimal(2, obj.getDinheiro());
        pstm.setBigDecimal(3, obj.getDebito());
        pstm.setBigDecimal(4, obj.getCredito());
        pstm.setBigDecimal(5, obj.getVoucher());
        pstm.setBigDecimal(6, obj.getCheque());
        pstm.setBigDecimal(8, obj.getOutros());
        pstm.setBigDecimal(8, obj.getTroco());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(PagamentoVenda obj) throws SQLException {
        String sql = "update pagamentos_vendas set dinheito = ?, debito = ?, credito = ?, voucher = ?, cheque =? "
                + "outros = ?, troco = ? where id_venda = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setBigDecimal(1, obj.getDinheiro());
        pstm.setBigDecimal(2, obj.getDebito());
        pstm.setBigDecimal(3, obj.getCredito());
        pstm.setBigDecimal(4, obj.getVoucher());
        pstm.setBigDecimal(5, obj.getCheque());
        pstm.setBigDecimal(6, obj.getOutros());
        pstm.setBigDecimal(7, obj.getTroco());
        pstm.setInt(8, obj.getVenda().getIdVenda());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(PagamentoVenda obj) throws SQLException {
        String sql = "delete from pagamentos where venda = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public PagamentoVenda pesquisar(PagamentoVenda obj) throws SQLException {
        PagamentoVenda pagamentoVenda = null;
        String sql = "select * from pagamentos_vendas where id_venda = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            pagamentoVenda = new PagamentoVenda();
            pagamentoVenda.setVenda(rs.getInt("id_venda"));
            pagamentoVenda.setDinheiro(rs.getBigDecimal("dinheiro"));
            pagamentoVenda.setDebito(rs.getBigDecimal("debito"));
            pagamentoVenda.setCredito(rs.getBigDecimal("credito"));
            pagamentoVenda.setVoucher(rs.getBigDecimal("voucher"));
        }
        pstm.close();
        return pagamentoVenda;
    }
    
}
