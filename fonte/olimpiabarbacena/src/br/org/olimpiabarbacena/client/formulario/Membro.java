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
package br.org.olimpiabarbacena.client.formulario;

import br.org.olimpiabarbacena.client.Principal;
import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.client.rpc.MembroServiceAsync;
import br.org.olimpiabarbacena.shared.InputValidator;
import br.org.olimpiabarbacena.shared.dados.Sexo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.Hidden;

public class Membro extends Composite {

	private static MembroUiBinder uiBinder = GWT.create(MembroUiBinder.class);
	@UiField
	Hidden hiddenId;
	@UiField
	TextBox textboxNome;
	@UiField
	DateBox dateboxNascimento;
	@UiField
	RadioButton radioSexoMasculino;
	@UiField
	RadioButton radioSexoFeminino;
	@UiField
	TextBox textboxCPF;
	@UiField
	TextBox textboxTelefone;
	@UiField
	TextBox textboxEmail;
	@UiField
	TextBox textboxEndereco;
	@UiField
	TextBox textboxCidade;
	@UiField
	ListBox comboEstado;
	@UiField
	TextBox textboxCEP;
	@UiField
	public Button buttonHistorico;
	@UiField
	public Button buttonExcluir;
	@UiField
	public Button buttonSalvar;
	@UiField
	public Button buttonFechar;
	Principal principal;	
	DialogBox dialogo;

	private final MembroServiceAsync membroService = GWT
			.create(MembroService.class);

	interface MembroUiBinder extends UiBinder<Widget, Membro> {
	}

	public Membro(Principal principal, DialogBox dialogo) {
		this.principal = principal;
		this.dialogo = dialogo;
		initWidget(uiBinder.createAndBindUi(this));

		dateboxNascimento.setFormat(new DateBox.DefaultFormat(DateTimeFormat
				.getFormat("dd/MM/yyyy")));

		// Let's disallow non-numeric entry in the normal text box.
		textboxCPF.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!InputValidator.isInteger(event.getCharCode())) {
					// TextBox.cancelKey() suppresses the current keyboard
					((TextBox) event.getSource()).cancelKey();
				}
			}
		});

		// Let's disallow non-numeric entry in the normal text box.
		textboxCEP.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!InputValidator.isInteger(event.getCharCode())) {
					// TextBox.cancelKey() suppresses the current keyboard
					((TextBox) event.getSource()).cancelKey();
				}

			}
		});

		// adiciona a lista dos estados brasileiros
		comboEstado.addItem("AC");
		comboEstado.addItem("AL");
		comboEstado.addItem("AP");
		comboEstado.addItem("AM");
		comboEstado.addItem("BA");
		comboEstado.addItem("CE");
		comboEstado.addItem("DF");
		comboEstado.addItem("ES");
		comboEstado.addItem("GO");
		comboEstado.addItem("MA");
		comboEstado.addItem("MT");
		comboEstado.addItem("MS");
		comboEstado.addItem("MG");
		comboEstado.addItem("PA");
		comboEstado.addItem("PB");
		comboEstado.addItem("PR");
		comboEstado.addItem("PE");
		comboEstado.addItem("PI");
		comboEstado.addItem("RJ");
		comboEstado.addItem("RN");
		comboEstado.addItem("RS");
		comboEstado.addItem("RO");
		comboEstado.addItem("RR");
		comboEstado.addItem("SC");
		comboEstado.addItem("SP");
		comboEstado.addItem("SE");
		comboEstado.addItem("TO");
	}

	public void get(String id) {
		membroService
				.obter(id,
						new AsyncCallback<br.org.olimpiabarbacena.shared.dados.Membro>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getMessage());
							}
							
							@Override
							public void onSuccess(
									br.org.olimpiabarbacena.shared.dados.Membro membro) {
								if (membro != null) {
									hiddenId.setValue(membro.getId());
									textboxNome.setText(membro.getNome());
									dateboxNascimento.setValue(membro
											.getNascimento());
									switch (membro.getSexo().ordinal()) {
									case 0: // Sexo.MASCULINO
										radioSexoMasculino.setValue(true);
										radioSexoFeminino.setValue(false);
										break;
									case 1: // Sexo.FEMININO
										radioSexoMasculino.setValue(false);
										radioSexoFeminino.setValue(true);
										break;
									}
									textboxCPF.setValue(membro.getCPF());
									textboxTelefone.setValue(membro.getTelefone());
									textboxEmail.setValue(membro.getEmail());
									textboxEndereco.setValue(membro.getEndereco());
									textboxCidade.setValue(membro.getCidade());
									for (int index = 0; index < comboEstado.getItemCount(); index++) {
										if (comboEstado.getItemText(index).equals(membro.getEstado())) {
											comboEstado.setSelectedIndex(index);
											break;
										}
									}
									textboxCEP.setValue(membro.getCEP());
								}
							}
						});
	}

	@UiHandler("buttonSalvar")
	void onButtonSalvarClick(ClickEvent event) {
		br.org.olimpiabarbacena.shared.dados.Membro membro = new br.org.olimpiabarbacena.shared.dados.Membro();
		membro.setId(hiddenId.getValue());
		membro.setNome(textboxNome.getText());
		membro.setNascimento(dateboxNascimento.getValue());
		if (radioSexoMasculino.getValue()) {
			membro.setSexo(Sexo.MASCULINO);
		} else {
			membro.setSexo(Sexo.FEMININO);
		}
		membro.setCPF(textboxCPF.getText());
		membro.setTelefone(textboxTelefone.getText());
		membro.setEmail(textboxEmail.getText());
		membro.setEndereco(textboxEndereco.getText());
		membro.setCidade(textboxCidade.getText());
		membro.setEstado(comboEstado.getItemText(comboEstado.getSelectedIndex()));
		membro.setCEP(textboxCEP.getText());

		membroService.salvar(membro, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Void result) {
				principal.getPesquisar().limpar();
				principal.getPesquisar().listarMembro();
				dialogo.hide();
			}
		});
	}

	@UiHandler("buttonFechar")
	void onButtonFecharClick(ClickEvent event) {
		dialogo.hide();
	}

}
