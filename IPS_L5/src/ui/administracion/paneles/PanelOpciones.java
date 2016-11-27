package ui.administracion.paneles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import business.exception.BusinessException;
import ui.administracion.VentanaPrincipalAdministracion;

public class PanelOpciones extends JPanel {

	private static final long serialVersionUID = -8205235287522932377L;

	private VentanaPrincipalAdministracion ventanaPrincipal;
	private JPanel panelNorteTitulo;
	private JPanel panelCentroOpciones;
	private JLabel labelTituloAdministracion;
	private JPanel panelBotonInformes;
	private JPanel panelBotonTransferencia;
	private JButton botonInformes;
	private JButton botonTransferecias;

	public PanelOpciones() {
		super();

		setPreferredSize(new Dimension(816, 646));
		setLayout(new BorderLayout(0, 0));

		add(getPanelNorteTitulo(), BorderLayout.NORTH);
		add(getPanelCentroOpciones(), BorderLayout.CENTER);
	}

	private JPanel getPanelNorteTitulo() {
		if (panelNorteTitulo == null) {
			panelNorteTitulo = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelNorteTitulo.getLayout();

			flowLayout.setVgap(10);
			flowLayout.setHgap(0);

			panelNorteTitulo.add(getLabelTituloAdministracion());
		}

		return panelNorteTitulo;
	}

	private JPanel getPanelCentroOpciones() {
		if (panelCentroOpciones == null) {
			panelCentroOpciones = new JPanel();

			panelCentroOpciones.setLayout(new GridLayout(0, 1, 0, 0));

			panelCentroOpciones.add(getPanelBotonInformes());
			panelCentroOpciones.add(getPanelBotonTransferencia());
		}

		return panelCentroOpciones;
	}

	private JLabel getLabelTituloAdministracion() {
		if (labelTituloAdministracion == null) {
			labelTituloAdministracion = new JLabel("Administraci\u00F3n");
			labelTituloAdministracion.setFont(new Font("Tahoma", Font.BOLD, 14));
		}

		return labelTituloAdministracion;
	}

	private JPanel getPanelBotonInformes() {
		if (panelBotonInformes == null) {
			panelBotonInformes = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelBotonInformes.getLayout();

			flowLayout.setVgap(125);
			flowLayout.setHgap(10);

			panelBotonInformes.add(getBotonInformes());
		}

		return panelBotonInformes;
	}

	private JPanel getPanelBotonTransferencia() {
		if (panelBotonTransferencia == null) {
			panelBotonTransferencia = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelBotonTransferencia.getLayout();
			flowLayout.setVgap(125);

			panelBotonTransferencia.add(getBotonTransferecias());
		}

		return panelBotonTransferencia;
	}

	// ===================================================
	// Botones de cambio de panel en la aplicación
	// ===================================================

	private JButton getBotonInformes() {
		if (botonInformes == null) {
			botonInformes = new JButton("Informes");

			botonInformes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					ventanaPrincipal.mostrarPanelSeleccionInforme();
				}
			});

			botonInformes.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return botonInformes;
	}

	private JButton getBotonTransferecias() {
		if (botonTransferecias == null) {
			botonTransferecias = new JButton("Transferencias");

			botonTransferecias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						ventanaPrincipal.mostrarPanelTransferencia();
					} catch (BusinessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});

			botonTransferecias.setFont(new Font("Tahoma", Font.BOLD, 18));
		}

		return botonTransferecias;
	}

	// =========================================
	// Controlar el estado del panel
	// =========================================

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

}