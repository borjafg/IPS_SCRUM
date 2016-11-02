package ui.almacen.recogida;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.OrdenTrabajo;
import model.Pedido;
import ui.almacen.VentanaPrincipalAlmacenero;
import ui.almacen.myTypes.model.MyPedido;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaPedidos;

public class PanelSeleccionPedido extends JPanel {

	private static final long serialVersionUID = -5092160186351L;

	protected VentanaPrincipalAlmacenero ventanaPrincipal;

	protected ModeloTablaPedidos modeloTablaPedidos;

	private JLabel labelPedidos;
	private JScrollPane scrollPanePedidos;
	private JTable tablaPedidosPendientes;

	// ==== Panel sur ====

	private JPanel panelSur;
	private JButton botonAtras;
	private JPanel panelNorte;

	public PanelSeleccionPedido() {
		super();

		setLayout(new BorderLayout(0, 0));
		add(getPanelNorte(), BorderLayout.NORTH);
		add(getScrollPanePedidos(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);

		setPreferredSize(new Dimension(374, 530));
	}

	public void inicializarDatos() throws BusinessException {
		List<MyPedido> pedidos = ServiceFactory.getRecogidaService().obtenerListaPedidosSinOrdenTrabajo();

		for (MyPedido ped : pedidos) {
			modeloTablaPedidos.addPedido(ped);
		}
	}

	private JLabel getLabelPedidos() {
		if (labelPedidos == null) {
			labelPedidos = new JLabel("Pedidos sin orden trabajo");

			labelPedidos.setFont(new Font("Tahoma", Font.BOLD, 18));
			labelPedidos.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelPedidos;
	}

	private JScrollPane getScrollPanePedidos() {
		if (scrollPanePedidos == null) {
			scrollPanePedidos = new JScrollPane(getTablaPedidosPendientes());
			scrollPanePedidos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			scrollPanePedidos.setBorder(new EmptyBorder(0, 2, 0, 2));

			scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPanePedidos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPanePedidos;
	}

	private JTable getTablaPedidosPendientes() {
		if (tablaPedidosPendientes == null) {
			modeloTablaPedidos = new ModeloTablaPedidos();
			tablaPedidosPendientes = new JTable(modeloTablaPedidos);

			tablaPedidosPendientes.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int filaSeleccionada = tablaPedidosPendientes.getSelectedRow();

					if (filaSeleccionada != -1) {
						Pedido pedido = modeloTablaPedidos.getPedido(filaSeleccionada);

						if (pedido != null) {
							try {
								OrdenTrabajo ot = ServiceFactory.getRecogidaService().generarOrdenTrabajo(pedido,
										ventanaPrincipal.getAlmacenero());

								reiniciarPanel();
								
								ventanaPrincipal.setOrdenTrabajo(ot);
								ventanaPrincipal.mostrarPanelRecogidaProductos();
							} catch (BusinessException excep) {
								reiniciarPanel();
								ventanaPrincipal.gestionarErrorConexion();
							}
						}
					}

				}
			});

			tablaPedidosPendientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tablaPedidosPendientes.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

			tablaPedidosPendientes.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
			tablaPedidosPendientes.setFont(new Font("Tahoma", Font.BOLD, 14));
		}

		return tablaPedidosPendientes;
	}

	// =====================================
	// Panel sur
	// =====================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			panelSur.setBorder(new EmptyBorder(8, 8, 8, 8));

			GridBagLayout gbl_panelSur = new GridBagLayout();

			gbl_panelSur.columnWidths = new int[] { 200, 80, 0 };
			gbl_panelSur.rowHeights = new int[] { 34, 0 };
			gbl_panelSur.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelSur.rowWeights = new double[] { 1.0, Double.MIN_VALUE };

			panelSur.setLayout(gbl_panelSur);

			GridBagConstraints gbc_botonAtras = new GridBagConstraints();

			gbc_botonAtras.fill = GridBagConstraints.BOTH;
			gbc_botonAtras.gridx = 1;
			gbc_botonAtras.gridy = 0;

			panelSur.add(getBotonAtras(), gbc_botonAtras);
		}

		return panelSur;
	}

	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atr\u00E1s");

			botonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reiniciarPanel();
					ventanaPrincipal.volverPanelOpciones();
				}
			});

			botonAtras.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return botonAtras;
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	private void reiniciarPanel() {
		modeloTablaPedidos.removeAll();
	}

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBorder(new EmptyBorder(1, 0, 5, 0));
			panelNorte.add(getLabelPedidos());
		}

		return panelNorte;
	}
}