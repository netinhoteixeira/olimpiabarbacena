package br.org.olimpiabarbacena.client.rpc;

import java.util.List;

import br.org.olimpiabarbacena.shared.dados.Membro;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MembroServiceAsync {
	
	void obter(String id, AsyncCallback<Membro> callback)
		throws IllegalArgumentException;

	void salvar(Membro membro, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	void remover(String id, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	void listar(String nome, AsyncCallback<List<Membro>> callback)
			throws IllegalArgumentException;
}
