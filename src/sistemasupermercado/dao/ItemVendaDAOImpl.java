
package sistemasupermercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.ItemVenda;
import sistemasupermercado.interfaces.dao.ItemVendaDAO;

public class ItemVendaDAOImpl implements ItemVendaDAO {
    private final Connection conexao;

    public ItemVendaDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(ItemVenda obj) throws SQLException {
        String sql = "insert into itens_vendas values (?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        pstm.setDouble(3, obj.getQuantidade());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(ItemVenda obj) throws SQLException {
        String sql = "update itens_vendas set quantidade = ? where id_venda = ? and codigo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setDouble(1, obj.getQuantidade());
        pstm.setInt(2, obj.getVenda().getIdVenda());
        pstm.setInt(3, obj.getProduto().getIdProduto());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(ItemVenda obj) throws SQLException {
        String sql = "delete from itens_vendas where id_venda = ? and codigo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    @Override
    public ItemVenda pesquisar(ItemVenda obj) throws SQLException {
        ItemVenda itemVenda = null;
        String sql = "select * from itens_vendas where id_venda = ? and codigo = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            itemVenda = new ItemVenda();
            itemVenda.setVenda(rs.getInt("id_venda"));
            itemVenda.setProduto(rs.getInt("codigo"));
            itemVenda.setQuantidade(rs.getDouble("quantidade"));
        }
        pstm.close();
        return itemVenda;
    }
    
    @Override
    public List<ItemVenda> listar(String filtro) throws SQLException {
        List<ItemVenda> itens = new ArrayList<>();
        String sql = "select * from itens_vendas " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(rs.getInt("id_venda"));
            itemVenda.setProduto(rs.getInt("codigo"));
            itemVenda.setQuantidade(rs.getDouble("quantidade"));
        }
        pstm.close();
        return itens;
    }

}
