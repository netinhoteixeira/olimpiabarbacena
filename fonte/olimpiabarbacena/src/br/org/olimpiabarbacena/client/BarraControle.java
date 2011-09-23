package br.org.olimpiabarbacena.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class BarraControle extends Composite {

	private static BarraControleUiBinder uiBinder = GWT
			.create(BarraControleUiBinder.class);
	@UiField ListBox comboMidia;
	@UiField Button buttonMidia;
	@UiField Button buttonMembro;

	interface BarraControleUiBinder extends UiBinder<Widget, BarraControle> {
	}

	public BarraControle() {
		initWidget(uiBinder.createAndBindUi(this));
		
		// adicionar tipos de mídia
		comboMidia.addItem("Livro", "LIVRO");
		comboMidia.addItem("CD", "CD");
		
	}

	@UiHandler("buttonMidia")
	void onButtonMidiaClick(ClickEvent event) {
		
	}
}
