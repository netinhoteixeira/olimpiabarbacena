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
package br.org.olimpiabarbacena.client.rpc;

import java.util.List;

import br.org.olimpiabarbacena.shared.dados.Emprestimo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("../rpc/emprestimo")
public interface EmprestimoService extends RemoteService {

	public Emprestimo obter(String id) throws IllegalArgumentException;

	public void emprestar(Emprestimo emprestimo)
			throws IllegalArgumentException;

	public void reservar(String id, String reservadopor) throws IllegalArgumentException;

	public void remover(String id) throws IllegalArgumentException;

	public void baixar(String id) throws IllegalArgumentException;

	public List<Emprestimo> listar(String nome) throws IllegalArgumentException;
}
