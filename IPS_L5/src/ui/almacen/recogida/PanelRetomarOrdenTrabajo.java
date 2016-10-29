package ui.almacen.recogida;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.almacen.AbstractPanelRetomarOrdenTrabajo;
import ui.almacen.VentanaPrincipalAlmacenero;

public class PanelRetomarOrdenTrabajo extends AbstractPanelRetomarOrdenTrabajo {

	private static final long serialVersionUID = -2179720108518020612L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	private JButton botonEmpezarRecoger;

	public PanelRetomarOrdenTrabajo() {
		super();

		setPreferredSize(new Dimension(374, 530));
	}

	@Override
	public void inicializarDatos() {
		// Cargar lista de ordenes de trabajo con productos para recoger
	}
	
	@Override
	protected JButton getBotonContinuar() {
		if (botonEmpezarRecoger == null) {
			botonEmpezarRecoger = new JButton("Empezar a recoger");

			botonEmpezarRecoger.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						ventanaPrincipal.mostrarPanelRecogidaProductos();
					} catch (Exception excep) {
						ventanaPrincipal.gestionarErrorConexion();
					}
				}
			});

			botonEmpezarRecoger.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return botonEmpezarRecoger;
	}
}