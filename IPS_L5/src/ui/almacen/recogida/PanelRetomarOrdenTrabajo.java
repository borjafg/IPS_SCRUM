package ui.almacen.recogida;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import business.exception.BusinessException;
import ui.almacen.VentanaPrincipalAlmacenero;

public class PanelRetomarOrdenTrabajo extends AbstractPanelRetomarOrdenTrabajo {

	private static final long serialVersionUID = -2179720108518020612L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	private JButton botonEmpezarRecoger;

	public PanelRetomarOrdenTrabajo() {
		super();

		setPreferredSize(new Dimension(300, 400));
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
					} catch (BusinessException excep) {
						ventanaPrincipal.gestionarErrorConexion(excep);
					}
				}
			});

			botonEmpezarRecoger.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return botonEmpezarRecoger;
	}

	@Override
	protected void accionClickTablaOrdenesTrabajo() {
		// TODO Auto-generated method stub

	}

}