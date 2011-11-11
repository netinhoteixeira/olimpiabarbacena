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
public class Midia implements IsSerializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;

	@Persistent
	private Date cadastro;

	@Persistent
	private String titulo;

	@Persistent
	private String edicao;

	@Persistent
	private String autor;

	@Persistent
	private Date publicacao;

	@Persistent
	private String editora;

	@Persistent
	private String categoria;

	@Persistent
	private String descricao;

	@Persistent
	private String idioma;

	@Persistent
	private String condicao;

	@Persistent
	private String localidade;

	@Persistent
	private String isbn13;

	@Persistent
	private String marc;

	@Persistent
	private byte[] capa;

	@Persistent
	private byte[] verso;

	@Persistent
	private String barra;

	@Persistent
	private Audio audio;

	@Persistent
	private Tipo tipo;

	public Midia() {
		this.cadastro = new Date();
	}

	public void setId(String id) {
		if ((id != null) && (!id.isEmpty())) {
			this.id = id;
		}
	}

	public String getId() {
		return this.id;
	}

	public Date getCadastro() {
		return this.cadastro;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getEdicao() {
		return this.edicao;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAutor() {
		return this.autor;
	}

	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}

	public Date getPublicacao() {
		return this.publicacao;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getEditora() {
		return this.editora;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}

	public String getCondicao() {
		return this.condicao;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLocalidade() {
		return this.localidade;
	}

	public void setISBN13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getISBN13() {
		return this.isbn13;
	}

	public void setMARC(String marc) {
		this.marc = marc;
	}

	public String getMARC() {
		return this.marc;
	}

	public void setCapa(byte[] capa) {
		this.capa = capa;
	}

	public byte[] getCapa() {
		return this.capa;
	}

	public void setVerso(byte[] verso) {
		this.verso = verso;
	}

	public byte[] getVerso() {
		return this.verso;
	}

	public void setBarra(String barra) {
		this.barra = barra;
	}

	public String getBarra() {
		return this.barra;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public Audio getAudio() {
		return this.audio;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Tipo getTipo() {
		return this.tipo;
	}

}
