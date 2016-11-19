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
import model.types.TipoTarjeta;

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
	private JLabel lblNumeroTarjeta;
	private JLabel lblCodigoSeguridd;
	private JLabel lblTipoTarjeta;
	private JComboBox<TipoCliente> comboBoxTipoTarjeta;
	private JTextField textFieldCodigoSec;
	private JTextField textFieldNumeroTarjeta;
	private JLabel lblFecha;
	private JTextField textFieldFecha;

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
			gbl_panelCentro.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panelCentro.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelCentro.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };

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
			GridBagConstraints gbc_lblNumeroTarjeta = new GridBagConstraints();
			gbc_lblNumeroTarjeta.insets = new Insets(0, 0, 5, 5);
			gbc_lblNumeroTarjeta.gridx = 2;
			gbc_lblNumeroTarjeta.gridy = 9;
			panelCentro.add(getLblNumeroTarjeta(), gbc_lblNumeroTarjeta);
			GridBagConstraints gbc_textFieldNumeroTarjeta = new GridBagConstraints();
			gbc_textFieldNumeroTarjeta.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldNumeroTarjeta.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldNumeroTarjeta.gridx = 4;
			gbc_textFieldNumeroTarjeta.gridy = 9;
			panelCentro.add(getTextField_1_1(), gbc_textFieldNumeroTarjeta);
			GridBagConstraints gbc_lblCodigoSeguridd = new GridBagConstraints();
			gbc_lblCodigoSeguridd.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodigoSeguridd.gridx = 2;
			gbc_lblCodigoSeguridd.gridy = 11;
			panelCentro.add(getLblCodigoSeguridd(), gbc_lblCodigoSeguridd);
			GridBagConstraints gbc_textFieldCodigoSec = new GridBagConstraints();
			gbc_textFieldCodigoSec.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldCodigoSec.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldCodigoSec.gridx = 4;
			gbc_textFieldCodigoSec.gridy = 11;
			panelCentro.add(getTextFieldCodigoSec(), gbc_textFieldCodigoSec);
			GridBagConstraints gbc_lblTipoTarjeta = new GridBagConstraints();
			gbc_lblTipoTarjeta.insets = new Insets(0, 0, 5, 5);
			gbc_lblTipoTarjeta.gridx = 2;
			gbc_lblTipoTarjeta.gridy = 13;
			panelCentro.add(getLblTipoTarjeta(), gbc_lblTipoTarjeta);
			GridBagConstraints gbc_comboBoxTipoTarjeta = new GridBagConstraints();
			gbc_comboBoxTipoTarjeta.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxTipoTarjeta.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxTipoTarjeta.gridx = 4;
			gbc_comboBoxTipoTarjeta.gridy = 13;
			panelCentro.add(getComboBox_1_1(), gbc_comboBoxTipoTarjeta);
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lblFecha.gridx = 2;
			gbc_lblFecha.gridy = 15;
			panelCentro.add(getLblFecha(), gbc_lblFecha);
			GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
			gbc_textFieldFecha.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldFecha.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldFecha.gridx = 4;
			gbc_textFieldFecha.gridy = 15;
			panelCentro.add(getTextFieldFecha(), gbc_textFieldFecha);
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
							.ejecutarPrueba(new NuevoCliente(getTextField_1().getText(), getTextField_2().getText(),(TipoCliente) getComboBox_1().getSelectedItem(),Long.parseLong(getTextField_1_1().getText()),Integer.parseInt(getTextFieldCodigoSec().getText()), (TipoTarjeta)getComboBox_1_1().getSelectedItem(),getTextFieldFecha().getText()));

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

	private JLabel getLblNumeroTarjeta() {
		if (lblNumeroTarjeta == null) {
			lblNumeroTarjeta = new JLabel("Numero tarjeta");
			lblNumeroTarjeta.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblNumeroTarjeta;
	}

	private JLabel getLblCodigoSeguridd() {
		if (lblCodigoSeguridd == null) {
			lblCodigoSeguridd = new JLabel("Codigo seguridad");
			lblCodigoSeguridd.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblCodigoSeguridd;
	}

	private JLabel getLblTipoTarjeta() {
		if (lblTipoTarjeta == null) {
			lblTipoTarjeta = new JLabel("Tipo tarjeta");
			lblTipoTarjeta.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblTipoTarjeta;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox<TipoCliente> getComboBox_1_1() {
		if (comboBoxTipoTarjeta == null) {
			comboBoxTipoTarjeta = new JComboBox<TipoCliente>();
			comboBoxTipoTarjeta.setModel(new DefaultComboBoxModel(TipoTarjeta.values()));
		}
		return comboBoxTipoTarjeta;
	}

	private JTextField getTextFieldCodigoSec() {
		if (textFieldCodigoSec == null) {
			textFieldCodigoSec = new JTextField();
			textFieldCodigoSec.setColumns(10);
		}
		return textFieldCodigoSec;
	}

	private JTextField getTextField_1_1() {
		if (textFieldNumeroTarjeta == null) {
			textFieldNumeroTarjeta = new JTextField();
			textFieldNumeroTarjeta.setColumns(10);
		}
		return textFieldNumeroTarjeta;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha");
			lblFecha.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblFecha;
	}
	private JTextField getTextFieldFecha() {
		if (textFieldFecha == null) {
			textFieldFecha = new JTextField();
			textFieldFecha.setColumns(10);
		}
		return textFieldFecha;
	}
}
