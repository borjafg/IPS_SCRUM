package ui.almacen.empaquetado;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.OrdenTrabajo;
import ui.almacen.AbstractPanelRetomarOrdenTrabajo;
import ui.almacen.myTypes.model.MyPedido_OT_Retomar;

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
						int fila = getTablaOrdenesTrabajo().getSelectedRow();

						if (fila != -1) {
							ventanaPrincipal.setOrdenTrabajo(modeloTablaOrdenesTrabajo.getOrdenTrabajo(fila));

							ventanaPrincipal.mostrarPanelEmpaquetadoProductos();

							modeloTablaOrdenesTrabajo.removeAll();
							modeloTablaPedidosOrdenTrabajo.removeAll();
						}
					} catch (BusinessException excep) {
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

	@Override
	protected void accionClickTablaOrdenesTrabajo() {
		int fila = getTablaOrdenesTrabajo().getSelectedRow();

		if (fila != -1) {
			try {
				OrdenTrabajo ot = modeloTablaOrdenesTrabajo.getOrdenTrabajo(fila);

				List<MyPedido_OT_Retomar> pedidosEmpaquetar = ServiceFactory.getEmpaquetadoService().getPedidosOT(ot);

				modeloTablaPedidosOrdenTrabajo.setPedidosEmpaquetar(pedidosEmpaquetar);
			}

			catch (BusinessException e) {
				// TODO: handle exception
			}
		}
	}

}