package ui.almacen.recogida;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import ui.almacen.VentanaPrincipalAlmacenero;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaOrdenesTrabajo;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaPedidosOrdenTrabajo;

/**
 * Evita que se duplique la mayor parte del código de las pantallas en las que
 * hay que retomar una orden de trabajo.</br>
 * </br>
 * El único problema es que hay incluir una llamada al método
 * <code>setPreferredSize(new Dimension(x, y)); </code>con el tamaño que debería
 * tener el panel de la subclase. En este caso el tamaño es 374x530</br>
 * </br>
 * Lo único que hay que redefinir es:<br/>
 * - El botón que determina qué se hace con la orden de trabajo</br>
 * - El método que inicializa el panel
 * 
 */
public abstract class AbstractPanelRetomarOrdenTrabajo extends JPanel {

	private static final long serialVersionUID = 22278103584098508L;

	protected VentanaPrincipalAlmacenero ventanaPrincipal;

	protected ModeloTablaOrdenesTrabajo modeloTablaOrdenesTrabajo;
	protected ModeloTablaPedidosOrdenTrabajo modeloTablaPedidosOrdenTrabajo;

	// ===========================================
	// Componentes de este panel
	// ===========================================

	// ==== Panel centro ====

	private JPanel panelCentro;

	private JLabel labelPedidos;
	private JScrollPane scrollPanePedidos;
	private JTable tablaPedidos;

	private JLabel labelOrdenesTrabajo;
	private JScrollPane scrollPaneOrdenesTrabajo;
	private JTable tablaOrdenesTrabajo;

	// ==== Panel sur ====

	private JPanel panelSur;
	private JButton botonAtras;

	public AbstractPanelRetomarOrdenTrabajo() {
		super();

		setLayout(new BorderLayout(0, 0));

		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);

		setPreferredSize(new Dimension(374, 530));
	}

	// =====================================
	// Panel centro
	// =====================================

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();

			panelCentro.setBorder(new EmptyBorder(0, 3, 0, 3));

			GridBagLayout gbl_panelCentro = new GridBagLayout();

			gbl_panelCentro.columnWidths = new int[] { 65, 240, 65, 0 };
			gbl_panelCentro.rowHeights = new int[] { 25, 145, 25, 180, 0 };
			gbl_panelCentro.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelCentro.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };

			panelCentro.setLayout(gbl_panelCentro);

			GridBagConstraints gbc_labelOrdenesTrabajo = new GridBagConstraints();

			gbc_labelOrdenesTrabajo.fill = GridBagConstraints.BOTH;
			gbc_labelOrdenesTrabajo.insets = new Insets(0, 0, 0, 5);
			gbc_labelOrdenesTrabajo.gridx = 1;
			gbc_labelOrdenesTrabajo.gridy = 0;

			panelCentro.add(getLabelOrdenesTrabajo(), gbc_labelOrdenesTrabajo);

			GridBagConstraints gbc_scrollPaneOrdenesTrabajo = new GridBagConstraints();

			gbc_scrollPaneOrdenesTrabajo.gridwidth = 3;
			gbc_scrollPaneOrdenesTrabajo.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneOrdenesTrabajo.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPaneOrdenesTrabajo.gridx = 0;
			gbc_scrollPaneOrdenesTrabajo.gridy = 1;

			panelCentro.add(getScrollPaneOrdenesTrabajo(), gbc_scrollPaneOrdenesTrabajo);

			GridBagConstraints gbc_labelPedidos = new GridBagConstraints();

			gbc_labelPedidos.insets = new Insets(0, 0, 0, 5);
			gbc_labelPedidos.fill = GridBagConstraints.BOTH;
			gbc_labelPedidos.gridx = 1;
			gbc_labelPedidos.gridy = 2;

			panelCentro.add(getLabelPedidos(), gbc_labelPedidos);

			GridBagConstraints gbc_scrollPanePedidos = new GridBagConstraints();

			gbc_scrollPanePedidos.gridwidth = 3;
			gbc_scrollPanePedidos.fill = GridBagConstraints.BOTH;
			gbc_scrollPanePedidos.gridx = 0;
			gbc_scrollPanePedidos.gridy = 3;

			panelCentro.add(getScrollPanePedidos(), gbc_scrollPanePedidos);
		}

		return panelCentro;
	}

	private JLabel getLabelOrdenesTrabajo() {
		if (labelOrdenesTrabajo == null) {
			labelOrdenesTrabajo = new JLabel("\u00D3rdenes de Trabajo");

			labelOrdenesTrabajo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			labelOrdenesTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelOrdenesTrabajo;
	}

	private JScrollPane getScrollPaneOrdenesTrabajo() {
		if (scrollPaneOrdenesTrabajo == null) {
			scrollPaneOrdenesTrabajo = new JScrollPane(getTablaOrdenesTrabajo());

			scrollPaneOrdenesTrabajo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneOrdenesTrabajo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPaneOrdenesTrabajo;
	}

	protected JTable getTablaOrdenesTrabajo() {
		if (tablaOrdenesTrabajo == null) {
			modeloTablaOrdenesTrabajo = new ModeloTablaOrdenesTrabajo();
			tablaOrdenesTrabajo = new JTable(modeloTablaOrdenesTrabajo);

			tablaOrdenesTrabajo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					accionClickTablaOrdenesTrabajo();
				}
			});
		}

		return tablaOrdenesTrabajo;
	}

	protected abstract void accionClickTablaOrdenesTrabajo();
	
	private JLabel getLabelPedidos() {
		if (labelPedidos == null) {
			labelPedidos = new JLabel("Pedidos en la orden trabajo");

			labelPedidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
			labelPedidos.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelPedidos;
	}

	private JScrollPane getScrollPanePedidos() {
		if (scrollPanePedidos == null) {
			scrollPanePedidos = new JScrollPane(getTablaPedidos());

			scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPanePedidos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPanePedidos;
	}

	private JTable getTablaPedidos() {
		if (tablaPedidos == null) {
			modeloTablaPedidosOrdenTrabajo = new ModeloTablaPedidosOrdenTrabajo();
			tablaPedidos = new JTable(modeloTablaPedidosOrdenTrabajo);
		}

		return tablaPedidos;
	}

	// =====================================
	// Panel sur
	// =====================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			panelSur.setBorder(new EmptyBorder(8, 8, 8, 8));

			GridBagLayout gbl_panelSur = new GridBagLayout();

			gbl_panelSur.columnWidths = new int[] { 40, 10, 0, 0 };
			gbl_panelSur.rowHeights = new int[] { 34, 0 };
			gbl_panelSur.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelSur.rowWeights = new double[] { 1.0, Double.MIN_VALUE };

			panelSur.setLayout(gbl_panelSur);

			GridBagConstraints gbc_botonAtras = new GridBagConstraints();

			gbc_botonAtras.fill = GridBagConstraints.BOTH;
			gbc_botonAtras.insets = new Insets(0, 0, 0, 5);
			gbc_botonAtras.gridx = 0;
			gbc_botonAtras.gridy = 0;

			panelSur.add(getBotonAtras(), gbc_botonAtras);

			GridBagConstraints gbc_botonEmpezarRecoger = new GridBagConstraints();

			gbc_botonEmpezarRecoger.fill = GridBagConstraints.BOTH;
			gbc_botonEmpezarRecoger.gridx = 2;
			gbc_botonEmpezarRecoger.gridy = 0;

			panelSur.add(getBotonContinuar(), gbc_botonEmpezarRecoger);
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

	/**
	 * Tiene que ser implementado por las subclases. Define qué es lo que hace
	 * la aplicación con una orden de trabajo seleccionada.
	 * 
	 * @return boton que procesa una Orden de Trabajo
	 * 
	 */
	protected abstract JButton getBotonContinuar();

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	public abstract void inicializarDatos() throws BusinessException;

	private void reiniciarPanel() {
		modeloTablaPedidosOrdenTrabajo.removeAll();
		modeloTablaOrdenesTrabajo.removeAll();
	}

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}
}