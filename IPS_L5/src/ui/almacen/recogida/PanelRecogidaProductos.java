package ui.almacen.recogida;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
import model.ProductoEnOrdenTrabajo;
import ui.almacen.VentanaPrincipalAlmacenero;
import ui.almacen.escaneres.EscanerProductosRecoger;
import ui.almacen.myTypes.model.MyProducto_OrdenadoPosicion;
import ui.almacen.myTypes.tablas.TablaFilasConVariasLineasRenderer;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaProductosRecoger;

public class PanelRecogidaProductos extends JPanel {

	private static final long serialVersionUID = -2481078673400949799L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;
	private EscanerProductosRecoger escaner;

	// ===========================================
	// Componentes de este panel
	// ===========================================

	// ==== Panel norte ====

	private JPanel panelNorte;
	private JLabel labelCodOrdenTrabajo;
	private JTextField textFieldCodOrdenTrabajo;

	// ==== Panel centro ====

	private JScrollPane scrollPaneCentro;
	private JTable tablaProductos;
	private ModeloTablaProductosRecoger modeloTablaProductos;

	// ==== Panel sur ====

	private JPanel panelSur;
	private JLabel labelIncidencias;
	private JSpinner spinnerUnidades;
	private JButton botonAtras;
	private JButton botonIncidencias;

	/**
	 * Devuelve un panel que permite empaquetar productos de una determinada
	 * orden de trabajo
	 * 
	 * @param ventanaPrincipal
	 *            referencia al JFrame que contiene el panel
	 * 
	 */
	public PanelRecogidaProductos() {
		super();

		setPreferredSize(new Dimension(300, 400));
		setLayout(new BorderLayout(0, 0));

		add(getPanelNorte(), BorderLayout.NORTH);
		add(getScrollPaneCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	// =====================================
	// Panel norte
	// =====================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBorder(new EmptyBorder(2, 0, 2, 0));

			panelNorte.add(getLabelCodOrdenTrabajo());
			panelNorte.add(getTextFieldCodOrdenTrabajo());
		}

		return panelNorte;
	}

	private JLabel getLabelCodOrdenTrabajo() {
		if (labelCodOrdenTrabajo == null) {
			labelCodOrdenTrabajo = new JLabel("C\u00F3digo de la OT: ");

			labelCodOrdenTrabajo.setHorizontalTextPosition(SwingConstants.CENTER);
			labelCodOrdenTrabajo.setAlignmentX(Component.CENTER_ALIGNMENT);
			labelCodOrdenTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
			labelCodOrdenTrabajo.setFont(new Font("Tahoma", Font.BOLD, 12));
		}

		return labelCodOrdenTrabajo;
	}

	private JTextField getTextFieldCodOrdenTrabajo() {
		if (textFieldCodOrdenTrabajo == null) {
			textFieldCodOrdenTrabajo = new JTextField();

			textFieldCodOrdenTrabajo.setEditable(false);
			textFieldCodOrdenTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldCodOrdenTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
			textFieldCodOrdenTrabajo.setColumns(10);
		}

		return textFieldCodOrdenTrabajo;
	}

	// =====================================
	// Panel centro
	// =====================================

	private JScrollPane getScrollPaneCentro() {
		if (scrollPaneCentro == null) {
			scrollPaneCentro = new JScrollPane(getTablaProductos());
			scrollPaneCentro.setBorder(new EmptyBorder(0, 3, 0, 3));

			scrollPaneCentro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneCentro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPaneCentro;
	}

	private JTable getTablaProductos() {
		if (tablaProductos == null) {
			modeloTablaProductos = new ModeloTablaProductosRecoger();
			tablaProductos = new JTable(modeloTablaProductos);

			tablaProductos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			tablaProductos.setDefaultRenderer(String.class, new TablaFilasConVariasLineasRenderer());
		}

		return tablaProductos;
	}

	// =====================================
	// Panel sur
	// =====================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			panelSur.setBorder(new EmptyBorder(0, 8, 0, 7));

			GridBagLayout gbl_panelSur = new GridBagLayout();

			gbl_panelSur.columnWidths = new int[] { 40, 40, 20, 0, 0, 10, 10, 0 };
			gbl_panelSur.rowHeights = new int[] { 12, 34, 10, 34, 9, 0 };
			gbl_panelSur.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelSur.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };

			panelSur.setLayout(gbl_panelSur);

			GridBagConstraints gbc_labelIncidencias = new GridBagConstraints();

			gbc_labelIncidencias.fill = GridBagConstraints.BOTH;
			gbc_labelIncidencias.gridwidth = 4;
			gbc_labelIncidencias.insets = new Insets(0, 0, 5, 5);
			gbc_labelIncidencias.gridx = 0;
			gbc_labelIncidencias.gridy = 1;

			panelSur.add(getLabelIncidencias(), gbc_labelIncidencias);

			GridBagConstraints gbc_spinnerUnidades = new GridBagConstraints();

			gbc_spinnerUnidades.gridwidth = 3;
			gbc_spinnerUnidades.fill = GridBagConstraints.BOTH;
			gbc_spinnerUnidades.insets = new Insets(0, 0, 5, 5);
			gbc_spinnerUnidades.gridx = 4;
			gbc_spinnerUnidades.gridy = 1;

			panelSur.add(getSpinnerUnidades(), gbc_spinnerUnidades);

			GridBagConstraints gbc_botonAtras = new GridBagConstraints();

			gbc_botonAtras.gridwidth = 2;
			gbc_botonAtras.fill = GridBagConstraints.BOTH;
			gbc_botonAtras.insets = new Insets(0, 0, 5, 5);
			gbc_botonAtras.gridx = 0;
			gbc_botonAtras.gridy = 3;

			panelSur.add(getBotonAtras(), gbc_botonAtras);

			GridBagConstraints gbc_botonIncidencias = new GridBagConstraints();

			gbc_botonIncidencias.fill = GridBagConstraints.BOTH;
			gbc_botonIncidencias.gridwidth = 4;
			gbc_botonIncidencias.insets = new Insets(0, 0, 5, 5);
			gbc_botonIncidencias.gridx = 3;
			gbc_botonIncidencias.gridy = 3;

			panelSur.add(getBotonIncidencias(), gbc_botonIncidencias);
		}

		return panelSur;
	}

	private JLabel getLabelIncidencias() {
		if (labelIncidencias == null) {
			labelIncidencias = new JLabel("");

			labelIncidencias.setForeground(new Color(165, 42, 42));
			labelIncidencias.setFont(new Font("Tahoma", Font.PLAIN, 18));
			labelIncidencias.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelIncidencias;
	}

	private JSpinner getSpinnerUnidades() {
		if (spinnerUnidades == null) {
			spinnerUnidades = new JSpinner();

			spinnerUnidades.setModel(new SpinnerNumberModel(1, 0, 100, 1));
			spinnerUnidades.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}

		return spinnerUnidades;

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

			botonAtras.setFont(new Font("Tahoma", Font.BOLD, 12));
		}

		return botonAtras;
	}

	private JButton getBotonIncidencias() {
		if (botonIncidencias == null) {
			botonIncidencias = new JButton("Registrar incidencia");

			botonIncidencias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ventanaPrincipal.mostrarPanelIncidencias();
				}
			});

			botonIncidencias.setFont(new Font("Tahoma", Font.BOLD, 12));
		}

		return botonIncidencias;
	}

	// =======================================
	// Recogida de productos
	// ========================================

	public void recoger(long id) {
		ProductoEnOrdenTrabajo prod = modeloTablaProductos.getProducto(id);

		if (prod == null) {
			ventanaPrincipal.getMessage().warning("Aviso", "Ese producto no esta en la OT");
		}

		else {
			int unidadesRecoger = (int) spinnerUnidades.getModel().getValue();
			int unidadesFaltan = prod.getUnidadesProducto() - prod.getUnidadesRecogidas();

			if (unidadesRecoger > unidadesFaltan) {
				ventanaPrincipal.getMessage().warning("Info",
						"No se pueden recoger más unidades de las que requiere la OT");
			}

			else {

				try {
					ServiceFactory.getRecogidaService().recogerUnidadesProducto(prod, unidadesRecoger);
					spinnerUnidades.setValue(1);

					if (unidadesFaltan - unidadesRecoger == 0) {
						modeloTablaProductos.removeProducto(id);
						escaner.removeProducto(id);

						if (modeloTablaProductos.getRowCount() == 0) {

							boolean incidencias = ServiceFactory.getRecogidaService()
									.huboIncidencias(ventanaPrincipal.getOrdenTrabajo());

							// --------------------
							// Sin incidencias
							// --------------------

							if (!incidencias) {
								int resul = JOptionPane.showConfirmDialog(ventanaPrincipal,
										"¿Pasar a empaquetar la OT?", "Confirmación", JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE);

								// -----------------------------------------
								// SÍ ==> La recogerá este almacenero
								// -----------------------------------------

								if (resul == JOptionPane.YES_OPTION) {
									ServiceFactory.getRecogidaService().marcarOT_empaquetado(
											ventanaPrincipal.getOrdenTrabajo(), ventanaPrincipal.getAlmacenero());

									ventanaPrincipal.mostrarPanelEmpaquetadoProductos();
								}

								// -----------------------------------------
								// NO ==> La recogera este almacenero
								// -----------------------------------------

								else {
									ServiceFactory.getRecogidaService()
											.marcarOT_empaquetado(ventanaPrincipal.getOrdenTrabajo(), null);
								}
							}

							// --------------------------
							// Hubo alguna incidencia
							// --------------------------

							else {
								ventanaPrincipal.getMessage().info("Info",
										"Se ha terminado de recoger los productos de la OT (Hubo incidencias)");
							}

							botonIncidencias.setEnabled(false);
						}
					}

					else {
						prod.recoger(unidadesRecoger);
						modeloTablaProductos.fireTableDataChanged();
					}
				}

				catch (BusinessException e) {
					ventanaPrincipal.gestionarErrorConexion(e);
				}
			}
		} // Fin primer else
	}

	// =======================================
	// Control del estado del panel
	// ========================================

	private void indicarQueHuboIncidencias() {
		getLabelIncidencias().setText("Ha habido incidencias");
	}

	private void reiniciarPanel() {
		getLabelIncidencias().setText("");
		getTextFieldCodOrdenTrabajo().setText("");

		escaner.dispose();
		escaner = null;

		botonIncidencias.setEnabled(true);

		spinnerUnidades.setValue(1);

		modeloTablaProductos.removeAll();
	}

	public void comprobarSihuboIncidencias() throws BusinessException {
		if (ServiceFactory.getRecogidaService().huboIncidencias(ventanaPrincipal.getOrdenTrabajo())) {
			indicarQueHuboIncidencias();
		}
	}

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	/**
	 * Carga la lista de productos de la orden de trabajo correspondiente y los
	 * ordena según su posición en el almacen
	 * 
	 * @throws BusinessException
	 * 
	 */
	public void inicializarDatos() throws BusinessException {
		// ----------------------------------
		// (1) --> Sacar productos en la OT
		// ----------------------------------

		Set<ProductoEnOrdenTrabajo> productos = ServiceFactory.getRecogidaService()
				.obtenerProductosOT(ventanaPrincipal.getOrdenTrabajo());

		// ----------------------------------
		// (2) --> Ordenar por posicion
		// ----------------------------------

		List<MyProducto_OrdenadoPosicion> prods = new ArrayList<MyProducto_OrdenadoPosicion>();

		for (ProductoEnOrdenTrabajo prod : productos) {
			prods.add(new MyProducto_OrdenadoPosicion(prod));
		}

		Collections.sort(prods);

		// ----------------------------------
		// (3) --> Añadir al modelo de la tabla
		// ----------------------------------

		modeloTablaProductos.setProductos(prods);

		cargarEscaner(prods);
	}

	private void cargarEscaner(List<MyProducto_OrdenadoPosicion> lista) {
		escaner = new EscanerProductosRecoger();

		escaner.setLocationRelativeTo(this);
		escaner.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		escaner.setPanelRecogidaProductos(this);
		escaner.llenarLista(lista);
		escaner.setVisible(true);
	}

}