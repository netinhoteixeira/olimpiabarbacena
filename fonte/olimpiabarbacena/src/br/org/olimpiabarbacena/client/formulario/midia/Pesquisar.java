package br.org.olimpiabarbacena.client.formulario.midia;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.cellview.client.CellTable;

public class Pesquisar extends Composite {

	private static PesquisarUiBinder uiBinder = GWT
			.create(PesquisarUiBinder.class);
	@UiField Button buttonPesquisar;
	@UiField TextBox textboxPesquisar;
	@UiField(provided=true) CellTable<Object> cellTable = new CellTable<Object>();

	interface PesquisarUiBinder extends UiBinder<Widget, Pesquisar> {
	}

	public Pesquisar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
