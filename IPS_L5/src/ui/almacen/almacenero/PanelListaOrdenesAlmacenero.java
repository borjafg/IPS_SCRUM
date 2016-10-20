package ui.almacen.almacenero;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import business.exception.BusinessException;
import ui.almacen.GestionAlmacen;
import ui.almacen.almacenero.logica.*;
import ui.almacen.almacenero.redefinicion.MyPedido;
import model.Pedido;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelListaOrdenesAlmacenero extends JPanel {

	/**
	 * Panel que muestra los pedidos y permite seleccionarlos para generar órdenes de trabajo
	 * @author Nacho
	 */
	private static final long serialVersionUID = 1L;
	private JList<MyPedido> listPedidos;
	private DefaultListModel<MyPedido> listaModelo;
	private TerminalPedido terminalPedido;
	private JPanel panelBoton;
	private JButton btnGenerar;
	private JScrollPane scrollPaneTabla;
	private GestionAlmacen frame;

	/**
	 * Create the panel.
	 * @throws BusinessException 
	 */
	public PanelListaOrdenesAlmacenero(GestionAlmacen frame) throws BusinessException {
		this.frame = frame;
		setLayout(new BorderLayout(0, 0));
		listaModelo =  new DefaultListModel<MyPedido>();
		crearTerminalPedido();
		add(getPanelBoton(), BorderLayout.SOUTH);
		add(getScrollPaneTabla(), BorderLayout.CENTER);
	}

	private void crearTerminalPedido() throws BusinessException {
		terminalPedido = new TerminalPedido();
		
		for (Pedido pedido : terminalPedido.getPedidos()) 
			listaModelo.addElement(new MyPedido(pedido));
	}

	private JList<MyPedido> getListPedidos() {
		if (listPedidos == null) {
			listPedidos = new JList<MyPedido>();
			listPedidos.setBorder(new TitledBorder(null, "Pedidos disponibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			listPedidos.setBackground(SystemColor.menu);
			listPedidos.setToolTipText("Tabla de pedidos");
			listPedidos.setFont(new Font("Arial", Font.PLAIN, 12));
			listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPedidos.setModel(listaModelo);
			
		}
		
		return listPedidos;
	}
	
	private JPanel getPanelBoton() {
		if (panelBoton == null) {
			panelBoton = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBoton.getLayout();
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelBoton.setBackground(SystemColor.menu);
			panelBoton.add(getBtnGenerar());
		}
		
		return panelBoton;
	}
	
	private JButton getBtnGenerar() {
		if (btnGenerar == null) {
			btnGenerar = new JButton("Generar");
			
			btnGenerar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (listPedidos.isSelectionEmpty())
						JOptionPane.showMessageDialog(getThis(), "No hay nada seleccionado", "Error de selección de pedido", JOptionPane.ERROR_MESSAGE);
					else {
						try {
							terminalPedido.generarOrdenTrabajo(listaModelo.getElementAt(listPedidos.getSelectedIndex()).getPedido());
						
						VentanaOrdenAlmacenero orden = new VentanaOrdenAlmacenero(getVentanaActual());
						orden.setVisible(true);
						} catch (BusinessException e) {
							e.printStackTrace();
						}
					}
						
				}
			});
			
			btnGenerar.setToolTipText("Genera la orden de trabajo");
			btnGenerar.setFont(new Font("Arial", Font.PLAIN, 12));
			btnGenerar.setHorizontalAlignment(SwingConstants.TRAILING);
		}
		
		return btnGenerar;
	}
	
	private JScrollPane getScrollPaneTabla() {
		if (scrollPaneTabla == null) {
			scrollPaneTabla = new JScrollPane();
			scrollPaneTabla.setViewportView(getListPedidos());
		}
		
		return scrollPaneTabla;
	}
	
	private PanelListaOrdenesAlmacenero getVentanaActual() {
		return this;
	}
	
	public TerminalPedido getTerminalPedido() {
		return terminalPedido;
	}
	
	public GestionAlmacen getMainFrame() {
		return frame;
	}
	
	public PanelListaOrdenesAlmacenero getThis() {
		return this;
	}
}