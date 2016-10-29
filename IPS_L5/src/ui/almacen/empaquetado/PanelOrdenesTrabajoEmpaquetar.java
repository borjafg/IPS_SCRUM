package ui.almacen.empaquetado;

import java.awt.Dimension;

import javax.swing.JPanel;

import ui.almacen.GestionAlmacen;

public class PanelOrdenesTrabajoEmpaquetar extends JPanel {

	private static final long serialVersionUID = -2747534485918494L;

	private GestionAlmacen ventanaPrincipal;

	public PanelOrdenesTrabajoEmpaquetar() {
		super();

		setPreferredSize(new Dimension(374, 530));
	}

	public void inicializarDatos() {
		// Cargar lista de ordenes de trabajo que se pueden empaquetar
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	private void reiniciarPanel() {

	}

	public void setVentanaPrincipal(GestionAlmacen ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}