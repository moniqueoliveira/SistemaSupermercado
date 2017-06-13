
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
import sistemasupermercado.dominio.ProdutoRetirado;
import sistemasupermercado.interfaces.dao.ProdutoRetiradoDAO;

public class ProdutoRetiradoDAOImpl implements ProdutoRetiradoDAO {
    private final Connection conexao;

    public ProdutoRetiradoDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(ProdutoRetirado obj) throws SQLException {
        String sql = "insert into produtos_retirados (id_produto, quantidade, data, id_sessao, id_motivo, observacao) "
                + "values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getProduto().getIdProduto());
        pstm.setBigDecimal(2, obj.getQuantidade());
        
        Timestamp timestamp = new Timestamp(obj.getData().getTimeInMillis());
        pstm.setTimestamp(3, timestamp);
        
        pstm.setInt(4, obj.getSessao().getIdSessao());
        pstm.setInt(5, obj.getMotivo().getIdMotivo());
        pstm.setString(6, obj.getObservacao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    /*@Override
    public boolean alterar(ProdutoRetirado obj) throws SQLException {
        String sql = "update produtos_retirados set id_produto = ?, quantidade = ?, data = ?, id_sessao = ?, "
                + "id_motivo = ? where id_retirada = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getProduto().getIdProduto());
        pstm.setBigDecimal(2, obj.getQuantidade());
        
        Timestamp timestamp = new Timestamp(obj.getData().getTimeInMillis());
        pstm.setTimestamp(3, timestamp);
        
        pstm.setInt(4, obj.getSessao().getIdSessao());
        pstm.setInt(5, obj.getMotivo().getIdMotivo());
        pstm.setInt(6, obj.getIdRetirada());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }*/

    /*@Override
    public boolean excluir(ProdutoRetirado obj) throws SQLException {
        String sql = "delete from produtos_retirados where id_retirada = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdRetirada());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }*/

    @Override
    public ProdutoRetirado pesquisar(ProdutoRetirado obj) throws SQLException {
        ProdutoRetirado produtoRetirado = null;
        String sql = "select * from produtos_retirados where id_retirada = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdRetirada());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            produtoRetirado = new ProdutoRetirado();
            produtoRetirado.setIdRetirada(rs.getInt("id_retirada"));
            produtoRetirado.setProduto(rs.getInt("id_produto"));
            produtoRetirado.setSessao(rs.getInt("id_sessao"));
            produtoRetirado.setMotivo(rs.getInt("id_motivo"));
            produtoRetirado.setQuantidade(rs.getBigDecimal("quantidade"));
            produtoRetirado.setObservacao(rs.getString("observacao"));
            
            Timestamp timestamp = rs.getTimestamp("data");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp.getTime());
            produtoRetirado.setData(calendar);
        }
        pstm.close();
        return produtoRetirado;
    }

    @Override
    public List<ProdutoRetirado> listar(String pesquisarPor, String texto, int idUnidade) throws SQLException {
        String filtro = new String();
        switch (pesquisarPor){
            case ("ID"):
                filtro = "pr left join sessoes s on pr.id_sessao = s.id_sessao left join usuarios us on "
                        + "s.id_usuario = us.id_usuario left join unidades un on us.id_unidade = un.id_unidade "
                        + "where pr.id_retirada like '%" + texto + "%' and un.id_unidade = " + idUnidade;
                break;
            case ("ID Produto"):
                filtro = "pr left join sessoes s on pr.id_sessao = s.id_sessao left join usuarios us on "
                        + "s.id_usuario = us.id_usuario left join unidades un on us.id_unidade = un.id_unidade "
                        + "where pr.id_produto like '%" + texto + "%' and un.id_unidade = " + idUnidade;
                break;
            case ("Produto"):
                filtro = "pr left join produtos p on pr.id_produto = p.id_produto "
                        + "left join sessoes s on pr.id_sessao = s.id_sessao left join usuarios us on "
                        + "s.id_usuario = us.id_usuario left join unidades un on us.id_unidade = un.id_unidade "
                        + "where p.descricao like '%" + texto + "%' and un.id_unidade = " + idUnidade;
                break;
            default:
                filtro = "pr left join sessoes s on pr.id_sessao = s.id_sessao left join usuarios us on "
                        + "s.id_usuario = us.id_usuario left join unidades un on us.id_unidade = un.id_unidade "
                        + "where un.id_unidade = " + idUnidade;
        }        
        return listar(filtro);
    }
    
    public List<ProdutoRetirado> listar(String filtro) throws SQLException {
        List<ProdutoRetirado> produtosRetirados = new ArrayList<>();
        String sql = "select * from produtos_retirados " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            ProdutoRetirado produtoRetirado = new ProdutoRetirado();
            produtoRetirado.setIdRetirada(rs.getInt("id_retirada"));
            produtoRetirado.setProduto(rs.getInt("id_produto"));
            produtoRetirado.setSessao(rs.getInt("id_sessao"));
            produtoRetirado.setMotivo(rs.getInt("id_motivo"));
            produtoRetirado.setQuantidade(rs.getBigDecimal("quantidade"));
            produtoRetirado.setObservacao(rs.getString("observacao"));
            
            Timestamp timestamp = rs.getTimestamp("data");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp.getTime());
            produtoRetirado.setData(calendar);
            
            produtosRetirados.add(produtoRetirado);
        }
        pstm.close();
        return produtosRetirados;
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }
    
}
