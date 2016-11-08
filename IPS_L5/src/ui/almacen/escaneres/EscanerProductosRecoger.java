package ui.almacen.escaneres;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;

import ui.almacen.myTypes.model.MyProducto_OrdenadoPosicion;
import ui.almacen.recogida.PanelRecogidaProductos;

import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class EscanerProductosRecoger extends JDialog {

	private static final long serialVersionUID = -7032515881883206995L;

	private JLabel labelCodigoProducto;
	private JTextField textFieldCodigo;
	private JButton botonSimular;

	private PanelRecogidaProductos panelRecogidaProductos;

	private DefaultListModel<MyProducto_OrdenadoPosicion> modeloListaId;
	private JList<MyProducto_OrdenadoPosicion> listaId;
	private JLabel labelProductos;
	private JScrollPane scrollPane;

	public EscanerProductosRecoger() {
		super();
		
		setPreferredSize(new Dimension(450, 500));
		setSize(new Dimension(450, 500));

		añadirComponentes();
	}

	private void añadirComponentes() {
		GridBagLayout gridBagLayout = new GridBagLayout();

		gridBagLayout.columnWidths = new int[] { 30, 100, 0, 30, 220, 30, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 250, 30, 70, 30, 50, 30, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };

		getContentPane().setLayout(gridBagLayout);

		GridBagConstraints gbc_labelProductos = new GridBagConstraints();
		gbc_labelProductos.fill = GridBagConstraints.BOTH;

		gbc_labelProductos.gridwidth = 4;
		gbc_labelProductos.insets = new Insets(0, 0, 5, 5);
		gbc_labelProductos.gridx = 1;
		gbc_labelProductos.gridy = 1;

		getContentPane().add(getLabelProductos(), gbc_labelProductos);

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();

		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;

		getContentPane().add(getScrollPane(), gbc_scrollPane);

		GridBagConstraints gbc_labelCodigoProducto = new GridBagConstraints();

		gbc_labelCodigoProducto.insets = new Insets(0, 0, 5, 5);
		gbc_labelCodigoProducto.fill = GridBagConstraints.BOTH;
		gbc_labelCodigoProducto.gridx = 1;
		gbc_labelCodigoProducto.gridy = 5;

		getContentPane().add(getLabelCodigoProducto(), gbc_labelCodigoProducto);

		GridBagConstraints gbc_textFieldCodigo = new GridBagConstraints();

		gbc_textFieldCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCodigo.fill = GridBagConstraints.BOTH;
		gbc_textFieldCodigo.gridx = 4;
		gbc_textFieldCodigo.gridy = 5;

		getContentPane().add(getTextFieldCodigo(), gbc_textFieldCodigo);

		GridBagConstraints gbc_botonSimular = new GridBagConstraints();

		gbc_botonSimular.fill = GridBagConstraints.BOTH;
		gbc_botonSimular.insets = new Insets(0, 0, 5, 5);
		gbc_botonSimular.gridx = 4;
		gbc_botonSimular.gridy = 7;

		getContentPane().add(getBotonSimular(), gbc_botonSimular);
	}

	private JLabel getLabelCodigoProducto() {
		if (labelCodigoProducto == null) {
			labelCodigoProducto = new JLabel("C\u00F3d. prod.");
			labelCodigoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelCodigoProducto;
	}

	private JTextField getTextFieldCodigo() {
		if (textFieldCodigo == null) {
			textFieldCodigo = new JTextField();
			textFieldCodigo.setColumns(10);
		}

		return textFieldCodigo;
	}

	private JList<MyProducto_OrdenadoPosicion> getListaId() {
		if (listaId == null) {
			modeloListaId = new DefaultListModel<>();

			listaId = new JList<MyProducto_OrdenadoPosicion>(modeloListaId);
			listaId.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		return listaId;
	}

	private JLabel getLabelProductos() {
		if (labelProductos == null) {
			labelProductos = new JLabel("Id de productos");
			labelProductos.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelProductos;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(getListaId());

			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPane;
	}

	private JButton getBotonSimular() {
		if (botonSimular == null) {
			botonSimular = new JButton("Simular lectura");

			botonSimular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (textFieldCodigo.getText() != null) {
						try {
							panelRecogidaProductos.recoger(Long.parseLong(textFieldCodigo.getText()));
						}

						catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(getThis(), "El valor tiene un formato inválido", "Aviso",
									JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});
		}

		return botonSimular;
	}

	private EscanerProductosRecoger getThis() {
		return this;
	}

	public void setPanelRecogidaProductos(PanelRecogidaProductos panelRecogidaProductos) {
		this.panelRecogidaProductos = panelRecogidaProductos;
	}

	public void llenarLista(List<MyProducto_OrdenadoPosicion> lista) {
		for (MyProducto_OrdenadoPosicion prod : lista) {
			modeloListaId.addElement(prod);
		}
	}

	public void removeProducto(long id) {
		for (int i = 0; i < modeloListaId.size(); i++) {

			if (modeloListaId.getElementAt(i).getProducto().getproductoPedido().getProducto().getId() == id) {
				modeloListaId.remove(i);
				return;
			}
		}
	}
}