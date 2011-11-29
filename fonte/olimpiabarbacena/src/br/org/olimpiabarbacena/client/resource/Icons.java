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
package br.org.olimpiabarbacena.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Icons extends ClientBundle {

	public static final Icons INSTANCE = GWT.create(Icons.class);

	@Source("book.png")
	ImageResource livro();

	@Source("cd.png")
	ImageResource cd();

	@Source("delete.png")
	ImageResource remover();

	@Source("dvd.png")
	ImageResource dvd();

	@Source("report.png")
	ImageResource revista();

	@Source("newspaper.png")
	ImageResource jornal();

	@Source("pencil.png")
	ImageResource editar();

	@Source("page_white.png")
	ImageResource desconhecido();

	@Source("arrow_down.png")
	ImageResource baixado();

	@Source("arrow_up_red.png")
	ImageResource emprestado();

	@Source("date.png")
	ImageResource reservar();

	@Source("tick.png")
	ImageResource baixar();
    
    @Source("eye.png")
    ImageResource visualizar();
}
