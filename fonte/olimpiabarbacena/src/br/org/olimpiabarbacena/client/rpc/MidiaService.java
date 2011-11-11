package br.org.olimpiabarbacena.client.rpc;

import java.util.List;

import br.org.olimpiabarbacena.shared.dados.Midia;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../rpc/midia")
public interface MidiaService extends RemoteService {
	
	public Midia obter(String id) throws IllegalArgumentException;

	public void salvar(Midia midia) throws IllegalArgumentException;

	public void remover(String id) throws IllegalArgumentException;

	public List<Midia> listar(String titulo) throws IllegalArgumentException;
}
