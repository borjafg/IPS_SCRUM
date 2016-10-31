package test.ui.panelesCentro;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Categoria;
import model.PosicionProducto;
import test.business.acciones.NuevoProducto;
import test.business.acciones.ProbarAccesoMapeador;
import test.ui.MenuPrincipal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCreacionProductos extends JPanel {

	private static final long serialVersionUID = -2570436651614645908L;
	
	private MenuPrincipal menuPrincipal;
	private JLabel labelNombre;
	private JTextField textFieldNombre;
	private JLabel labelCategoria;
	private JComboBox comboBoxCategoria;
	private JLabel labelPrecio;
	private JTextField textFieldPrecio;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton botonConfirmar;
	private JLabel labelDatos;
	private JLabel lblDescripcin;
	private JTextField textFieldDescripcion;
	private JLabel lblPosicionProducto;
	private DefaultComboBoxModel<PosicionProducto> modeloCombo;
	private DefaultComboBoxModel<Categoria> modeloComboCategoria;
	private JComboBox comboBoxPosicionesProducto;
	
	public PanelCreacionProductos() {
		super();
		setLayout(new BorderLayout(0, 0));
		
		// Definir el tamaño aproximado que debería tener el panel
		//
		setPreferredSize( new Dimension( 640, 480 ) );//definir tamaño por defecto
		
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
	
	private JComboBox getComboBoxCategoria() {
		if (comboBoxCategoria == null) {
			comboBoxCategoria = new JComboBox();//modelo tienen que ser todas las categorias que no tengan hijos
		}
		modeloComboCategoria = new DefaultComboBoxModel<Categoria>();
		comboBoxCategoria.setModel(modeloComboCategoria);
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
			gbl_panelCentro.columnWidths = new int[]{10, 95, 10, 255, 10, 0};
			gbl_panelCentro.rowHeights = new int[]{0, 0, 10, 30, 10, 30, 10, 30, 0, 0, 0, 0, 0};
			gbl_panelCentro.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelCentro.setLayout(gbl_panelCentro);
			GridBagConstraints gbc_labelDatos = new GridBagConstraints();
			gbc_labelDatos.gridwidth = 3;
			gbc_labelDatos.insets = new Insets(0, 0, 5, 5);
			gbc_labelDatos.gridx = 1;
			gbc_labelDatos.gridy = 1;
			panelCentro.add(getLabelDatos(), gbc_labelDatos);
			GridBagConstraints gbc_labelNombre = new GridBagConstraints();
			gbc_labelNombre.fill = GridBagConstraints.BOTH;
			gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
			gbc_labelNombre.gridx = 1;
			gbc_labelNombre.gridy = 3;
			panelCentro.add(getLabelNombre(), gbc_labelNombre);
			GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
			gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
			gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldNombre.gridx = 3;
			gbc_textFieldNombre.gridy = 3;
			panelCentro.add(getTextFieldNombre(), gbc_textFieldNombre);
			GridBagConstraints gbc_labelCategoria = new GridBagConstraints();
			gbc_labelCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_labelCategoria.gridx = 1;
			gbc_labelCategoria.gridy = 5;
			panelCentro.add(getLabelCategoria(), gbc_labelCategoria);
			GridBagConstraints gbc_comboBoxCategoria = new GridBagConstraints();
			gbc_comboBoxCategoria.fill = GridBagConstraints.BOTH;
			gbc_comboBoxCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxCategoria.gridx = 3;
			gbc_comboBoxCategoria.gridy = 5;
			panelCentro.add(getComboBoxCategoria(), gbc_comboBoxCategoria);
			GridBagConstraints gbc_labelPrecio = new GridBagConstraints();
			gbc_labelPrecio.insets = new Insets(0, 0, 5, 5);
			gbc_labelPrecio.gridx = 1;
			gbc_labelPrecio.gridy = 7;
			panelCentro.add(getLabelPrecio(), gbc_labelPrecio);
			GridBagConstraints gbc_textFieldPrecio = new GridBagConstraints();
			gbc_textFieldPrecio.fill = GridBagConstraints.BOTH;
			gbc_textFieldPrecio.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPrecio.gridx = 3;
			gbc_textFieldPrecio.gridy = 7;
			panelCentro.add(getTextFieldPrecio(), gbc_textFieldPrecio);
			GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
			gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
			gbc_lblDescripcin.gridx = 1;
			gbc_lblDescripcin.gridy = 9;
			panelCentro.add(getLblDescripcin(), gbc_lblDescripcin);
			GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
			gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDescripcion.gridx = 3;
			gbc_textFieldDescripcion.gridy = 9;
			panelCentro.add(getTextFieldDescripcion(), gbc_textFieldDescripcion);
			GridBagConstraints gbc_lblPosicionProducto = new GridBagConstraints();
			gbc_lblPosicionProducto.insets = new Insets(0, 0, 0, 5);
			gbc_lblPosicionProducto.gridx = 1;
			gbc_lblPosicionProducto.gridy = 11;
			panelCentro.add(getLblPosicionProducto(), gbc_lblPosicionProducto);
			GridBagConstraints gbc_comboBoxPosicionesProducto = new GridBagConstraints();
			gbc_comboBoxPosicionesProducto.insets = new Insets(0, 0, 0, 5);
			gbc_comboBoxPosicionesProducto.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxPosicionesProducto.gridx = 3;
			gbc_comboBoxPosicionesProducto.gridy = 11;
			panelCentro.add(getComboBoxPosicionesProducto(), gbc_comboBoxPosicionesProducto);
			GridBagConstraints gbc_comboBoxPosicionesProducto1 = new GridBagConstraints();
	
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
					
					String resultado = menuPrincipal.getProcesadorTest().ejecutarPrueba(new NuevoProducto(getTextFieldNombre().getText(), (Categoria)getComboBoxCategoria().getSelectedItem(), Double.parseDouble(getTextFieldPrecio().getText()), getTextFieldDescripcion().getText(),(PosicionProducto)getComboBoxPosicionesProducto().getSelectedItem()));
					menuPrincipal.getTextAreaResultados().setText(resultado);
					((CardLayout)menuPrincipal.getPanelCentro().getLayout()).show(menuPrincipal.getPanelCentro(),"panelResultados" );
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
	private JLabel getLblPosicionProducto() {
		if (lblPosicionProducto == null) {
			lblPosicionProducto = new JLabel("Posicion");
			lblPosicionProducto.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblPosicionProducto;
	}
	
	private JComboBox getComboBoxPosicionesProducto() {
		if (comboBoxPosicionesProducto == null) {
			comboBoxPosicionesProducto = new JComboBox();
		}
		modeloCombo = new DefaultComboBoxModel<PosicionProducto>();
		comboBoxPosicionesProducto.setModel(modeloCombo);
		return comboBoxPosicionesProducto;
	}
}