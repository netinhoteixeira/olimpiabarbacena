package br.org.olimpiabarbacena.client.formulario.midia;

import br.org.olimpiabarbacena.client.rpc.EmprestimoService;
import br.org.olimpiabarbacena.client.rpc.EmprestimoServiceAsync;
import br.org.olimpiabarbacena.shared.dados.Emprestimo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.Label;

/**
 * <p>
 * Este &eacute; o formul&aacute;rio para inser&ccedil;&atilde;o de Reserva de
 * M&iacute;dia.
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
public class Reserva extends Composite {

	Emprestimo emprestimo;
	DialogBox dialogo;
	private static ReservaUiBinder uiBinder = GWT.create(ReservaUiBinder.class);
	@UiField
	TextBox textboxReservadoPor;
	@UiField Label labelUltimaReserva;
	@UiField DateLabel labelReserva;
	@UiField
	Button buttonOK;
	@UiField
	Button buttonCancelar;

	private final EmprestimoServiceAsync emprestimoService = GWT
			.create(EmprestimoService.class);

	interface ReservaUiBinder extends UiBinder<Widget, Reserva> {
	}

	public Reserva(Emprestimo emprestimo, DialogBox dialogo) {
		this.emprestimo = emprestimo;
		this.dialogo = dialogo;
		initWidget(uiBinder.createAndBindUi(this));

		textboxReservadoPor.setText(emprestimo.getReservadoPor());
		if (emprestimo.getReserva() != null) {
			labelReserva.setValue(emprestimo.getReserva());
		} else {
			labelUltimaReserva.setVisible(false);
			labelReserva.setVisible(false);
		}
	}

	@UiHandler("buttonOK")
	void onButtonOKClick(ClickEvent event) {
		emprestimoService.reservar(emprestimo.getId(), textboxReservadoPor.getText(), new AsyncCallback<Void>() {
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
