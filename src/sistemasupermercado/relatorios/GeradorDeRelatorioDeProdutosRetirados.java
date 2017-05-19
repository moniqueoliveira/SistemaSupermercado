/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.relatorios;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import sistemasupermercado.conexao.ConnectionFactory;

/**
 *
 * @author Monique
 */
public class GeradorDeRelatorioDeProdutosRetirados extends GeradorDeRelatorios {
        public JFrame abrirRelatorio(Integer i) throws JRException {
 
    // note que estamos chamando o novo relatório
    InputStream inputStream = getClass().getResourceAsStream("RelatorioDeProdutosRetirados.jasper");
    // mapa de parâmetros do relatório
    Map parametros = new HashMap();
    ConnectionFactory conexao = new ConnectionFactory();
 
    /*
     * Insere o parâmetro primeiroNome no mapa, com o valor F%
     * ou seja, todos os clientes que tenham primeiro nome começando
     * com a letra F.
     */
    
    
    
    parametros.put( "parameter1", i);
    // outros possíveis parâmetros aqui...
 
    
    try {
 
        // abre o relatório
        return new GeradorDeRelatorios().openReport( "Relatório de Produtos Retirados", inputStream, parametros,
                conexao.getConnection());
 
    } catch ( JRException exc ) {
        System.out.println(exc.getMessage());
    }
    
    return null;
    
    }
}
