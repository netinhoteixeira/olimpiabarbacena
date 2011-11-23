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
package br.org.olimpiabarbacena.server.rpc;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.server.dados.PMF;
import br.org.olimpiabarbacena.shared.dados.Membro;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class MembroServiceImpl extends RemoteServiceServlet implements
		MembroService {

	@Override
	public Membro obter(String id) throws IllegalArgumentException {
		Membro membro = new Membro();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			membro = pm.getObjectById(Membro.class, id);
		} finally {
			pm.close();
		}

		return membro;
	}

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
	public void remover(String id) throws IllegalArgumentException {
		Membro membro = new Membro();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			membro = pm.getObjectById(Membro.class, id);
			if (membro != null) {
				pm.deletePersistent(membro);
			}
		} finally {
			pm.close();
		}
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
				// simula��o do LIKE
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
