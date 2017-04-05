
package sistemasupermercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.MotivoProdutoRetirado;
import sistemasupermercado.interfaces.dao.MotivoProdutoRetiradoDAO;

public class MotivoProdutoRetiradoDAOImpl implements MotivoProdutoRetiradoDAO {
    private final Connection conexao;

    public MotivoProdutoRetiradoDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(MotivoProdutoRetirado obj) throws SQLException {
        if (obj.getIdMotivo() == null) return inserirSemID(obj);
        else return inserirComId(obj);
    }
    
    private boolean inserirComId(MotivoProdutoRetirado obj) throws SQLException {
        String sql = "insert into motivos_produtos_retirados values (?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdMotivo());
        pstm.setString(2, obj.getDescricao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
    private boolean inserirSemID(MotivoProdutoRetirado obj) throws SQLException {
        String sql = "insert into motivos_produtos_retirados (descricao) values (?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(MotivoProdutoRetirado obj) throws SQLException {
        String sql = "update motivos_produtos_retirados set descricao = ? where id_motivo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        pstm.setInt(2, obj.getIdMotivo());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(MotivoProdutoRetirado obj) throws SQLException {
        String sql = "delete from motivos_produtos_retirados where id_motivo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdMotivo());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public MotivoProdutoRetirado pesquisar(MotivoProdutoRetirado obj) throws SQLException {
        MotivoProdutoRetirado motivoProdutoRetirado = null;
        String sql = "select * from motivos_produtos_retirados where id_motivo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdMotivo());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            motivoProdutoRetirado = new MotivoProdutoRetirado();
            motivoProdutoRetirado.setIdMotivo(rs.getInt("id_motivo"));
            motivoProdutoRetirado.setDescricao(rs.getString("descricao"));
        }
        pstm.close();
        return motivoProdutoRetirado;
    }
    
    @Override
    public List<MotivoProdutoRetirado> listar(String pesquisaPor, String texto) throws SQLException {
        if (pesquisaPor.equals("ID")){
            return listar("where id_motivo like '%" + texto + "%'");
        } else { 
            if (pesquisaPor.equals("Descrição")) {
                return listar("where descricao like '%" + texto + "%'");
            } else {
                return listar("");
            }
        }
    }

    
    private List<MotivoProdutoRetirado> listar(String filtro) throws SQLException {
        List<MotivoProdutoRetirado> motivos = new ArrayList<>();
        String sql = "select * from motivos_produtos_retirados " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            MotivoProdutoRetirado motivoProdutoRetirado = new MotivoProdutoRetirado();
            motivoProdutoRetirado.setIdMotivo(rs.getInt("id_motivo"));
            motivoProdutoRetirado.setDescricao(rs.getString("descricao"));
            motivos.add(motivoProdutoRetirado);
        }
        pstm.close();
        return motivos;
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }
    
}
