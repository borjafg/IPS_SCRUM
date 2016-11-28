package ui.almacen.myTypes.ventanaMensaje;

import javax.swing.Icon;
import javax.swing.UIManager;

import ui.almacen.VentanaPrincipalAlmacenero;

public class MostrarMensaje {

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	public MostrarMensaje(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private void showMessage(String titulo, String mensaje, Icon icon) {
		Mensaje dialog = new Mensaje();

		dialog.setLocationRelativeTo(ventanaPrincipal);

		dialog.setTitle(titulo);
		dialog.setMensaje(mensaje);
		dialog.setIconImage(IconToImage.convert(icon));

		dialog.setVisible(true);
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
		showMessage(titulo, mensaje, (Icon) UIManager.getIcon("OptionPane.informationIcon"));
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
		showMessage(titulo, mensaje, (Icon) UIManager.getIcon("OptionPane.warningIcon"));
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
		showMessage(titulo, mensaje, (Icon) UIManager.getIcon("OptionPane.errorIcon"));
	}

}