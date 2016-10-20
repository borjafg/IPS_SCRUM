package ui.almacen.almacenero;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import ui.almacen.almacenero.logica.*;
import ui.almacen.almacenero.modelosTabla.ModeloTablaProductos;
import ui.almacen.almacenero.redefinicion.MyIncidencia;
import model.Incidencia;
import model.ProductoEnOrdenTrabajo;

import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaOrdenAlmacenero extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPaneListProductos;
	private JTable tableProductos;
	private JPanel panelBoton;
	private JButton btnIncidencia;
	private TerminalOT terminalOT;
	private ModeloTablaProductos modelo;
	
	private PanelListaOrdenesAlmacenero panelLista;
	private JButton btnValidar;
	private boolean hayIncidencias = false;

	/**
	 * Create the panel.
	 * @throws BusinessException 
	 */
	
	public VentanaOrdenAlmacenero(PanelListaOrdenesAlmacenero panelLista) throws BusinessException {
		this.panelLista = panelLista;
		setTitle("Orden de trabajo");
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(panelLista.getMainFrame()); 
		setBounds(100, 100, 700, 525);
		terminalOT = new TerminalOT(panelLista.getTerminalPedido().getOrdenTrabajo());
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getScrollPaneListProductos(), BorderLayout.CENTER);
		getContentPane().add(getPanelBoton(), BorderLayout.SOUTH);

	}

	private JScrollPane getScrollPaneListProductos() {
		if (scrollPaneListProductos == null) {
			scrollPaneListProductos = new JScrollPane();
			scrollPaneListProductos.setViewportView(getTableProductos());
		}
		return scrollPaneListProductos;
	}
	private JTable getTableProductos() {
		if (tableProductos == null) {
			tableProductos = new JTable();
			tableProductos.setToolTipText("Productos en la orden de trabajo");
			tableProductos.setRowSelectionAllowed(false);
			tableProductos.setFont(new Font("Arial", Font.PLAIN, 12));
			rellenarTabla();
		}
		return tableProductos;
	}

	private void rellenarTabla() {
		// String[] columns = {"Referencia", "Precio", "Posicion"};
		List<ProductoEnOrdenTrabajo> productos = terminalOT.getProductos();

		this.modelo = new ModeloTablaProductos();
		tableProductos.setModel(this.modelo);
		for (ProductoEnOrdenTrabajo producto : productos)
			modelo.addProducto(producto);

	}

	private JPanel getPanelBoton() {
		if (panelBoton == null) {
			panelBoton = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBoton.getLayout();
			flowLayout.setAlignment(FlowLayout.TRAILING);
			panelBoton.add(getBtnIncidencia());
			panelBoton.add(getBtnValidar());
		}
		return panelBoton;
	}

	private JButton getBtnIncidencia() {
		if (btnIncidencia == null) {
			btnIncidencia = new JButton("Generar incidencia");
			btnIncidencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					TerminalIncidencia terminalIncidencia = new TerminalIncidencia(terminalOT.getOrdenTrabajo());
					String causa = JOptionPane.showInputDialog("Señale la causa de la incidencia");
					if (causa != null) {
						try {
							Incidencia incidencia = terminalIncidencia.generarIncidencia(causa);
							MyIncidencia incidenciaString = new MyIncidencia(incidencia);
							JOptionPane.showMessageDialog(null, incidenciaString,
									"Incidencia generada", JOptionPane.INFORMATION_MESSAGE);
							hayIncidencias = true;
						} catch (BusinessException e1) {
							e1.printStackTrace();
						}
						
					}
				}
			});
		}
		return btnIncidencia;
	}
	
	public VentanaOrdenAlmacenero getDialog() {
		return this;
	}
	private JButton getBtnValidar() {
		if (btnValidar == null) {
			btnValidar = new JButton("Leer siguiente producto");
			btnValidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ProductoEnOrdenTrabajo producto = modelo.getProducto(0);
					try {
						ServiceFactory.getAlmacenService().actualizarProductoEnOrdenTrabajo(producto);
						modelo.removeProducto(0);
						
						if (modelo.getRowCount() == 0) {
							if (!hayIncidencias) {
								int respuesta = JOptionPane.showConfirmDialog(getThis(), "¿Pasar a empaquetar?", "Empaquetado", JOptionPane.YES_NO_OPTION);
								
								if (respuesta == JOptionPane.YES_OPTION)
									panelLista.getMainFrame().pasarEmpaquetar(terminalOT.getOrdenTrabajo());
								else {
									JOptionPane.showMessageDialog(getThis(), "Ha elegido no pasar a empaquetar el paquete", "Empaquetar", JOptionPane.INFORMATION_MESSAGE);
									
								}
								getThis().dispose();
							}
							else
								JOptionPane.showMessageDialog(getThis(), "No puede pasar a empaquetar ya que ha habido incidencias", "Error al empaquetar", JOptionPane.ERROR_MESSAGE);
						}
					} catch (BusinessException e) {
						e.printStackTrace();
					}
					
					
					
					
				}
			});
		}
		return btnValidar;
	}
	
	public VentanaOrdenAlmacenero getThis() {
		return this;
	}
}
