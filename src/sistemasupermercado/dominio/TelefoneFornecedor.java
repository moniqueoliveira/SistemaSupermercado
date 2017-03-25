
package sistemasupermercado.dominio;

public class TelefoneFornecedor {
    private final Fornecedor fornecedor;
    private String telefone;

    public TelefoneFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public TelefoneFornecedor(int idFornecedor) {
        this.fornecedor = new Fornecedor();
        this.fornecedor.setIdFornecedor(idFornecedor);
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean validarTelefone() {
        return !this.telefone.equals("(  )     -    ");
    }
    
}
