
package sistemasupermercado.dao;

import sistemasupermercado.interfaces.dao.SessaoCaixaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.SessaoCaixa;

public class SessaoCaixaDAOImpl implements SessaoCaixaDAO {
    private final Connection conexao;

    public SessaoCaixaDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public boolean inserir(SessaoCaixa obj) throws SQLException {
        String sql = "insert into sessoes_caixas (id_sessao, valor_inicial_caixa, numero_caixa) values (?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getSessao().getIdSessao());
        pstm.setBigDecimal(2, obj.getValorInicialCaixa());
        pstm.setInt(3, obj.getCaixa().getNumeroCaixa());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public SessaoCaixa pesquisar(SessaoCaixa obj) throws SQLException {
        SessaoCaixa sessaoCaixa = null;
        String sql = "select * from sessoes_caixas where id_sessao = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getSessao().getIdSessao());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            sessaoCaixa = new SessaoCaixa();
            sessaoCaixa.setSessao(obj.getSessao());
            sessaoCaixa.setCaixa(rs.getInt("numero_caixa"));
            sessaoCaixa.setValorInicialCaixa(rs.getBigDecimal("valor_inicial_caixa"));
            sessaoCaixa.setValorFechamento(rs.getBigDecimal("valor_fechamento"));
        }
        pstm.close();
        return sessaoCaixa;
    }

    @Override
    public boolean alterarValorFechamento(SessaoCaixa obj) throws SQLException {
        String sql = "update sessoes_caixas set valor_fechamento = ? where id_sessao = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setBigDecimal(1, obj.getValorFechamento());
        pstm.setInt(2, obj.getSessao().getIdSessao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterarValorInicial(SessaoCaixa obj) throws SQLException {
        String sql = "update sessoes_caixas set valor_inicial_caixa = ? where id_sessao = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setBigDecimal(1, obj.getValorInicialCaixa());
        pstm.setInt(2, obj.getSessao().getIdSessao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }

    
}
