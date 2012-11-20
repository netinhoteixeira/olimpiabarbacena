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
import br.org.olimpiabarbacena.shared.dados.Tipo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.Label;

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
	private CD cd;
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

	public CD getCD() {
		return this.cd;
	}

	public void setCD(CD cd) {
		this.cd = cd;
	}

	public Jornal getJornal() {
		return this.jornal;
	}

	public void setJornal(Jornal jornal) {
		this.jornal = jornal;
	}

	@UiHandler("buttonMidia")
	void onButtonMidiaClick(ClickEvent event) {
		String value = comboMidia.getValue(comboMidia.getSelectedIndex());

		if (value.equals("LIVRO")) {
			dialogo = new DialogBox(false);
			dialogo.setWidth("464px");
			dialogo.setHeight("417px");

			livro = new Livro(principal, dialogo);
			livro.buttonEmprestar.setVisible(false);
			livro.buttonFechar.setText("Cancelar");

			dialogo.setWidget(livro);
			dialogo.center();
		} else if (value.equals("CD") || value.equals("DVD")) {
			dialogo = new DialogBox(false);
			dialogo.setWidth("462px");
			dialogo.setHeight("261px");

			cd = new CD(principal, dialogo, (value.equals("CD") ? Tipo.CD
					: Tipo.DVD));
			cd.buttonEmprestar.setVisible(false);
			cd.buttonFechar.setText("Cancelar");

			dialogo.setWidget(cd);
			dialogo.center();
		} else if (value.equals("JORNAL") || value.equals("REVISTA")) {
			dialogo = new DialogBox(false);
			dialogo.setWidth("460px");
			dialogo.setHeight("359px");

			jornal = new Jornal(principal, dialogo,
					(value.equals("JORNAL") ? Tipo.JORNAL : Tipo.REVISTA));
			jornal.buttonEmprestar.setVisible(false);
			jornal.buttonFechar.setText("Cancelar");

			dialogo.setWidget(jornal);
			dialogo.center();
		} else {
			// Cria o diálogo
			dialogo = new DialogBox(false);

			// Cria uma âncora para aceitar os eventos de clique
			final Anchor dialogoAvisoFechar = new Anchor("X");

			// Adiciona um "handler" para âncora
			dialogoAvisoFechar.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					dialogo.hide();
				}
			});

			// Create dialog
			dialogo.setHTML("Aviso&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");

			// Get caption element
			final HTML caption = ((HTML) dialogo.getCaption());

			// Add anchor to caption
			caption.getElement().appendChild(dialogoAvisoFechar.getElement());

			// Add click handler to caption
			caption.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					// Get x,y caption click relative to the anchor
					final int x = event.getRelativeX(dialogoAvisoFechar
							.getElement());
					final int y = event.getRelativeY(dialogoAvisoFechar
							.getElement());

					// Check click was within bounds of anchor
					if (x >= 0 && y >= 0
							&& x <= dialogoAvisoFechar.getOffsetWidth()
							&& y <= dialogoAvisoFechar.getOffsetHeight()) {
						// Raise event on anchor
						dialogoAvisoFechar.fireEvent(event);
					}
				}
			});

			final Label labelAviso = new Label("Selecione um tipo de mídia.");
			dialogo.setWidget(labelAviso);

			// Show the dialog
			dialogo.show();
			dialogo.center();
		}
	}

	@UiHandler("buttonMembro")
	void onButtonMembroClick(ClickEvent event) {
		dialogo = new DialogBox(false);
		dialogo.setWidth("466px");
		dialogo.setHeight("319px");

		membro = new Membro(principal, dialogo);
		membro.buttonHistorico.setVisible(false);
		membro.buttonFechar.setText("Cancelar");

		dialogo.setWidget(membro);
		dialogo.center();
	}
}