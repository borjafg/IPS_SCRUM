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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
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
import ui.almacen.myTypes.escaneres.EscanerProductosEmpaquetar;
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
	private JButton botonCerrarPaquete;

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

			tablaProductosOrdenTrabajo.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 13));
			tablaProductosOrdenTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 13));

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
			labelPaquete = new JLabel("Nº caja:");

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
			panelOpciones.add(getBotonCerrarPaquete());
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

	private JButton getBotonCerrarPaquete() {
		if (botonCerrarPaquete == null) {
			botonCerrarPaquete = new JButton("Cerrar paquete");
			botonCerrarPaquete.setHorizontalTextPosition(SwingConstants.CENTER);

			botonCerrarPaquete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					cerrarPaquete();
				}
			});

			botonCerrarPaquete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}

		return botonCerrarPaquete;
	}

	// ==============================================
	// Gestión de paquetes
	// ==============================================

	/**
	 * Cierra el paquete que el almacenero tiene abierto
	 * 
	 */
	private void cerrarPaquete() {
		try {
			// ---------------------------------------
			// Si el paquete tiene productos dentro
			// ---------------------------------------

			if (ServiceFactory.getEmpaquetadoService().sePuedeCerrarPaquete(paqueteActual)) {

				Paquete paq = ServiceFactory.getEmpaquetadoService().cerrarPaquete(paqueteActual,
						ventanaPrincipal.getOrdenTrabajo());

				escaner.generarEtiquetas(paqueteActual);

				// -----------------------------------------------------
				// Si al cerrar el paquete se habia empaquetado todo
				// -----------------------------------------------------

				if (modeloTablaProductosEmpaquetar.getRowCount() == 0) {

					// ----------------------------------------
					// Si la orden de trabajo está terminada
					// ----------------------------------------

					if (paq == null) {
						terminarOrdenTrabajo();
						textFieldPaquete.setText("");
					}

					// ----------------------------------------------
					// Si todavía hay que empaquetar más productos
					// ----------------------------------------------

					else {
						List<ProductoEnOrdenTrabajo> productos = ServiceFactory.getEmpaquetadoService()
								.cargarProductosOT(ventanaPrincipal.getOrdenTrabajo(), null);

						paqueteActual = paq;
						textFieldPaquete.setText(paqueteActual.getNumCaja() + "");

						cargarProductosEmpaquetar(productos);
					}

					// En cualquier caso se termino de empaquetar el pedido
					// dentro de la orden de trabajo

					textFieldPedido.setText("");
				}

				// ------------------------------------
				// Si no se había empaquetado todo
				// ------------------------------------

				else {
					Pedido pedido = modeloTablaProductosEmpaquetar.getProducto(0).getproductoPedido().getPedido();

					paq.setPedido(pedido);

					paqueteActual = paq;

					textFieldPaquete.setText(paqueteActual.getNumCaja() + "");
					textFieldPedido.setText(pedido.getId() + "");
				}
			}

			// ------------------------------------------
			// Si el paquete no tiene productos dentro
			// ------------------------------------------
			else {
				ventanaPrincipal.getMessage().warning("Aviso", "No se puede cerrar un paquete vacío");
			}
		}

		catch (BusinessException e) {
			ventanaPrincipal.gestionarErrorConexion(e);
		}
	}

	/**
	 * Se ejecuta cuando se termina de empaquetar una orden de trabajo.
	 * 
	 * @throws BusinessException
	 *             ha ocurrido un error
	 * 
	 */
	public void terminarOrdenTrabajo() throws BusinessException {
		ServiceFactory.getEmpaquetadoService().terminarOrdenTrabajo(ventanaPrincipal.getOrdenTrabajo());

		ventanaPrincipal.getMessage().info("info", "Se ha terminado empaquetar la OT");

		// Ya no queda nada que empaquetar
		botonCerrarPaquete.setEnabled(false);
	}

	/**
	 * Carga en la tabla y en el escáner una lista de productos que hay que
	 * empaquetar.
	 * 
	 * @param productos
	 *            lista de productos que hay que cargar
	 * 
	 */
	public void cargarProductosEmpaquetar(List<ProductoEnOrdenTrabajo> productos) {
		modeloTablaProductosEmpaquetar.setProductos(productos);
		escaner.llenarLista(productos);
	}

	// ==============================================
	// Empaquetado de productos
	// ==============================================

	/**
	 * Asocia a un paquete tantas unidades de producto como indique el
	 * spinnerUnidades.
	 * 
	 * @param ref
	 *            referencia al producto dentro de la OT (se obtiene de un
	 *            escáner)
	 * 
	 */
	public void empaquetar(String ref) {
		ProductoEnOrdenTrabajo prod = modeloTablaProductosEmpaquetar.getProducto(ref);

		if (prod == null) {
			ventanaPrincipal.getMessage().warning("Aviso", "Ese producto no está en la OT");
		}

		else if (paqueteActual == null) {
			ventanaPrincipal.getMessage().warning("Aviso", "No hay ningún paquete abierto");
		}

		// --------------------------------------------------
		// Si hay un paquete abierto y el prod es valido
		// --------------------------------------------------

		else {
			int unidadesEmpaquetar = (int) spinnerUnidades.getModel().getValue();
			int unidadesFaltan = prod.getUnidadesProducto() - prod.getUnidadesEmpaquetadas();

			if (unidadesEmpaquetar > unidadesFaltan) {
				ventanaPrincipal.getMessage().warning("Aviso",
						"No se pueden empaquetar más unidades de las que requiere la OT");
			}

			// --------------------------------------------------
			// Si no empaqueta mas de lo que debe
			// --------------------------------------------------

			else {

				try {
					ServiceFactory.getEmpaquetadoService().asignarProductoPaquete(prod, paqueteActual,
							unidadesEmpaquetar);

					// --------------------------------------------------
					// Si es el primer producto empaquetado
					// --------------------------------------------------

					if (getTextFieldPedido().getText().equals("")) {
						escaner.reiniciarEtiquetas();

						getTextFieldPedido().setText(prod.getproductoPedido().getPedido().getId() + "");

						// Limpiar la tabla y el escáner (dejar los productos
						// del mismo pedido)
						limpiarModelo(prod.getproductoPedido().getPedido());
					}

					// -----------------------------------------------------
					// Si es el primer producto que se mete en el paquete
					// -----------------------------------------------------

					if (paqueteActual.getPedido() == null) {
						paqueteActual.setPedido(prod.getproductoPedido().getPedido());
					}

					spinnerUnidades.setValue(1);

					if (unidadesFaltan - unidadesEmpaquetar == 0) {
						modeloTablaProductosEmpaquetar.removeProducto(ref);
						escaner.removeProducto(ref);
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

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	public VentanaPrincipalAlmacenero getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private void reiniciarPanel() {
		modeloTablaProductosEmpaquetar.removeAll();
		paqueteActual = null;

		escaner.dispose();
		escaner = null;

		spinnerUnidades.setValue(1);

		botonCerrarPaquete.setEnabled(true);

		getTextFieldOrdenTrabajo().setText("");
		getTextFieldPedido().setText("");
		getTextFieldPaquete().setText("");
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

		// ----------------------------------------------------
		// Si hay un paquete abierto pero no tiene productos
		// ----------------------------------------------------

		if (paquete.getPedido() == null) {
			productos = ServiceFactory.getEmpaquetadoService().cargarProductosOT(ventanaPrincipal.getOrdenTrabajo(),
					null);
		}

		// ----------------------------------------------------------
		// Si hay un paquete abierto que está asociado a un pedido
		// ----------------------------------------------------------

		else {
			productos = ServiceFactory.getEmpaquetadoService().cargarProductosOT(ventanaPrincipal.getOrdenTrabajo(),
					paquete.getPedido());
		}

		this.paqueteActual = paquete;
		modeloTablaProductosEmpaquetar.setProductos(new ArrayList<ProductoEnOrdenTrabajo>(productos));

		cargarEscaner(new ArrayList<ProductoEnOrdenTrabajo>(productos));

		// ----------------------------------------------
		// -> Cambiar texto boton Abrir / Cerrar paquete
		//
		// -> Id de pedido
		// -> Num de caja
		// -> Id de OT
		// ----------------------------------------------

		textFieldOrdenTrabajo.setText(ventanaPrincipal.getOrdenTrabajo().getId() + "");

		textFieldPaquete.setText(paqueteActual.getNumCaja() + "");

		if (paqueteActual.getPedido() != null) {
			textFieldPedido.setText(paqueteActual.getPedido().getId() + "");
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