package test.ui.panelesCentro;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;

import model.Pedido;
import test.business.acciones.NuevaCategoria;
import test.business.acciones.PagarPedidos;
import test.ui.MenuPrincipal;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PanelConfirmacionPedido extends JPanel {
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton btnConfirmar;
	private JPanel panelPedidos;
	private JScrollPane scrollPanePedidos;
	private JList<Pedido> list;
	private MenuPrincipal menuPrincipal;
	
	private DefaultListModel<Pedido> modeloPedidos;
	/**
	 * Create the panel.
	 */
	public PanelConfirmacionPedido() {
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
			gbl_panelCentro.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelCentro.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelCentro.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelCentro.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelCentro.setLayout(gbl_panelCentro);
			GridBagConstraints gbc_panelPedidos = new GridBagConstraints();
			gbc_panelPedidos.insets = new Insets(0, 0, 5, 5);
			gbc_panelPedidos.fill = GridBagConstraints.BOTH;
			gbc_panelPedidos.gridx = 3;
			gbc_panelPedidos.gridy = 2;
			panelCentro.add(getPanelPedidos(), gbc_panelPedidos);
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
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar Pagado");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String resultado = menuPrincipal.getProcesadorTest().ejecutarPrueba(new PagarPedidos(getList().getSelectedValue()));

					menuPrincipal.getTextAreaResultados().setText(resultado);

					((CardLayout) menuPrincipal.getPanelCentro().getLayout()).show(menuPrincipal.getPanelCentro(),
							"panelResultados");
					getList().clearSelection();

					modeloPedidos.removeAllElements();
				}
			});
		}
		return btnConfirmar;
	}
	private JPanel getPanelPedidos() {
		if (panelPedidos == null) {
			panelPedidos = new JPanel();
			panelPedidos.setLayout(new BorderLayout(0, 0));
			panelPedidos.add(getScrollPanePedidos());
			
		}
		return panelPedidos;
	}
	private JScrollPane getScrollPanePedidos() {
		if (scrollPanePedidos == null) {
			scrollPanePedidos = new JScrollPane();
			scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPanePedidos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPanePedidos.setViewportView(getList());
		}
		return scrollPanePedidos;
	}
	
	private JList<Pedido> getList() {
		if (list == null) {
			modeloPedidos = new DefaultListModel<Pedido>();
			list = new JList<Pedido>();
			list.setModel(modeloPedidos);
		}
		return list;
	}
	
	
	public void cargarDatos(){
		List<Pedido> pedidos = menuPrincipal.getCargadorComponentes().cargarPedidosNoPagados();
		
		for(Pedido ped : pedidos){
			modeloPedidos.addElement(ped);
		}
	}
}
