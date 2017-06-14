/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasupermercado.relatorios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import sistemasupermercado.conexao.ConnectionFactory;

/**
 *
 * @author Monique
 */
public class GeradorDeNota{
    
  
    public JFrame viewNota( String titulo, JasperPrint print ) {

        JRViewer viewer = new JRViewer( print );
 
        // cria o JFrame
        JFrame frameRelatorio = new JFrame( titulo );
 
        // adiciona o JRViewer no JFrame
        frameRelatorio.add( viewer, BorderLayout.CENTER );
 
        // configura o tamanho padrão do JFrame
        frameRelatorio.setSize(300, 250);
 
        // maximiza o JFrame para ocupar a tela toda.
        //frameRelatorio.setExtendedState();
 
        // configura a operação padrão quando o JFrame for fechado.
        //frameRelatorio.setSize(dmnsn);
        frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
 
        frameRelatorio.setLocationRelativeTo(null);
        frameRelatorio.setAlwaysOnTop(true);
        frameRelatorio.setMaximumSize(new Dimension(300, 250));
            
        URL url = this.getClass().getResource("/sistemasupermercado/imagens/icone.png");
        Image image = java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/sistemasupermercado/imagens/icone.png"));
        frameRelatorio.setIconImage(image);
                    
        
        return frameRelatorio;
        
        // exibe o JFrame
        //frameRelatorio.setVisible(true);
 
    }
    
    public JFrame abrirNota(Integer i) throws JRException {
     
    // note que estamos chamando o novo relatório
    InputStream inputStream = getClass().getResourceAsStream("NotaFiscal.jasper");
    // mapa de parâmetros do relatório
    Map parametros = new HashMap();
    ConnectionFactory conexao = new ConnectionFactory();

    parametros.put( "idVenda", i);
    try {
 
        // abre o relatório
        JFrame notaFiscal = new GeradorDeRelatorios().openReport( "Nota de Venda", inputStream, parametros,
                conexao.getConnection());
        notaFiscal.setExtendedState(0);
        notaFiscal.setLocationRelativeTo(null);
        return notaFiscal;
 
    } catch ( JRException exc ) {
        System.out.println(exc.getMessage());
    }
    return null;
    }
    
    public JFrame openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            Connection conexao ) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, conexao );
 
        // abre o JasperPrint em um JFrame
        return viewNota( titulo, print );
        
 
    }
}
