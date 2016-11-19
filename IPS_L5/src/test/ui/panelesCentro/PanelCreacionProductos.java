package test.ui.panelesCentro;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Categoria;
import model.PosicionProducto;
import model.types.EstanteriaProducto;
import test.business.acciones.NuevoProducto;
import test.ui.MenuPrincipal;

public class PanelCreacionProductos extends JPanel {

	private static final long serialVersionUID = -2570436651614645908L;

	private MenuPrincipal menuPrincipal;
	private JLabel labelNombre;
	private JTextField textFieldNombre;
	private JLabel labelCategoria;
	private JComboBox<Categoria> comboBoxCategoria;
	private JLabel labelPrecio;
	private JTextField textFieldPrecio;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton botonConfirmar;
	private JLabel labelDatos;
	private JLabel lblDescripcin;
	private JTextField textFieldDescripcion;
	private DefaultComboBoxModel<Categoria> modeloComboCategoria;
	private JLabel label;
	private JLabel labelAltura;
	private JTextField textFieldAltura;
	private JLabel labelPasillo;
	private JTextField textFieldPasillo;
	private JLabel labelEstanteria;
	private JComboBox<EstanteriaProducto> comboBoxEstanteria;
	private JLabel labelPosicionX;
	private JTextField textFieldPosicionX;
	private JLabel lblIva;
	private JLabel lblPeso;
	private JLabel lblVolumen;
	private JTextField textFieldIVA;
	private JTextField textFieldPeso;
	private JTextField textFieldVolumen;

	public PanelCreacionProductos() {
		super();
		setLayout(new BorderLayout(0, 0));

		// Definir el tamaño aproximado que debería tener el panel
		//
		setPreferredSize(new Dimension(840, 620));// definir tamaño por defecto

		add(getPanelCentro());
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	public void setVentanaPrincipal(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
	}

	private JLabel getLabelNombre() {
		if (labelNombre == null) {
			labelNombre = new JLabel("Nombre:");
			labelNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
			labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
			labelNombre.setHorizontalTextPosition(SwingConstants.CENTER);
		}

		return labelNombre;
	}

	private JTextField getTextFieldNombre() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setColumns(20);
		}

		return textFieldNombre;
	}

	private JLabel getLabelCategoria() {
		if (labelCategoria == null) {
			labelCategoria = new JLabel("Categoria");
			labelCategoria.setFont(new Font("Tahoma", Font.BOLD, 18));
			labelCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelCategoria;
	}

	private JComboBox<Categoria> getComboBoxCategoria() {
		if (comboBoxCategoria == null) {
			comboBoxCategoria = new JComboBox<Categoria>();// modelo tienen que
															// ser todas las
															// categorias que no
															// tengan hijos
			modeloComboCategoria = new DefaultComboBoxModel<Categoria>();
			comboBoxCategoria.setModel(modeloComboCategoria);

		}

		return comboBoxCategoria;
	}

	private JLabel getLabelPrecio() {
		if (labelPrecio == null) {
			labelPrecio = new JLabel("Precio");
			labelPrecio.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return labelPrecio;
	}

	private JTextField getTextFieldPrecio() {
		if (textFieldPrecio == null) {
			textFieldPrecio = new JTextField();
			textFieldPrecio.setColumns(10);
		}

		return textFieldPrecio;
	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			GridBagLayout gbl_panelCentro = new GridBagLayout();
			gbl_panelCentro.columnWidths = new int[] { 10, 95, 10, 255, 10, 0 };
			gbl_panelCentro.rowHeights = new int[] { 0, 10, 30, 10, 30, 10, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panelCentro.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelCentro.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			panelCentro.setLayout(gbl_panelCentro);
			GridBagConstraints gbc_labelDatos = new GridBagConstraints();
			gbc_labelDatos.gridwidth = 3;
			gbc_labelDatos.insets = new Insets(0, 0, 5, 5);
			gbc_labelDatos.gridx = 1;
			gbc_labelDatos.gridy = 0;
			panelCentro.add(getLabelDatos(), gbc_labelDatos);
			GridBagConstraints gbc_labelNombre = new GridBagConstraints();
			gbc_labelNombre.fill = GridBagConstraints.BOTH;
			gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
			gbc_labelNombre.gridx = 1;
			gbc_labelNombre.gridy = 2;
			panelCentro.add(getLabelNombre(), gbc_labelNombre);
			GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
			gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
			gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldNombre.gridx = 3;
			gbc_textFieldNombre.gridy = 2;
			panelCentro.add(getTextFieldNombre(), gbc_textFieldNombre);
			GridBagConstraints gbc_labelCategoria = new GridBagConstraints();
			gbc_labelCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_labelCategoria.gridx = 1;
			gbc_labelCategoria.gridy = 3;
			panelCentro.add(getLabelCategoria(), gbc_labelCategoria);
			GridBagConstraints gbc_comboBoxCategoria = new GridBagConstraints();
			gbc_comboBoxCategoria.fill = GridBagConstraints.BOTH;
			gbc_comboBoxCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxCategoria.gridx = 3;
			gbc_comboBoxCategoria.gridy = 3;
			panelCentro.add(getComboBoxCategoria(), gbc_comboBoxCategoria);
			GridBagConstraints gbc_labelPrecio = new GridBagConstraints();
			gbc_labelPrecio.insets = new Insets(0, 0, 5, 5);
			gbc_labelPrecio.gridx = 1;
			gbc_labelPrecio.gridy = 4;
			panelCentro.add(getLabelPrecio(), gbc_labelPrecio);
			GridBagConstraints gbc_textFieldPrecio = new GridBagConstraints();
			gbc_textFieldPrecio.fill = GridBagConstraints.BOTH;
			gbc_textFieldPrecio.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPrecio.gridx = 3;
			gbc_textFieldPrecio.gridy = 4;
			panelCentro.add(getTextFieldPrecio(), gbc_textFieldPrecio);
			GridBagConstraints gbc_lblIva = new GridBagConstraints();
			gbc_lblIva.insets = new Insets(0, 0, 5, 5);
			gbc_lblIva.gridx = 1;
			gbc_lblIva.gridy = 5;
			panelCentro.add(getLblIva(), gbc_lblIva);
			GridBagConstraints gbc_textFieldIVA = new GridBagConstraints();
			gbc_textFieldIVA.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldIVA.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldIVA.gridx = 3;
			gbc_textFieldIVA.gridy = 5;
			panelCentro.add(getTextFieldIVA(), gbc_textFieldIVA);
			GridBagConstraints gbc_lblPeso = new GridBagConstraints();
			gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
			gbc_lblPeso.gridx = 1;
			gbc_lblPeso.gridy = 6;
			panelCentro.add(getLblPeso(), gbc_lblPeso);
			GridBagConstraints gbc_textFieldPeso = new GridBagConstraints();
			gbc_textFieldPeso.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPeso.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPeso.gridx = 3;
			gbc_textFieldPeso.gridy = 6;
			panelCentro.add(getTextFieldPeso(), gbc_textFieldPeso);
			GridBagConstraints gbc_lblVolumen = new GridBagConstraints();
			gbc_lblVolumen.insets = new Insets(0, 0, 5, 5);
			gbc_lblVolumen.gridx = 1;
			gbc_lblVolumen.gridy = 7;
			panelCentro.add(getLblVolumen(), gbc_lblVolumen);
			GridBagConstraints gbc_textFieldVolumen = new GridBagConstraints();
			gbc_textFieldVolumen.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldVolumen.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldVolumen.gridx = 3;
			gbc_textFieldVolumen.gridy = 7;
			panelCentro.add(getTextFieldVolumen(), gbc_textFieldVolumen);
			GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
			gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripcin.gridx = 1;
			gbc_lblDescripcin.gridy = 8;
			panelCentro.add(getLblDescripcin(), gbc_lblDescripcin);
			GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
			gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDescripcion.gridx = 3;
			gbc_textFieldDescripcion.gridy = 8;
			panelCentro.add(getTextFieldDescripcion(), gbc_textFieldDescripcion);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridwidth = 3;
			gbc_label.gridx = 1;
			gbc_label.gridy = 10;
			panelCentro.add(getLabel(), gbc_label);
			GridBagConstraints gbc_labelAltura = new GridBagConstraints();
			gbc_labelAltura.insets = new Insets(0, 0, 5, 5);
			gbc_labelAltura.gridx = 1;
			gbc_labelAltura.gridy = 12;
			panelCentro.add(getLabelAltura(), gbc_labelAltura);
			GridBagConstraints gbc_textFieldAltura = new GridBagConstraints();
			gbc_textFieldAltura.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldAltura.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldAltura.gridx = 3;
			gbc_textFieldAltura.gridy = 12;
			panelCentro.add(getTextFieldAltura(), gbc_textFieldAltura);
			GridBagConstraints gbc_labelPasillo = new GridBagConstraints();
			gbc_labelPasillo.insets = new Insets(0, 0, 5, 5);
			gbc_labelPasillo.gridx = 1;
			gbc_labelPasillo.gridy = 14;
			panelCentro.add(getLabelPasillo(), gbc_labelPasillo);
			GridBagConstraints gbc_textFieldPasillo = new GridBagConstraints();
			gbc_textFieldPasillo.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPasillo.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPasillo.gridx = 3;
			gbc_textFieldPasillo.gridy = 14;
			panelCentro.add(getTextFieldPasillo(), gbc_textFieldPasillo);
			GridBagConstraints gbc_labelEstanteria = new GridBagConstraints();
			gbc_labelEstanteria.insets = new Insets(0, 0, 5, 5);
			gbc_labelEstanteria.gridx = 1;
			gbc_labelEstanteria.gridy = 16;
			panelCentro.add(getLabelEstanteria(), gbc_labelEstanteria);
			GridBagConstraints gbc_comboBoxEstanteria = new GridBagConstraints();
			gbc_comboBoxEstanteria.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxEstanteria.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxEstanteria.gridx = 3;
			gbc_comboBoxEstanteria.gridy = 16;
			panelCentro.add(getComboBoxEstanteria(), gbc_comboBoxEstanteria);
			GridBagConstraints gbc_labelPosicionX = new GridBagConstraints();
			gbc_labelPosicionX.insets = new Insets(0, 0, 0, 5);
			gbc_labelPosicionX.gridx = 1;
			gbc_labelPosicionX.gridy = 18;
			panelCentro.add(getLabelPosicionX(), gbc_labelPosicionX);
			GridBagConstraints gbc_textFieldPosicionX = new GridBagConstraints();
			gbc_textFieldPosicionX.insets = new Insets(0, 0, 0, 5);
			gbc_textFieldPosicionX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPosicionX.gridx = 3;
			gbc_textFieldPosicionX.gridy = 18;
			panelCentro.add(getTextFieldPosicionX(), gbc_textFieldPosicionX);

		}

		return panelCentro;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			panelSur.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
			panelSur.add(getBotonConfirmar());
		}

		return panelSur;
	}

	private JButton getBotonConfirmar() {
		if (botonConfirmar == null) {
			botonConfirmar = new JButton("Confirmar");
			botonConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// Crear la posicion del producto
					
					PosicionProducto posicion = new PosicionProducto();
					
					posicion.setAltura(Integer.parseInt(getTextFieldAltura().getText()));
					posicion.setEstanteriaPoducto((EstanteriaProducto) getComboBoxEstanteria().getSelectedItem());
					posicion.setPosicionX(Integer.parseInt(getTextFieldPosicionX().getText()));
					posicion.setPasillo(Integer.parseInt(getTextFieldPasillo().getText()));
					
					// Sacar los datos del producto
					
					String nombre = getTextFieldNombre().getText();
					Categoria categoria = (Categoria) getComboBoxCategoria().getSelectedItem();
					Double precio = Double.parseDouble(getTextFieldPrecio().getText());
					String descripcion = getTextFieldDescripcion().getText();
					double iva = Double.parseDouble(getTextFieldIVA().getText());
					double peso = Double.parseDouble(getTextFieldPeso().getText());
					double volumen = Double.parseDouble(getTextFieldVolumen().getText());
					// Creamos el producto
					
					String resultado = menuPrincipal.getProcesadorTest()
							.ejecutarPrueba(new NuevoProducto(nombre, categoria, precio, descripcion, posicion,iva,peso,volumen));

					// Mostramos el resultado de la ejecución
					
					menuPrincipal.getTextAreaResultados().setText(resultado);
					((CardLayout) menuPrincipal.getPanelCentro().getLayout()).show(menuPrincipal.getPanelCentro(),
							"panelResultados");
				}
			});
		}

		return botonConfirmar;
	}

	private JLabel getLabelDatos() {
		if (labelDatos == null) {
			labelDatos = new JLabel("Datos del producto");
			labelDatos.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return labelDatos;
	}

	private JLabel getLblDescripcin() {
		if (lblDescripcin == null) {
			lblDescripcin = new JLabel("Descripci\u00F3n:");
			lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblDescripcin;
	}

	private JTextField getTextFieldDescripcion() {
		if (textFieldDescripcion == null) {
			textFieldDescripcion = new JTextField();
			textFieldDescripcion.setColumns(10);
		}
		return textFieldDescripcion;
	}

	public void cargarDatos() {
		List<Categoria> listaCategorias = menuPrincipal.getCargadorComponentes().cargarCategorias();

		for (Categoria cat : listaCategorias) {
			modeloComboCategoria.addElement(cat);
		}
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Datos posicion");
			label.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return label;
	}

	private JLabel getLabelAltura() {
		if (labelAltura == null) {
			labelAltura = new JLabel("Altura: ");
			labelAltura.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return labelAltura;
	}

	private JTextField getTextFieldAltura() {
		if (textFieldAltura == null) {
			textFieldAltura = new JTextField();
			textFieldAltura.setColumns(10);
		}

		return textFieldAltura;
	}

	private JLabel getLabelPasillo() {
		if (labelPasillo == null) {
			labelPasillo = new JLabel("Pasillo: ");
			labelPasillo.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return labelPasillo;
	}

	private JTextField getTextFieldPasillo() {
		if (textFieldPasillo == null) {
			textFieldPasillo = new JTextField();
			textFieldPasillo.setColumns(10);
		}
		return textFieldPasillo;
	}

	private JLabel getLabelEstanteria() {
		if (labelEstanteria == null) {
			labelEstanteria = new JLabel("Estanteria: ");
			labelEstanteria.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return labelEstanteria;
	}

	private JComboBox<EstanteriaProducto> getComboBoxEstanteria() {
		if (comboBoxEstanteria == null) {
			comboBoxEstanteria = new JComboBox<EstanteriaProducto>();
			comboBoxEstanteria.setModel(new DefaultComboBoxModel<EstanteriaProducto>(EstanteriaProducto.values()));
		}
		return comboBoxEstanteria;
	}

	private JLabel getLabelPosicionX() {
		if (labelPosicionX == null) {
			labelPosicionX = new JLabel("PosicionX:");
			labelPosicionX.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return labelPosicionX;
	}

	private JTextField getTextFieldPosicionX() {
		if (textFieldPosicionX == null) {
			textFieldPosicionX = new JTextField();
			textFieldPosicionX.setColumns(10);
		}
		return textFieldPosicionX;
	}
	private JLabel getLblIva() {
		if (lblIva == null) {
			lblIva = new JLabel("IVA");
			lblIva.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblIva;
	}
	private JLabel getLblPeso() {
		if (lblPeso == null) {
			lblPeso = new JLabel("Peso");
			lblPeso.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblPeso;
	}
	private JLabel getLblVolumen() {
		if (lblVolumen == null) {
			lblVolumen = new JLabel("Volumen");
			lblVolumen.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblVolumen;
	}
	private JTextField getTextFieldIVA() {
		if (textFieldIVA == null) {
			textFieldIVA = new JTextField();
			textFieldIVA.setColumns(10);
		}
		return textFieldIVA;
	}
	private JTextField getTextFieldPeso() {
		if (textFieldPeso == null) {
			textFieldPeso = new JTextField();
			textFieldPeso.setColumns(10);
		}
		return textFieldPeso;
	}
	private JTextField getTextFieldVolumen() {
		if (textFieldVolumen == null) {
			textFieldVolumen = new JTextField();
			textFieldVolumen.setColumns(10);
		}
		return textFieldVolumen;
	}
}