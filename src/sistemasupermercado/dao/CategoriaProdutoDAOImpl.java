
package sistemasupermercado.dao;

import sistemasupermercado.interfaces.dao.CategoriaProdutoDAO;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.CategoriaProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaProdutoDAOImpl implements CategoriaProdutoDAO {
    private final Connection conexao;

    public CategoriaProdutoDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(CategoriaProduto obj) throws SQLException {
        if (obj.getIdCategoria() == null) return inserirSemID(obj);
        return inserirComID(obj);
    }
    
    public boolean inserirSemID(CategoriaProduto obj) throws SQLException {
        String sql = "insert into categorias_produtos (descricao) values (?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
    public boolean inserirComID(CategoriaProduto obj) throws SQLException {
        String sql = "insert into categorias_produtos (id_categoria, descricao) values (?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdCategoria());
        pstm.setString(2, obj.getDescricao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(CategoriaProduto obj) throws SQLException {
        String sql = "update categorias_produtos set descricao = ? where id_categoria = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        pstm.setInt(2, obj.getIdCategoria());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;        
    }

    @Override
    public boolean excluir(CategoriaProduto obj) throws SQLException {
        String sql = "delete from categorias_produtos where id_categoria = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdCategoria());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public CategoriaProduto pesquisar(CategoriaProduto obj) throws SQLException {
        CategoriaProduto categoriaProduto = null;
        String sql = "select * from categorias_produtos where id_categoria = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdCategoria());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            categoriaProduto = new CategoriaProduto();
            categoriaProduto.setIdCategoria(obj.getIdCategoria());
            categoriaProduto.setDescricao(rs.getString("descricao"));
        }
        pstm.close();
        return categoriaProduto;
    }

    @Override
    public List<CategoriaProduto> listar(String pesquisarPor, String texto) throws SQLException {
        String filtro;
        switch(pesquisarPor) {
            case ("ID"):
                filtro = "where id_categoria like '%" + texto + "%'";
                break;
            default:
                filtro = "where descricao like '%" + texto + "%'";
        }
        return listar(filtro);
    }
    
    public List<CategoriaProduto> listar(String filtro) throws SQLException {
        List<CategoriaProduto> categorias = new ArrayList<>();
        String sql = "select * from categorias_produtos " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            CategoriaProduto categoriaProduto = new CategoriaProduto();
            categoriaProduto.setIdCategoria(rs.getInt("id_categoria"));
            categoriaProduto.setDescricao(rs.getString("descricao"));
            categorias.add(categoriaProduto);
        }
        pstm.close();
        return categorias;
    }
    
}
