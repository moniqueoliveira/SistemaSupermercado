
package sistemasupermercado.dominio;

import java.util.List;

public class Fornecedor {
    private Integer idFornecedor;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private boolean ativo;
    private EnderecoFornecedor endereco;
    private EmailFornecedor email;
    private List<TelefoneFornecedor> telefones;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor) {
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

    public EnderecoFornecedor getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoFornecedor endereco) {
        this.endereco = endereco;
    }

    public EmailFornecedor getEmail() {
        return email;
    }

    public void setEmail(EmailFornecedor email) {
        this.email = email;
    }

    public List<TelefoneFornecedor> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneFornecedor> telefones) {
        this.telefones = telefones;
    }
    
    public boolean validarCnpj(){
        return !this.cnpj.equals("  .   .   /    -  ");
    }
}
