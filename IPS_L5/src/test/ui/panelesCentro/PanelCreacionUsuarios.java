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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.types.TipoCliente;
import test.business.acciones.NuevoCliente;
import test.ui.MenuPrincipal;

public class PanelCreacionUsuarios extends JPanel {

	private static final long serialVersionUID = 4928998223031270452L;

	private MenuPrincipal menuPrincipal;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton btnConfirmar;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblDireccion;
	private JTextField textFieldDireccion;
	private JLabel lblTipoUsuario;

	private JComboBox<TipoCliente> comboBox;
	DefaultComboBoxModel<TipoCliente> modeloComboTipoCliente;

	private JLabel lblDatosUsuario;

	/**
	 * Create the panel.
	 */
	public PanelCreacionUsuarios() {
		super();

		// Definir el tamaño aproximado que debería tener el panel
		//
		setPreferredSize(new Dimension(640, 480));
		setLayout(new BorderLayout(0, 0));
		
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	public void setVentanaPrincipal(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;

	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();

			GridBagLayout gbl_panelCentro = new GridBagLayout();

			gbl_panelCentro.columnWidths = new int[] { 0, 0, 0, 0, 166, 0, 0 };
			gbl_panelCentro.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panelCentro.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelCentro.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					Double.MIN_VALUE };

			panelCentro.setLayout(gbl_panelCentro);

			GridBagConstraints gbc_lblDatosUsuario = new GridBagConstraints();

			gbc_lblDatosUsuario.gridwidth = 3;
			gbc_lblDatosUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_lblDatosUsuario.gridx = 2;
			gbc_lblDatosUsuario.gridy = 1;

			panelCentro.add(getLblDatosUsuario(), gbc_lblDatosUsuario);

			GridBagConstraints gbc_lblNombre = new GridBagConstraints();

			gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombre.gridx = 2;
			gbc_lblNombre.gridy = 3;

			panelCentro.add(getLblNombre(), gbc_lblNombre);

			GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();

			gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldNombre.gridx = 4;
			gbc_textFieldNombre.gridy = 3;

			panelCentro.add(getTextField_1(), gbc_textFieldNombre);

			GridBagConstraints gbc_lblDireccion = new GridBagConstraints();

			gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
			gbc_lblDireccion.gridx = 2;
			gbc_lblDireccion.gridy = 5;

			panelCentro.add(getLblDireccion(), gbc_lblDireccion);

			GridBagConstraints gbc_textFieldDireccion = new GridBagConstraints();

			gbc_textFieldDireccion.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldDireccion.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDireccion.gridx = 4;
			gbc_textFieldDireccion.gridy = 5;

			panelCentro.add(getTextField_2(), gbc_textFieldDireccion);

			GridBagConstraints gbc_lblTipoUsuario = new GridBagConstraints();

			gbc_lblTipoUsuario.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoUsuario.gridx = 2;
			gbc_lblTipoUsuario.gridy = 7;

			panelCentro.add(getLblTipoUsuario(), gbc_lblTipoUsuario);

			GridBagConstraints gbc_comboBox = new GridBagConstraints();

			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 4;
			gbc_comboBox.gridy = 7;

			panelCentro.add(getComboBox_1(), gbc_comboBox);
		}

		return panelCentro;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);

			panelSur.add(getBtnConfirmar());
		}

		return panelSur;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");

			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String resultado = menuPrincipal.getProcesadorTest()
							.ejecutarPrueba(new NuevoCliente(getTextField_1().getText(), getTextField_2().getText(),
									(TipoCliente) getComboBox_1().getSelectedItem()));

					menuPrincipal.getTextAreaResultados().setText(resultado);

					((CardLayout) menuPrincipal.getPanelCentro().getLayout()).show(menuPrincipal.getPanelCentro(),
							"panelResultados");
				}
			});
		}

		return btnConfirmar;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return lblNombre;
	}

	private JTextField getTextField_1() {
		if (textFieldNombre == null) {
			textFieldNombre = new JTextField();
			textFieldNombre.setColumns(10);
		}

		return textFieldNombre;
	}

	private JLabel getLblDireccion() {
		if (lblDireccion == null) {
			lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return lblDireccion;
	}

	private JTextField getTextField_2() {
		if (textFieldDireccion == null) {
			textFieldDireccion = new JTextField();
			textFieldDireccion.setColumns(10);
		}

		return textFieldDireccion;
	}

	private JLabel getLblTipoUsuario() {
		if (lblTipoUsuario == null) {
			lblTipoUsuario = new JLabel("Tipo usuario:");
			lblTipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return lblTipoUsuario;
	}

	private JComboBox<TipoCliente> getComboBox_1() {
		if (comboBox == null) {
			modeloComboTipoCliente = new DefaultComboBoxModel<TipoCliente>(TipoCliente.values());

			comboBox = new JComboBox<TipoCliente>(modeloComboTipoCliente);
		}

		return comboBox;
	}

	private JLabel getLblDatosUsuario() {
		if (lblDatosUsuario == null) {
			lblDatosUsuario = new JLabel("Datos Usuario");
			lblDatosUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return lblDatosUsuario;
	}
}
