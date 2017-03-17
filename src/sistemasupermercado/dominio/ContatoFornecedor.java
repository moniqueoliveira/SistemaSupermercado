
package sistemasupermercado.dominio;

public class ContatoFornecedor {
    private int idContato;
    private String telefone;
    private String rua;
    private int numero;
    private String cep;
    private String complemtento;
    private String bairro;
    private String cidade;
    private String uf;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getIdContatoFornecedor() {
        return idContato;
    }

    public void setIdContatoFornecedor(int idContato) {
        this.idContato = idContato;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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
    
}
