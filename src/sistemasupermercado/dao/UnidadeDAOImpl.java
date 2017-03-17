package sistemasupermercado.dao;

import java.sql.Connection;
import sistemasupermercado.dominio.Unidade;
import sistemasupermercado.conexao.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.interfaces.dao.UnidadeDAO;

public class UnidadeDAOImpl implements UnidadeDAO {
    private final Connection conexao;
    
    public UnidadeDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(Unidade obj) throws SQLException {
        String sql = "insert into unidades(descricao) values (?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
    @Override
    public boolean alterar(Unidade obj) throws SQLException {
        String sql = "update unidades set descricao = ? where codigo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        pstm.setInt(2, obj.getIdUnidade());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(Unidade obj) throws SQLException {
        String sql = "delete from unidades where id_unidade = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdUnidade());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public Unidade pesquisar(Unidade obj) throws SQLException {
        Unidade unidade = null;
        String sql = "select descricao from unidades where id_unidade = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdUnidade());
        ResultSet rs = pstm.executeQuery();
        
        if (rs.next()) {
            unidade = new Unidade();
            unidade.setIdUnidade(obj.getIdUnidade());
            unidade.setDescricao(rs.getString("descricao"));
        }
        pstm.close();
        return unidade;
    }

    @Override
    public List<Unidade> listar(String filtro) throws SQLException {
        List<Unidade> unidades = new ArrayList<>();
        String sql = "select * from unidades " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) {
            Unidade unidade = new Unidade();
            unidade.setIdUnidade(rs.getInt("id_unidade"));
            unidade.setDescricao("descricao");
            unidades.add(unidade);
        }
        pstm.close();
        return unidades;
    }
    
}
