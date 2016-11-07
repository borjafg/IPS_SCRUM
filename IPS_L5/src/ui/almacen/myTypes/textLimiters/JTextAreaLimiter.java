package ui.almacen.myTypes.textLimiters;

import java.awt.Toolkit;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class JTextAreaLimiter extends DocumentFilter {

	private int maxCharacters;

	public JTextAreaLimiter(int maxCharacters) {
		super();

		this.maxCharacters = maxCharacters;
	}

	@Override
	public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {

		if ((fb.getDocument().getLength() + str.length()) <= maxCharacters)
			super.insertString(fb, offs, str, a);

		else
			// Emitir un pitido
			Toolkit.getDefaultToolkit().beep();
	}

	@Override
	public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {

		if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters)
			super.replace(fb, offs, length, str, a);

		else
			Toolkit.getDefaultToolkit().beep();
	}
}