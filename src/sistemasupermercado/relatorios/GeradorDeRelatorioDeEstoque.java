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

public class GeradorDeRelatorioDeEstoque extends GeradorDeRelatorios {
    public JFrame abrirRelatorio(Integer i) throws JRException {
 
        InputStream inputStream = getClass().getResourceAsStream("relatorioEstoque.jasper");

        Map parametros = new HashMap();
        ConnectionFactory conexao = new ConnectionFactory();

        parametros.put( "idUnidade", i);

        try {

            // abre o relatório
            return new GeradorDeRelatorios().openReport( "Relatório de Estoque", inputStream, parametros,
                    conexao.getConnection());

        } catch ( JRException exc ) {
            System.out.println(exc.getMessage());
        }
        return null;
    }
}
