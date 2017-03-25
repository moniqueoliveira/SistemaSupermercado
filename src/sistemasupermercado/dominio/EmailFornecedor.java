
package sistemasupermercado.dominio;

public class EmailFornecedor {
    private final Fornecedor fornecedor;
    private String email;

    public EmailFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public EmailFornecedor(int idFornecedor) {
        this.fornecedor = new Fornecedor();
        fornecedor.setIdFornecedor(idFornecedor);
    }
    

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validarEmail() {
        return this.email.contains("@");
    }
    
}
