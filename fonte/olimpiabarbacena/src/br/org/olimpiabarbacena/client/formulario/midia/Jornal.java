package br.org.olimpiabarbacena.client.formulario.midia;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Jornal extends Composite {

	private static CDUiBinder uiBinder = GWT.create(CDUiBinder.class);
	@UiField
	Button buttonSalvar;
	@UiField
	TextBox textboxTitulo;
	@UiField
	Button buttonRemover;
	@UiField
	Button buttonDevolucao;
	@UiField
	Button buttonFechar;
	@UiField
	Button buttonReservar;
	@UiField
	Button buttonEmprestimo;
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
	TextBox textboxISSN;
	@UiField
	TextBox textboxMARC;
	@UiField
	TextArea textareaDescricao;
	DialogBox dialogo;

	interface CDUiBinder extends UiBinder<Widget, Jornal> {
	}

	public Jornal(DialogBox dialogo) {
		this.dialogo = dialogo;
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("buttonSalvar")
	void onButtonSalvarClick(ClickEvent event) {
	}

	@UiHandler("buttonRemover")
	void onButtonRemoverClick(ClickEvent event) {
	}

	@UiHandler("buttonDevolucao")
	void onButtonDevolucaoClick(ClickEvent event) {
	}

	@UiHandler("buttonFechar")
	void onButtonFecharClick(ClickEvent event) {
		dialogo.hide();
	}
}
