package ui.almacen.empaquetado;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Paquete;
import model.Pedido;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.VentanaPrincipalAlmacenero;
import ui.almacen.escaneres.EscanerProductosEmpaquetar;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaProductosEmpaquetar;

public class PanelEmpaquetadoProductos extends JPanel {

	private static final long serialVersionUID = -45321105L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	private EscanerProductosEmpaquetar escaner;
	private ModeloTablaProductosEmpaquetar modeloTablaProductosEmpaquetar;
	private Paquete paqueteActual;

	// ===========================================
	// Componentes de este panel
	// ===========================================

	// ==== Panel norte ====

	private JPanel panelNorte;

	private JLabel labelOrdenTrabajo;
	private JTextField textFieldOrdenTrabajo;

	// ==== Panel centro ====

	private JScrollPane panelCentro;
	private JTable tablaProductosOrdenTrabajo;

	// ==== Panel Sur ====

	private JPanel panelSur;

	private JPanel panelPaqueteInfo;

	private JLabel labelPedido;
	private JTextField textFieldPedido;
	private JLabel labelPaquete;
	private JTextField textFieldPaquete;

	private JPanel panelOpciones;

	private JSpinner spinnerUnidades;
	private JButton botonAtras;
	private JButton botonAbrirCerrarPaquete;

	public PanelEmpaquetadoProductos() throws BusinessException {
		super();

		setPreferredSize(new Dimension(300, 400));

		setLayout(new BorderLayout(0, 0));
		add(getPanelNorte(), BorderLayout.NORTH);
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	// =====================================
	// Panel norte
	// =====================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();

			panelNorte.setBorder(new EmptyBorder(8, 3, 10, 3));

			GridBagLayout gbl_panelNorte = new GridBagLayout();

			gbl_panelNorte.columnWidths = new int[] { 202, 160, 0 };
			gbl_panelNorte.rowHeights = new int[] { 40, 0 };
			gbl_panelNorte.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelNorte.rowWeights = new double[] { 0.0, Double.MIN_VALUE };

			panelNorte.setLayout(gbl_panelNorte);

			GridBagConstraints gbc_labelOrdenTrabajo = new GridBagConstraints();

			gbc_labelOrdenTrabajo.fill = GridBagConstraints.BOTH;
			gbc_labelOrdenTrabajo.insets = new Insets(0, 0, 0, 5);
			gbc_labelOrdenTrabajo.gridx = 0;
			gbc_labelOrdenTrabajo.gridy = 0;

			panelNorte.add(getLabelOrdenTrabajo(), gbc_labelOrdenTrabajo);

			GridBagConstraints gbc_textFieldOrdenTrabajo = new GridBagConstraints();

			gbc_textFieldOrdenTrabajo.fill = GridBagConstraints.BOTH;
			gbc_textFieldOrdenTrabajo.gridx = 1;
			gbc_textFieldOrdenTrabajo.gridy = 0;

			panelNorte.add(getTextFieldOrdenTrabajo(), gbc_textFieldOrdenTrabajo);
		}

		return panelNorte;
	}

	private JLabel getLabelOrdenTrabajo() {
		if (labelOrdenTrabajo == null) {
			labelOrdenTrabajo = new JLabel("Cod. Orden de Trabajo:");

			labelOrdenTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
			labelOrdenTrabajo.setFont(new Font("Tahoma", Font.BOLD, 11));
		}

		return labelOrdenTrabajo;
	}

	private JTextField getTextFieldOrdenTrabajo() {
		if (textFieldOrdenTrabajo == null) {
			textFieldOrdenTrabajo = new JTextField();
			textFieldOrdenTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 12));

			textFieldOrdenTrabajo.setEditable(false);
			textFieldOrdenTrabajo.setColumns(10);
		}

		return textFieldOrdenTrabajo;
	}

	// =====================================
	// Panel centro
	// =====================================

	private JScrollPane getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JScrollPane(getTablaProductosOrdenTrabajo());

			panelCentro.setBorder(new EmptyBorder(0, 2, 0, 2));
			panelCentro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panelCentro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return panelCentro;
	}

	private JTable getTablaProductosOrdenTrabajo() {
		if (tablaProductosOrdenTrabajo == null) {
			modeloTablaProductosEmpaquetar = new ModeloTablaProductosEmpaquetar();
			tablaProductosOrdenTrabajo = new JTable(modeloTablaProductosEmpaquetar);

			tablaProductosOrdenTrabajo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			tablaProductosOrdenTrabajo.setFocusable(false);
			tablaProductosOrdenTrabajo.setRowSelectionAllowed(false);
			tablaProductosOrdenTrabajo.setCellSelectionEnabled(false);
		}

		return tablaProductosOrdenTrabajo;
	}

	// =====================================
	// Panel Sur
	// =====================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			panelSur.setLayout(new BorderLayout(0, 0));
			panelSur.add(getPanelPaqueteInfo(), BorderLayout.CENTER);
			panelSur.add(getPanelOpciones(), BorderLayout.SOUTH);
		}

		return panelSur;
	}

	// --------------------------
	// ---- PanelPaqueteInfo ----
	// --------------------------

	private JPanel getPanelPaqueteInfo() {
		if (panelPaqueteInfo == null) {
			panelPaqueteInfo = new JPanel();

			panelPaqueteInfo.setBorder(new EmptyBorder(10, 7, 10, 7));

			GridBagLayout gbl_panelPaqueteInfo = new GridBagLayout();

			gbl_panelPaqueteInfo.columnWidths = new int[] { 100, 140, 0 };
			gbl_panelPaqueteInfo.rowHeights = new int[] { 40, 5, 40, 0 };
			gbl_panelPaqueteInfo.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelPaqueteInfo.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };

			panelPaqueteInfo.setLayout(gbl_panelPaqueteInfo);

			GridBagConstraints gbc_labelPedido = new GridBagConstraints();

			gbc_labelPedido.insets = new Insets(0, 0, 5, 5);
			gbc_labelPedido.fill = GridBagConstraints.BOTH;
			gbc_labelPedido.gridx = 0;
			gbc_labelPedido.gridy = 0;

			panelPaqueteInfo.add(getLabelPedido(), gbc_labelPedido);

			GridBagConstraints gbc_textFieldPedido = new GridBagConstraints();

			gbc_textFieldPedido.insets = new Insets(0, 0, 5, 0);
			gbc_textFieldPedido.fill = GridBagConstraints.BOTH;
			gbc_textFieldPedido.gridx = 1;
			gbc_textFieldPedido.gridy = 0;

			panelPaqueteInfo.add(getTextFieldPedido(), gbc_textFieldPedido);

			GridBagConstraints gbc_labelPaquete = new GridBagConstraints();

			gbc_labelPaquete.insets = new Insets(0, 0, 0, 5);
			gbc_labelPaquete.fill = GridBagConstraints.BOTH;
			gbc_labelPaquete.gridx = 0;
			gbc_labelPaquete.gridy = 2;

			panelPaqueteInfo.add(getLabelPaquete(), gbc_labelPaquete);

			GridBagConstraints gbc_textFieldPaquete = new GridBagConstraints();

			gbc_textFieldPaquete.fill = GridBagConstraints.BOTH;
			gbc_textFieldPaquete.gridx = 1;
			gbc_textFieldPaquete.gridy = 2;

			panelPaqueteInfo.add(getTextFieldPaquete(), gbc_textFieldPaquete);
		}

		return panelPaqueteInfo;
	}

	private JLabel getLabelPedido() {
		if (labelPedido == null) {
			labelPedido = new JLabel("Pedido empaquetar:");

			labelPedido.setBorder(null);
			labelPedido.setFont(new Font("Tahoma", Font.BOLD, 12));
			labelPedido.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelPedido;
	}

	private JTextField getTextFieldPedido() {
		if (textFieldPedido == null) {
			textFieldPedido = new JTextField("");
			textFieldPedido.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldPedido.setEditable(false);

			textFieldPedido.setHorizontalAlignment(SwingConstants.LEFT);
			textFieldPedido.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			textFieldPedido.setBorder(null);
			textFieldPedido.setColumns(10);
		}

		return textFieldPedido;
	}

	private JLabel getLabelPaquete() {
		if (labelPaquete == null) {
			labelPaquete = new JLabel("Cod. paquete:");

			labelPaquete.setBorder(null);
			labelPaquete.setFont(new Font("Tahoma", Font.BOLD, 12));
			labelPaquete.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelPaquete;
	}

	private JTextField getTextFieldPaquete() {
		if (textFieldPaquete == null) {
			textFieldPaquete = new JTextField("");
			textFieldPaquete.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldPaquete.setEditable(false);

			textFieldPaquete.setHorizontalAlignment(SwingConstants.LEFT);
			textFieldPaquete.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			textFieldPaquete.setBorder(null);
			textFieldPaquete.setColumns(10);
		}

		return textFieldPaquete;
	}

	// -----------------------
	// ---- PanelOpciones ----
	// -----------------------

	private JPanel getPanelOpciones() {
		if (panelOpciones == null) {
			panelOpciones = new JPanel();

			FlowLayout fl_panelOpciones = (FlowLayout) panelOpciones.getLayout();
			fl_panelOpciones.setHgap(3);

			fl_panelOpciones.setAlignment(FlowLayout.RIGHT);
			fl_panelOpciones.setVgap(8);

			panelOpciones.add(getSpinnerUnidades());
			panelOpciones.add(getBotonAtras());
			panelOpciones.add(getBotonAbrirCerrarPaquete());
		}

		return panelOpciones;
	}

	private JSpinner getSpinnerUnidades() {
		if (spinnerUnidades == null) {
			spinnerUnidades = new JSpinner();

			spinnerUnidades.setFont(new Font("Tahoma", Font.PLAIN, 12));
			spinnerUnidades.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		}

		return spinnerUnidades;
	}

	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atr\u00E1s");

			botonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					reiniciarPanel();

					ventanaPrincipal.volverPanelOpciones();
				}
			});

			botonAtras.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}

		return botonAtras;
	}

	private JButton getBotonAbrirCerrarPaquete() {
		if (botonAbrirCerrarPaquete == null) {
			botonAbrirCerrarPaquete = new JButton("Abrir paquete");

			botonAbrirCerrarPaquete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					// No hay ningún paquete abierto
					if (paqueteActual == null) {
						try {
							paqueteActual = ServiceFactory.getEmpaquetadoService()
									.abrirPaquete(ventanaPrincipal.getOrdenTrabajo());

							botonAbrirCerrarPaquete.setText("Cerrar paquete");
							textFieldPaquete.setText(paqueteActual.getNumCaja() + "");
							escaner.reiniciarEtiquetas();
						}

						catch (BusinessException e) {
							ventanaPrincipal.gestionarErrorConexion(e);
						}
					}

					// Hay un paquete abierto
					else {

						try {
							if (ServiceFactory.getEmpaquetadoService().sePuedeCerrarPaquete(paqueteActual)) {
								ServiceFactory.getEmpaquetadoService().cerrarPaquete(paqueteActual);
								botonAbrirCerrarPaquete.setText("Abrir paquete");

								textFieldPaquete.setText("");

								escaner.generarEtiquetas(paqueteActual);

								paqueteActual = null;
							}

							else {
								JOptionPane.showMessageDialog(ventanaPrincipal, "No se puede cerrar un paquete vacío",
										"Aviso", JOptionPane.WARNING_MESSAGE);
							}
						}

						catch (BusinessException e) {
							ventanaPrincipal.gestionarErrorConexion(e);
							return;
						}

						// Si al cerrar el paquete se habia recogido todo
						if (modeloTablaProductosEmpaquetar.getRowCount() == 0) {
							try {
								List<ProductoEnOrdenTrabajo> productos = ServiceFactory.getEmpaquetadoService()
										.cargarProductosOT(ventanaPrincipal.getOrdenTrabajo(), null);

								// Si la orden de trabajo está terminada
								if (productos.isEmpty()) {
									JOptionPane.showMessageDialog(ventanaPrincipal, "Se ha terminado empaquetar la OT",
											"Info", JOptionPane.INFORMATION_MESSAGE);

									botonAbrirCerrarPaquete.setEnabled(false);

									ServiceFactory.getEmpaquetadoService()
											.terminarOrdenTrabajo(ventanaPrincipal.getOrdenTrabajo());
								}

								// Si todavía hay que empaquetar más productos
								else {
									modeloTablaProductosEmpaquetar.setProductos(productos);
									escaner.llenarLista(productos);

									botonAtras.setEnabled(true);
									botonAbrirCerrarPaquete.setEnabled(true);
									spinnerUnidades.setEnabled(true);

									textFieldPedido.setText("");

									textFieldPaquete.setText("");
								}

								botonAtras.setEnabled(true);
							}

							catch (BusinessException e) {
								ventanaPrincipal.gestionarErrorConexion(e);
							}
						}

					} // primer else
				}
			});

			botonAbrirCerrarPaquete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}

		return botonAbrirCerrarPaquete;
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private void reiniciarPanel() {
		modeloTablaProductosEmpaquetar.removeAll();
		paqueteActual = null;

		escaner.dispose();
		escaner = null;

		spinnerUnidades.setValue(1);

		botonAtras.setEnabled(true);
		spinnerUnidades.setEnabled(true);

		botonAbrirCerrarPaquete.setEnabled(true);

		botonAbrirCerrarPaquete.setText("");

		getTextFieldOrdenTrabajo().setText("");
		getTextFieldPedido().setText("");
		getTextFieldPaquete().setText("");
	}

	public void empaquetar(String ref) {
		ProductoEnOrdenTrabajo prod = modeloTablaProductosEmpaquetar.getProducto(ref);

		if (prod == null) {
			JOptionPane.showMessageDialog(ventanaPrincipal, "Ese producto no está en la OT", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}

		else if (paqueteActual == null) {
			JOptionPane.showMessageDialog(ventanaPrincipal, "No hay ningún paquete abierto", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		}

		// Si hay un paquete abierto y el prod es valido
		else {
			int unidadesEmpaquetar = (int) spinnerUnidades.getModel().getValue();
			int unidadesFaltan = prod.getUnidadesProducto() - prod.getUnidadesEmpaquetadas();

			if (unidadesEmpaquetar > unidadesFaltan) {
				JOptionPane.showMessageDialog(ventanaPrincipal,
						"No se pueden empaquetar más unidades de las que requiere la OT", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			}

			// Si no empaqueta mas de lo que debe
			else {

				try {
					ServiceFactory.getEmpaquetadoService().asignarProductoPaquete(prod, paqueteActual,
							unidadesEmpaquetar);

					// Si es el primer producto empaquetado
					if (getTextFieldPedido().getText().equals("")) {
						getTextFieldPedido().setText(prod.getproductoPedido().getPedido().getId() + "");

						// LimpiarModelo (dejar los productos del mismo pedido)
						limpiarModelo(prod.getproductoPedido().getPedido());
					}

					// Si es el primer producto que se mete en el paquete
					if (paqueteActual.getPedido() == null) {
						paqueteActual.setPedido(prod.getproductoPedido().getPedido());
					}

					spinnerUnidades.setValue(1);

					if (unidadesFaltan - unidadesEmpaquetar == 0) {
						modeloTablaProductosEmpaquetar.removeProducto(ref);
						escaner.removeProducto(ref);

						comprobarSiQuedanMasProductos();
					}

					else {
						prod.empaquetar(unidadesEmpaquetar);

						// Hay que actualizar la tabla
						modeloTablaProductosEmpaquetar.fireTableDataChanged();
					}
				}

				catch (BusinessException e) {
					ventanaPrincipal.gestionarErrorConexion(e);
				}
			}
		} // Fin primer else
	}

	/**
	 * Elimina del modelo todos los productos que no pertenezcan al pedido.
	 * 
	 * @param pedido
	 *            pedido que se usará para "filtrar"
	 * 
	 */
	private void limpiarModelo(Pedido pedido) {
		modeloTablaProductosEmpaquetar.removeProductosPedido(pedido);
		escaner.removeProductosPedido(pedido);
	}

	/**
	 * Cuando se termina de empaquetar todos los productos de la tabla, el
	 * almacenero no podrá hacer nada hasta que no cierre el paquete actual.
	 * 
	 */
	private void comprobarSiQuedanMasProductos() {
		if (modeloTablaProductosEmpaquetar.getRowCount() == 0) {
			botonAtras.setEnabled(false);
			spinnerUnidades.setEnabled(false);

			textFieldPedido.setText("");

			JOptionPane.showMessageDialog(ventanaPrincipal, "Cierre el paquete actual para continuar", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Carga la lista de productos que hay que empaquetar.
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void inicializarDatos() throws BusinessException {
		Paquete paquete = ServiceFactory.getEmpaquetadoService().cargarPaquete(ventanaPrincipal.getOrdenTrabajo());

		List<ProductoEnOrdenTrabajo> productos;

		// Si no hay ningun paquete abierto asociado a la OT
		if (paquete == null) {
			productos = ServiceFactory.getEmpaquetadoService().cargarProductosOT(ventanaPrincipal.getOrdenTrabajo(),
					null);
		}

		// Si hay un paquete abierto pero no tiene productos
		else if (paquete.getPedido() == null) {
			productos = ServiceFactory.getEmpaquetadoService().cargarProductosOT(ventanaPrincipal.getOrdenTrabajo(),
					null);
		}

		// Si hay un paquete abierto que está asociado a un pedido
		else {
			productos = ServiceFactory.getEmpaquetadoService().cargarProductosOT(ventanaPrincipal.getOrdenTrabajo(),
					paquete.getPedido());
		}

		this.paqueteActual = paquete;
		modeloTablaProductosEmpaquetar.setProductos(productos);

		cargarEscaner(productos);

		// ----------------------------------------------
		// -> Cambiar texto boton Abrir / Cerrar paquete
		//
		// -> Id de pedido
		// -> Num de caja
		// -> Id de OT
		// ----------------------------------------------

		textFieldOrdenTrabajo.setText(ventanaPrincipal.getOrdenTrabajo().getId() + "");

		if (paqueteActual == null) {
			botonAbrirCerrarPaquete.setText("Abrir paquete");
		}

		else {
			botonAbrirCerrarPaquete.setText("Cerrar paquete");

			textFieldPaquete.setText(paquete.getNumCaja() + "");

			if (paquete.getPedido() != null) {
				textFieldPedido.setText(paquete.getPedido().getId() + "");
			}
		}
	}

	private void cargarEscaner(List<ProductoEnOrdenTrabajo> lista) {
		escaner = new EscanerProductosEmpaquetar();

		escaner.setLocationRelativeTo(ventanaPrincipal);
		escaner.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		escaner.setPanelEmpaquetadoProductos(this);
		escaner.llenarLista(lista);
		escaner.setVisible(true);
	}

}