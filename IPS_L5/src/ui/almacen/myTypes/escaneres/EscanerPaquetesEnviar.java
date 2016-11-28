package ui.almacen.myTypes.escaneres;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import model.Paquete;
import ui.almacen.envios.PanelEnvioPaquetes;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaPaquetes;

public class EscanerPaquetesEnviar extends JDialog {

	private static final long serialVersionUID = 1327931386614679961L;

	private PanelEnvioPaquetes panelEnvios;

	// ----------------------
	// Panel norte
	// ----------------------

	private JPanel panelNorte;
	private JLabel labelPaquetes;

	// ----------------------
	// Panel centro
	// ----------------------

	private JScrollPane scrollPaneCentro;
	private JTable tablaPaquetes;

	private ModeloTablaPaquetes modeloTablaPaquetes;

	// ----------------------
	// Panel sur
	// ----------------------

	private JPanel panelSur;
	private JTextField textFieldCodigoPaquete;
	private JButton botonSimularLectura;

	// ======================================
	// Constructor e inicizalización
	// ======================================

	public EscanerPaquetesEnviar() {
		setResizable(false);
		setSize(new Dimension(300, 400));

		getContentPane().add(getPanelNorte(), BorderLayout.NORTH);
		getContentPane().add(getScrollPaneCentro(), BorderLayout.CENTER);
		getContentPane().add(getPanelSur(), BorderLayout.SOUTH);
	}

	public void setPanelEnvios(PanelEnvioPaquetes panelEnvios) {
		this.panelEnvios = panelEnvios;
	}

	public void setPaquetes(List<Paquete> paquetes) {
		modeloTablaPaquetes.setPaquetes(paquetes);
	}

	public void addPaquete(Paquete paquete) {
		modeloTablaPaquetes.addPaquete(paquete);
	}

	public void removePaquete(Paquete paquete) {
		modeloTablaPaquetes.removePaquete(paquete);
	}

	public void ordenarPaquetes() {
		modeloTablaPaquetes.ordenar();
	}

	// ===========================
	// Panel norte
	// ===========================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelNorte.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setHgap(0);

			panelNorte.add(getLabelPaquetes());
		}

		return panelNorte;
	}

	private JLabel getLabelPaquetes() {
		if (labelPaquetes == null) {
			labelPaquetes = new JLabel("Paquetes sin envio asignado");
			labelPaquetes.setFont(new Font("Tahoma", Font.BOLD, 12));
		}

		return labelPaquetes;
	}

	// ===========================
	// Panel centro
	// ===========================

	private JScrollPane getScrollPaneCentro() {
		if (scrollPaneCentro == null) {
			scrollPaneCentro = new JScrollPane(getTablaPaquetes());
			scrollPaneCentro.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPaneCentro.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPaneCentro;
	}

	private JTable getTablaPaquetes() {
		if (tablaPaquetes == null) {
			modeloTablaPaquetes = new ModeloTablaPaquetes();

			tablaPaquetes = new JTable(modeloTablaPaquetes);
			tablaPaquetes.setFont(new Font("Tahoma", Font.PLAIN, 13));
			tablaPaquetes.setRowSelectionAllowed(false);
		}

		return tablaPaquetes;
	}

	// ===========================
	// Panel sur
	// ===========================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();

			flowLayout.setAlignment(FlowLayout.RIGHT);
			flowLayout.setVgap(9);
			flowLayout.setHgap(9);

			panelSur.add(getTextFieldCodigoPaquete());
			panelSur.add(getBotonSimularLectura());
		}

		return panelSur;
	}

	private JTextField getTextFieldCodigoPaquete() {
		if (textFieldCodigoPaquete == null) {
			textFieldCodigoPaquete = new JTextField();

			textFieldCodigoPaquete.setHorizontalAlignment(SwingConstants.RIGHT);
			textFieldCodigoPaquete.setFont(new Font("Tahoma", Font.PLAIN, 12));
			textFieldCodigoPaquete.setColumns(10);
		}

		return textFieldCodigoPaquete;
	}

	private JButton getBotonSimularLectura() {
		if (botonSimularLectura == null) {
			botonSimularLectura = new JButton("Simular lectura");

			botonSimularLectura.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					Long id;

					try {
						String cod = getTextFieldCodigoPaquete().getText();
						id = Long.parseLong(cod);

						Paquete paquete = modeloTablaPaquetes.findPaqueteById(id);

						if (paquete != null) {
							panelEnvios.agregarPaqueteEnvio(paquete);
							modeloTablaPaquetes.removePaquete(paquete);
						}

						else {
							panelEnvios.getVentanaPrincipal().getMessage().warning("Aviso",
									"No hay ningún paquete en la lista con ese código");
						}
					}

					catch (NumberFormatException nfe) {
						panelEnvios.getVentanaPrincipal().getMessage().warning("Aviso",
								"El código escrito no es válido");
					}
				}
			});

			botonSimularLectura.setFont(new Font("Tahoma", Font.PLAIN, 12));
		}

		return botonSimularLectura;
	}

}