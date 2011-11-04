package br.org.olimpiabarbacena.shared;

import com.google.gwt.event.dom.client.KeyCodes;

public class InputValidator {

	public static boolean isInteger(char keycode) {
		return Character.isDigit(keycode)
				|| (keycode == (char) KeyCodes.KEY_TAB)
				|| (keycode == (char) KeyCodes.KEY_BACKSPACE)
				|| (keycode == (char) KeyCodes.KEY_DELETE)
				|| (keycode == (char) KeyCodes.KEY_ENTER)
				|| (keycode == (char) KeyCodes.KEY_HOME)
				|| (keycode == (char) KeyCodes.KEY_END)
				|| (keycode == (char) KeyCodes.KEY_LEFT)
				|| (keycode == (char) KeyCodes.KEY_UP)
				|| (keycode == (char) KeyCodes.KEY_RIGHT)
				|| (keycode == (char) KeyCodes.KEY_DOWN);
	}

}
