
package sistemasupermercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.EmailFornecedor;
import sistemasupermercado.interfaces.dao.EmailFornecedorDAO;

public class EmailFornecedorDAOImpl implements EmailFornecedorDAO {
    private final Connection conexao;

    public EmailFornecedorDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    @Override
    public List<EmailFornecedor> listar(int idFornecedor) throws SQLException {
        List<EmailFornecedor> emails = new ArrayList<>();
        String sql = "select * from emails_fornecedores where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, idFornecedor);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            EmailFornecedor email = new EmailFornecedor(idFornecedor);
            email.setEmail(rs.getString("email"));
            
            emails.add(email);
        }
        pstm.close();
        return emails;
    }

    @Override
    public boolean inserir(EmailFornecedor obj) throws SQLException {
        String sql = "insert into emails_fornecedores values (?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getFornecedor().getIdFornecedor());
        pstm.setString(2, obj.getEmail());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(EmailFornecedor obj) throws SQLException {
        String sql = "update emails_fornecedores set email = ? where id_fornecedor = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getEmail());
        pstm.setInt(2, obj.getFornecedor().getIdFornecedor());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(EmailFornecedor obj) throws SQLException {
        String sql = "delete from emails_fornecedores where id_fornecedor = ? and email = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getFornecedor().getIdFornecedor());
        pstm.setString(2, obj.getEmail());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }
    
}
