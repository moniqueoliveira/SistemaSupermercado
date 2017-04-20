
package sistemasupermercado.servicos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Calendar;
import sistemasupermercado.dao.ProdutoRetiradoDAOImpl;
import sistemasupermercado.dominio.Estoque;
import sistemasupermercado.dominio.ProdutoRetirado;
import sistemasupermercado.exceptions.DadosInvalidosException;
import sistemasupermercado.exceptions.EstoqueInsuficienteException;
import sistemasupermercado.exceptions.RetornoDeAlteracaoDeDadosInesperadoException;
import sistemasupermercado.interfaces.dao.ProdutoRetiradoDAO;

public class ProdutoRetiradoServico {
    ProdutoRetirado produtoRetirado;
    
    public void inserir(ProdutoRetirado produtoRetirado) {
        validarDados(produtoRetirado);
        ProdutoRetiradoDAO produtoRetiradoDAO = new ProdutoRetiradoDAOImpl();
        
        produtoRetirado.setData(Calendar.getInstance());
        produtoRetirado.getData().setTimeInMillis(System.currentTimeMillis());
        
        try {
            verificarEstoque(produtoRetirado);
            verificarResultado(produtoRetiradoDAO.inserir(produtoRetirado));
            produtoRetiradoDAO.fecharConexao();
            atualizarEstoque(produtoRetirado);
        } catch(SQLException ex) {
            throw new RuntimeException("SQLException: " + ex.getMessage());
        }
    }

    private void validarDados(ProdutoRetirado produtoRetirado) {
        StringBuilder mensagem = new StringBuilder();
        
        if (produtoRetirado.getMotivo() == null) mensagem.append("Motivo\n");
        if (produtoRetirado.getProduto() == null) mensagem.append("Produto\n");
        if (produtoRetirado.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) mensagem.append("Quantidade (deve ser maior do que zero)");
        
        if (mensagem.length() > 1)
            throw new DadosInvalidosException("Os seguintes dados estão sem preenchimento ou foram preenchidos "
                    + "incorretamente:\n" + mensagem);
        
        validarQuantidade(produtoRetirado);
    }

    private void verificarResultado(boolean result) {
        if (!result)
            throw new RetornoDeAlteracaoDeDadosInesperadoException();
    }

    private void atualizarEstoque(ProdutoRetirado produtoRetirado) {
        Estoque estoque = new Estoque();
        EstoqueServico estoqueServico = new EstoqueServico();
        
        estoque.setProduto(produtoRetirado.getProduto());
        estoque.setUnidade(produtoRetirado.getSessao().getUsuario().getUnidade());
        
        Estoque estoqueAuxiliar = estoqueServico.pesquisar(estoque);
        estoque.setQuantidade(estoqueAuxiliar.getQuantidade().subtract(produtoRetirado.getQuantidade()));
        BigDecimal valorUnitarioMedio = estoqueAuxiliar.getValorTotal().divide(estoqueAuxiliar.getQuantidade(), RoundingMode.HALF_UP);
        
        BigDecimal valorTotalRetirado = produtoRetirado.getQuantidade().multiply(valorUnitarioMedio);
        estoque.setValorTotal(estoqueAuxiliar.getValorTotal().subtract(valorTotalRetirado));

        
        estoqueServico.alterar(estoque);
    }

    private void validarQuantidade(ProdutoRetirado produtoRetirado) {
        if (!produtoRetirado.getProduto().isVendaFracionada() && 
                produtoRetirado.getQuantidade().subtract(new BigDecimal(produtoRetirado.getQuantidade().intValue())).doubleValue() != 0.00) {
            throw new DadosInvalidosException("O produto não é estocado em quantidades fracionadas!");
        }
    }

    private void verificarEstoque(ProdutoRetirado produtoRetirado) {
        Estoque estoque = new Estoque();
        EstoqueServico estoqueServico = new EstoqueServico();
        
        estoque.setProduto(produtoRetirado.getProduto());
        estoque.setUnidade(produtoRetirado.getSessao().getUsuario().getUnidade());
        
        estoque = estoqueServico.pesquisar(estoque);
        
        if (estoque == null) {
            throw new EstoqueInsuficienteException("Não há unidades do produto selecionado em estoque.");
        } else if (produtoRetirado.getQuantidade().compareTo(estoque.getQuantidade()) == 1) {
            throw new EstoqueInsuficienteException("Há apenas " + estoque.getQuantidade() + " unidades/quilos do produto.");
        }
    }
}
