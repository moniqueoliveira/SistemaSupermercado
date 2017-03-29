
package sistemasupermercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.Produto;
import sistemasupermercado.interfaces.dao.ProdutoDAO;

public class ProdutoDAOImpl implements ProdutoDAO {
    private final Connection conexao;

    public ProdutoDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(Produto obj) throws SQLException {
        String sql = "insert into produtos (codigo, descricao, descricao_reduzida, venda_fracionada, id_categoria, "
                + "codigo_de_barras) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getCodigo());
        pstm.setString(2, obj.getDescricao());
        pstm.setString(3, obj.getDescricaoReduzida());
        pstm.setBoolean(4, obj.isVendaFracionada());
        pstm.setInt(5, obj.getCategoria().getIdCategoria());
        pstm.setString(6, obj.getCodigoDeBarras());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(Produto obj) throws SQLException {
        String sql = "update produtos set descricao = ?, descricao_reduzida = ?, venda_fracionada = ?, "
                + "id_categoria = ?, codigo_de_barras = ? where codigo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getDescricao());
        pstm.setString(2, obj.getDescricaoReduzida());
        pstm.setBoolean(3, obj.isVendaFracionada());
        pstm.setInt(4, obj.getCategoria().getIdCategoria());
        pstm.setString(5, obj.getCodigoDeBarras());
        pstm.setInt(6, obj.getCodigo());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(Produto obj) throws SQLException {
        String sql = "delete from produtos where codigo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getCodigo());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public Produto pesquisar(Produto obj) throws SQLException {
        Produto produto = null;
        String sql = "select * from produtos where codigo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getCodigo());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            produto = new Produto();
            produto.setCodigo(rs.getInt("codigo"));
            produto.setCategoria(rs.getInt("id_categoria"));
            produto.setCodigoDeBarras(rs.getString("codigo_de_barras"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setDescricaoReduzida(rs.getString("descricao_reduzida"));
            produto.setVendaFracionada(rs.getBoolean("venda_fracionada"));
        }
        pstm.close();
        return produto;
    }

    @Override
    public List<Produto> listar(String pesquisaPor, String texto) throws SQLException {
        return listar(texto);
    }
    
    private List<Produto> listar(String filtro) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "select * from produtos " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setCodigo(rs.getInt("codigo"));
            produto.setCategoria(rs.getInt("id_categoria"));
            produto.setCodigoDeBarras(rs.getString("codigo_de_barras"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setDescricaoReduzida(rs.getString("descricao_reduzida"));
            produto.setVendaFracionada(rs.getBoolean("venda_fracionada"));
        }
        pstm.close();
        return produtos;
    }
    
}
