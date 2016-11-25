package ui.administracion.paneles;

import javax.swing.JPanel;

import ui.administracion.VentanaPrincipalAdministracion;

public class PanelOpciones extends JPanel {

	private static final long serialVersionUID = -8205235287522932377L;

	private VentanaPrincipalAdministracion ventanaPrincipal;

	public PanelOpciones() {
		super();
	}

	// =========================================
	// Controlar el estado del panel
	// =========================================

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

}