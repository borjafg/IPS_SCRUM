package ui.almacen.myTypes.ventanaMensaje;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mensaje extends JDialog {

	private static final long serialVersionUID = -5213034293823487651L;

	// ----------------------------------------
	// Panel central
	// ----------------------------------------

	private JScrollPane scrollPaneMensaje;
	private JTextArea textAreaMensaje;

	// ----------------------------------------
	// Panel sur
	// ----------------------------------------

	private JPanel panelBoton;
	private JButton botonOk;

	/**
	 * Create the dialog.
	 * 
	 */
	public Mensaje() {
		super();

		setBounds(100, 100, 285, 290);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setAlwaysOnTop(true);

		getContentPane().add(getScrolPaneMensaje(), BorderLayout.CENTER);
		getContentPane().add(getPanelBoton(), BorderLayout.SOUTH);
	}

	public void setMensaje(String mensaje) {
		getTextAreaMensaje().setText(mensaje);
	}

	// =====================================
	// Panel central
	// =====================================

	private JScrollPane getScrolPaneMensaje() {
		if (scrollPaneMensaje == null) {
			scrollPaneMensaje = new JScrollPane(getTextAreaMensaje());

			scrollPaneMensaje.setBorder(new EmptyBorder(4, 4, 0, 4));

			scrollPaneMensaje.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneMensaje.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}

		return scrollPaneMensaje;
	}

	private JTextArea getTextAreaMensaje() {
		if (textAreaMensaje == null) {
			textAreaMensaje = new JTextArea();

			textAreaMensaje.setEditable(false);
			textAreaMensaje.setMargin(new Insets(6, 6, 6, 6));

			textAreaMensaje.setLineWrap(true);
			textAreaMensaje.setWrapStyleWord(true);

			textAreaMensaje.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}

		return textAreaMensaje;
	}

	// =====================================
	// Panel sur
	// =====================================

	private JPanel getPanelBoton() {
		if (panelBoton == null) {
			panelBoton = new JPanel();

			panelBoton.setBorder(new EmptyBorder(4, 4, 4, 4));
			panelBoton.setLayout(new FlowLayout(FlowLayout.RIGHT));

			panelBoton.add(getBotonOk());
		}

		return panelBoton;
	}

	private JButton getBotonOk() {
		if (botonOk == null) {
			botonOk = new JButton("Ok");

			botonOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});

			botonOk.setHorizontalTextPosition(SwingConstants.CENTER);
			botonOk.setFont(new Font("Tahoma", Font.BOLD, 13));
		}

		return botonOk;
	}

}