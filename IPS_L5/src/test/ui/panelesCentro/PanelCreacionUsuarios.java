package test.ui.panelesCentro;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import test.ui.MenuPrincipal;

public class PanelCreacionUsuarios extends JPanel {

	
	private MenuPrincipal menuPrincipal;
	
	
	/**
	 * Create the panel.
	 */
	public PanelCreacionUsuarios() {
		super();
		setLayout(new BorderLayout(0, 0));
		
		// Definir el tamaño aproximado que debería tener el panel
		//
		setPreferredSize( new Dimension( 640, 480 ) );//definir tamaño por defecto
		
		
	}

	public void setVentanaPrincipal(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
		
	}

}
