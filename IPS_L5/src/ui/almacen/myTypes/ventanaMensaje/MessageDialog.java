package ui.almacen.myTypes.ventanaMensaje;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ui.almacen.VentanaPrincipalAlmacenero;

public class MessageDialog {

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	public MessageDialog(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	/**
	 * Muestra un mensaje de diálogo del tipo INFORMATION_MESSAGE
	 * 
	 * @param titulo
	 *            titulo del mensaje
	 * @param mensaje
	 *            cuerpo del mensaje
	 * 
	 */
	public void info(String titulo, String mensaje) {
		JScrollPane sp = getScrollPane(mensaje);

		JOptionPane.showMessageDialog(ventanaPrincipal, sp, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Muestra un mensaje de diálogo del tipo WARNING_MESSAGE
	 * 
	 * @param titulo
	 *            titulo del mensaje
	 * @param mensaje
	 *            cuerpo del mensaje
	 * 
	 */
	public void warning(String titulo, String mensaje) {
		JScrollPane sp = getScrollPane(mensaje);

		JOptionPane.showMessageDialog(ventanaPrincipal, sp, titulo, JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Muestra un mensaje de diálogo del tipo ERROR_MESSAGE
	 * 
	 * @param titulo
	 *            titulo del mensaje
	 * @param mensaje
	 *            cuerpo del mensaje
	 * 
	 */
	public void error(String titulo, String mensaje) {
		JScrollPane sp = getScrollPane(mensaje);

		JOptionPane.showMessageDialog(ventanaPrincipal, sp, titulo, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Devuelve un panel con barras de scroll un tamaño horizontal y vertical
	 * limitado
	 * 
	 * @param mensaje
	 *            mensaje que debe contener
	 * 
	 * @return panel de scroll con el mensaje indicado
	 * 
	 */
	private JScrollPane getScrollPane(String mensaje) {
		// -------------------------------------
		// Componente para colocar el texto
		// -------------------------------------

		JTextArea ta = new JTextArea(mensaje);

		ta.setWrapStyleWord(false);
		ta.setLineWrap(true);

		Dimension d = ta.getPreferredSize();

		// -------------------------------------
		// Panel de scroll para contenerlo
		// -------------------------------------

		JScrollPane sp = new JScrollPane(ta);

		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		sp.setPreferredSize(d); // Tamaño necesario para guardar el texto
		sp.setMaximumSize(new Dimension(290, 390)); // hasta un límite

		// -------------------------------------
		// Devolver el panel de scroll
		// -------------------------------------

		return sp;
	}

}