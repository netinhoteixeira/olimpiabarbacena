package br.org.olimpiabarbacena.shared.dados;

import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Membro implements IsSerializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;

	@Persistent
	private Date cadastro;

	@Persistent
	private String nome;

	@Persistent
	private Date nascimento;

	@Persistent
	private Sexo sexo;

	@Persistent
	private String cpf;

	@Persistent
	private String telefone;

	@Persistent
	private String email;

	@Persistent
	private String endereco;

	@Persistent
	private String cidade;

	@Persistent
	private String estado;

	@Persistent
	private String cep;

	public Membro() {
		this.cadastro = new Date();
	}

	public String getId() {
		return this.id;
	}

	public Date getCadastro() {
		return this.cadastro;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Date getNascimento() {
		return this.nascimento;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getCPF() {
		return this.cpf;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setCEP(String cep) {
		this.cep = cep;
	}

	public String getCEP() {
		return this.cep;
	}

}
