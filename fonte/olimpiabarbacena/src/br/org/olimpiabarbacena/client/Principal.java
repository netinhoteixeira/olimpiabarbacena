package br.org.olimpiabarbacena.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Principal implements EntryPoint {

	Menu menu;
	Pesquisar pesquisar;
	Controle controle;

	@Override
	public void onModuleLoad() {
		this.menu = new Menu(this);
		this.pesquisar = new Pesquisar(this);
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