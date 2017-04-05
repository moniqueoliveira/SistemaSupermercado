
package sistemasupermercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.TelefoneFornecedor;
import sistemasupermercado.interfaces.dao.TelefoneFornecedorDAO;

public class TelefoneFornecedorDAOImpl implements TelefoneFornecedorDAO {
    private final Connection conexao;

    public TelefoneFornecedorDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public List<TelefoneFornecedor> listar(int idFornecedor) throws SQLException {
        List<TelefoneFornecedor> telefones = new ArrayList<>();
        String sql = "select * from telefones_fornecedores where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, idFornecedor);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            TelefoneFornecedor telefone = new TelefoneFornecedor(idFornecedor);
            telefone.setTelefone(rs.getString("telefone"));
            
            telefones.add(telefone);
        }
        pstm.close();
        return telefones;
    }

    @Override
    public boolean inserir(TelefoneFornecedor obj) throws SQLException {
        String sql = "insert into telefones_fornecedores values (?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getFornecedor().getIdFornecedor());
        pstm.setString(2, obj.getTelefone());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(TelefoneFornecedor obj) throws SQLException {
        String sql = "update telefones_fornecedores set telefone = ? where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getTelefone());
        pstm.setInt(2, obj.getFornecedor().getIdFornecedor());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(TelefoneFornecedor obj) throws SQLException {
        String sql = "delete from telefones_fornecedores where id_fornecedor = ? and telefone = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getFornecedor().getIdFornecedor());
        pstm.setString(2, obj.getTelefone());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public void fecharConexao() throws SQLException {
        this.conexao.close();
    }
    
}
