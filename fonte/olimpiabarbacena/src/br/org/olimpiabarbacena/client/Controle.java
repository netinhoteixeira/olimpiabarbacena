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
package br.org.olimpiabarbacena.client;

import br.org.olimpiabarbacena.client.formulario.Membro;
import br.org.olimpiabarbacena.client.formulario.midia.CD;
import br.org.olimpiabarbacena.client.formulario.midia.Jornal;
import br.org.olimpiabarbacena.client.formulario.midia.Livro;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class Controle extends Composite {

	private static ControleUiBinder uiBinder = GWT
			.create(ControleUiBinder.class);
	@UiField
	ListBox comboMidia;
	@UiField
	Button buttonMidia;
	@UiField
	Button buttonMembro;
	private DialogBox dialogo;
	private Livro livro;
	private CD CD;
	private Jornal jornal;
	private Membro membro;
	private Principal principal;

	interface ControleUiBinder extends UiBinder<Widget, Controle> {
	}

	public Controle(Principal principal) {
		this.principal = principal;
		initWidget(uiBinder.createAndBindUi(this));

		// adicionar tipos de mídia
		comboMidia.addItem("Selecione", "");
		comboMidia.addItem("CD", "CD");
		comboMidia.addItem("DVD", "DVD");
		comboMidia.addItem("Jornal", "JORNAL");
		comboMidia.addItem("Livro", "LIVRO");
		comboMidia.addItem("Revista", "REVISTA");
	}
	
	public DialogBox getDialogo() {
		return this.dialogo;
	}
	
	public void setDialogo(DialogBox dialogo) {
		this.dialogo = dialogo;
	}
	
	public Livro getLivro() {
		return this.livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	@UiHandler("buttonMidia")
	void onButtonMidiaClick(ClickEvent event) {
		String value = comboMidia.getValue(comboMidia.getSelectedIndex());

		if (value.equals("LIVRO")) {
			dialogo = new DialogBox(false);
			dialogo.setWidth("464px");
			dialogo.setHeight("417px");

			livro = new Livro(principal, dialogo);
			livro.buttonEmprestimo.setVisible(false);
			livro.buttonReservar.setVisible(false);
			livro.buttonFechar.setText("Cancelar");
			
			dialogo.setWidget(livro);
			dialogo.center();
		} else if (value.equals("CD") || value.equals("DVD")) {
			dialogo = new DialogBox(false);
			dialogo.setWidth("686px");
			dialogo.setHeight("260px");

			CD = new CD(dialogo);
			dialogo.setWidget(CD);
			dialogo.center();
		} else if (value.equals("JORNAL") || value.equals("REVISTA")) {
			dialogo = new DialogBox(false);
			dialogo.setWidth("686px");
			dialogo.setHeight("360px");

			jornal = new Jornal(dialogo);
			dialogo.setWidget(jornal);
			dialogo.center();
		}
	}

	@UiHandler("buttonMembro")
	void onButtonMembroClick(ClickEvent event) {
		dialogo = new DialogBox(false);
		dialogo.setWidth("458px");
		dialogo.setHeight("283px");

		membro = new Membro(principal, dialogo);
		membro.buttonHistorico.setVisible(false);
		membro.buttonExcluir.setVisible(false);
		membro.buttonFechar.setText("Cancelar");

		dialogo.setWidget(membro);
		dialogo.center();
	}
}