
package sistemasupermercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.Estoque;
import sistemasupermercado.interfaces.dao.EstoqueDAO;

public class EstoqueDAOImpl implements EstoqueDAO {
    private final Connection conexao;

    public EstoqueDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(Estoque obj) throws SQLException {
        String sql = "insert into estoques values (?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getUnidade().getIdUnidade());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        pstm.setDouble(3, obj.getQuantidade());
        pstm.setBigDecimal(4, obj.getValorTotal());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(Estoque obj) throws SQLException {
        String sql = "update estoques set quantidade = ?, valor_total = ? where id_unidade = ? and codigo = ?);";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setDouble(1, obj.getQuantidade());
        pstm.setBigDecimal(2, obj.getValorTotal());
        pstm.setInt(3, obj.getUnidade().getIdUnidade());
        pstm.setInt(4, obj.getProduto().getIdProduto());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public Estoque pesquisar(Estoque obj) throws SQLException {
        Estoque estoque = null;
        String sql = "select * from estoques where id_unidade = ? and codigo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getUnidade().getIdUnidade());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            estoque = new Estoque();
            estoque.setProduto(obj.getProduto());
            estoque.setUnidade(obj.getUnidade());
            estoque.setQuantidade(rs.getDouble("quantidade"));
            estoque.setValorTotal(rs.getBigDecimal("valor_total"));
        }
        pstm.close();
        return estoque;
    }

    @Override
    public List<Estoque> listar(String filtro) throws SQLException {
        List<Estoque> estoques = new ArrayList<>();
        String sql = "select * from estoques " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Estoque estoque = new Estoque();
            estoque.setProduto(rs.getInt("codigo"));
            estoque.setUnidade(rs.getInt("id_unidade"));
            estoque.setQuantidade(rs.getDouble("quantidade"));
            estoque.setValorTotal(rs.getBigDecimal("valor_total"));
            
            estoques.add(estoque);
        }
        pstm.close();
        return estoques;
    }
    
}
