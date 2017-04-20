/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.servicos;

import java.util.List;
import sistemasupermercado.dominio.Usuario;
import sistemasupermercado.exceptions.PesquisaNulaException;

public class AutenticacaoServico {
    
    public Usuario autenticar(Usuario usuario){
	UsuarioServico usuarioServico = new UsuarioServico();
        List<Usuario> usuarios = usuarioServico.listar("Login", usuario.getLogin());

        if (usuarios.isEmpty()) {
            throw new PesquisaNulaException("Usuário inexiste!");
        } else {
            if (!usuarioServico.validarSenha(usuario.getSenha(), usuarios.get(0).getSenha()))
                throw new PesquisaNulaException("Senha inválida!");
            return usuarios.get(0);
        }
    }
}
