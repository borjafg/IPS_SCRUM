package ui.almacen.recogida;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelRegistroIncidencias extends JPanel {

	private static final long serialVersionUID = 9039636379609218939L;

	private JPanel panelNorte;
	private JLabel labelRegistrarIncidencia;

	private JScrollPane scrollPaneCentro;
	private JTextArea textAreaIncidencia;

	private JPanel panelSur;
	private JButton botonAtras;
	private JButton botonRegistrar;

	/**
	 * Crea un panel que permite registrar una incidencia asociada a un orden de
	 * trabajo
	 * 
	 */
	public PanelRegistroIncidencias() {
		super();

		setPreferredSize(new Dimension(374, 530));
		setLayout(new BorderLayout(0, 0));

		add(getPanelNorte(), BorderLayout.NORTH);
		add(getScrollPaneCentro(), BorderLayout.CENTER);
		add(getPanelSur(), BorderLayout.SOUTH);
	}

	private JLabel getLabelRegistrarIncidencia() {
		if (labelRegistrarIncidencia == null) {
			labelRegistrarIncidencia = new JLabel("Registrar incidencia");
			labelRegistrarIncidencia.setFont(new Font("Tahoma", Font.BOLD, 17));
			labelRegistrarIncidencia.setHorizontalAlignment(SwingConstants.CENTER);
		}

		return labelRegistrarIncidencia;
	}

	// =====================================
	// Panel norte
	// =====================================

	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			panelNorte = new JPanel();
			panelNorte.setBorder(new EmptyBorder(4, 0, 8, 0));
			panelNorte.add(getLabelRegistrarIncidencia());
		}

		return panelNorte;
	}

	// =====================================
	// Panel centro
	// =====================================

	private JScrollPane getScrollPaneCentro() {
		if (scrollPaneCentro == null) {
			scrollPaneCentro = new JScrollPane(getTextAreaIncidencia(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneCentro.setBorder(new EmptyBorder(0, 5, 0, 5));
		}

		return scrollPaneCentro;
	}

	private JTextArea getTextAreaIncidencia() {
		if (textAreaIncidencia == null) {
			textAreaIncidencia = new JTextArea();
			
			textAreaIncidencia.setWrapStyleWord(true);
			textAreaIncidencia.setLineWrap(true);
			textAreaIncidencia.setFont(new Font("Monospaced", Font.PLAIN, 16));
		}

		return textAreaIncidencia;
	}

	// =====================================
	// Panel sur
	// =====================================

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			
			panelSur.setBorder(new EmptyBorder(10, 0, 10, 0));
			
			GridBagLayout gbl_panelSur = new GridBagLayout();
			
			gbl_panelSur.columnWidths = new int[]{20, 100, 30, 100, 20, 0};
			gbl_panelSur.rowHeights = new int[]{30, 0};
			gbl_panelSur.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panelSur.rowWeights = new double[]{1.0, Double.MIN_VALUE};
			
			panelSur.setLayout(gbl_panelSur);
			
			GridBagConstraints gbc_botonAtras = new GridBagConstraints();
			
			gbc_botonAtras.fill = GridBagConstraints.BOTH;
			gbc_botonAtras.insets = new Insets(0, 0, 0, 5);
			gbc_botonAtras.gridx = 1;
			gbc_botonAtras.gridy = 0;
			
			panelSur.add(getBotonAtras(), gbc_botonAtras);
			
			GridBagConstraints gbc_botonRegistrar = new GridBagConstraints();
			
			gbc_botonRegistrar.insets = new Insets(0, 0, 0, 5);
			gbc_botonRegistrar.fill = GridBagConstraints.BOTH;
			gbc_botonRegistrar.gridx = 3;
			gbc_botonRegistrar.gridy = 0;
			
			panelSur.add(getBotonRegistrar(), gbc_botonRegistrar);
		}

		return panelSur;
	}
	
	private JButton getBotonAtras() {
		if (botonAtras == null) {
			botonAtras = new JButton("Atr\u00E1s");
			botonAtras.setBorder(null);
			
			botonAtras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			
			botonAtras.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		
		return botonAtras;
	}
	
	private JButton getBotonRegistrar() {
		if (botonRegistrar == null) {
			botonRegistrar = new JButton("Registrar");
			botonRegistrar.setBorder(null);
			botonRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		
		return botonRegistrar;
	}
}