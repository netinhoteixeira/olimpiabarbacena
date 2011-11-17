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
