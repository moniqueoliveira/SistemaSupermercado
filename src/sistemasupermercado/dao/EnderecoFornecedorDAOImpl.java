
package sistemasupermercado.dao;

import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.EnderecoFornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistemasupermercado.interfaces.dao.EnderecoFornecedorDAO;

public class EnderecoFornecedorDAOImpl implements EnderecoFornecedorDAO {
    private final Connection conexao;

    public EnderecoFornecedorDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(EnderecoFornecedor obj) throws SQLException {
        String sql = "insert into enderecos_fornecedores (logradouro, numero, cep, complemento, bairro, cidade, uf, if_fornecedor)"
                + " values (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getLogradouro());
        pstm.setInt(2, obj.getNumero());
        pstm.setString(3, obj.getCep());
        pstm.setString(4, obj.getComplemtento());
        pstm.setString(5, obj.getBairro());
        pstm.setString(6, obj.getCidade());
        pstm.setString(7, obj.getUf());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(EnderecoFornecedor obj) throws SQLException {
        String sql = "update enderecos_fornecedores set logradouro = ?, numero = ?, cep = ?, complemento = ?, "
                + "bairro = ?, cidade = ?, uf = ? where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getLogradouro());
        pstm.setInt(2, obj.getNumero());
        pstm.setString(3, obj.getCep());
        pstm.setString(4, obj.getComplemtento());
        pstm.setString(5, obj.getBairro());
        pstm.setString(6, obj.getCidade());
        pstm.setString(7, obj.getUf());
        pstm.setInt(8, obj.getFornecedor().getIdFornecedor());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(EnderecoFornecedor obj) throws SQLException {
        String sql = "delete from enderecos_fornecedores where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getFornecedor().getIdFornecedor());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public EnderecoFornecedor pesquisar(EnderecoFornecedor obj) throws SQLException {
        EnderecoFornecedor enderecoFornecedor = null;
        String sql = "select * from enderecos_fornecedores where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getFornecedor().getIdFornecedor());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            enderecoFornecedor = new EnderecoFornecedor(obj.getFornecedor());
            enderecoFornecedor.setLogradouro(rs.getString("logradouro"));
            enderecoFornecedor.setNumero(rs.getInt("numero"));
            enderecoFornecedor.setCep(rs.getString("cep"));
            enderecoFornecedor.setComplemtento(rs.getString("complemento"));
            enderecoFornecedor.setBairro(rs.getString("bairro"));
            enderecoFornecedor.setCidade(rs.getString("cidade"));
            enderecoFornecedor.setUf(rs.getString("uf"));
        }
        pstm.close();
        return enderecoFornecedor;
    }
    
}
