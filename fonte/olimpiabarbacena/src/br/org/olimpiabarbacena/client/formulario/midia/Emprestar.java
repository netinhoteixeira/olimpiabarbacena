package br.org.olimpiabarbacena.client.formulario.midia;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Emprestar extends Composite {

	private static CDUiBinder uiBinder = GWT.create(CDUiBinder.class);
	@UiField
	ListBox listboxEmprestar;
	@UiField
	TextBox textboxDevolucao;
	@UiField
	Button buttonEmprestar;

	interface CDUiBinder extends UiBinder<Widget, Emprestar> {
	}

	public Emprestar() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
