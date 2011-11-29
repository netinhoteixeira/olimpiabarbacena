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

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EmprestimoServiceAsync {

	void obter(String id, AsyncCallback<Emprestimo> callback)
			throws IllegalArgumentException;

	void emprestar(Emprestimo emprestimo, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	void reservar(String id, String reservadopor, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	void remover(String id, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	void baixar(String id, AsyncCallback<Void> callback)
			throws IllegalArgumentException;

	void listar(String nome, AsyncCallback<List<Emprestimo>> callback)
			throws IllegalArgumentException;
}
