package br.org.olimpiabarbacena.client.rpc;

import java.util.List;

import br.org.olimpiabarbacena.shared.dados.Midia;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MidiaServiceAsync {
	
	void obter(String id, AsyncCallback<Midia> callback)
		throws IllegalArgumentException;

	void salvar(Midia midia, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	void remover(String id, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	void listar(String titulo, AsyncCallback<List<Midia>> callback)
			throws IllegalArgumentException;
}
