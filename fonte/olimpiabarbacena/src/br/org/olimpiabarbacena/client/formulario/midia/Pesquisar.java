package br.org.olimpiabarbacena.client.formulario.midia;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class Pesquisar extends Composite {

	private static PesquisarUiBinder uiBinder = GWT
			.create(PesquisarUiBinder.class);

	interface PesquisarUiBinder extends UiBinder<Widget, Pesquisar> {
	}

	public Pesquisar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
