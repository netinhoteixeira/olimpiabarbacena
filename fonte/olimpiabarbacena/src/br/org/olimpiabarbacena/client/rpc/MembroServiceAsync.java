package br.org.olimpiabarbacena.client.rpc;

import java.util.List;

import br.org.olimpiabarbacena.shared.dados.Membro;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MembroServiceAsync {

	void salvar(Membro membro, AsyncCallback<Void> callback);

	void remover(Membro membro, AsyncCallback<Void> callback);

	void listar(AsyncCallback<List<Membro>> callback);
}
