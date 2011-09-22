package br.org.olimpiabarbacena.client.formulario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.ListBox;

public class Membro extends Composite {

	private static MembroUiBinder uiBinder = GWT.create(MembroUiBinder.class);
	@UiField Button buttonSalvar;
	@UiField TextBox textboxNome;
	@UiField Button buttonFechar;
	@UiField Button buttonExcluir;
	@UiField Button buttonHistorico;
	@UiField TextBox textboxCPF;
	@UiField TextBox textboxEmail;
	@UiField TextBox textboxEndereco;
	@UiField ListBox listboxEstado;
	@UiField ListBox listboxSexo;
	@UiField TextBox textboxCidade;
	@UiField TextBox textboxCEP;
	@UiField TextBox textboxTelefone;

	interface MembroUiBinder extends UiBinder<Widget, Membro> {
	}

	public Membro() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("buttonSalvar")
	void onButtonSalvarClick(ClickEvent event) {
	}
}
