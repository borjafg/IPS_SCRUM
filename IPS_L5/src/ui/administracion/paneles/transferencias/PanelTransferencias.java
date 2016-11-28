package ui.administracion.paneles.transferencias;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Pedido;
import ui.administracion.VentanaPrincipalAdministracion;

public class PanelTransferencias extends JPanel {

	private static final long serialVersionUID = -7679569757550310944L;

	private VentanaPrincipalAdministracion ventanaPrincipal;

	private JPanel panelTitulo;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton botonAtras;
	private JPanel panelLista;
	private JPanel panelConfirmarPago;
	private JButton btnConfirmar;
	private JScrollPane scrollPane;
	private JList<Pedido> listPedidos;
	private DefaultListModel<Pedido> modeloPedidos;

	/**
	 * Create the panel.
	 * 
	 */
	public PanelTransferencias() {
		super();

		setPreferredSize(new Dimension(816, 646));
		setLayout(new BorderLayout(0, 0));

		add(getPanelTitulo(), BorderLayout.NORTH);
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
		}

		return panelTitulo;
	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();

			panelCentro.setLayout(new BorderLayout(0, 0));

			panelCentro.add(getPanelLista(), BorderLayout.CENTER);
			panelCentro.add(getPanelConfirmarPago(), BorderLayout.EAST);
		}

		return panelCentro;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			FlowLayout fl_panelSur = (FlowLayout) panelSur.getLayout();
			fl_panelSur.setAlignment(FlowLayout.RIGHT);

			panelSur.add(getBotonAtras());
		}

		return panelSur;
	}

	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atr\u00E1s");

			botonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modeloPedidos.removeAllElements();
					ventanaPrincipal.volverPanelOpciones();
				}
			});

			botonAtras.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return botonAtras;
	}

	public void inicializarDatos() throws BusinessException {
		List<Pedido> listPedido = ServiceFactory.getAdministracionService().generarPedidosNoPagados();

		if (listPedido != null) {
			for (Pedido ped : listPedido) {
				modeloPedidos.addElement(ped);
			}
		}
	}

	// =========================================
	// Controlar el estado del panel
	// =========================================

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private JPanel getPanelLista() {
		if (panelLista == null) {
			panelLista = new JPanel();

			panelLista.setLayout(new BorderLayout(0, 0));

			panelLista.add(getScrollPane(), BorderLayout.CENTER);
		}

		return panelLista;
	}

	private JPanel getPanelConfirmarPago() {
		if (panelConfirmarPago == null) {
			panelConfirmarPago = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelConfirmarPago.getLayout();
			flowLayout.setVgap(200);

			panelConfirmarPago.add(getBtnConfirmar());
		}

		return panelConfirmarPago;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");

			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if (getListPedidos().getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Selecciones algún pedido para confirmalo");
					}

					else {
						Pedido ped = getListPedidos().getSelectedValue();

						try {
							ServiceFactory.getAdministracionService().confirmarPedido(ped);

							// elimino todos los datos
							modeloPedidos.removeAllElements();
							inicializarDatos();
						}

						catch (BusinessException e1) {
							e1.printStackTrace();
						}

						try {
							inicializarDatos();
						}

						catch (BusinessException e) {
							e.printStackTrace();
						}
					}
				}
			});

			btnConfirmar.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return btnConfirmar;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getListPedidos());

			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		}

		return scrollPane;
	}

	private JList<Pedido> getListPedidos() {
		if (listPedidos == null) {
			modeloPedidos = new DefaultListModel<Pedido>();

			listPedidos = new JList<Pedido>();

			listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listPedidos.setModel(modeloPedidos);
		}

		return listPedidos;
	}

}