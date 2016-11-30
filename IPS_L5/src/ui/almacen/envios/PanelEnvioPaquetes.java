package ui.almacen.envios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import business.exception.BusinessException;
import infrastructure.ServiceFactory;
import model.Paquete;
import ui.almacen.VentanaPrincipalAlmacenero;
import ui.almacen.myTypes.escaneres.EscanerPaquetesEnviar;
import ui.almacen.myTypes.tablas.modelosTabla.ModeloTablaPaquetes;

public class PanelEnvioPaquetes extends JPanel {

	private static final long serialVersionUID = 1828028842807322192L;

	private VentanaPrincipalAlmacenero ventanaPrincipal;

	private EscanerPaquetesEnviar escaner;

	// ===================================
	// Panel norte
	// ===================================

	private JPanel panelNorte;
	private JLabel labelPaquetesSinEnvio;

	// ===================================
	// Panel centro
	// ===================================

	private JPanel panelCentro;

	// ---------------------------
	// ---- Panel de paquetes ----
	// ---------------------------

	private JScrollPane scrollPanePaquetes;
	private JTable tablaPaquetes;
	private ModeloTablaPaquetes modeloTablaPaquetes;

	// --------------------------------
	// --- Panel Modificacion envio ---
	// --------------------------------

	private JPanel PanelModificacionEnvio;
	private JButton botonSacarEnvio;
	private JLabel labelPaquetesEnvio;

	// ------------------------------------
	// ---- Panel de paquetes en envio ----
	// ------------------------------------

	private JScrollPane scrollPanePaquetesEnvio;
	private JTable tablaPaquetesEnEnvio;
	private ModeloTablaPaquetes modeloTablaPaquetesEnEnvio;

	// ===================================
	// Panel sur
	// ===================================

	private JPanel panelSur;
	private JButton botonAtras;
	private JButton botonCerrarEnvio;

	public PanelEnvioPaquetes() {
		super();

		setLayout(new BorderLayout(0, 0));

		add(getPanelNorte(), BorderLayout.NORTH);
		add(getPanelCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);

		setPreferredSize(new Dimension(300, 400));
	}

	public void inicializarDatos() throws BusinessException {
		List<Paquete> paquetes = ServiceFactory.getEnvioService().obtenerListaPaquetesSinEnvio();

		escaner = new EscanerPaquetesEnviar();

		escaner.setPanelEnvios(this);

		escaner.setPaquetes(new ArrayList<Paquete>(paquetes));
		escaner.ordenarPaquetes();

		modeloTablaPaquetes.setPaquetes(new ArrayList<Paquete>(paquetes));
		modeloTablaPaquetes.ordenar();

		escaner.setVisible(true);
	}

	// ======================================
	// Panel norte
	// ======================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();

			panelNorte.setBorder(new EmptyBorder(4, 0, 5, 0));
			panelNorte.add(getLabelPaquetesSinEnvio());
		}

		return panelNorte;
	}

	private JLabel getLabelPaquetesSinEnvio() {
		if (labelPaquetesSinEnvio == null) {
			labelPaquetesSinEnvio = new JLabel("Paquetes sin env\u00EDo");

			labelPaquetesSinEnvio.setFont(new Font("Tahoma", Font.BOLD, 14));
			labelPaquetesSinEnvio.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelPaquetesSinEnvio;
	}

	// ======================================
	// Panel centro
	// ======================================

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBorder(new EmptyBorder(0, 2, 0, 2));

			GridBagLayout gbl_panelCentro = new GridBagLayout();

			gbl_panelCentro.columnWidths = new int[] { 296, 0 };
			gbl_panelCentro.rowHeights = new int[] { 130, 70, 130, 0 };
			gbl_panelCentro.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gbl_panelCentro.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };

			panelCentro.setLayout(gbl_panelCentro);

			GridBagConstraints gbc_scrollPanePaquetes = new GridBagConstraints();

			gbc_scrollPanePaquetes.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPanePaquetes.fill = GridBagConstraints.BOTH;
			gbc_scrollPanePaquetes.gridx = 0;
			gbc_scrollPanePaquetes.gridy = 0;

			panelCentro.add(getScrollPanePaquetes(), gbc_scrollPanePaquetes);

			GridBagConstraints gbc_PanelModificacionEnvio = new GridBagConstraints();

			gbc_PanelModificacionEnvio.insets = new Insets(0, 0, 5, 0);
			gbc_PanelModificacionEnvio.fill = GridBagConstraints.BOTH;
			gbc_PanelModificacionEnvio.gridx = 0;
			gbc_PanelModificacionEnvio.gridy = 1;

			panelCentro.add(getPanelModificacionEnvio(), gbc_PanelModificacionEnvio);

			GridBagConstraints gbc_scrollPanePaquetesEnvio = new GridBagConstraints();

			gbc_scrollPanePaquetesEnvio.fill = GridBagConstraints.BOTH;
			gbc_scrollPanePaquetesEnvio.gridx = 0;
			gbc_scrollPanePaquetesEnvio.gridy = 2;

			panelCentro.add(getScrollPanePaquetesEnvio(), gbc_scrollPanePaquetesEnvio);
		}

		return panelCentro;
	}

	// ---------------------------
	// ---- Panel de paquetes ----
	// ---------------------------

	private JScrollPane getScrollPanePaquetes() {
		if (scrollPanePaquetes == null) {
			scrollPanePaquetes = new JScrollPane(getTablaPaquetes());

			scrollPanePaquetes.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPanePaquetes.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		}

		return scrollPanePaquetes;
	}

	private JTable getTablaPaquetes() {
		if (tablaPaquetes == null) {
			modeloTablaPaquetes = new ModeloTablaPaquetes();

			tablaPaquetes = new JTable(modeloTablaPaquetes);

			tablaPaquetes.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 13));
			tablaPaquetes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}

		return tablaPaquetes;
	}

	// --------------------------------
	// --- Panel Modificacion envio ---
	// --------------------------------

	private JPanel getPanelModificacionEnvio() {
		if (PanelModificacionEnvio == null) {
			PanelModificacionEnvio = new JPanel();
			PanelModificacionEnvio.setBorder(new EmptyBorder(5, 0, 0, 0));

			GridBagLayout gbl_PanelModificacionEnvio = new GridBagLayout();

			gbl_PanelModificacionEnvio.columnWidths = new int[] { 125, 25, 125, 0 };
			gbl_PanelModificacionEnvio.rowHeights = new int[] { 35, 35 };
			gbl_PanelModificacionEnvio.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_PanelModificacionEnvio.rowWeights = new double[] { 1.0, 4.9E-324 };

			PanelModificacionEnvio.setLayout(gbl_PanelModificacionEnvio);

			GridBagConstraints gbc_botonSacarEnvio = new GridBagConstraints();
			gbc_botonSacarEnvio.fill = GridBagConstraints.BOTH;

			gbc_botonSacarEnvio.insets = new Insets(0, 0, 5, 5);
			gbc_botonSacarEnvio.gridx = 1;
			gbc_botonSacarEnvio.gridy = 0;

			PanelModificacionEnvio.add(getBotonSacarEnvio(), gbc_botonSacarEnvio);

			GridBagConstraints gbc_labelPaquetesEnvio = new GridBagConstraints();
			gbc_labelPaquetesEnvio.gridwidth = 3;
			gbc_labelPaquetesEnvio.fill = GridBagConstraints.BOTH;
			gbc_labelPaquetesEnvio.gridx = 0;
			gbc_labelPaquetesEnvio.gridy = 1;

			PanelModificacionEnvio.add(getLabelPaquetesEnvio(), gbc_labelPaquetesEnvio);
		}

		return PanelModificacionEnvio;
	}

	private JButton getBotonSacarEnvio() {
		if (botonSacarEnvio == null) {

			ImageIcon icon = new ImageIcon(
					PanelEnvioPaquetes.class.getResource("/ui/almacen/envios/img/flechaHaciaArriba.png"));

			botonSacarEnvio = new JButton(icon);

			botonSacarEnvio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int[] filasSeleccionadas = tablaPaquetesEnEnvio.getSelectedRows();

					if (filasSeleccionadas.length > 0) {
						quitarPaquetesEnvio(filasSeleccionadas);
					}

					else {
						ventanaPrincipal.getMessage().warning("Aviso", "No se ha seleccionado ningún paquete");
					}
				}
			});
		}

		return botonSacarEnvio;
	}

	private JLabel getLabelPaquetesEnvio() {
		if (labelPaquetesEnvio == null) {
			labelPaquetesEnvio = new JLabel("Paquetes en el env\u00EDo");

			labelPaquetesEnvio.setHorizontalAlignment(SwingConstants.CENTER);
			labelPaquetesEnvio.setFont(new Font("Tahoma", Font.BOLD, 14));
		}

		return labelPaquetesEnvio;
	}

	private void quitarPaquetesEnvio(int[] filasSeleccionadas) {
		List<Paquete> paquetesQuitar = new ArrayList<Paquete>();

		for (int fila : filasSeleccionadas) {
			modeloTablaPaquetes.addPaquete(modeloTablaPaquetesEnEnvio.getPaquete(fila));
			paquetesQuitar.add(modeloTablaPaquetesEnEnvio.getPaquete(fila));
		}

		for (Paquete paq : paquetesQuitar) {
			modeloTablaPaquetesEnEnvio.removePaquete(paq);
			escaner.addPaquete(paq);
		}

		modeloTablaPaquetes.ordenar();
		escaner.ordenarPaquetes();
	}

	// ------------------------------------
	// ---- Panel de paquetes en envio ----
	// ------------------------------------

	private JScrollPane getScrollPanePaquetesEnvio() {
		if (scrollPanePaquetesEnvio == null) {
			scrollPanePaquetesEnvio = new JScrollPane(getTablaPaquetesEnEnvio());

			scrollPanePaquetesEnvio.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPanePaquetesEnvio.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		}

		return scrollPanePaquetesEnvio;
	}

	private JTable getTablaPaquetesEnEnvio() {
		if (tablaPaquetesEnEnvio == null) {
			modeloTablaPaquetesEnEnvio = new ModeloTablaPaquetes();

			tablaPaquetesEnEnvio = new JTable(modeloTablaPaquetesEnEnvio);

			tablaPaquetesEnEnvio.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 13));
			tablaPaquetesEnEnvio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}

		return tablaPaquetesEnEnvio;
	}

	// ======================================
	// Panel sur
	// ======================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();

			panelSur.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelSur.setLayout(new FlowLayout(FlowLayout.RIGHT, 8, 1));

			panelSur.add(getBotonAtras());
			panelSur.add(getBotonCerrarEnvio());
		}

		return panelSur;
	}

	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atr\u00E1s");
			botonAtras.setHorizontalTextPosition(SwingConstants.CENTER);

			botonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reiniciarPanel();
					ventanaPrincipal.volverPanelOpciones();
				}
			});

			botonAtras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}

		return botonAtras;
	}

	private JButton getBotonCerrarEnvio() {
		if (botonCerrarEnvio == null) {
			botonCerrarEnvio = new JButton("Cerrar env\u00EDo");
			botonCerrarEnvio.setHorizontalTextPosition(SwingConstants.CENTER);

			botonCerrarEnvio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int[] filasSeleccionadas = tablaPaquetesEnEnvio.getSelectedRows();

					if (filasSeleccionadas.length > 0) {
						try {
							evaluarPaquetes(filasSeleccionadas);
						}

						catch (BusinessException excep) {
							ventanaPrincipal.gestionarErrorConexion(excep);
						}
					}

					else {
						ventanaPrincipal.getMessage().warning("Aviso", "No se puede cerrar un envío vacío");
					}
				}
			});

			botonCerrarEnvio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}

		return botonCerrarEnvio;
	}

	private void evaluarPaquetes(int[] filasSeleccionadas) throws BusinessException {
		List<Paquete> paquetesEnvio = new ArrayList<Paquete>();

		for (int fila : filasSeleccionadas) {
			paquetesEnvio.add(modeloTablaPaquetesEnEnvio.getPaquete(fila));
		}

		ServiceFactory.getEnvioService().cerrarEnvio(paquetesEnvio, ventanaPrincipal.getTransportista());

		ventanaPrincipal.getMessage().info("Envio cerrado", "Se ha cerrado correctamente el envio");

		escaner.dispose();
		escaner = null;
	}

	// ==============================================
	// Controlar el estado del panel
	// ==============================================

	public void setVentanaPrincipal(VentanaPrincipalAlmacenero ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	public VentanaPrincipalAlmacenero getVentanaPrincipal() {
		return ventanaPrincipal;
	}

	public void reiniciarPanel() {
		modeloTablaPaquetes.removeAll();
		modeloTablaPaquetesEnEnvio.removeAll();

		escaner.dispose();
		escaner = null;
	}

	public void agregarPaqueteEnvio(Paquete paquete) {
		modeloTablaPaquetesEnEnvio.addPaquete(paquete);
		modeloTablaPaquetes.removePaquete(paquete);
	}

}