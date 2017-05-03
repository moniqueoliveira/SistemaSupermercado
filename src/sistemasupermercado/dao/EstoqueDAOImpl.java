
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
        pstm.setBigDecimal(3, obj.getQuantidade());
        pstm.setBigDecimal(4, obj.getValorTotal());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(Estoque obj) throws SQLException {
        String sql = "update estoques set quantidade = ?, valor_total = ? where id_unidade = ? and id_produto = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setBigDecimal(1, obj.getQuantidade());
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
        String sql = "select * from estoques where id_unidade = ? and id_produto = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getUnidade().getIdUnidade());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            estoque = new Estoque();
            estoque.setProduto(obj.getProduto());
            estoque.setUnidade(obj.getUnidade());
            estoque.setQuantidade(rs.getBigDecimal("quantidade"));
            estoque.setValorTotal(rs.getBigDecimal("valor_total"));
        }
        pstm.close();
        return estoque;
    }

    @Override
    public List<Estoque> listar(String pesquisarPor, String texto, int idUnidade) throws SQLException {
        String filtro = new String();
        switch (pesquisarPor) {
            case ("ID Produto"):
                filtro = "where id_produto like '%" + texto + "%' and id_unidade = " + idUnidade;
                break;
            case ("Descrição"):
                filtro = "left join produtos on estoques.id_produto = produtos.id_produto where "
                        + "produtos.descricao like '%" + texto + "%' and estoques.id_unidade = " + idUnidade;
                break;
            case ("Descrição reduzida"):
                filtro = "left join produtos on estoques.id_produto = produtos.id_produto where "
                        + "produtos.descricao_reduzida like '%" + texto + "%' and estoques.id_unidade = " + idUnidade;
                break;
            case ("Codigo de barras"):
                filtro = "left join produtos on estoques.id_produto = produtos.id_produto where "
                        + "produtos.codigo_de_barras like '%" + texto + "%' and estoques.id_unidade = " + idUnidade;
                break;
            case ("Categoria"):
                filtro += "left join produtos on estoques.id_produto = produtos.id_produto left join "
                        + "categorias_produtos on categorias_produtos.id_categoria = produtos.id_categoria "
                        + "where categorias_produtos.descricao like '%" + texto + "%' and estoques.id_unidade = " + idUnidade;
        }
        return listar(filtro);
    }
    
    private List<Estoque> listar(String filtro) throws SQLException {
        List<Estoque> estoques = new ArrayList<>();
        String sql = "select * from estoques " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Estoque estoque = new Estoque();
            estoque.setProduto(rs.getInt("id_produto"));
            estoque.setUnidade(rs.getInt("id_unidade"));
            estoque.setQuantidade(rs.getBigDecimal("quantidade"));
            estoque.setValorTotal(rs.getBigDecimal("valor_total"));
            
            estoques.add(estoque);
        }
        pstm.close();
        return estoques;
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }
    
}
