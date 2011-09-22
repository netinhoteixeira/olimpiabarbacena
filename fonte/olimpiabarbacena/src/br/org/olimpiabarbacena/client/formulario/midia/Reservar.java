package br.org.olimpiabarbacena.client.formulario.midia;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ListBox;

public class Reservar extends Composite {

	private static CDUiBinder uiBinder = GWT.create(CDUiBinder.class);
	@UiField Button buttonReservar;
	@UiField ListBox listboxReservar;
	@UiField TextBox textboxEmprestado;
	@UiField ListBox listboxListaEspera;

	interface CDUiBinder extends UiBinder<Widget, Reservar> {
	}

	public Reservar() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
