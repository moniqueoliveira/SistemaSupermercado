package sistemasupermercado.relatorios;
import java.awt.BorderLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import sistemasupermercado.conexao.ConnectionFactory;

public class GeradorDeRelatorios {
 
    /**
     * Abre um relatório usando uma conexão como datasource.
     *
     * @param titulo Título usado na janela do relatório.
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param conexao Conexão utilizada para a execução da query.
     * @throws JRException Caso ocorra algum problema na execução do relatório
     */
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            Connection conexao ) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, conexao );
 
        // abre o JasperPrint em um JFrame
        viewReportFrame( titulo, print );
 
    }
 
    /*
     * Abre um relatório usando um datasource genérico.
     *
     * @param titulo Título usado na janela do relatório.
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param dataSource Datasource a ser utilizado pelo relatório.
     * @throws JRException Caso ocorra algum problema na execução do relatório
     */
    
    
    /*public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            JRDataSource dataSource ) throws JRException {
 
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, dataSource );
 
        // abre o JasperPrint em um JFrame
        viewReportFrame( titulo, print );
 
    }*/


 
    /**
     * Cria um JFrame para exibir o relatório representado pelo JasperPrint.
     *
     * @param titulo Título do JFrame.
     * @param print JasperPrint do relatório.
     */
    private static void viewReportFrame( String titulo, JasperPrint print ) {
 
        /*
         * Cria um JRViewer para exibir o relatório.
         * Um JRViewer é uma JPanel.
         */
        JRViewer viewer = new JRViewer( print );
 
        // cria o JFrame
        JFrame frameRelatorio = new JFrame( titulo );
 
        // adiciona o JRViewer no JFrame
        frameRelatorio.add( viewer, BorderLayout.CENTER );
 
        // configura o tamanho padrão do JFrame
        frameRelatorio.setSize( 500, 500 );
 
        // maximiza o JFrame para ocupar a tela toda.
        frameRelatorio.setExtendedState( JFrame.MAXIMIZED_BOTH );
 
        // configura a operação padrão quando o JFrame for fechado.
        frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
 
        // exibe o JFrame
        frameRelatorio.setVisible(true);
 
    }
    
    public void abrirRelatorioClientes() throws JRException {
 
    InputStream inputStream = getClass().getResourceAsStream("report1.jasper");

    Map parametros = new HashMap();
    ConnectionFactory conexao = new ConnectionFactory();
    
    
    parametros.put( "parameter1", 100 );
    // outros possíveis parâmetros aqui...
 
    
    try {
 
        // abre o relatório
        GeradorDeRelatorios.openReport( "Teste", inputStream, parametros,
                conexao.getConnection());
 
    } catch ( JRException exc ) {
        System.out.println(exc.getMessage());
    }
    
    
    
    }
    
    public void abrirRelatorioClientes(Integer i) throws JRException {
 
    // note que estamos chamando o novo relatório
    InputStream inputStream = getClass().getResourceAsStream("report1.jasper");
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
        GeradorDeRelatorios.openReport( "Teste", inputStream, parametros,
                conexao.getConnection());
 
    } catch ( JRException exc ) {
        System.out.println(exc.getMessage());
    }
    
    
    
    }
 
    
    /*public void abrirRelatorioEstoque(Integer i) throws JRException {
 
        InputStream inputStream = getClass().getResourceAsStream("report2.jasper");

        Map parametros = new HashMap();
        ConnectionFactory conexao = new ConnectionFactory();

        parametros.put( "parameter1", i);

        try {

            // abre o relatório
            GeradorDeRelatorios.openReport( "Teste", inputStream, parametros,
                    conexao.getConnection());

        } catch ( JRException exc ) {
            System.out.println(exc.getMessage());
        }
    }*/
    
}
 

