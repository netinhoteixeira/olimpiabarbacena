package br.org.olimpiabarbacena.server.rpc;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;

import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.server.dados.PMF;
import br.org.olimpiabarbacena.shared.dados.Membro;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class MembroServiceImpl extends RemoteServiceServlet implements
		MembroService {

	@Override
	public void salvar(Membro membro) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(membro);
		} finally {
			pm.close();
		}
	}

	@Override
	public void remover(Membro membro) {

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Membro> listar() {
		List<Membro> membros = null;
		List<Membro> result = new ArrayList<Membro>();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			membros = (List<Membro>) pm.newQuery(Membro.class).execute();
			// DONE: Fix bug "NucleusUserException: Object Manager has been closed"
			membros.size();
		} finally {
			pm.close();
		}
		
		// DONE: Tem que criar um novo objeto para retornar o resultado
		if (membros != null) {
			if (!membros.isEmpty()) {
				for (Membro membro : membros) {
					result.add(membro);
				}
			}
		}
		
		return result;
	}

}
