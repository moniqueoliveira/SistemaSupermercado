
package sistemasupermercado.dao;

import sistemasupermercado.conexao.ConnectionFactory;
import sistemasupermercado.dominio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemasupermercado.interfaces.dao.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO {
    private final Connection conexao;

    public UsuarioDAOImpl() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public boolean inserir(Usuario obj) throws SQLException {
        String sql = "insert into usuarios (nome, login, senha, id_unidade, id_funcao) values (?, ?, ?, ?, ?)";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getNome());
        pstm.setString(2, obj.getLogin());
        pstm.setString(3, obj.getSenha());
        pstm.setInt(4, obj.getUnidade().getIdUnidade());
        pstm.setInt(5, obj.getFuncaoUsuario().getIdFuncao());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean alterar(Usuario obj) throws SQLException {
        String sql = "update usuarios set nome = ?, login = ?, senha = ?, id_unidade = ?, id_funcao = ? where id_usuario = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setString(1, obj.getNome());
        pstm.setString(2, obj.getLogin());
        pstm.setString(3, obj.getSenha());
        pstm.setInt(4, obj.getUnidade().getIdUnidade());
        pstm.setInt(5, obj.getFuncaoUsuario().getIdFuncao());
        pstm.setInt(6, obj.getIdUsuario());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public boolean excluir(Usuario obj) throws SQLException {
        String sql = "delete from usuarios where id_usuario = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdUsuario());
        int result = pstm.executeUpdate();
        pstm.close();
        return result == 1;
    }

    @Override
    public Usuario pesquisar(Usuario obj) throws SQLException {
        Usuario usuario = null;
        String sql = "select * from usuarios where id_usuario = ?";
        PreparedStatement pstm = conexao.prepareStatement(sql);
        pstm.setInt(1, obj.getIdUsuario());
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setIdUsuario(obj.getIdUsuario());
            usuario.setNome(rs.getString("nome"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setUnidade(rs.getInt("id_unidade"));
            usuario.setFuncaoUsuario(rs.getInt("id_funcao"));
        }
        pstm.close();
        return usuario;
    }

    @Override
    public List<Usuario> listar(String filtro) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "select * from usuarios " + filtro;
        PreparedStatement pstm = conexao.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(rs.getInt("is_usuario"));
            usuario.setNome(rs.getString("nome"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setUnidade(rs.getInt("id_unidade"));
            usuario.setFuncaoUsuario(rs.getInt("id_funcao"));
            usuarios.add(usuario);
        }
        pstm.close();
        return usuarios;
    }
    
}
