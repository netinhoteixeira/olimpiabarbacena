package br.org.olimpiabarbacena.client;

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

public class BarraControle extends Composite {

	private static BarraControleUiBinder uiBinder = GWT
			.create(BarraControleUiBinder.class);
	@UiField ListBox comboMidia;
	@UiField Button buttonMidia;
	@UiField Button buttonMembro;
	DialogBox dialogo;
	Livro livro;

	interface BarraControleUiBinder extends UiBinder<Widget, BarraControle> {
	}

	public BarraControle() {
		initWidget(uiBinder.createAndBindUi(this));
		
		// adicionar tipos de m�dia
		comboMidia.addItem("Selecione", "");
		comboMidia.addItem("Livro", "LIVRO");
		comboMidia.addItem("CD", "CD");
		comboMidia.addItem("DVD", "DVD");
		comboMidia.addItem("Jornal", "JORNAL");
		comboMidia.addItem("Revista", "REVISTA");
	}

	@UiHandler("buttonMidia")
	void onButtonMidiaClick(ClickEvent event) {
		String value = comboMidia.getValue(comboMidia.getSelectedIndex());
		
		if (value.equals("LIVRO")) {
			dialogo = new DialogBox(false);
			dialogo.setWidth("686px");
			dialogo.setHeight("417px");
			
			livro = new Livro(dialogo);
			dialogo.setWidget(livro);
			dialogo.center();
		} else if (value.equals("CD")) {
			
		}
	}
}
