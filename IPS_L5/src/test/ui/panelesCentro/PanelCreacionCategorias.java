package test.ui.panelesCentro;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import model.Categoria;
import model.PosicionProducto;
import test.business.acciones.NuevaCategoria;
import test.business.acciones.NuevoProducto;
import test.ui.MenuPrincipal;

import java.awt.Insets;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCreacionCategorias extends JPanel {
	private MenuPrincipal menuPrincipal;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JLabel lblDatosCategorias;
	private JLabel lblNombreCategoria;
	private JTextField textFieldNombreCategoria;
	private JLabel lblCategoriaPadre;
	private JComboBox comboBoxCategoriasPadres;
	private JButton btnConfirmar;

	/**
	 * Create the panel.
	 */
	public PanelCreacionCategorias() {
		setPreferredSize( new Dimension( 640, 480 ) );
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
			gbl_panelCentro.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 321, 0, 0};
			gbl_panelCentro.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
			gbl_panelCentro.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelCentro.setLayout(gbl_panelCentro);
			GridBagConstraints gbc_lblDatosCategorias = new GridBagConstraints();
			gbc_lblDatosCategorias.gridwidth = 4;
			gbc_lblDatosCategorias.insets = new Insets(0, 0, 5, 5);
			gbc_lblDatosCategorias.gridx = 3;
			gbc_lblDatosCategorias.gridy = 1;
			panelCentro.add(getLblDatosCategorias(), gbc_lblDatosCategorias);
			GridBagConstraints gbc_lblNombreCategoria = new GridBagConstraints();
			gbc_lblNombreCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombreCategoria.gridx = 4;
			gbc_lblNombreCategoria.gridy = 3;
			panelCentro.add(getLblNombreCategoria(), gbc_lblNombreCategoria);
			GridBagConstraints gbc_textFieldNombreCategoria = new GridBagConstraints();
			gbc_textFieldNombreCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldNombreCategoria.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldNombreCategoria.gridx = 6;
			gbc_textFieldNombreCategoria.gridy = 3;
			panelCentro.add(getTextFieldNombreCategoria(), gbc_textFieldNombreCategoria);
			GridBagConstraints gbc_lblCategoriaPadre = new GridBagConstraints();
			gbc_lblCategoriaPadre.insets = new Insets(0, 0, 0, 5);
			gbc_lblCategoriaPadre.gridx = 4;
			gbc_lblCategoriaPadre.gridy = 5;
			panelCentro.add(getLblCategoriaPadre(), gbc_lblCategoriaPadre);
			GridBagConstraints gbc_comboBoxCategoriasPadres = new GridBagConstraints();
			gbc_comboBoxCategoriasPadres.insets = new Insets(0, 0, 0, 5);
			gbc_comboBoxCategoriasPadres.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxCategoriasPadres.gridx = 6;
			gbc_comboBoxCategoriasPadres.gridy = 5;
			panelCentro.add(getComboBoxCategoriasPadres(), gbc_comboBoxCategoriasPadres);
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
	private JLabel getLblDatosCategorias() {
		if (lblDatosCategorias == null) {
			lblDatosCategorias = new JLabel("Datos categorias");
			lblDatosCategorias.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblDatosCategorias;
	}
	private JLabel getLblNombreCategoria() {
		if (lblNombreCategoria == null) {
			lblNombreCategoria = new JLabel("Nombre:");
			lblNombreCategoria.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblNombreCategoria;
	}
	private JTextField getTextFieldNombreCategoria() {
		if (textFieldNombreCategoria == null) {
			textFieldNombreCategoria = new JTextField();
			textFieldNombreCategoria.setColumns(10);
		}
		return textFieldNombreCategoria;
	}
	private JLabel getLblCategoriaPadre() {
		if (lblCategoriaPadre == null) {
			lblCategoriaPadre = new JLabel("Categoria padre:");
			lblCategoriaPadre.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lblCategoriaPadre;
	}
	private JComboBox getComboBoxCategoriasPadres() {
		if (comboBoxCategoriasPadres == null) {
			comboBoxCategoriasPadres = new JComboBox();
		}
		return comboBoxCategoriasPadres;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String resultado = menuPrincipal.getProcesadorTest().ejecutarPrueba(new NuevaCategoria(getTextFieldNombreCategoria().getText(), (Categoria)getComboBoxCategoriasPadres().getSelectedItem()));					menuPrincipal.getTextAreaResultados().setText(resultado);
					((CardLayout)menuPrincipal.getPanelCentro().getLayout()).show(menuPrincipal.getPanelCentro(),"panelResultados" );
					
				}
			});
		}
		return btnConfirmar;
	}
}
