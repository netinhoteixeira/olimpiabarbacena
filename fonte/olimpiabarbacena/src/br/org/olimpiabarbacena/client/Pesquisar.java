package br.org.olimpiabarbacena.client;

import java.util.List;

import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.client.rpc.MembroServiceAsync;
import br.org.olimpiabarbacena.shared.dados.Membro;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class Pesquisar extends Composite {

	private static PesquisarUiBinder uiBinder = GWT
			.create(PesquisarUiBinder.class);
	@UiField
	Button buttonPesquisar;
	@UiField
	TextBox textboxPesquisar;
	@UiField(provided = true)
	CellTable<Object> cellTable = new CellTable<Object>();
	Principal principal;
	
	private final MembroServiceAsync membroService = GWT
			.create(MembroService.class);

	interface PesquisarUiBinder extends UiBinder<Widget, Pesquisar> {
	}

	public Pesquisar(Principal principal) {
		this.principal = principal;
		initWidget(uiBinder.createAndBindUi(this));

		cellTable.setPageSize(5);
	}
	
	public void listarAcervo() {
		
	}
	
	public void pesquisarAcervo() {
		
	}
	
	public void listarMembro() {
		membroService.listar(new AsyncCallback<List<Membro>>() {
			@Override
			public void onFailure(Throwable caught) {

			}
			
			@Override
			public void onSuccess(List<Membro> result) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	public void pesquisarMembro() {
		
	}

}
