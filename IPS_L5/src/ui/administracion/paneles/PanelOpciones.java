package ui.administracion.paneles;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import business.exception.BusinessException;
import infrastructure.Log;
import ui.administracion.VentanaPrincipalAdministracion;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelOpciones extends JPanel {

	private static final long serialVersionUID = -8205235287522932377L;

	private VentanaPrincipalAdministracion ventanaPrincipal;
	private JPanel panelNorteTitulo;
	private JPanel panelCentroOpciones;
	private JLabel lblTituloAdministracion;
	private JPanel panelBotonInformes;
	private JPanel panelBotonTransferencia;
	private JButton btnInformes;
	private JButton btnTransferecias;

	public PanelOpciones() {
		super();
		setLayout(new BorderLayout(0, 0));
		add(getPanelNorteTitulo(), BorderLayout.NORTH);
		add(getPanelCentroOpciones(), BorderLayout.CENTER);
	}

	// =========================================
	// Controlar el estado del panel
	// =========================================

	public void setVentanaPrincipal(VentanaPrincipalAdministracion ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	private JPanel getPanelNorteTitulo() {
		if (panelNorteTitulo == null) {
			panelNorteTitulo = new JPanel();
			panelNorteTitulo.add(getLblTituloAdministracion());
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

	private JLabel getLblTituloAdministracion() {
		if (lblTituloAdministracion == null) {
			lblTituloAdministracion = new JLabel("Administraci\u00F3n");
			lblTituloAdministracion.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblTituloAdministracion;
	}

	private JPanel getPanelBotonInformes() {
		if (panelBotonInformes == null) {
			panelBotonInformes = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBotonInformes.getLayout();
			flowLayout.setVgap(125);
			flowLayout.setHgap(10);
			panelBotonInformes.add(getBtnInformes());
		}
		return panelBotonInformes;
	}

	private JPanel getPanelBotonTransferencia() {
		if (panelBotonTransferencia == null) {
			panelBotonTransferencia = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBotonTransferencia.getLayout();
			flowLayout.setVgap(125);
			panelBotonTransferencia.add(getBtnTransferecias());
		}
		return panelBotonTransferencia;
	}

	/*
	 * Botones de cambio de panel en la aplicación
	 */

	private JButton getBtnInformes() {
		if (btnInformes == null) {
			btnInformes = new JButton("Informes");
			btnInformes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					ventanaPrincipal.mostrarPanelSeleccionInforme();

				}
			});
			btnInformes.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return btnInformes;
	}

	private JButton getBtnTransferecias() {
		if (btnTransferecias == null) {
			btnTransferecias = new JButton("Transferencias");
			btnTransferecias.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ventanaPrincipal.mostrarPanelTransferencia();

				}
			});
			btnTransferecias.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return btnTransferecias;
	}
}