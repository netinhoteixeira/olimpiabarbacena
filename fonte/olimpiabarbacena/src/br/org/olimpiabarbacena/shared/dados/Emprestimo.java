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
public class Emprestimo implements IsSerializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;

	@Persistent
	private Date cadastro;

	@Persistent
	private Date reserva;

	@Persistent
	private String reservadopor;

	@Persistent
	private Date emprestimo;

	@Persistent
	private Integer diasentrega;

	@Persistent
	private Date entrega;

	@Persistent
	private Midia midia;

	@Persistent
	private Membro membro;

	public Emprestimo() {
		this.cadastro = new Date();
	}

	public String getId() {
		return this.id;
	}

	public Date getCadastro() {
		return this.cadastro;
	}

	public void setReserva(Date reserva) {
		this.reserva = reserva;
	}

	public Date getReserva() {
		return this.reserva;
	}

	public void setReservadoPor(String reservadopor) {
		this.reservadopor = reservadopor;
	}

	public String getReservadoPor() {
		return this.reservadopor;
	}

	public void setEmprestimo(Date emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Date getEmprestimo() {
		return this.emprestimo;
	}

	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	public Membro getMembro() {
		return this.membro;
	}

	public void setDiasEntrega(Integer diasentrega) {
		this.diasentrega = diasentrega;
	}

	public Integer getDiasEntrega() {
		return this.diasentrega;
	}

	public void setEntrega(Date entrega) {
		this.entrega = entrega;
	}

	public Date getEntrega() {
		return this.entrega;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Midia getMidia() {
		return this.midia;
	}

}
