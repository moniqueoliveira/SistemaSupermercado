
package sistemasupermercado.dominio;

public class EnderecoFornecedor {
    private final Fornecedor fornecedor;
    private String logradouro;
    private Integer numero;
    private String cep;
    private String complemtento;
    private String bairro;
    private String cidade;
    private String uf;

    public EnderecoFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemtento() {
        return complemtento;
    }

    public void setComplemtento(String complemtento) {
        this.complemtento = complemtento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public boolean validarCep() {
        return this.cep.length() == 9;
    }
    
    public boolean validarUf() {
        return this.uf.length() == 2;
    }
}
