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

import br.org.olimpiabarbacena.client.rpc.MidiaService;
import br.org.olimpiabarbacena.server.dados.PMF;
import br.org.olimpiabarbacena.shared.dados.Midia;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class MidiaServiceImpl extends RemoteServiceServlet implements
		MidiaService {

	@Override
	public Midia obter(String id) throws IllegalArgumentException {
		Midia midia = new Midia();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			midia = pm.getObjectById(Midia.class, id);
		} finally {
			pm.close();
		}

		return midia;
	}

	@Override
	public void salvar(Midia midia) throws IllegalArgumentException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(midia);
		} finally {
			pm.close();
		}
	}

	@Override
	public void remover(String id) throws IllegalArgumentException {
		Midia midia = new Midia();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			midia = pm.getObjectById(Midia.class, id);
			if (midia != null) {
				pm.deletePersistent(midia);
			}
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Midia> listar(String titulo) {
		titulo = (titulo == null) ? new String() : info.netinho.util.Text
				.retirarAcentos(titulo.trim()).toLowerCase();
		List<Midia> midias = null;
		List<Midia> result = new ArrayList<Midia>();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Midia.class);
			query.setOrdering("titulo asc");
			midias = (List<Midia>) query.execute();
			// DONE: Fix bug
			// "NucleusUserException: Object Manager has been closed"
			midias.size();
		} finally {
			pm.close();
		}

		// DONE: Tem que criar um novo objeto para retornar o resultado
		if (midias != null) {
			if (!midias.isEmpty()) {
				// simulação do LIKE
				if (titulo.length() > 0) {
					for (Midia midia : midias) {
						String _titulo = info.netinho.util.Text.retirarAcentos(
								midia.getTitulo().trim()).toLowerCase();
						if (_titulo.startsWith(titulo)) {
							result.add(midia);
						}
					}
				} else {
					for (Midia midia : midias) {
						result.add(midia);
					}
				}
			}
		}

		return result;
	}

}