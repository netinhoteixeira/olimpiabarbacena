package br.org.olimpiabarbacena.client.rpc;

import java.util.List;

import br.org.olimpiabarbacena.shared.dados.Membro;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../rpc/membro")
public interface MembroService extends RemoteService {
	
	public Membro obter(String id) throws IllegalArgumentException;

	public void salvar(Membro membro) throws IllegalArgumentException;

	public void remover(String id) throws IllegalArgumentException;

	public List<Membro> listar(String nome) throws IllegalArgumentException;
}
