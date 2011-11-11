package br.org.olimpiabarbacena.client;

import br.org.olimpiabarbacena.shared.Pesquisa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class Menu extends Composite {

	private static MenuUiBinder uiBinder = GWT.create(MenuUiBinder.class);
	@UiField
	Hyperlink linkAcervo;
	@UiField
	Hyperlink linkMembro;
	private Pesquisa selecionado;
	private Principal principal;

	interface MenuUiBinder extends UiBinder<Widget, Menu> {
	}

	public Menu(Principal principal) {
		this.principal = principal;
		initWidget(uiBinder.createAndBindUi(this));

		// If the application starts with no history token, redirect to a new
		// 'acervo' state.
		String initToken = History.getToken();
		if ((initToken.length() == 0)||(initToken.equals("acervo"))) {
			History.newItem("acervo");
			onLinkAcervoClick(null);
		} else if (initToken.equals("membro")) {
			onLinkMembroClick(null);
		}

		// Now that we've setup our listener, fire the initial history state.
		History.fireCurrentHistoryState();
	}
	
	public Pesquisa getSelecionado() {
		return this.selecionado;
	}

	@UiHandler("linkAcervo")
	public void onLinkAcervoClick(ClickEvent event) {
		this.selecionado = Pesquisa.ACERVO;
		this.principal.getPesquisar().listarAcervo();
	}

	@UiHandler("linkMembro")
	public void onLinkMembroClick(ClickEvent event) {
		this.selecionado = Pesquisa.MEMBRO;
		this.principal.getPesquisar().textboxPesquisar.setText(new String());
		this.principal.getPesquisar().listarMembro();
	}
}