package br.org.olimpiabarbacena.client.formulario.midia;

import java.util.List;

import br.org.olimpiabarbacena.client.rpc.EmprestimoService;
import br.org.olimpiabarbacena.client.rpc.EmprestimoServiceAsync;
import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.client.rpc.MembroServiceAsync;
import br.org.olimpiabarbacena.shared.dados.Membro;
import br.org.olimpiabarbacena.shared.dados.Midia;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * <p>
 * Este &eacute; o formul&aacute;rio para inser&ccedil;&atilde;o de
 * Empr&eacute;stimo de M&iacute;dia.
 * </p>
 * <p>
 * Esta classe est&aacute; no pacote <code>formulario</code> porque &eacute;
 * onde se encontram todos os formul&aacute;rios do cliente. No cliente,
 * n&oacute;s permitimos o fornecimento de informa&ccedil;&otilde;es para serem
 * enviados por uma requisi&ccedil;&atilde;o RPC. No servidor, n&oacute;s
 * inserimos os dados fornecidos caso n&atilde;o possua uma
 * identifica&ccedil;&atilde;o ou salvamos caso exista uma
 * identifica&ccedil;&atilde;o.
 * </p>
 */
public class Emprestar extends Composite {

	Midia midia;
	DialogBox dialogo;
	private static EmprestarUiBinder uiBinder = GWT
			.create(EmprestarUiBinder.class);
	@UiField
	ListBox listboxMembro;
	@UiField
	Button buttonOK;
	@UiField
	IntegerBox textboxDiasEntrega;
	@UiField
	Button buttonCancelar;

	private final MembroServiceAsync membroService = GWT
			.create(MembroService.class);

	private final EmprestimoServiceAsync emprestimoService = GWT
			.create(EmprestimoService.class);

	interface EmprestarUiBinder extends UiBinder<Widget, Emprestar> {
	}

	public Emprestar(Midia midia, DialogBox dialogo) {
		this.midia = midia;
		this.dialogo = dialogo;
		initWidget(uiBinder.createAndBindUi(this));

		membroService.listar(new String(), new AsyncCallback<List<Membro>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(List<Membro> result) {
				for (Membro membro : result) {
					listboxMembro.addItem(membro.getNome(), membro.getId());
				}
			}
		});
	}

	@UiHandler("buttonOK")
	void onButtonOKClick(ClickEvent event) {
		br.org.olimpiabarbacena.shared.dados.Emprestimo emprestimo = new br.org.olimpiabarbacena.shared.dados.Emprestimo();
		emprestimo.setMidia(midia.getId());
		emprestimo.setMembro(listboxMembro.getValue(listboxMembro
				.getSelectedIndex()));
		emprestimo.setDiasEntrega(textboxDiasEntrega.getValue());

		emprestimoService.emprestar(emprestimo, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Void result) {
				dialogo.hide();
			}
		});
	}

	@UiHandler("buttonCancelar")
	void onButtonCancelarClick(ClickEvent event) {
		dialogo.hide();
	}
}
