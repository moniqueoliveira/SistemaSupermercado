
package sistemasupermercado.servicos;

import java.sql.SQLException;
import java.util.List;
import sistemasupermercado.dao.UsuarioDAOImpl;
import sistemasupermercado.dominio.Usuario;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.PesquisaNulaException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.UsuarioDAO;

public class UsuarioServico {
    UsuarioDAO usuarioDAO;
    
    public void inserir(Usuario usuario) {
        validarDados(usuario);
        usuarioDAO = new UsuarioDAOImpl();
        try {
            verificarResultado(usuarioDAO.inserir(usuario));
            usuarioDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void alterar(Usuario usuario) {
        validarDados(usuario);
        usuarioDAO = new UsuarioDAOImpl();
        try {
            verificarResultado(usuarioDAO.alterar(usuario));
            usuarioDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public void excluir(Usuario usuario) {
        usuarioDAO = new UsuarioDAOImpl();
        try {
            verificarResultado(usuarioDAO.excluir(usuario));
            usuarioDAO.fecharConexao();
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public Usuario pesquisar(Usuario usuario) {
        usuarioDAO = new UsuarioDAOImpl();
        try {
            usuario = usuarioDAO.pesquisar(usuario);
            validarPesquisa(usuario);
            
            usuario.setFuncaoUsuario(new FuncaoUsuarioServico().pesquisar(usuario.getFuncaoUsuario()));
            usuario.setUnidade(new UnidadeServico().pesquisar(usuario.getUnidade()));
            
            usuarioDAO.fecharConexao();
            return usuario;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public List<Usuario> listar(int idUnidade) {
        return listar("", "", idUnidade);
    }
    
    public List<Usuario> listar() {
        usuarioDAO = new UsuarioDAOImpl();
        try {
            List<Usuario> usuarios = usuarioDAO.listar();
            
            FuncaoUsuarioServico funcaoUsuarioServico = new FuncaoUsuarioServico();
            for(int i = 0; i < usuarios.size(); i++) {
                // Define a descrição da função do usuário e da unidade. Ao listar os usuários somente o ID da função é definido.
                usuarios.get(i).setFuncaoUsuario(funcaoUsuarioServico.pesquisar(usuarios.get(i).getFuncaoUsuario()));
            }
            usuarioDAO.fecharConexao();
            return usuarios;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public List<Usuario> listar(String pesquisarPor, String texto, int idUnidade) {
        usuarioDAO = new UsuarioDAOImpl();
        try {
            List<Usuario> usuarios = usuarioDAO.listar(pesquisarPor, texto, idUnidade);
            
            FuncaoUsuarioServico funcaoUsuarioServico = new FuncaoUsuarioServico();
            for(int i = 0; i < usuarios.size(); i++) {
                // Define a descrição da função do usuário e da unidade. Ao listar os usuários somente o ID da função é definido.
                usuarios.get(i).setFuncaoUsuario(funcaoUsuarioServico.pesquisar(usuarios.get(i).getFuncaoUsuario()));
            }
            usuarioDAO.fecharConexao();
            return usuarios;
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }
    
    public boolean validarSenha(String senha1, String senha2) {
        return senha1.equals(senha2);
    }

    private void validarDados(Usuario usuario) {
        StringBuilder mensagem = new StringBuilder();
        if (usuario.getFuncaoUsuario() == null) mensagem.append("Função\n");
        if (usuario.getLogin().equals("")) mensagem.append("Login\n");
        if (usuario.getNome().equals("")) mensagem.append("Nome\n");
        if (usuario.getSenha().equals("")) mensagem.append("Senha e/ou Confirmar senha\n");
        
        if (mensagem.length() > 1)
            throw new DadosInvalidosException("O(s) seguinte(s) dado(s) estão sem preenchimento ou foram preenchidos"
                    + "incorretamente:\n" + mensagem);
        
        validarLogin(usuario);
    }

    private void verificarResultado(boolean result) {
        if (!result) throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }

    private void validarPesquisa(Usuario usuario) {
        if (usuario == null) throw new PesquisaNulaException();
    }

    private void validarLogin(Usuario usuario) {
        List<Usuario> usuarios = listar();
        for (Usuario usuarioLista : usuarios) {
            if (usuarioLista.getLogin().equals(usuario.getLogin())){
                
                if (usuario.getIdUsuario() != null && 
                        usuario.getIdUsuario().equals(usuarioLista.getIdUsuario()))
                    return;
                
                throw new DadosInvalidosException("O login digitado já existe!");
                
            }
        }
    }
    
    
    
    
}
