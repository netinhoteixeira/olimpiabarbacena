package br.org.olimpiabarbacena.client;

import java.util.ArrayList;
import java.util.List;

import br.org.olimpiabarbacena.client.resource.Icons;
import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.client.rpc.MembroServiceAsync;
import br.org.olimpiabarbacena.client.rpc.MidiaService;
import br.org.olimpiabarbacena.client.rpc.MidiaServiceAsync;
import br.org.olimpiabarbacena.shared.Pesquisa;
import br.org.olimpiabarbacena.shared.dados.Membro;
import br.org.olimpiabarbacena.shared.dados.Midia;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
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

	interface PesquisarUiBinder extends UiBinder<Widget, Pesquisar> {
	}

	public Pesquisar(final Principal principal) {
		this.principal = principal;
		initWidget(uiBinder.createAndBindUi(this));

		simplePager.setDisplay(cellTable);
		simplePager.setPageSize(6);

		// Adiciona a coluna texto para exibir o nome.
		TextColumn<Object> colunaNome = new TextColumn<Object>() {

			@Override
			public String getValue(Object object) {
				String nome = new String();

				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Midia")) {
					nome = ((Midia) object).getTitulo();
				} else if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Membro")) {
					nome = ((Membro) object).getNome();
				}

				return nome;
			}
		};
		colunaNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		cellTable.addColumn(colunaNome, "Nome");

		// Adiciona a coluna com o botão editar.
		ButtonCell buttonCellEditar = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.pencil();
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
			public void update(int index, Object object, String value) {

				if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Midia")) {
					principal.getControle().setDialogo(new DialogBox(false));

					principal.getControle().getDialogo().setWidth("458px");
					principal.getControle().getDialogo().setHeight("283px");

					principal.getControle().setLivro(new br.org.olimpiabarbacena.client.formulario.midia.Livro(principal, principal.getControle().getDialogo()));
					principal.getControle().getLivro().buttonEmprestimo.setVisible(false);
					principal.getControle().getLivro().buttonReservar.setVisible(false);
					principal.getControle().getLivro().buttonFechar.setText("Cancelar");

					principal.getControle().getLivro().get(((Midia) object).getId());

					principal.getControle().getDialogo().setWidget(principal.getControle().getLivro());
					principal.getControle().getDialogo().center();
				} else if (object.getClass().getName()
						.equals("br.org.olimpiabarbacena.shared.dados.Membro")) {
					principal.getControle().setDialogo(new DialogBox(false));

					principal.getControle().getDialogo().setWidth("458px");
					principal.getControle().getDialogo().setHeight("283px");

					br.org.olimpiabarbacena.client.formulario.Membro membro = new br.org.olimpiabarbacena.client.formulario.Membro(
							principal, principal.getControle().getDialogo());
					membro.buttonHistorico.setVisible(false);
					membro.buttonExcluir.setVisible(false);
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

		// Adiciona a coluna com o botão remover.
		ButtonCell buttonCellRemover = new ButtonCell() {

			@Override
			public void render(Context context, SafeHtml data,
					SafeHtmlBuilder sb) {
				if (data != null) {
					ImageResource icon = Icons.INSTANCE.delete();
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
				} else if (object.getClass().getName()
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
	}

	public void limpar() {
		textboxPesquisar.setText(new String());
	}

	public void listarAcervo() {
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

	@UiHandler("buttonPesquisar")
	void onButtonPesquisarClick(ClickEvent event) {
		if (principal.getMenu().getSelecionado() == Pesquisa.ACERVO) {
			this.listarAcervo();
		} else {
			this.listarMembro();
		}
	}
}
