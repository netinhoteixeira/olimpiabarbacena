package br.org.olimpiabarbacena.server.rpc;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.server.dados.PMF;
import br.org.olimpiabarbacena.shared.dados.Membro;

import javax.jdo.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class MembroServiceImpl extends RemoteServiceServlet implements
		MembroService {

	@Override
	public void salvar(Membro membro) throws IllegalArgumentException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(membro);
		} finally {
			pm.close();
		}
	}

	@Override
	public void remover(Membro membro) throws IllegalArgumentException {

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Membro> listar(String nome) {
		nome = (nome == null) ? new String() : info.netinho.util.Text
				.retirarAcentos(nome.trim()).toLowerCase();
		List<Membro> membros = null;
		List<Membro> result = new ArrayList<Membro>();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Membro.class);
			query.setOrdering("nome asc");
			membros = (List<Membro>) query.execute();
			// DONE: Fix bug
			// "NucleusUserException: Object Manager has been closed"
			membros.size();
		} finally {
			pm.close();
		}

		// DONE: Tem que criar um novo objeto para retornar o resultado
		if (membros != null) {
			if (!membros.isEmpty()) {
				// simulação do LIKE
				if (nome.length() > 0) {
					for (Membro membro : membros) {
						String _nome = info.netinho.util.Text.retirarAcentos(
								membro.getNome().trim()).toLowerCase();
						if (_nome.startsWith(nome)) {
							result.add(membro);
						}
					}
				} else {
					for (Membro membro : membros) {
						result.add(membro);
					}
				}
			}
		}

		return result;
	}

}
