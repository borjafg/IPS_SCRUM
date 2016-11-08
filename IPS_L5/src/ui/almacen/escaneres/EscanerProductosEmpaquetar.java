package ui.almacen.escaneres;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.Cliente;
import model.Paquete;
import model.ProductoEnOrdenTrabajo;
import ui.almacen.empaquetado.PanelEmpaquetadoProductos;
import ui.almacen.myTypes.escaner.MyProductoEmpaquetar;

public class EscanerProductosEmpaquetar extends JDialog {

	private static final long serialVersionUID = 5772176575409409970L;

	private PanelEmpaquetadoProductos panelEmpaquetadoProductos;
	private DefaultListModel<MyProductoEmpaquetar> modeloProductosEmpaquetar;

	// ======================================
	// Componentes de esta ventana
	// ======================================

	// ==== norte ====

	private JLabel labelProductos;

	// ==== centro ====

	private JPanel panelCentro;

	private JScrollPane scrollPaneListaProductos;
	private JList<MyProductoEmpaquetar> listaProductos;

	private JPanel panelSimular;
	private JButton botonSimularLecturaProducto;

	// ==== sur ====

	private JPanel panelSur;
	private JLabel labelEtiquetas;

	private JScrollPane scrollPaneEtiquetas;
	private JTextArea textAreaEtiquetas;

	/**
	 * Crea el JDialog, aunque sin llenar la lista de prodctos
	 * 
	 */
	public EscanerProductosEmpaquetar() {
		super();

		setPreferredSize(new Dimension(450, 500));
		setSize(new Dimension(450, 500));

		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getLabelProductos(), BorderLayout.NORTH);

		getContentPane().add(getPanelCentro(), BorderLayout.CENTER);
		getContentPane().add(getPanelSur(), BorderLayout.SOUTH);
	}

	// ======================================
	// Componentes de esta ventana
	// ======================================

	// ---------------
	// ---- norte ----
	// ---------------

	private JLabel getLabelProductos() {
		if (labelProductos == null) {
			labelProductos = new JLabel("Seleccione un producto para simular escaneado");

			labelProductos.setBorder(new EmptyBorder(12, 0, 12, 0));
			labelProductos.setFont(new Font("Tahoma", Font.BOLD, 18));
			labelProductos.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelProductos;
	}

	// ----------------
	// ---- centro ----
	// ----------------

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();

			panelCentro.setLayout(new BorderLayout(0, 0));

			panelCentro.add(getScrollPaneListaProductos(), BorderLayout.CENTER);
			panelCentro.add(getPanelSimular(), BorderLayout.SOUTH);
		}

		return panelCentro;
	}

	private JScrollPane getScrollPaneListaProductos() {
		if (scrollPaneListaProductos == null) {
			scrollPaneListaProductos = new JScrollPane(getListaProductos());

			scrollPaneListaProductos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneListaProductos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

			scrollPaneListaProductos.setBorder(new EmptyBorder(0, 6, 0, 6));
		}

		return scrollPaneListaProductos;
	}

	private JList<MyProductoEmpaquetar> getListaProductos() {
		if (listaProductos == null) {
			modeloProductosEmpaquetar = new DefaultListModel<MyProductoEmpaquetar>();

			listaProductos = new JList<MyProductoEmpaquetar>(modeloProductosEmpaquetar);
			listaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaProductos.setBorder(new EmptyBorder(0, 0, 0, 0));
		}

		return listaProductos;
	}

	private JPanel getPanelSimular() {
		if (panelSimular == null) {
			panelSimular = new JPanel();

			FlowLayout fl_panelSimular = (FlowLayout) panelSimular.getLayout();

			fl_panelSimular.setHgap(20);
			fl_panelSimular.setVgap(20);
			fl_panelSimular.setAlignment(FlowLayout.RIGHT);

			panelSimular.add(getBotonSimularLecturaProducto());
		}

		return panelSimular;
	}

	private JButton getBotonSimularLecturaProducto() {
		if (botonSimularLecturaProducto == null) {
			botonSimularLecturaProducto = new JButton("Simular lectura de referencia de producto");

			botonSimularLecturaProducto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int fila = listaProductos.getSelectedIndex();

					if (fila != -1) {
						panelEmpaquetadoProductos.empaquetar(
								modeloProductosEmpaquetar.getElementAt(fila).getProductoOT().getRef_OrdenTrabajo());
					}
				}
			});

			botonSimularLecturaProducto.setHorizontalTextPosition(SwingConstants.CENTER);
			botonSimularLecturaProducto.setAlignmentX(Component.CENTER_ALIGNMENT);
			botonSimularLecturaProducto.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return botonSimularLecturaProducto;
	}

	// -------------
	// ---- sur ----
	// -------------

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			panelSur.setBorder(new EmptyBorder(0, 0, 0, 2));

			GridBagLayout gbl_panelSur = new GridBagLayout();

			gbl_panelSur.columnWidths = new int[] { 235, 350, 0 };
			gbl_panelSur.rowHeights = new int[] { 40, 100, 0, 0 };
			gbl_panelSur.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelSur.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };

			panelSur.setLayout(gbl_panelSur);

			GridBagConstraints gbc_labelEtiquetas = new GridBagConstraints();

			gbc_labelEtiquetas.fill = GridBagConstraints.BOTH;
			gbc_labelEtiquetas.insets = new Insets(0, 0, 5, 5);
			gbc_labelEtiquetas.gridx = 0;
			gbc_labelEtiquetas.gridy = 0;

			panelSur.add(getLabelEtiquetas(), gbc_labelEtiquetas);

			GridBagConstraints gbc_scrollPaneEtiquetas = new GridBagConstraints();

			gbc_scrollPaneEtiquetas.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPaneEtiquetas.gridheight = 2;
			gbc_scrollPaneEtiquetas.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneEtiquetas.gridx = 1;
			gbc_scrollPaneEtiquetas.gridy = 0;

			panelSur.add(getScrollPaneEtiquetas(), gbc_scrollPaneEtiquetas);
		}

		return panelSur;
	}

	private JLabel getLabelEtiquetas() {
		if (labelEtiquetas == null) {
			labelEtiquetas = new JLabel("Etiquetas del paquete:");

			labelEtiquetas.setHorizontalAlignment(SwingConstants.CENTER);
			labelEtiquetas.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return labelEtiquetas;
	}

	private JScrollPane getScrollPaneEtiquetas() {
		if (scrollPaneEtiquetas == null) {
			scrollPaneEtiquetas = new JScrollPane(getTextAreaEtiquetas());

			scrollPaneEtiquetas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneEtiquetas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		}

		return scrollPaneEtiquetas;
	}

	private JTextArea getTextAreaEtiquetas() {
		if (textAreaEtiquetas == null) {
			textAreaEtiquetas = new JTextArea();

			textAreaEtiquetas.setLineWrap(true);
			textAreaEtiquetas.setWrapStyleWord(true);
			textAreaEtiquetas.setEditable(false);
			textAreaEtiquetas.setText("Todav\u00EDa no generadas");
			textAreaEtiquetas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}

		return textAreaEtiquetas;
	}

	// ========================================
	// Control del estado de la ventana
	// ========================================

	public String generarEtiquetas(ProductoEnOrdenTrabajo producto, Cliente cliente, Paquete paquete) {
		StringBuilder sb = new StringBuilder();

		sb.append("Direccion: ").append(producto.getproductoPedido().getPedido().getDireccionCompleta()).append("\n");
		sb.append("destinatario: ").append(cliente.getNombre()).append("\n");
		sb.append("Codigo paquete: ").append(paquete.getId());

		return sb.toString();
	}

	public void removeProducto(String ref) {
		for (int i = 0; i < modeloProductosEmpaquetar.size(); i++) {
			if (modeloProductosEmpaquetar.getElementAt(i).getProductoOT().getRef_OrdenTrabajo().equals(ref)) {
				modeloProductosEmpaquetar.remove(i);
				return;
			}
		}
	}

	public void llenarLista(List<ProductoEnOrdenTrabajo> lista) {
		for (ProductoEnOrdenTrabajo prod : lista) {
			modeloProductosEmpaquetar.addElement(new MyProductoEmpaquetar(prod));
		}
	}

	public void setPanelEmpaquetadoProductos(PanelEmpaquetadoProductos panelEmpaquetadoProductos) {
		this.panelEmpaquetadoProductos = panelEmpaquetadoProductos;
	}
}