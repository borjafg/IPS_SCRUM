package test.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import test.business.LogicaTest;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	// =====================
	// Panel norte
	// =====================

	private JPanel panelNorte;
	private JButton botonAñadirProducto;
	private JButton botonBorrarProducto;
	private JButton botonProbarAcceso;

	// =====================
	// Paneles centro
	// =====================

	private JPanel panelCentro;
	private PanelCreacionProductos panelCreacionProductos;

	// Panel resultados

	private JPanel panelResultados;
	private JTextArea textAreaResultados;
	private JLabel labelResultado;
	private JPanel panelResultados_norte;
	private JButton botonCambiarStock;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 540);

		contentPane = new JPanel();
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanelNorte(), BorderLayout.NORTH);
		contentPane.add(getPanelCentro(), BorderLayout.CENTER);
	}

	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setBorder(new LineBorder(new Color(192, 192, 192)));
			panelCentro.setLayout(new CardLayout(5, 0));
			
			añadirPanelesCentro();
		}

		return panelCentro;
	}
	
	private void añadirPanelesCentro() {
		panelCentro.add(getPanelResultados(), "panelResultados");
		panelCentro.add(getPanelCreacionProductos(), "panelCreacionProductos");
	}

	// ====================
	// Panel norte
	// ====================

	private JButton getBotonAñadirProducto() {
		if (botonAñadirProducto == null) {
			botonAñadirProducto = new JButton("Crear producto");
			botonAñadirProducto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}

		return botonAñadirProducto;
	}

	private JButton getBotonBorrarProducto() {
		if (botonBorrarProducto == null) {
			botonBorrarProducto = new JButton("Borrar producto");
			botonBorrarProducto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}

		return botonBorrarProducto;
	}

	private JButton getBotonProbarAcceso() {
		if (botonProbarAcceso == null) {
			botonProbarAcceso = new JButton("Probar acceso a base datos");
			botonProbarAcceso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					getBotonAñadirProducto().setEnabled(false);
					getBotonBorrarProducto().setEnabled(false);
					getBotonCambiarStock().setEnabled(false);
					
					String resultado = new LogicaTest().ejecutarPrueba(LogicaTest.TEST_ACCESO);
					textAreaResultados.setText(resultado);
					
					getBotonAñadirProducto().setEnabled(true);
					getBotonBorrarProducto().setEnabled(true);
					getBotonCambiarStock().setEnabled(true);
				}
			});
			botonProbarAcceso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}

		return botonProbarAcceso;
	}

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBorder(null);
			FlowLayout fl_panelNorte = (FlowLayout) panelNorte.getLayout();
			fl_panelNorte.setVgap(8);
			panelNorte.add(getBotonAñadirProducto());
			panelNorte.add(getBotonBorrarProducto());
			panelNorte.add(getBotonCambiarStock());
			panelNorte.add(getBotonProbarAcceso());
		}

		return panelNorte;
	}

	// ====================
	// Panel de resultados
	// ====================

	private JPanel getPanelResultados() {
		if (panelResultados == null) {
			panelResultados = new JPanel();
			panelResultados.setLayout(new BorderLayout(0, 0));
			panelResultados.add(getPanelResultados_norte(), BorderLayout.NORTH);
			panelResultados.add(getTextAreaResultados());
		}

		return panelResultados;
	}

	private JTextArea getTextAreaResultados() {
		if (textAreaResultados == null) {
			textAreaResultados = new JTextArea();
			textAreaResultados.setLineWrap(true);
			textAreaResultados.setName("");
			textAreaResultados.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			textAreaResultados.setText("Todav\u00EDa no se ha realizado ninguna operaci\u00F3n");
			textAreaResultados.setFont(new Font("Calibri", Font.BOLD, 23));
		}

		return textAreaResultados;
	}

	// =======================
	// Paneles del centro
	// =======================

	private PanelCreacionProductos getPanelCreacionProductos() {
		if (panelCreacionProductos == null) {
			panelCreacionProductos = new PanelCreacionProductos();
			panelCreacionProductos.setVentanaPrincipal(this);
		}

		return panelCreacionProductos;
	}
	private JLabel getLabelResultado() {
		if (labelResultado == null) {
			labelResultado = new JLabel("Resultado de la ejecuci\u00F3n:");
			labelResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
			labelResultado.setHorizontalTextPosition(SwingConstants.CENTER);
			labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
			labelResultado.setFont(new Font("Tahoma", Font.BOLD, 22));
		}
		return labelResultado;
	}
	private JPanel getPanelResultados_norte() {
		if (panelResultados_norte == null) {
			panelResultados_norte = new JPanel();
			panelResultados_norte.setBorder(null);
			FlowLayout flowLayout = (FlowLayout) panelResultados_norte.getLayout();
			flowLayout.setVgap(10);
			panelResultados_norte.add(getLabelResultado());
		}
		return panelResultados_norte;
	}
	private JButton getBotonCambiarStock() {
		if (botonCambiarStock == null) {
			botonCambiarStock = new JButton("Cambiar stock");
			botonCambiarStock.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return botonCambiarStock;
	}
}