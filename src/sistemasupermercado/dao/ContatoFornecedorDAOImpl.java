
package sistemasupermercado.dao;

import sistemasupermercado.interfaces.dao.ContatoFornecedorDAO;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.ContatoFornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContatoFornecedorDAOImpl implements ContatoFornecedorDAO {
    private final Connection conexao;

    public ContatoFornecedorDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public boolean inserir(ContatoFornecedor obj) throws SQLException {
        String sql = "insert into contatos_fornecedores (telefone, rua, numero, cep, complemento, bairro, cidade, uf)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getTelefone());
        pstm.setString(2, obj.getRua());
        pstm.setInt(3, obj.getNumero());
        pstm.setString(4, obj.getCep());
        pstm.setString(5, obj.getComplemtento());
        pstm.setString(6, obj.getBairro());
        pstm.setString(7, obj.getCidade());
        pstm.setString(8, obj.getUf());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(ContatoFornecedor obj) throws SQLException {
        String sql = "update contatos_fornecedores set telefone = ?, rua = ?, numero = ?, cep = ?, complemento = ?, "
                + "bairro = ?, cidade = ?, uf = ? where id_contato = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getTelefone());
        pstm.setString(2, obj.getRua());
        pstm.setInt(3, obj.getNumero());
        pstm.setString(4, obj.getCep());
        pstm.setString(5, obj.getComplemtento());
        pstm.setString(6, obj.getBairro());
        pstm.setString(7, obj.getCidade());
        pstm.setString(8, obj.getUf());
        pstm.setInt(9, obj.getIdContatoFornecedor());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(ContatoFornecedor obj) throws SQLException {
        String sql = "delete from contatos_fornecedores where id_contato = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdContatoFornecedor());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public ContatoFornecedor pesquisar(ContatoFornecedor obj) throws SQLException {
        ContatoFornecedor contatoFornecedor = null;
        String sql = "select * from contatos_fornecedores where id_contato = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdContatoFornecedor());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            contatoFornecedor = new ContatoFornecedor();
            contatoFornecedor.setIdContatoFornecedor(obj.getIdContatoFornecedor());
            contatoFornecedor.setTelefone(rs.getString("telefone"));
            contatoFornecedor.setRua(rs.getString("rua"));
            contatoFornecedor.setNumero(rs.getInt("numero"));
            contatoFornecedor.setCep(rs.getString("cep"));
            contatoFornecedor.setComplemtento(rs.getString("complemento"));
            contatoFornecedor.setBairro(rs.getString("bairro"));
            contatoFornecedor.setCidade(rs.getString("cidade"));
            contatoFornecedor.setUf(rs.getString("uf"));
        }
        pstm.close();
        return contatoFornecedor;
    }
    
}
