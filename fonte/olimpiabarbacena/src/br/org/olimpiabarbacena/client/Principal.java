package br.org.olimpiabarbacena.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Principal implements EntryPoint {
	
	private Menu menu;
	private Pesquisar pesquisar;
	private Controle controle;

	@Override
	public void onModuleLoad() {
		this.pesquisar = new Pesquisar(this);
		// FIX: Caused by: java.lang.NullPointerException
		// at br.org.olimpiabarbacena.client.Menu.onLinkAcervoClick(Menu.java:50)
		// at br.org.olimpiabarbacena.client.Menu.(Menu.java:38)
		// at br.org.olimpiabarbacena.client.Principal.onModuleLoad(Principal.java:14)
		// ... 9 more
		this.menu = new Menu(this);
		this.controle = new Controle(this);

		RootPanel.get("content").add(menu);
		RootPanel.get("content").add(pesquisar);
		RootPanel.get("content").add(controle);
	}

	public Menu getMenu() {
		return this.menu;
	}

	public Pesquisar getPesquisar() {
		return this.pesquisar;
	}

	public Controle getControle() {
		return this.controle;
	}

}