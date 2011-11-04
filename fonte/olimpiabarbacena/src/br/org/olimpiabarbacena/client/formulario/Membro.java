package br.org.olimpiabarbacena.client.formulario;

import br.org.olimpiabarbacena.client.rpc.MembroService;
import br.org.olimpiabarbacena.client.rpc.MembroServiceAsync;
import br.org.olimpiabarbacena.shared.InputValidator;
import br.org.olimpiabarbacena.shared.dados.Sexo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class Membro extends Composite {

	private static MembroUiBinder uiBinder = GWT.create(MembroUiBinder.class);
	@UiField
	TextBox textboxNome;
	@UiField
	DateBox dateboxNascimento;
	@UiField
	RadioButton radioSexoMasculino;
	@UiField
	RadioButton radioSexoFeminino;
	@UiField
	TextBox textboxCPF;
	@UiField
	TextBox textboxTelefone;
	@UiField
	TextBox textboxEmail;
	@UiField
	TextBox textboxEndereco;
	@UiField
	TextBox textboxCidade;
	@UiField
	ListBox comboEstado;
	@UiField
	TextBox textboxCEP;
	@UiField
	public Button buttonHistorico;
	@UiField
	public Button buttonExcluir;
	@UiField
	public Button buttonSalvar;
	@UiField
	public Button buttonFechar;
	DialogBox dialogo;

	private final MembroServiceAsync membroService = GWT
			.create(MembroService.class);

	interface MembroUiBinder extends UiBinder<Widget, Membro> {
	}

	public Membro(DialogBox dialogo) {
		this.dialogo = dialogo;
		initWidget(uiBinder.createAndBindUi(this));

		dateboxNascimento.setFormat(new DateBox.DefaultFormat(DateTimeFormat
				.getFormat("dd/MM/yyyy")));

		// Let's disallow non-numeric entry in the normal text box.
		textboxCPF.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!InputValidator.isInteger(event.getCharCode())) {
					// TextBox.cancelKey() suppresses the current keyboard
					((TextBox) event.getSource()).cancelKey();
				}
			}
		});

		// Let's disallow non-numeric entry in the normal text box.
		textboxCEP.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!InputValidator.isInteger(event.getCharCode())) {
					// TextBox.cancelKey() suppresses the current keyboard
					((TextBox) event.getSource()).cancelKey();
				}

			}
		});

		// adiciona a lista dos estados brasileiros
		comboEstado.addItem("AC");
		comboEstado.addItem("AL");
		comboEstado.addItem("AP");
		comboEstado.addItem("AM");
		comboEstado.addItem("BA");
		comboEstado.addItem("CE");
		comboEstado.addItem("DF");
		comboEstado.addItem("ES");
		comboEstado.addItem("GO");
		comboEstado.addItem("MA");
		comboEstado.addItem("MT");
		comboEstado.addItem("MS");
		comboEstado.addItem("MG");
		comboEstado.addItem("PA");
		comboEstado.addItem("PB");
		comboEstado.addItem("PR");
		comboEstado.addItem("PE");
		comboEstado.addItem("PI");
		comboEstado.addItem("RJ");
		comboEstado.addItem("RN");
		comboEstado.addItem("RS");
		comboEstado.addItem("RO");
		comboEstado.addItem("RR");
		comboEstado.addItem("SC");
		comboEstado.addItem("SP");
		comboEstado.addItem("SE");
		comboEstado.addItem("TO");
	}

	@UiHandler("buttonSalvar")
	void onButtonSalvarClick(ClickEvent event) {
		br.org.olimpiabarbacena.shared.dados.Membro membro = new br.org.olimpiabarbacena.shared.dados.Membro();
		membro.setNome(textboxNome.getText());
		membro.setNascimento(dateboxNascimento.getValue());
		if (radioSexoMasculino.getValue()) {
			membro.setSexo(Sexo.MASCULINO);
		} else {
			membro.setSexo(Sexo.FEMININO);
		}
		membro.setCPF(textboxCPF.getText());
		membro.setTelefone(textboxTelefone.getText());
		membro.setEmail(textboxEmail.getText());
		membro.setEndereco(textboxEndereco.getText());
		membro.setCidade(textboxCidade.getText());
		membro.setEstado(comboEstado.getItemText(comboEstado.getSelectedIndex()));
		membro.setCEP(textboxCEP.getText());

		membroService.salvar(membro, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub

			}
		});
	}

	@UiHandler("buttonFechar")
	void onButtonFecharClick(ClickEvent event) {
		dialogo.hide();
	}
}
