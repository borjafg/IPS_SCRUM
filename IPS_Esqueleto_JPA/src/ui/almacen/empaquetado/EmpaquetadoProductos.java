package ui.almacen.empaquetado;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Cliente;
import model.OrdenTrabajo;
import model.Paquete;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.empaquetado.modelosTabla.ModeloTablaProductos;

public class EmpaquetadoProductos extends JPanel {

	private static final long serialVersionUID = -45321105L;

	private JFrame ventanaPrincipal;

	private JPanel panelAsignacionProductos_Centro;
	private JPanel panelAsignacionProductos_Sur;
	private JTable tablaProductosOrdenTrabajo;
	private JButton botonSimularLecturaProducto;

	private ModeloTablaProductos modeloTablaProductos;

	private OrdenTrabajo ordenTrabajoActual;
	private Paquete paqueteActual;

	public EmpaquetadoProductos(JFrame ventanaPrincipal) throws BusinessException {
		modeloTablaProductos = new ModeloTablaProductos();
		
		this.ventanaPrincipal = ventanaPrincipal;

		this.setLayout(new BorderLayout(3, 3));
		this.add(getPanelAsignacionProductos_Centro(), BorderLayout.CENTER);
		this.add(getPanelAsignacionProductos_Sur(), BorderLayout.SOUTH);
	}

	/**
	 * Carga la lista de productos que hay que empaquetar
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void inicializar(OrdenTrabajo ordenTrabajo) throws BusinessException {
		this.ordenTrabajoActual = ordenTrabajo;
		
		List<ProductoEnOrdenTrabajo> listaProductos = ServiceFactory.getEmpaquetadoService()
				.getListaProductosOrdenTrabajo(ordenTrabajoActual.getId());

		for (ProductoEnOrdenTrabajo producto : listaProductos) {
			modeloTablaProductos.addProducto(producto);
		}

		paqueteActual = new Paquete();
	}

	/* ================================================= */
	/* === Panel de asignacion de producto a paquete === */
	/* ================================================= */

	private JPanel getPanelAsignacionProductos_Centro() {
		if (panelAsignacionProductos_Centro == null) {
			panelAsignacionProductos_Centro = new JPanel();
			panelAsignacionProductos_Centro.setLayout(new GridLayout(1, 0, 5, 0));
			panelAsignacionProductos_Centro.add(getProductosOrdenTrabajo());
		}

		return panelAsignacionProductos_Centro;
	}

	private JPanel getPanelAsignacionProductos_Sur() {
		if (panelAsignacionProductos_Sur == null) {
			panelAsignacionProductos_Sur = new JPanel();
			FlowLayout fl_panelAsignacionProductos_Sur = (FlowLayout) panelAsignacionProductos_Sur.getLayout();
			fl_panelAsignacionProductos_Sur.setAlignment(FlowLayout.RIGHT);
			panelAsignacionProductos_Sur.add(getBotonSimularLecturaProducto());
		}

		return panelAsignacionProductos_Sur;
	}

	private JTable getProductosOrdenTrabajo() {
		if (tablaProductosOrdenTrabajo == null) {
			tablaProductosOrdenTrabajo = new JTable(modeloTablaProductos);
			tablaProductosOrdenTrabajo.setFocusable(false);
			tablaProductosOrdenTrabajo.setRowSelectionAllowed(false);
			tablaProductosOrdenTrabajo.setCellSelectionEnabled(false);
		}

		return tablaProductosOrdenTrabajo;
	}

	private JButton getBotonSimularLecturaProducto() {
		if (botonSimularLecturaProducto == null) {
			botonSimularLecturaProducto = new JButton("Simular lector");

			botonSimularLecturaProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					simularLecturaReferencia();
				}
			});
		}

		return botonSimularLecturaProducto;
	}

	private void simularLecturaReferencia() {
		ProductoEnOrdenTrabajo producto = modeloTablaProductos.getProducto(0);

		String seleccion = JOptionPane.showInputDialog(ventanaPrincipal,
				producto.getOrdenTrabajo().getId() + "", "Ref. orden trabajo", JOptionPane.QUESTION_MESSAGE);

		try {
			Long valor = Long.parseLong(seleccion);

			if (valor.equals(ordenTrabajoActual.getId())) {
				ServiceFactory.getEmpaquetadoService().asignarProductoPaquete(producto, paqueteActual);
			}

			else {
				JOptionPane.showMessageDialog(ventanaPrincipal, "Las referencias no coinciden", "Error", 
						JOptionPane.ERROR_MESSAGE);
			}

			modeloTablaProductos.removeProducto(0);

			if (modeloTablaProductos.getRowCount() == 0) {
				Cliente destinatario = ServiceFactory.getEmpaquetadoService()
						.getClientePedido(producto.getproductoPedido().getPedido());

				JOptionPane.showMessageDialog(ventanaPrincipal, 
						generarEtiquetas(producto, destinatario), "Generando etiquetas",  JOptionPane.PLAIN_MESSAGE);
			}
		}

		catch (NumberFormatException excep) {
			JOptionPane.showMessageDialog(ventanaPrincipal, "Entrada incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
		}

		catch (BusinessException excep) {
			JOptionPane.showMessageDialog(ventanaPrincipal, "No se ha podido realizar la operación", "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private String generarEtiquetas(ProductoEnOrdenTrabajo producto, Cliente cliente) {
		StringBuilder sb = new StringBuilder();

		sb.append("Direccion: ").append(producto.getproductoPedido().getPedido().getDireccionCompleta()).append("\n");
		sb.append("destinatario: ").append(cliente.getNombre()).append("\n");
		sb.append("Codigo paquete: ").append(paqueteActual.getId());

		return sb.toString();
	}

}