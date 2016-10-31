package test.ui.panelesCentro;

import javax.swing.JPanel;

import test.business.acciones.NuevaPosicion;
import test.business.acciones.NuevoProducto;
import test.ui.MenuPrincipal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import model.Categoria;
import model.PosicionProducto;
import model.types.EstanteriaProducto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCreacionPosicion extends JPanel {
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton btnConfirmar;
	private MenuPrincipal menuPrincipal;
	private JLabel lblAltura;
	private JTextField textFieldAltura;
	private JLabel lblDatosPosicion;
	private JLabel lblPasillo;
	private JTextField textFieldPasillo;
	private JLabel lblEstanteria;
	private JComboBox comboBoxEstanteria;
	private JLabel lblPosicionX;
	private JTextField textFieldPosicionX;

	/**
	 * Create the panel.
	 */
	public PanelCreacionPosicion() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize( new Dimension( 640, 480 ) );
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);

	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			GridBagLayout gbl_panelCentro = new GridBagLayout();
			gbl_panelCentro.columnWidths = new int[]{0, 24, 38, 153, 0, 0};
			gbl_panelCentro.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelCentro.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelCentro.setLayout(gbl_panelCentro);
			GridBagConstraints gbc_lblDatosPosicion = new GridBagConstraints();
			gbc_lblDatosPosicion.gridwidth = 3;
			gbc_lblDatosPosicion.insets = new Insets(0, 0, 5, 5);
			gbc_lblDatosPosicion.gridx = 1;
			gbc_lblDatosPosicion.gridy = 0;
			panelCentro.add(getLblDatosPosicion(), gbc_lblDatosPosicion);
			GridBagConstraints gbc_lblAltura = new GridBagConstraints();
			gbc_lblAltura.insets = new Insets(0, 0, 5, 5);
			gbc_lblAltura.gridx = 1;
			gbc_lblAltura.gridy = 2;
			panelCentro.add(getLblAltura(), gbc_lblAltura);
			GridBagConstraints gbc_textFieldAltura = new GridBagConstraints();
			gbc_textFieldAltura.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldAltura.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldAltura.gridx = 3;
			gbc_textFieldAltura.gridy = 2;
			panelCentro.add(getTextFieldAltura(), gbc_textFieldAltura);
			GridBagConstraints gbc_lblPasillo = new GridBagConstraints();
			gbc_lblPasillo.insets = new Insets(0, 0, 5, 5);
			gbc_lblPasillo.gridx = 1;
			gbc_lblPasillo.gridy = 4;
			panelCentro.add(getLblPasillo(), gbc_lblPasillo);
			GridBagConstraints gbc_textFieldPasillo = new GridBagConstraints();
			gbc_textFieldPasillo.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPasillo.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPasillo.gridx = 3;
			gbc_textFieldPasillo.gridy = 4;
			panelCentro.add(getTextFieldPasillo(), gbc_textFieldPasillo);
			GridBagConstraints gbc_lblEstanteria = new GridBagConstraints();
			gbc_lblEstanteria.insets = new Insets(0, 0, 5, 5);
			gbc_lblEstanteria.gridx = 1;
			gbc_lblEstanteria.gridy = 6;
			panelCentro.add(getLblEstanteria(), gbc_lblEstanteria);
			GridBagConstraints gbc_comboBoxEstanteria = new GridBagConstraints();
			gbc_comboBoxEstanteria.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxEstanteria.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxEstanteria.gridx = 3;
			gbc_comboBoxEstanteria.gridy = 6;
			panelCentro.add(getComboBoxEstanteria(), gbc_comboBoxEstanteria);
			GridBagConstraints gbc_lblPosicionX = new GridBagConstraints();
			gbc_lblPosicionX.insets = new Insets(0, 0, 0, 5);
			gbc_lblPosicionX.gridx = 1;
			gbc_lblPosicionX.gridy = 8;
			panelCentro.add(getLblPosicionX(), gbc_lblPosicionX);
			GridBagConstraints gbc_textFieldPosicionX = new GridBagConstraints();
			gbc_textFieldPosicionX.insets = new Insets(0, 0, 0, 5);
			gbc_textFieldPosicionX.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPosicionX.gridx = 3;
			gbc_textFieldPosicionX.gridy = 8;
			panelCentro.add(getTextFieldPosicionX(), gbc_textFieldPosicionX);
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
				public void actionPerformed(ActionEvent e) {
					String resultado = menuPrincipal.getProcesadorTest().ejecutarPrueba(new NuevaPosicion(Integer.parseInt(getTextFieldAltura().getText()), Integer.parseInt(getTextFieldPasillo().getText())
							, Integer.parseInt(getTextFieldPosicionX().getText()), (EstanteriaProducto)getComboBoxEstanteria().getSelectedItem()));
					menuPrincipal.getTextAreaResultados().setText(resultado);
					((CardLayout)menuPrincipal.getPanelCentro().getLayout()).show(menuPrincipal.getPanelCentro(),"panelResultados" );
					
				}
			});
		}
		return btnConfirmar;
	}

	public void setVentanaPrincipal(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
		
	}
	private JLabel getLblAltura() {
		if (lblAltura == null) {
			lblAltura = new JLabel("Altura: ");
			lblAltura.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblAltura;
	}
	private JTextField getTextFieldAltura() {
		if (textFieldAltura == null) {
			textFieldAltura = new JTextField();
			textFieldAltura.setColumns(10);
		}
		return textFieldAltura;
	}
	private JLabel getLblDatosPosicion() {
		if (lblDatosPosicion == null) {
			lblDatosPosicion = new JLabel("Datos posicion");
			lblDatosPosicion.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblDatosPosicion;
	}
	private JLabel getLblPasillo() {
		if (lblPasillo == null) {
			lblPasillo = new JLabel("Pasillo: ");
			lblPasillo.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblPasillo;
	}
	private JTextField getTextFieldPasillo() {
		if (textFieldPasillo == null) {
			textFieldPasillo = new JTextField();
			textFieldPasillo.setColumns(10);
		}
		return textFieldPasillo;
	}
	private JLabel getLblEstanteria() {
		if (lblEstanteria == null) {
			lblEstanteria = new JLabel("Estanteria: ");
			lblEstanteria.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblEstanteria;
	}
	private JComboBox getComboBoxEstanteria() {
		if (comboBoxEstanteria == null) {
			comboBoxEstanteria = new JComboBox();
			comboBoxEstanteria.setModel(new DefaultComboBoxModel(EstanteriaProducto.values()));
		}
		return comboBoxEstanteria;
	}
	private JLabel getLblPosicionX() {
		if (lblPosicionX == null) {
			lblPosicionX = new JLabel("PosicionX:");
			lblPosicionX.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblPosicionX;
	}
	private JTextField getTextFieldPosicionX() {
		if (textFieldPosicionX == null) {
			textFieldPosicionX = new JTextField();
			textFieldPosicionX.setColumns(10);
		}
		return textFieldPosicionX;
	}
}
