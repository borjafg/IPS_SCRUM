package ui.almacen.myTypes.tablas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Permite que el contenido de las celdas de un JTable puedan ocupar varias
 * lineas.
 *
 */
public class TablaFilasConVariasLineasRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 3536134598669759054L;

	private JTextArea renderer;

	public TablaFilasConVariasLineasRenderer() {
		renderer = new JTextArea();

		renderer.setLineWrap(true);
		renderer.setWrapStyleWord(true);

		renderer.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));

		renderer.setFont(new Font("Tahoma", Font.PLAIN, 13));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (isSelected) {
			renderer.setForeground(table.getSelectionForeground());
			renderer.setBackground(table.getSelectionBackground());
		}

		else {
			renderer.setForeground(table.getForeground());
			renderer.setBackground(table.getBackground());
		}

		renderer.setFont(table.getFont());
		renderer.setText((value == null) ? "" : value.toString());

		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(renderer);

		table.setRowHeight(row, contentPane.getPreferredSize().height);

		return contentPane;
	}
}