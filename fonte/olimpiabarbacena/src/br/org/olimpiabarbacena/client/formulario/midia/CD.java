package br.org.olimpiabarbacena.client.formulario.midia;

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
import com.google.gwt.user.client.ui.CheckBox;

public class CD extends Composite {

	private static CDUiBinder uiBinder = GWT.create(CDUiBinder.class);
	@UiField Button buttonSalvar;
	@UiField TextBox textboxTitulo;
	@UiField Button buttonRemover;
	@UiField Button buttonDevolucao;
	@UiField Button buttonFechar;
	@UiField Button buttonReservar;
	@UiField Button buttonEmprestimo;
	@UiField TextBox textboxArtista;
	@UiField TextBox textboxGravadora;
	@UiField TextBox textboxCondicao;
	@UiField TextBox textboxLocalidade;
	@UiField ListBox listboxIdioma;
	@UiField ListBox listboxCategoria;
	@UiField CheckBox checkboxAudio;

	interface CDUiBinder extends UiBinder<Widget, CD> {
	}

	public CD() {
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
}