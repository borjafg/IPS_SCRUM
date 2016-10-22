package test.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class PanelCreacionProductos extends JPanel {

	private static final long serialVersionUID = -2570436651614645908L;
	
	private MenuPrincipal menuPrincipal;
	private JLabel labelNombre;
	private JTextField textFieldNombre;
	private JLabel labelCategoria;
	private JComboBox comboBoxCategoria;
	private JLabel labelDireccion;
	private JTextField textFieldDireccion;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton botonConfirmar;
	private JLabel labelDatos;
	
	public PanelCreacionProductos() {
		super();
		setLayout(new BorderLayout(0, 0));
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
			comboBoxCategoria = new JComboBox();
		}
		
		return comboBoxCategoria;
	}
	
	private JLabel getLabelDireccion() {
		if (labelDireccion == null) {
			labelDireccion = new JLabel("Direcci\u00F3n");
			labelDireccion.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		
		return labelDireccion;
	}
	
	private JTextField getTextFieldDireccion() {
		if (textFieldDireccion == null) {
			textFieldDireccion = new JTextField();
			textFieldDireccion.setColumns(10);
		}
		
		return textFieldDireccion;
	}
	
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			GridBagLayout gbl_panelCentro = new GridBagLayout();
			gbl_panelCentro.columnWidths = new int[]{10, 95, 10, 255, 10, 0};
			gbl_panelCentro.rowHeights = new int[]{0, 0, 10, 30, 10, 30, 10, 30, 0};
			gbl_panelCentro.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
			GridBagConstraints gbc_labelDireccion = new GridBagConstraints();
			gbc_labelDireccion.insets = new Insets(0, 0, 0, 5);
			gbc_labelDireccion.gridx = 1;
			gbc_labelDireccion.gridy = 7;
			panelCentro.add(getLabelDireccion(), gbc_labelDireccion);
			GridBagConstraints gbc_textFieldDireccion = new GridBagConstraints();
			gbc_textFieldDireccion.fill = GridBagConstraints.BOTH;
			gbc_textFieldDireccion.insets = new Insets(0, 0, 0, 5);
			gbc_textFieldDireccion.gridx = 3;
			gbc_textFieldDireccion.gridy = 7;
			panelCentro.add(getTextFieldDireccion(), gbc_textFieldDireccion);
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
}