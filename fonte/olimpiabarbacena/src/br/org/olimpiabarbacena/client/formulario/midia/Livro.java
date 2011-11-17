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
package br.org.olimpiabarbacena.client.formulario.midia;

import br.org.olimpiabarbacena.client.Principal;
import br.org.olimpiabarbacena.client.rpc.MidiaService;
import br.org.olimpiabarbacena.client.rpc.MidiaServiceAsync;
import br.org.olimpiabarbacena.shared.dados.Tipo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Livro extends Composite {

	private static CDUiBinder uiBinder = GWT.create(CDUiBinder.class);
	@UiField
	Hidden hiddenId;
	@UiField
	Button buttonSalvar;
	@UiField
	TextBox textboxTitulo;
	@UiField
	public Button buttonFechar;
	@UiField
	public Button buttonReservar;
	@UiField
	public Button buttonEmprestimo;
	@UiField
	TextBox textboxAutor;
	@UiField
	TextBox textboxEditora;
	@UiField
	TextBox textboxEdicao;
	@UiField
	TextBox textboxLocalidade;
	@UiField
	ListBox listboxIdioma;
	@UiField
	ListBox listboxCategoria;
	@UiField
	TextBox textboxCondicao;
	@UiField
	TextBox textboxISBN;
	@UiField
	TextBox textboxMARC;
	@UiField
	TextArea textareaDescricao;
	Principal principal;	
	DialogBox dialogo;

	private final MidiaServiceAsync midiaService = GWT
			.create(MidiaService.class);

	interface CDUiBinder extends UiBinder<Widget, Livro> {
	}

	public Livro(Principal principal, DialogBox dialogo) {
		this.principal = principal;
		this.dialogo = dialogo;
		initWidget(uiBinder.createAndBindUi(this));
	}
	

	public void get(String id) {
		midiaService
				.obter(id,
						new AsyncCallback<br.org.olimpiabarbacena.shared.dados.Midia>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getMessage());
							}
							
							@Override
							public void onSuccess(
									br.org.olimpiabarbacena.shared.dados.Midia midia) {
								if (midia != null) {
									hiddenId.setValue(midia.getId());
									textboxTitulo.setValue(midia.getTitulo());
									textboxAutor.setValue(midia.getAutor());
								}
							}
						});
	}

	@UiHandler("buttonSalvar")
	void onButtonSalvarClick(ClickEvent event) {
		br.org.olimpiabarbacena.shared.dados.Midia midia = new br.org.olimpiabarbacena.shared.dados.Midia();
		
		midia.setId(hiddenId.getValue());
		midia.setTitulo(textboxTitulo.getText());
		midia.setAutor(textboxAutor.getText());
		midia.setTipo(Tipo.LIVRO);
		
		midiaService.salvar(midia, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Void result) {
				principal.getPesquisar().limpar();
				principal.getPesquisar().listarAcervo();
				dialogo.hide();
			}
		});
	}

	@UiHandler("buttonFechar")
	void onButtonFecharClick(ClickEvent event) {
		dialogo.hide();
	}
}
