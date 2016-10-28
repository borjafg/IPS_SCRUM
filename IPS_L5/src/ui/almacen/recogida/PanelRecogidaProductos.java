package ui.almacen.recogida;

import java.awt.Dimension;

import javax.swing.JPanel;

import ui.almacen.GestionAlmacen;

public class PanelRecogidaProductos extends JPanel {

	private static final long serialVersionUID = -2481078673400949799L;

	private GestionAlmacen ventanaPrincipal;
	
	public PanelRecogidaProductos(GestionAlmacen ventanaPrincipal) {
		super();
		
		this.ventanaPrincipal = ventanaPrincipal;
		
		setPreferredSize(new Dimension(374, 530));
	}

	public void inicializarDatos() {
		// Cargar lista de productos en la Orden de Trabajo
	}
}