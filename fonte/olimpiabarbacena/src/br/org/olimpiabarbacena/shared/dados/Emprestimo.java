/**
 * Copyright 2011 Francisco Ernesto Teixeira e Jady Pâmella Barbacena
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	private Date previsao;

	@Persistent
	private Date entrega;

	// @Persistent(dependent = "true")
	// private Midia midia;
	@Persistent
	private String midia;
	private Midia midiaobject;

	// @Persistent(dependent = "true")
	// private Membro membro;
	@Persistent
	private String membro;
	private Membro membroobject;

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

	public void setMembro(String membro) {
		this.membro = membro;
	}

	public String getMembro() {
		return this.membro;
	}

	public void setMembroObject(Membro membroobject) {
		this.membroobject = membroobject;
	}

	public Membro getMembroObject() {
		return this.membroobject;
	}

	public void setDiasEntrega(Integer diasentrega) {
		this.diasentrega = diasentrega;
	}

	public Integer getDiasEntrega() {
		return this.diasentrega;
	}
	
	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	public Date getPrevisao() {
		return this.previsao;
	}

	public void setEntrega(Date entrega) {
		this.entrega = entrega;
	}

	public Date getEntrega() {
		return this.entrega;
	}

	public void setMidia(String midia) {
		this.midia = midia;
	}

	public String getMidia() {
		return this.midia;
	}

	public void setMidiaObject(Midia midiaobject) {
		this.midiaobject = midiaobject;
	}

	public Midia getMidiaObject() {
		return this.midiaobject;
	}

}
