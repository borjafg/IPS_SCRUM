package ui.almacen.recogida;

import java.awt.Dimension;

import javax.swing.JPanel;

import ui.almacen.GestionAlmacen;

public class PanelRetomarOrdenTrabajo extends JPanel {

	private static final long serialVersionUID = -2179720108518020612L;

	private GestionAlmacen ventanaPrincipal;

	public PanelRetomarOrdenTrabajo() {
		super();

		setPreferredSize(new Dimension(374, 530));
	}

	public void inicializarDatos() {
		// Cargar lista de ordenes de trabajo con productos para recoger
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	private void reiniciarPanel() {
		// TODO
	}

	public void setVentanaPrincipal(GestionAlmacen ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}