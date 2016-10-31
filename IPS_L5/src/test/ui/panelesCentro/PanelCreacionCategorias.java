package test.ui.panelesCentro;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import model.Categoria;
import test.business.acciones.NuevaCategoria;
import test.ui.MenuPrincipal;

public class PanelCreacionCategorias extends JPanel {

	private static final long serialVersionUID = 1L;

	private MenuPrincipal menuPrincipal;
	private JPanel panelCentro;
	private JPanel panelSur;
	private JLabel lblDatosCategorias;
	private JLabel lblNombreCategoria;
	private JTextField textFieldNombreCategoria;
	private JLabel lblCategoriaPadre;
	private JButton btnConfirmar;
	private JScrollPane scrollPaneCategoriasPadre;
	private JList<Categoria> listCategoriasPadre;

	private DefaultListModel<Categoria> modeloListCategoria;

	/**
	 * Create the panel.
	 */
	public PanelCreacionCategorias() {
		setPreferredSize(new Dimension(840, 620));
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

			gbl_panelCentro.columnWidths = new int[] { 0, 82, 0, 0, 0, 321, 0, 0 };
			gbl_panelCentro.rowHeights = new int[] { 32, 0, 0, 0, 0, 0, 0, 0, 0 };
			gbl_panelCentro.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelCentro.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };

			panelCentro.setLayout(gbl_panelCentro);

			GridBagConstraints gbc_list = new GridBagConstraints();

			gbc_list.insets = new Insets(0, 0, 5, 5);
			gbc_list.fill = GridBagConstraints.BOTH;
			gbc_list.gridx = 0;
			gbc_list.gridy = 0;

			GridBagConstraints gbc_lblDatosCategorias = new GridBagConstraints();

			gbc_lblDatosCategorias.gridwidth = 6;
			gbc_lblDatosCategorias.insets = new Insets(0, 0, 5, 5);
			gbc_lblDatosCategorias.gridx = 0;
			gbc_lblDatosCategorias.gridy = 0;

			panelCentro.add(getLblDatosCategorias(), gbc_lblDatosCategorias);

			GridBagConstraints gbc_lblNombreCategoria = new GridBagConstraints();

			gbc_lblNombreCategoria.gridwidth = 2;
			gbc_lblNombreCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_lblNombreCategoria.gridx = 1;
			gbc_lblNombreCategoria.gridy = 2;

			panelCentro.add(getLblNombreCategoria(), gbc_lblNombreCategoria);

			GridBagConstraints gbc_textFieldNombreCategoria = new GridBagConstraints();

			gbc_textFieldNombreCategoria.gridwidth = 3;
			gbc_textFieldNombreCategoria.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldNombreCategoria.fill = GridBagConstraints.BOTH;
			gbc_textFieldNombreCategoria.gridx = 3;
			gbc_textFieldNombreCategoria.gridy = 2;

			panelCentro.add(getTextFieldNombreCategoria(), gbc_textFieldNombreCategoria);

			GridBagConstraints gbc_lblCategoriaPadre = new GridBagConstraints();

			gbc_lblCategoriaPadre.gridwidth = 2;
			gbc_lblCategoriaPadre.insets = new Insets(0, 0, 5, 5);
			gbc_lblCategoriaPadre.gridx = 1;
			gbc_lblCategoriaPadre.gridy = 4;

			panelCentro.add(getLblCategoriaPadre(), gbc_lblCategoriaPadre);

			GridBagConstraints gbc_scrollPaneCategoriasPadre = new GridBagConstraints();

			gbc_scrollPaneCategoriasPadre.gridwidth = 3;
			gbc_scrollPaneCategoriasPadre.gridheight = 3;
			gbc_scrollPaneCategoriasPadre.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPaneCategoriasPadre.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneCategoriasPadre.gridx = 3;
			gbc_scrollPaneCategoriasPadre.gridy = 4;

			panelCentro.add(getScrollPaneCategoriasPadre(), gbc_scrollPaneCategoriasPadre);
		}

		return panelCentro;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			GridBagLayout gbl_panelSur = new GridBagLayout();

			gbl_panelSur.columnWidths = new int[] { 663, 79, 65, 0 };
			gbl_panelSur.rowHeights = new int[] { 30, 30, 30, 0 };
			gbl_panelSur.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_panelSur.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };

			panelSur.setLayout(gbl_panelSur);

			GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();

			gbc_btnConfirmar.fill = GridBagConstraints.BOTH;
			gbc_btnConfirmar.insets = new Insets(0, 0, 5, 5);
			gbc_btnConfirmar.gridx = 1;
			gbc_btnConfirmar.gridy = 1;

			panelSur.add(getBtnConfirmar(), gbc_btnConfirmar);
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

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");

			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String resultado = menuPrincipal.getProcesadorTest().ejecutarPrueba(new NuevaCategoria(
							getTextFieldNombreCategoria().getText(), getListCategoriasPadre().getSelectedValue()));

					menuPrincipal.getTextAreaResultados().setText(resultado);

					((CardLayout) menuPrincipal.getPanelCentro().getLayout()).show(menuPrincipal.getPanelCentro(),
							"panelResultados");
					getListCategoriasPadre().setSelectedIndex(-1);

					modeloListCategoria.removeAllElements();
				}
			});
		}

		return btnConfirmar;
	}

	private JScrollPane getScrollPaneCategoriasPadre() {
		if (scrollPaneCategoriasPadre == null) {
			scrollPaneCategoriasPadre = new JScrollPane();

			scrollPaneCategoriasPadre.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneCategoriasPadre.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPaneCategoriasPadre.setViewportView(getListCategoriasPadre());
		}

		return scrollPaneCategoriasPadre;
	}

	private JList<Categoria> getListCategoriasPadre() {
		if (listCategoriasPadre == null) {
			listCategoriasPadre = new JList<Categoria>();

			listCategoriasPadre.putClientProperty("List.isFileList", Boolean.TRUE);
			
			modeloListCategoria = new DefaultListModel<Categoria>();
			listCategoriasPadre.setModel(modeloListCategoria);
		}

		return listCategoriasPadre;
	}

	public void cargarDatos() {
		List<Categoria> listaCategorias = menuPrincipal.getCargadorComponentes().cargarCategoriasSinProductos();

		for (Categoria cat : listaCategorias) {
			modeloListCategoria.addElement(cat);
		}
	}
}