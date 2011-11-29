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
package br.org.olimpiabarbacena.client;

import java.util.ArrayList;
import java.util.List;

import br.org.olimpiabarbacena.client.formulario.midia.Reserva;
import br.org.olimpiabarbacena.client.resource.Icons;
import br.org.olimpiabarbacena.client.rpc.EmprestimoService;
import br.org.olimpiabarbacena.client.rpc.EmprestimoServiceAsync;
import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.client.rpc.MembroServiceAsync;
import br.org.olimpiabarbacena.client.rpc.MidiaService;
import br.org.olimpiabarbacena.client.rpc.MidiaServiceAsync;
import br.org.olimpiabarbacena.shared.Pesquisa;
import br.org.olimpiabarbacena.shared.dados.Emprestimo;
import br.org.olimpiabarbacena.shared.dados.Membro;
import br.org.olimpiabarbacena.shared.dados.Midia;
import br.org.olimpiabarbacena.shared.dados.Tipo;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;

public class Pesquisar extends Composite {

	private static PesquisarUiBinder uiBinder = GWT
			.create(PesquisarUiBinder.class);
	@UiField
	Button buttonPesquisar;
	@UiField
	public TextBox textboxPesquisar;
	@UiField(provided = true)
	CellTable<Object> cellTable = new CellTable<Object>();
	AsyncDataProvider<Object> dataProvider;
	@UiField
	SimplePager simplePager;
	Principal principal;

	private final MidiaServiceAsync midiaService = GWT
			.create(MidiaService.class);

	private final MembroServiceAsync membroService = GWT
			.create(MembroService.class);

	private final EmprestimoServiceAsync emprestimoService = GWT
			.create(EmprestimoService.class);

	interface PesquisarUiBinder extends UiBinder<Widget, Pesquisar> {
	}

	public Pesquisar(final Principal principal) {
		this.principal = principal;
		initWidget(uiBinder.createAndBindUi(this));

		simplePager.setDisplay(cellTable);
		simplePager.setPageSize(6);
	}

	public void limpar() {
		textboxPesquisar.setText(new String());
	}

	public void listarAcervo() {
		// remove todas as colunas
		for (int i = cellTable.getColumnCount() - 1; i >= 0; i--) {
			cellTable.removeColumn(i);
		}

		// Adiciona coluna imagem para exibir o tipo.
		Column<Object, ImageResource> colunaTipo = new Column<Object, ImageResource>(
				new ImageResourceCell()) {

			@Override
			public ImageResource getValue(Object object) {
				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Midia")) {
					if (((Midia) object).getTipo() == Tipo.LIVRO) {
						return Icons.INSTANCE.livro();
					} else if (((Midia) object).getTipo() == Tipo.CD) {
						return Icons.INSTANCE.cd();
					} else if (((Midia) object).getTipo() == Tipo.DVD) {
						return Icons.INSTANCE.dvd();
					} else if (((Midia) object).getTipo() == Tipo.REVISTA) {
						return Icons.INSTANCE.revista();
					} else if (((Midia) object).getTipo() == Tipo.JORNAL) {
						return Icons.INSTANCE.jornal();
					} else {
						return Icons.INSTANCE.desconhecido();
					}
				}

				return Icons.INSTANCE.desconhecido();
			}
		};
		cellTable.addColumn(colunaTipo, "Tipo");
		cellTable.setColumnWidth(colunaTipo, "16px");

		// Adiciona coluna texto para exibir o título.
		TextColumn<Object> colunaNome = new TextColumn<Object>() {

			@Override
			public String getValue(Object object) {
				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Midia")) {
					return ((Midia) object).getTitulo();
				}

				return new String();
			}
		};
		colunaNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		cellTable.addColumn(colunaNome, "Título");

		// Adiciona coluna com o botão editar.
		ButtonCell buttonCellEditar = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.editar();
					String iconDisplay = AbstractImagePrototype.create(icon)
							.getHTML();
					iconDisplay = iconDisplay.replaceFirst(">",
							" title=\"Editar\">");
					SafeHtml html = SafeHtmlUtils
							.fromTrustedString(iconDisplay);
					sb.append(html);
				}
			}
		};
		Column<Object, String> colunaEditar = new Column<Object, String>(
				buttonCellEditar) {

			@Override
			public String getValue(Object object) {
				return "Editar";
			}
		};
		colunaEditar.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int index, Object object, String value) {
				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Midia")) {
					if (((Midia) object).getTipo() == Tipo.LIVRO) {
						principal.getControle()
								.setDialogo(new DialogBox(false));

						principal.getControle().getDialogo().setWidth("464px");
						principal.getControle().getDialogo().setHeight("417px");

						principal
								.getControle()
								.setLivro(
										new br.org.olimpiabarbacena.client.formulario.midia.Livro(
												principal, principal
														.getControle()
														.getDialogo()));

						principal.getControle().getLivro()
								.get(((Midia) object).getId());

						principal.getControle().getDialogo()
								.setWidget(principal.getControle().getLivro());
						principal.getControle().getDialogo().center();
					} else if (((Midia) object).getTipo() == Tipo.CD) {
						principal.getControle()
								.setDialogo(new DialogBox(false));

						principal.getControle().getDialogo().setWidth("462px");
						principal.getControle().getDialogo().setHeight("261px");

						principal
								.getControle()
								.setCD(new br.org.olimpiabarbacena.client.formulario.midia.CD(
										principal, principal.getControle()
												.getDialogo(), Tipo.CD));

						principal.getControle().getCD()
								.get(((Midia) object).getId());

						principal.getControle().getDialogo()
								.setWidget(principal.getControle().getCD());
						principal.getControle().getDialogo().center();
					} else if (((Midia) object).getTipo() == Tipo.DVD) {
						principal.getControle()
								.setDialogo(new DialogBox(false));

						principal.getControle().getDialogo().setWidth("462px");
						principal.getControle().getDialogo().setHeight("261px");

						principal
								.getControle()
								.setCD(new br.org.olimpiabarbacena.client.formulario.midia.CD(
										principal, principal.getControle()
												.getDialogo(), Tipo.DVD));

						principal.getControle().getCD()
								.get(((Midia) object).getId());

						principal.getControle().getDialogo()
								.setWidget(principal.getControle().getCD());
						principal.getControle().getDialogo().center();
					} else if (((Midia) object).getTipo() == Tipo.JORNAL) {
						principal.getControle()
								.setDialogo(new DialogBox(false));

						principal.getControle().getDialogo().setWidth("460px");
						principal.getControle().getDialogo().setHeight("359px");

						principal
								.getControle()
								.setJornal(
										new br.org.olimpiabarbacena.client.formulario.midia.Jornal(
												principal, principal
														.getControle()
														.getDialogo(),
												Tipo.JORNAL));

						principal.getControle().getJornal()
								.get(((Midia) object).getId());

						principal.getControle().getDialogo()
								.setWidget(principal.getControle().getJornal());
						principal.getControle().getDialogo().center();
					} else if (((Midia) object).getTipo() == Tipo.REVISTA) {
						principal.getControle()
								.setDialogo(new DialogBox(false));

						principal.getControle().getDialogo().setWidth("460px");
						principal.getControle().getDialogo().setHeight("359px");

						principal
								.getControle()
								.setJornal(
										new br.org.olimpiabarbacena.client.formulario.midia.Jornal(
												principal, principal
														.getControle()
														.getDialogo(),
												Tipo.REVISTA));

						principal.getControle().getJornal()
								.get(((Midia) object).getId());

						principal.getControle().getDialogo()
								.setWidget(principal.getControle().getJornal());
						principal.getControle().getDialogo().center();
					}
				}
			}
		});
		colunaEditar.setCellStyleNames("gwt-cell-pointer");
		cellTable.addColumn(colunaEditar, new String());
		cellTable.setColumnWidth(colunaEditar, "16px");

		// Adiciona coluna com o botão remover.
		ButtonCell buttonCellRemover = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.remover();
					String iconDisplay = AbstractImagePrototype.create(icon)
							.getHTML();
					iconDisplay = iconDisplay.replaceFirst(">",
							" title=\"Remover\">");
					SafeHtml html = SafeHtmlUtils
							.fromTrustedString(iconDisplay);
					sb.append(html);
				}
			}
		};
		Column<Object, String> colunaRemover = new Column<Object, String>(
				buttonCellRemover) {
			@Override
			public String getValue(Object object) {
				return "Remover";
			}
		};
		colunaRemover.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int index, Object object, String value) {
				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Midia")) {
					if (Window.confirm("Deseja remover \""
							+ ((Midia) object).getTitulo()
							+ "\" e suas dependências?")) {
						midiaService.remover(((Midia) object).getId(),
								new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
									};

									@Override
									public void onSuccess(Void result) {
										limpar();
										listarAcervo();
									};
								});
					}
				}
			}
		});
		colunaRemover.setCellStyleNames("gwt-cell-pointer");
		cellTable.addColumn(colunaRemover, new String());
		cellTable.setColumnWidth(colunaRemover, "16px");

		dataProvider = new AsyncDataProvider<Object>() {

			@Override
			protected void onRangeChanged(HasData<Object> display) {
				final Range range = display.getVisibleRange();
				final int start = range.getStart();
				final int end = start + range.getLength();

				midiaService.listar(textboxPesquisar.getText(),
						new AsyncCallback<List<Midia>>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getMessage());
							}

							@Override
							public void onSuccess(List<Midia> result) {
								// convert old list type to new list type
								List<Object> list = new ArrayList<Object>(
										result.size());
								for (Midia midia : result) {
									list.add(midia);
								}
								updateRowData(start, list.subList(start,
										(result.size() < end) ? result.size()
												: end));
								updateRowCount(result.size(), true);
							}
						});
			}
		};
		dataProvider.addDataDisplay(cellTable);
	}

	public void listarMembro() {
		// remove todas as colunas
		for (int i = cellTable.getColumnCount() - 1; i >= 0; i--) {
			cellTable.removeColumn(i);
		}

		// Adiciona coluna texto para exibir o nome.
		TextColumn<Object> colunaNome = new TextColumn<Object>() {

			@Override
			public String getValue(Object object) {
				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Membro")) {
					return ((Membro) object).getNome();
				}

				return new String();
			}
		};
		colunaNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		cellTable.addColumn(colunaNome, "Nome");

		// Adiciona coluna com o botão editar.
		ButtonCell buttonCellEditar = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.editar();
					String iconDisplay = AbstractImagePrototype.create(icon)
							.getHTML();
					iconDisplay = iconDisplay.replaceFirst(">",
							" title=\"Editar\">");
					SafeHtml html = SafeHtmlUtils
							.fromTrustedString(iconDisplay);
					sb.append(html);
				}
			}
		};
		Column<Object, String> colunaEditar = new Column<Object, String>(
				buttonCellEditar) {

			@Override
			public String getValue(Object object) {
				return "Editar";
			}
		};
		colunaEditar.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int index, Object object, String value) {
				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Membro")) {
					principal.getControle().setDialogo(new DialogBox(false));

					principal.getControle().getDialogo().setWidth("466px");
					principal.getControle().getDialogo().setHeight("319px");

					br.org.olimpiabarbacena.client.formulario.Membro membro = new br.org.olimpiabarbacena.client.formulario.Membro(
							principal, principal.getControle().getDialogo());
					membro.buttonHistorico.setVisible(false);
					membro.buttonFechar.setText("Cancelar");

					membro.get(((Membro) object).getId());

					principal.getControle().getDialogo().setWidget(membro);
					principal.getControle().getDialogo().center();
				}
			}
		});
		colunaEditar.setCellStyleNames("gwt-cell-pointer");
		cellTable.addColumn(colunaEditar, new String());
		cellTable.setColumnWidth(colunaEditar, "16px");

		// Adiciona coluna com o botão remover.
		ButtonCell buttonCellRemover = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.remover();
					String iconDisplay = AbstractImagePrototype.create(icon)
							.getHTML();
					iconDisplay = iconDisplay.replaceFirst(">",
							" title=\"Remover\">");
					SafeHtml html = SafeHtmlUtils
							.fromTrustedString(iconDisplay);
					sb.append(html);
				}
			}
		};
		Column<Object, String> colunaRemover = new Column<Object, String>(
				buttonCellRemover) {
			@Override
			public String getValue(Object object) {
				return "Remover";
			}
		};
		colunaRemover.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int index, Object object, String value) {
				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Membro")) {
					if (Window.confirm("Deseja remover \""
							+ ((Membro) object).getNome()
							+ "\" e suas dependências?")) {
						membroService.remover(((Membro) object).getId(),
								new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
									};

									@Override
									public void onSuccess(Void result) {
										limpar();
										listarMembro();
									};
								});
					}
				}
			}
		});
		colunaRemover.setCellStyleNames("gwt-cell-pointer");
		cellTable.addColumn(colunaRemover, new String());
		cellTable.setColumnWidth(colunaRemover, "16px");

		dataProvider = new AsyncDataProvider<Object>() {

			@Override
			protected void onRangeChanged(HasData<Object> display) {
				final Range range = display.getVisibleRange();
				final int start = range.getStart();
				final int end = start + range.getLength();

				membroService.listar(textboxPesquisar.getText(),
						new AsyncCallback<List<Membro>>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getMessage());
							}

							@Override
							public void onSuccess(List<Membro> result) {
								// convert old list type to new list type
								List<Object> list = new ArrayList<Object>(
										result.size());
								for (Membro membro : result) {
									list.add(membro);
								}
								updateRowData(start, list.subList(start,
										(result.size() < end) ? result.size()
												: end));
								updateRowCount(result.size(), true);
							}
						});
			}
		};
		dataProvider.addDataDisplay(cellTable);
	}

	public void listarEmprestimo() {
		// remove todas as colunas
		for (int i = cellTable.getColumnCount() - 1; i >= 0; i--) {
			cellTable.removeColumn(i);
		}

		// Adiciona coluna imagem para exibir a situação.
		Column<Object, ImageResource> colunaSituacao = new Column<Object, ImageResource>(
				new ImageResourceCell()) {

			@Override
			public ImageResource getValue(Object object) {
				if (object
						.getClass()
						.getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Emprestimo")) {
					if (((Emprestimo) object).getEntrega() == null) {
						return Icons.INSTANCE.emprestado();
					} else {
						return Icons.INSTANCE.baixado();
					}
				} else {
					return Icons.INSTANCE.desconhecido();
				}
			}
		};
		cellTable.addColumn(colunaSituacao, new String());
		cellTable.setColumnWidth(colunaSituacao, "16px");

		// Adiciona coluna texto para exibir o título.
		TextColumn<Object> colunaNome = new TextColumn<Object>() {

			@Override
			public String getValue(Object object) {
				if (object
						.getClass()
						.getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Emprestimo")) {
					return ((Emprestimo) object).getMidiaObject().getTitulo();
				}

				return new String();
			}
		};
		colunaNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		cellTable.addColumn(colunaNome, "Título");

		// Adiciona coluna com o botão reservar.
		ButtonCell buttonCellReservar = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.reservar();
					String iconDisplay = AbstractImagePrototype.create(icon)
							.getHTML();
					iconDisplay = iconDisplay.replaceFirst(">",
							" title=\"Reservar\">");
					SafeHtml html = SafeHtmlUtils
							.fromTrustedString(iconDisplay);
					sb.append(html);
				}
			}
		};
		Column<Object, String> colunaReservar = new Column<Object, String>(
				buttonCellReservar) {
			@Override
			public String getValue(Object object) {
				return "Reservar";
			}
		};
		colunaReservar.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int index, Object object, String value) {
				if (object
						.getClass()
						.getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Emprestimo")) {
					principal.getControle().setDialogo(new DialogBox(false));

					principal.getControle().getDialogo().setWidth("450px");
					principal.getControle().getDialogo().setHeight("91px");

					Reserva reserva = new Reserva(((Emprestimo) object),
							principal.getControle().getDialogo());

					principal.getControle().getDialogo().setWidget(reserva);
					principal.getControle().getDialogo().center();
				}
			}
		});
		colunaReservar.setCellStyleNames("gwt-cell-pointer");
		cellTable.addColumn(colunaReservar, new String());
		cellTable.setColumnWidth(colunaReservar, "16px");

		// Adiciona coluna com o botão reservar.
		ButtonCell buttonCellBaixar = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.baixar();
					String iconDisplay = AbstractImagePrototype.create(icon)
							.getHTML();
					iconDisplay = iconDisplay.replaceFirst(">",
							" title=\"Baixar\">");
					SafeHtml html = SafeHtmlUtils
							.fromTrustedString(iconDisplay);
					sb.append(html);
				}
			}
		};
		Column<Object, String> colunaBaixar = new Column<Object, String>(
				buttonCellBaixar) {
			@Override
			public String getValue(Object object) {
				return "Baixar";
			}
		};
		colunaBaixar.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int index, Object object, String value) {
				if (object
						.getClass()
						.getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Emprestimo")) {
					if (((Emprestimo) object).getEntrega() == null) {
						if (Window.confirm("Deseja baixar \""
								+ ((Emprestimo) object).getMidiaObject()
										.getTitulo()
								+ "\" emprestado para \""
								+ ((Emprestimo) object).getMembroObject()
										.getNome() + "\"?")) {
							emprestimoService.baixar(
									((Emprestimo) object).getId(),
									new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
										};

										@Override
										public void onSuccess(Void result) {
											limpar();
											listarEmprestimo();
										};
									});
						}
					} else {
						Window.alert("Esta mídia já foi devolvida.");
					}

				}
			}
		});
		colunaBaixar.setCellStyleNames("gwt-cell-pointer");
		cellTable.addColumn(colunaBaixar, new String());
		cellTable.setColumnWidth(colunaBaixar, "16px");

		// Adiciona coluna com o botão visualizar.
		ButtonCell buttonCellVisualizar = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.visualizar();
					String iconDisplay = AbstractImagePrototype.create(icon)
							.getHTML();
					iconDisplay = iconDisplay.replaceFirst(">",
							" title=\"Visualizar\">");
					SafeHtml html = SafeHtmlUtils
							.fromTrustedString(iconDisplay);
					sb.append(html);
				}
			}
		};
		Column<Object, String> colunaVisualizar = new Column<Object, String>(
				buttonCellVisualizar) {

			@Override
			public String getValue(Object object) {
				return "Visualizar";
			}
		};
		colunaVisualizar.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int index, Object object, String value) {
				if (object
						.getClass()
						.getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Emprestimo")) {

					principal.getControle().setDialogo(new DialogBox(false));

					principal.getControle().getDialogo().setWidth("451px");
					principal.getControle().getDialogo().setHeight("124px");
					
					principal
							.getControle()
							.getDialogo()
							.setWidget(
									new br.org.olimpiabarbacena.client.formulario.midia.Emprestimo(
											((Emprestimo) object), principal
													.getControle().getDialogo()));
					principal.getControle().getDialogo().center();
				}
			}
		});
		colunaVisualizar.setCellStyleNames("gwt-cell-pointer");
		cellTable.addColumn(colunaVisualizar, new String());
		cellTable.setColumnWidth(colunaVisualizar, "16px");

		// Adiciona coluna com o botão remover.
		ButtonCell buttonCellRemover = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.remover();
					String iconDisplay = AbstractImagePrototype.create(icon)
							.getHTML();
					iconDisplay = iconDisplay.replaceFirst(">",
							" title=\"Remover\">");
					SafeHtml html = SafeHtmlUtils
							.fromTrustedString(iconDisplay);
					sb.append(html);
				}
			}
		};
		Column<Object, String> colunaRemover = new Column<Object, String>(
				buttonCellRemover) {
			@Override
			public String getValue(Object object) {
				return "Remover";
			}
		};
		colunaRemover.setFieldUpdater(new FieldUpdater<Object, String>() {
			@Override
			public void update(int index, Object object, String value) {
				if (object
						.getClass()
						.getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Emprestimo")) {
					if (Window.confirm("Deseja remover \""
							+ ((Emprestimo) object).getMidiaObject()
									.getTitulo() + "\" emprestado para \""
							+ ((Emprestimo) object).getMembroObject().getNome()
							+ "\"?")) {
						emprestimoService.remover(
								((Emprestimo) object).getId(),
								new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
									};

									@Override
									public void onSuccess(Void result) {
										limpar();
										listarEmprestimo();
									};
								});
					}
				}
			}
		});
		colunaRemover.setCellStyleNames("gwt-cell-pointer");
		cellTable.addColumn(colunaRemover, new String());
		cellTable.setColumnWidth(colunaRemover, "16px");

		dataProvider = new AsyncDataProvider<Object>() {

			@Override
			protected void onRangeChanged(HasData<Object> display) {
				final Range range = display.getVisibleRange();
				final int start = range.getStart();
				final int end = start + range.getLength();

				emprestimoService.listar(textboxPesquisar.getText(),
						new AsyncCallback<List<Emprestimo>>() {
							@Override
							public void onFailure(Throwable caught) {
								Window.alert(caught.getMessage());
							}

							@Override
							public void onSuccess(List<Emprestimo> result) {
								// convert old list type to new list type
								List<Object> list = new ArrayList<Object>(
										result.size());
								for (Emprestimo emprestimo : result) {
									list.add(emprestimo);
								}
								updateRowData(start, list.subList(start,
										(result.size() < end) ? result.size()
												: end));
								updateRowCount(result.size(), true);
							}
						});
			}
		};
		dataProvider.addDataDisplay(cellTable);
	}

	@UiHandler("buttonPesquisar")
	void onButtonPesquisarClick(ClickEvent event) {
		if (principal.getMenu().getSelecionado() == Pesquisa.ACERVO) {
			this.listarAcervo();
		} else if (principal.getMenu().getSelecionado() == Pesquisa.MEMBRO) {
			this.listarMembro();
		} else if (principal.getMenu().getSelecionado() == Pesquisa.EMPRESTIMO) {
			this.listarEmprestimo();
		}
	}
}
