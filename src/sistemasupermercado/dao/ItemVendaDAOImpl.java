
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
        String sql = "insert into itens_vendas(id_venda, id_produto, quantidade, numero_item, subtotal) values (?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        pstm.setBigDecimal(3, obj.getQuantidade());
        pstm.setInt(4, obj.getNumeroItem());
        pstm.setBigDecimal(5, obj.getSubtotal());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(ItemVenda obj) throws SQLException {
        String sql = "update itens_vendas set quantidade = ?, subtotal = ?, cancelado = ? where id_venda = ? and id_produto = ? and numero_item = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setBigDecimal(1, obj.getQuantidade());
        pstm.setBigDecimal(2, obj.getSubtotal());
        pstm.setBoolean(3, obj.isCancelado());
        pstm.setInt(4, obj.getVenda().getIdVenda());
        pstm.setInt(5, obj.getProduto().getIdProduto());
        pstm.setInt(6, obj.getNumeroItem());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(ItemVenda obj) throws SQLException {
        String sql = "delete from itens_vendas where id_venda = ? and id_produto = ? and numero_item = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        pstm.setInt(3, obj.getNumeroItem());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
    @Override
    public boolean excluirItens(int idVenda) throws SQLException {
        String sql = "delete from itens_vendas where id_venda = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, idVenda);
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
    @Override
    public ItemVenda pesquisar(ItemVenda obj) throws SQLException {
        ItemVenda itemVenda = null;
        String sql = "select * from itens_vendas where id_venda = ? and id_produto = ? and numero_item = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getVenda().getIdVenda());
        pstm.setInt(2, obj.getProduto().getIdProduto());
        pstm.setInt(3, obj.getNumeroItem());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            itemVenda = new ItemVenda();
            itemVenda.setVenda(rs.getInt("id_venda"));
            itemVenda.setProduto(rs.getInt("id_produto"));
            itemVenda.setQuantidade(rs.getBigDecimal("quantidade"));
            itemVenda.setNumeroItem(rs.getInt("numero_item"));
            itemVenda.setSubtotal(rs.getBigDecimal("subtotal"));
            itemVenda.setCancelado(rs.getBoolean("cancelado"));
        }
        pstm.close();
        return itemVenda;
    }
    
    @Override
    public List<ItemVenda> listar(int idVenda) throws SQLException {
        return listar("where id_venda = " + idVenda);
    }
    
    private List<ItemVenda> listar(String filtro) throws SQLException {
        List<ItemVenda> itens = new ArrayList<>();
        String sql = "select * from itens_vendas " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setVenda(rs.getInt("id_venda"));
            itemVenda.setProduto(rs.getInt("id_produto"));
            itemVenda.setQuantidade(rs.getBigDecimal("quantidade"));
            itemVenda.setNumeroItem(rs.getInt("numero_item"));
            itemVenda.setSubtotal(rs.getBigDecimal("subtotal"));
            itemVenda.setCancelado(rs.getBoolean("cancelado"));
            itens.add(itemVenda);
        }
        pstm.close();
        return itens;
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }

}
