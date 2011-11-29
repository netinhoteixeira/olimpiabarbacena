package br.org.olimpiabarbacena.client.formulario.midia;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class Emprestimo extends Composite {

	br.org.olimpiabarbacena.shared.dados.Emprestimo emprestimo;
	DialogBox dialogo;
	private static EmprestimoUiBinder uiBinder = GWT
			.create(EmprestimoUiBinder.class);
	@UiField
	TextBox textboxEmprestadoPara;
	@UiField
	IntegerBox integerboxPrevisao;
	@UiField
	DateBox dateboxEntrega;
	@UiField
	Button buttonFechar;

	interface EmprestimoUiBinder extends UiBinder<Widget, Emprestimo> {
	}

	public Emprestimo(
			br.org.olimpiabarbacena.shared.dados.Emprestimo emprestimo,
			DialogBox dialogo) {
		this.emprestimo = emprestimo;
		this.dialogo = dialogo;
		initWidget(uiBinder.createAndBindUi(this));

		textboxEmprestadoPara.setValue(emprestimo.getMembroObject().getNome());
		integerboxPrevisao.setValue(emprestimo.getDiasEntrega());
		if (emprestimo.getEntrega() != null) {
			dateboxEntrega.setValue(emprestimo.getEntrega());
		}
	}

	@UiHandler("buttonFechar")
	void onButtonFecharClick(ClickEvent event) {
		dialogo.hide();
	}
}
