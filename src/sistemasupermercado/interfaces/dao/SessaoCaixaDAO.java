
package sistemasupermercado.interfaces.dao;

import java.sql.SQLException;
import sistemasupermercado.dominio.SessaoCaixa;

public interface SessaoCaixaDAO {

    public void fecharConexao() throws SQLException;
    
    public boolean inserir(SessaoCaixa obj) throws SQLException;

    public SessaoCaixa pesquisar(SessaoCaixa obj) throws SQLException;

    public boolean alterarValorFechamento(SessaoCaixa obj) throws SQLException;

    public boolean alterarValorInicial(SessaoCaixa obj) throws SQLException;
    
}
