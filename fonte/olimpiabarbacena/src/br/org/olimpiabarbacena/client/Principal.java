package br.org.olimpiabarbacena.client;

import br.org.olimpiabarbacena.client.formulario.midia.Pesquisar;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class Principal implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel.get("content").add(new Pesquisar());
		RootPanel.get("content").add(new BarraControle());
	}

}
