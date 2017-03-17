
package sistemasupermercado.dominio;

public class Fornecedor {
    private int idFornecedor;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private ContatoFornecedor contato;

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public ContatoFornecedor getContato() {
        return contato;
    }

    public void setContato(ContatoFornecedor contato) {
        this.contato = contato;
    }
    
    public void setContato(int idContato) {
        this.contato = new ContatoFornecedor();
        this.contato.setIdContatoFornecedor(idContato);
    }
    
}
