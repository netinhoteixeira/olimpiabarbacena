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
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import br.org.olimpiabarbacena.client.rpc.EmprestimoService;
import br.org.olimpiabarbacena.server.dados.PMF;
import br.org.olimpiabarbacena.shared.dados.Emprestimo;
import br.org.olimpiabarbacena.shared.dados.Membro;
import br.org.olimpiabarbacena.shared.dados.Midia;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class EmprestimoServiceImpl extends RemoteServiceServlet implements
		EmprestimoService {

	@Override
	public Emprestimo obter(String id) throws IllegalArgumentException {
		Emprestimo emprestimo = new Emprestimo();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			emprestimo = pm.getObjectById(Emprestimo.class, id);
		} finally {
			pm.close();
		}

		return emprestimo;
	}

	@Override
	public void emprestar(Emprestimo emprestimo)
			throws IllegalArgumentException {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		emprestimo.setEmprestimo(new Date());

		try {
			pm.makePersistent(emprestimo);
		} finally {
			pm.close();
		}
	}

	@Override
	public void reservar(String id, String reservadopor) throws IllegalArgumentException {
		if (id != null) {
			if (!id.isEmpty()) {
				PersistenceManager pm = PMF.get().getPersistenceManager();
				try {
					Emprestimo emprestimo = pm.getObjectById(Emprestimo.class, id);
					if (emprestimo != null) {
						emprestimo.setReservadoPor(reservadopor);
						emprestimo.setReserva(new Date());
						pm.makePersistent(emprestimo);
					}
				} finally {
					pm.close();
				}
			}
		}
	}

	@Override
	public void remover(String id) throws IllegalArgumentException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Emprestimo emprestimo = pm.getObjectById(Emprestimo.class, id);
			if (emprestimo != null) {
				pm.deletePersistent(emprestimo);
			}
		} finally {
			pm.close();
		}
	}

	@Override
	public void baixar(String id) throws IllegalArgumentException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Emprestimo emprestimo = pm.getObjectById(Emprestimo.class, id);
			if (emprestimo != null) {
				if (emprestimo.getEntrega() == null) {
					emprestimo.setEntrega(new Date());
					pm.makePersistent(emprestimo);
				}
			}
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emprestimo> listar(String nome) {
		nome = (nome == null) ? new String() : info.netinho.util.Text
				.retirarAcentos(nome.trim()).toLowerCase();
		List<Emprestimo> emprestimos = null;
		List<Emprestimo> result = new ArrayList<Emprestimo>();

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query query = pm.newQuery(Emprestimo.class);
			// query.setOrdering("nome asc");
			emprestimos = (List<Emprestimo>) query.execute();
			// DONE: Fix bug
			// "NucleusUserException: Object Manager has been closed"
			emprestimos.size();
		} finally {
			pm.close();
		}

		// obtém os objetos de mídia e de membro
		for (int i = 0; i < emprestimos.size(); i++) {
			if (emprestimos.get(i).getMembro() != null) {
				if (!emprestimos.get(i).getMembro().isEmpty()) {
					pm = PMF.get().getPersistenceManager();
					try {
						Membro membro = pm.getObjectById(Membro.class,
								emprestimos.get(i).getMembro());
						emprestimos.get(i).setMembroObject(membro);
					} finally {
						pm.close();
					}
				}
			}

			if (emprestimos.get(i).getMidia() != null) {
				if (!emprestimos.get(i).getMidia().isEmpty()) {
					pm = PMF.get().getPersistenceManager();
					try {
						Midia midia = pm.getObjectById(Midia.class, emprestimos
								.get(i).getMidia());
						emprestimos.get(i).setMidiaObject(midia);
					} finally {
						pm.close();
					}
				}
			}
		}

		// DONE: Tem que criar um novo objeto para retornar o resultado
		if (emprestimos != null) {
			if (!emprestimos.isEmpty()) {
				// simulação do LIKE
				if (nome.length() > 0) {
					for (Emprestimo emprestimo : emprestimos) {
						if (emprestimo.getMidia() != null) {
							Midia midia = null;

							pm = PMF.get().getPersistenceManager();
							try {
								midia = pm.getObjectById(Midia.class,
										emprestimo.getMidia());
							} finally {
								pm.close();
							}
							if (midia != null) {
								String _nome = info.netinho.util.Text
										.retirarAcentos(
												midia.getTitulo().trim())
										.toLowerCase();
								if (_nome.startsWith(nome)) {
									result.add(emprestimo);
								}
							}
						}
					}
				} else {
					for (Emprestimo emprestimo : emprestimos) {
						result.add(emprestimo);
					}
				}
			}
		}

		return result;
	}

}
