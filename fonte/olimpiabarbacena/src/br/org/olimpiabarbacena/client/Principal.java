/**
 * Copyright 2011 Francisco Ernesto Teixeira e Jady Pâmella Barbacena
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
		// at
		// br.org.olimpiabarbacena.client.Menu.onLinkAcervoClick(Menu.java:50)
		// at br.org.olimpiabarbacena.client.Menu.(Menu.java:38)
		// at
		// br.org.olimpiabarbacena.client.Principal.onModuleLoad(Principal.java:14)
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