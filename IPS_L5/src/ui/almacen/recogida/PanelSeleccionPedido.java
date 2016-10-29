package ui.almacen.recogida;

import java.awt.Dimension;

import javax.swing.JPanel;

import ui.almacen.GestionAlmacen;

public class PanelSeleccionPedido extends JPanel {

	private static final long serialVersionUID = -5092160186351L;

	private GestionAlmacen ventanaPrincipal;

	public PanelSeleccionPedido() {
		super();

		setPreferredSize(new Dimension(374, 530));
	}

	public void inicializarDatos() {
		// Cargar lista de posibles pedidos
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