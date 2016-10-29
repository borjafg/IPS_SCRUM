package ui.almacen.empaquetado;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.almacen.AbstractPanelRetomarOrdenTrabajo;

public class PanelOrdenesTrabajoEmpaquetar extends AbstractPanelRetomarOrdenTrabajo {

	private static final long serialVersionUID = -2747534485918494L;

	private JButton botonEmpezarRecoger;

	public PanelOrdenesTrabajoEmpaquetar() {
		super();

		setPreferredSize(new Dimension(374, 530));
	}

	@Override
	protected JButton getBotonContinuar() {
		if (botonEmpezarRecoger == null) {
			botonEmpezarRecoger = new JButton("Empezar a empaquetar");

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

	@Override
	public void inicializarDatos() {
		// Cargar lista de ordenes de trabajo asociadas al almacenero o que no
		// tienen un almacenero asignado

		// Estas órdenes de trabajo deben estar lista para empaquetar
	}
}